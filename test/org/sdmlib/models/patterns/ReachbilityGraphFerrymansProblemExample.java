package org.sdmlib.models.patterns;

import org.junit.Test;
import org.sdmlib.models.debug.FlipBook;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.patterns.example.ferrmansproblem.Bank;
import org.sdmlib.models.patterns.example.ferrmansproblem.Boat;
import org.sdmlib.models.patterns.example.ferrmansproblem.River;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BankPO;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BoatPO;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.CargoPO;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.CreatorCreator;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.ModelPattern;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.ModelPatternCreator;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.RiverPO;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.storyboards.Storyboard;

public class ReachbilityGraphFerrymansProblemExample
{
   @Test
   public void FerrymansProblemExample()
   {
      Storyboard storyboard = new Storyboard("test");
      
      
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
      
      CreatorCreator cc = new CreatorCreator();

      JsonIdMap map = cc.createIdMap("s");
      
      String s1cert = rs1.computeCertificate(map);
      
      storyboard.add(s1cert);
      
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph()
      .withMasterMap(map).withStates(rs1).withTodo(rs1).withStateMap(s1cert, rs1);
      
      //================================================
      //      map.withCreator(new ModelPatternCreator());
      //      FlipBook flipBook = new FlipBook().withMap(map); 
      //      String id = map.getId(reachabilityGraph);
      //      
      //================================================
      // load boat rule
      ModelPattern loadPattern = (ModelPattern) new ModelPattern().withName("load");
      map.getId(loadPattern);
      
      RiverPO riverPO = loadPattern.hasElementRiverPO();
      
      BoatPO boatPO = riverPO.hasBoat();
      
      boatPO.startNAC().hasCargo().endNAC();
      
      BankPO bankPO = boatPO.hasBank();
      
      CargoPO cargoPO = bankPO.hasCargos();
      
      CargoPO goatPO = bankPO.startNAC().hasCargos().hasName("goat").hasMatchOtherThen(cargoPO);
      bankPO.hasCargos().hasMatchOtherThen(cargoPO).hasMatchOtherThen(goatPO);
      bankPO.endNAC();
      
      loadPattern.clone(reachabilityGraph);
      
      bankPO.startDestroy().hasCargos(cargoPO).endDestroy();
      
      boatPO.startCreate().hasCargo(cargoPO).endCreate();
      
      storyboard.add(loadPattern.dumpDiagram("loadBoat"));
      
      reachabilityGraph.addToRules(loadPattern);
      
      
      //================================================
      // move boat rule
      ModelPattern movePattern = (ModelPattern) new ModelPattern().withName("move");
      
      riverPO = movePattern.hasElementRiverPO();
      
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
      
      storyboard.add(movePattern.dumpDiagram("moveBoat"));
      
      reachabilityGraph.addToRules(movePattern);
      
      
      //================================================
      long size = reachabilityGraph.explore();
      
      storyboard.add("Number of States: " + size);
      
      storyboard.add("Small reachbility graph with hyperlinks to states: ");
      storyboard.add(reachabilityGraph.dumpDiagram("ferrymansproblemRG"));   
      
      storyboard.add("large reachbility graph with embedded states: ");
      storyboard.addObjectDiagram(map, reachabilityGraph, true);
      
      storyboard.dumpHTML();
   }
}
