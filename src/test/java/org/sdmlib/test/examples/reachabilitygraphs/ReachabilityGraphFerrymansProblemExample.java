package org.sdmlib.test.examples.reachabilitygraphs;

import org.junit.Test;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.util.ReachabilityGraphCreator;
import org.sdmlib.storyboards.StoryPage;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Bank;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Boat;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.River;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BankPO;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BankSet;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BoatPO;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CargoPO;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.RiverCreator;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.RiverPO;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.RiverSet;

import de.uniks.networkparser.IdMap;

public class ReachabilityGraphFerrymansProblemExample
{
     /**
    * 
    * @see <a href='../../../../../../../../doc/FerrymansProblemExample.html'>FerrymansProblemExample.html</a>
*/
//   @Test
   public void FerrymansProblemExample()
   {
      StoryPage storyboard = new StoryPage();
      
      
      //================================================
      storyboard.add("initial situation:");
      
      River river = new River();
  
      Boat boat = river.createBoat();

      Bank left = river.createBanks().withName("left").withBoat(boat);
      
      left.createCargos().withName("cabbage");
      left.createCargos().withName("goat");
      left.createCargos().withName("wolf");
      
      river.createBanks().withName("right");
      
      storyboard.addObjectDiagram(river);
            
      storyboard.add("compute certificates");
      
      ReachableState rs1 = new ReachableState().withGraphRoot(river);
      
      RiverCreator cc = new RiverCreator();

      IdMap map = cc.createIdMap("s");
      map.with(ReachabilityGraphCreator.createIdMap("rg"));
      
      String s1cert = rs1.computeCertificate(map);
      
      storyboard.add(s1cert);
      
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph()
      .withMasterMap(map).withStates(rs1).withTodo(rs1).withStateMap(s1cert, rs1);
      
      //================================================
      //      map.with(new ModelPatternCreator());
      //      FlipBook flipBook = new FlipBook().withMap(map); 
      //      String id = map.getId(reachabilityGraph);
      //      
      //================================================
      // load boat rule
      
      RiverPO riverPO = new RiverPO();
      
      Pattern loadPattern = riverPO.getPattern().withName("load");
      map.getId(loadPattern);
      
      BoatPO boatPO = riverPO.hasBoat();
      
      boatPO.startNAC().hasCargo().endNAC();
      
      BankPO bankPO = boatPO.hasBank();
      
      CargoPO cargoPO = bankPO.hasCargos();
      
      CargoPO goatPO = bankPO.startNAC().hasCargos().hasName("goat").hasMatchOtherThen(cargoPO);
      bankPO.hasCargos().hasMatchOtherThen(cargoPO).hasMatchOtherThen(goatPO);
      bankPO.endNAC();
      
      loadPattern.clone(reachabilityGraph);
      
      bankPO.startDestroy().hasCargos(cargoPO).endDestroy();
      
      boatPO.createCargo(cargoPO);
      
      PatternObject loadPatternPO = new PatternObject<>();
      
      loadPatternPO.withPatternObjectName("loadPatternPO");
      
      loadPatternPO.withPattern(loadPattern);
      
      storyboard.addPattern(loadPatternPO, false);
      
      reachabilityGraph.addToRules(loadPattern);
      
      
      //================================================
      // move boat rule
      
      riverPO = new RiverPO();
      
      Pattern movePattern = riverPO.getPattern().withName("move");

      boatPO = riverPO.hasBoat();
      
      BankPO oldBankPO = boatPO.hasBank();
      
      BankPO newBankPO = riverPO.hasBanks().hasMatchOtherThen(oldBankPO);
      
      // do not leave the goat allone with some other cargo
      goatPO = oldBankPO.startNAC().hasCargos().hasName("goat");
      oldBankPO.hasCargos().hasMatchOtherThen(goatPO).endNAC();
      
      movePattern.clone(reachabilityGraph);
      
      boatPO.startDestroy().hasBank(oldBankPO).endDestroy();
      
      boatPO.startCreate().hasBank(newBankPO).endCreate();
      
      cargoPO = boatPO.startSubPattern().hasCargo();
      
      boatPO.startDestroy().hasCargo(cargoPO).endDestroy();
      
      cargoPO.startCreate().hasBank(newBankPO).endCreate().endSubPattern();
      
      PatternObject movePatternPO = new PatternObject<>();
      
      movePatternPO.withPatternObjectName("movePatternPO");
      
      movePatternPO.withPattern(movePattern);
      
      storyboard.addPattern(movePatternPO, false);
      
      reachabilityGraph.addToRules(movePattern);
      
      
      //================================================
//FIXME ME ALBERT      long size = reachabilityGraph.explore();
      long size = 0;
      storyboard.add("Number of States: " + size);
      
      storyboard.add("Small reachbility graph with hyperlinks to states: ");
      storyboard.add(reachabilityGraph.dumpDiagram("ferrymansproblemRG"));   
      
      storyboard.add("large reachbility graph with embedded states: ");
      storyboard.addObjectDiagram(map, reachabilityGraph, true);
      
      RiverSet rivers = new RiverSet().with( reachabilityGraph.getStates().getGraphRoot());
      BankSet banks = rivers.getBanks().hasName("right");
      
      for (Bank bank : banks)
      {
         if (bank.getCargos().size() == 3)
         {
            storyboard.add("Found a solution.");
            break;
         }
      }
      
      storyboard.dumpHTML();
   }
}
