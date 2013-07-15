package org.sdmlib.models.patterns;

import org.junit.Test;
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
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.RiverPO;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.serialization.json.JsonIdMap;

public class ReachbilityGraphFerrymansProblemExample
{
   @Test
   public void FerrymansProblemExample()
   {
      Scenario scenario = new Scenario("test");
      
      
      //================================================
      scenario.add("initial situation:");
      
      River river = new River();
  
      Boat boat = river.createBoat();

      Bank left = river.createBanks().withName("left").withBoat(boat);
      
      left.createCargos().withName("cabbage");
      left.createCargos().withName("goat");
      left.createCargos().withName("wolf");
      
      river.createBanks().withName("right");
      
      scenario.addObjectDiag(river);
            
      scenario.add("compute certificates");
      
      ReachableState rs1 = new ReachableState().withGraphRoot(river);
      
      CreatorCreator cc = new CreatorCreator();

      JsonIdMap map = cc.createIdMap("s");
      
      String s1cert = rs1.computeCertificate(map);
      
      scenario.add(s1cert);
      
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph()
      .withMasterMap(map).withStates(rs1).withTodo(rs1).withStateMap(s1cert, rs1);
      
      
      //================================================
      // load boat rule
      ModelPattern loadPattern = (ModelPattern) new ModelPattern().withName("load");
      
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
      
      scenario.add(loadPattern.dumpDiagram("loadBoat"));
      
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
      
      scenario.add(movePattern.dumpDiagram("moveBoat"));
      
      reachabilityGraph.addToRules(movePattern);
      
      
      //================================================
      long size = reachabilityGraph.explore();
      
      scenario.add("Number of States: " + size);
      
      scenario.add("Small reachbility graph with hyperlinks to states: ");
      scenario.add(reachabilityGraph.dumpDiagram("ferrymansproblemRG"));   
      
      scenario.add("large reachbility graph with embedded states: ");
      scenario.addObjectDiag(map, reachabilityGraph, true);
      
      scenario.dumpHTML();
   }
}
