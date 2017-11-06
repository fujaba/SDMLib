package org.sdmlib.test.examples.reachabilitygraphs;

import java.util.Collection;

import org.junit.Test;
import org.sdmlib.models.pattern.LazyCloneOp;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.ReachabilityGraph.Searchmode;
import org.sdmlib.models.pattern.util.ReachabilityGraphCreator;
import org.sdmlib.storyboards.Kanban;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Bank;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Boat;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Cargo;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.River;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BankPO;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BankSet;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BoatPO;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CargoPO;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.RiverCreator;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.RiverPO;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.RiverSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBoat;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LCargo;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LRiver;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBankPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBankSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBoatPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LCargoPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LRiverCreator;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LRiverPO;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LRiverSet;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class ReachabilityGraphFerrymansProblemExample
{
   /**
    * 
    * @see <a href='../../../../../../../../doc/FerrymansProblemExample.html'>FerrymansProblemExample.html</a>
    */
   @Test
   public void FerrymansProblemExample()
   {
      Storyboard storyboard = new Storyboard();

      // ================================================
      storyboard.add("initial situation:");

      River river = new River();

      Boat boat = river.createBoat();

      Bank left = river.createBanks().withName("left").withBoat(boat);

      left.createCargos().withName("cabbage");
      left.createCargos().withName("goat");
      left.createCargos().withName("wolf");

      river.createBanks().withName("right");

      storyboard.addObjectDiagram(river);


      ReachableState rs1 = new ReachableState().withGraphRoot(river);

      RiverCreator cc = new RiverCreator();

      IdMap map = cc.createIdMap("s");
      map.with(ReachabilityGraphCreator.createIdMap("rg"));

      LazyCloneOp lazyCloneOp = new LazyCloneOp().setMap(map);
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph()
            .withMasterMap(map).withStates(rs1).withTodo(rs1);
      reachabilityGraph.setLazyCloneOp(lazyCloneOp);

      String s1cert = rs1.computeCertificate(map);

      storyboard.addPreformatted(s1cert);


      // ================================================
      // map.with(new ModelPatternCreator());
      // FlipBook flipBook = new FlipBook().withMap(map);
      // String id = map.getId(reachabilityGraph);
      //
      // ================================================
      // load boat rule

      RiverPO riverPO = new RiverPO();

      Pattern loadPattern = (Pattern) riverPO.getPattern().withName("load").withDebugMode(Kanban.DEBUG_ON);
      map.getId(loadPattern);

      BoatPO boatPO = riverPO.hasBoat();

      boatPO.startNAC().hasCargo().endNAC();

      BankPO bankPO = boatPO.hasBank();

      CargoPO cargoPO = bankPO.hasCargos();

      CargoPO goatPO = bankPO.startNAC().hasCargos().hasName("goat").hasMatchOtherThen(cargoPO);
      bankPO.hasCargos().hasMatchOtherThen(cargoPO).hasMatchOtherThen(goatPO);
      bankPO.endNAC();

      loadPattern.createCloneOp();

      bankPO.startDestroy().hasCargos(cargoPO).endDestroy();

      boatPO.createCargo(cargoPO);

      storyboard.addPattern(loadPattern, false);

      reachabilityGraph.addToRules(loadPattern);

      // ================================================
      // move boat rule

      riverPO = new RiverPO();

      Pattern movePattern = (Pattern) riverPO.getPattern().withName("move").withDebugMode(Kanban.DEBUG_ON);

      boatPO = riverPO.hasBoat();

      BankPO oldBankPO = boatPO.hasBank();

      BankPO newBankPO = riverPO.hasBanks().hasMatchOtherThen(oldBankPO);

      // do not leave the goat allone with some other cargo
      goatPO = oldBankPO.startNAC().hasCargos().hasName("goat");
      oldBankPO.hasCargos().hasMatchOtherThen(goatPO).endNAC();

      movePattern.createCloneOp();

      boatPO.startDestroy().hasBank(oldBankPO).endDestroy();

      boatPO.startCreate().hasBank(newBankPO).endCreate();

      cargoPO = boatPO.startSubPattern().hasCargo();

      cargoPO.createBoatLink(boatPO, Pattern.DESTROY);

      cargoPO.startCreate().hasBank(newBankPO).endCreate().endSubPattern();

      storyboard.addPattern(movePattern, false);

      reachabilityGraph.addToRules(movePattern);

      // ================================================
      long size = reachabilityGraph.explore();

      for (ReachableState s : reachabilityGraph.getStates())
      {
         storyboard.add("Reachable State " + s.getNumber());
         River r = (River) s.getGraphRoot();
         storyboard.addObjectDiagram(r, r.getBoat(), r.getBanks(), r.getBanks().getCargos());
      }


      storyboard.assertEquals("Number of Reachable States expected: ", 27L, size);

      storyboard.add("Small reachbility graph with hyperlinks to states: ");
      storyboard.add(reachabilityGraph.dumpDiagram("ferrymansproblemRG"));

      storyboard.add("large reachbility graph with embedded states: ");
      storyboard.addObjectDiagram(reachabilityGraph);

      RiverSet rivers = new RiverSet().with(reachabilityGraph.getStates().getGraphRoot());
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

     /**
    * 
    * @see <a href='../../../../../../../../doc/FerrymansProblemManuel.html'>FerrymansProblemManuel.html</a>
 */
   @Test
   public void FerrymansProblemManuel()
   {
      Storyboard storyboard = new Storyboard();

      // ================================================
      storyboard.add("initial situation:");

      River river = new River();

      Boat boat = river.createBoat();

      Bank left = river.createBanks().withName("left").withBoat(boat);

      left.createCargos().withName("cabbage");
      left.createCargos().withName("goat");
      left.createCargos().withName("wolf");

      river.createBanks().withName("right");

      storyboard.addObjectDiagram(river);


      ReachableState rs1 = new ReachableState().withGraphRoot(river);

      RiverCreator cc = new RiverCreator();

      IdMap map = cc.createIdMap("s");
      map.with(ReachabilityGraphCreator.createIdMap("rg"));
      
      LazyCloneOp lazyCloneOp = new LazyCloneOp().setMap(map);
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph()
            .withMasterMap(map)
            .withStart(rs1)
            .withLazyCloning();

      // ================================================
      // load boat operations

      reachabilityGraph.withTrafo("load wolf", g -> load(g, "wolf"));
      reachabilityGraph.withTrafo("load goat", g -> load(g, "goat"));
      reachabilityGraph.withTrafo("load cabbage", g -> load(g, "cabbage"));
      
      // ================================================
      // move boat rule
      reachabilityGraph.withTrafo("move boat", g -> move(g));
      
      
      // ================================================
      long size = reachabilityGraph.explore();

      for (ReachableState s : reachabilityGraph.getStates())
      {
         storyboard.add("Reachable State " + s.getNumber());
         River r = (River) s.getGraphRoot();
         storyboard.addObjectDiagram(r);
      }


      storyboard.assertEquals("Number of Reachable States expected: ", 27L, size);

      storyboard.add("large reachbility graph with embedded states: ");
      storyboard.addObjectDiagram(reachabilityGraph);

      RiverSet rivers = new RiverSet().with(reachabilityGraph.getStates().getGraphRoot());
      BankSet banks = rivers.getBanks()
            .hasName("right")
            .filter(bank -> bank.getCargos().size() == 3);

      storyboard.assertTrue("found a solution ", ! banks.isEmpty());

      storyboard.dumpHTML();
   }

   
   private void load(Object g, String cargoName)
   {
      River river = (River) g;
      
      Boat boat = river.getBoat();
      
      if (boat.getCargo() == null)
      {
         Bank bank = boat.getBank();
         
         if (bank.getCargos().size() == 3 && ! cargoName.equals("goat"))
         {
            // reject
            return;
         }
         
         for (Cargo cargo : bank.getCargos())
         {
            if (cargo.getName().equals(cargoName))
            {
               bank.withoutCargos(cargo);
               boat.withCargo(cargo);
               return;
            }
         }
      }
   }

   
   private void move(Object g)
   {
      River river = (River) g;
      
      Boat boat = river.getBoat();
      
      Bank oldBank = boat.getBank();
      
      if (oldBank.getCargos().size() >= 2)
      {
         // would i leave alone the goat with some other cargo?
         for (Cargo cargo : oldBank.getCargos())
         {
            if (cargo.getName().equals("goat"))
            {
               // reject
               return;
            }
         }
      }
         
      for (Bank newBank : river.getBanks())
      {
         if (newBank != oldBank)
         {
            boat.setBank(newBank);
            
            Cargo cargo = boat.getCargo();
            if (cargo != null)
            {
               boat.setCargo(null);
               newBank.withCargos(cargo);
            }
            return;
         }
      }
   }
   
   
   /**
    * 
    * @see <a href='../../../../../../../../doc/LayzFerrymansProblemExample.html'>LayzFerrymansProblemExample.html</a>
    */
   @Test
   public void LayzFerrymansProblemExample()
   {
      Storyboard storyboard = new Storyboard();

      // ================================================
      storyboard.add("initial situation:");

      LRiver river = new LRiver();

      LBoat boat = river.createBoat();

      LBank left = river.createBanks().withName("left").withBoat(boat);

      left.createCargos().withName("cabbage");
      LCargo goat = left.createCargos().withName("goat");
      LCargo wolf = left.createCargos().withName("wolf");

      LBank right = river.createBanks().withName("right");

      storyboard.addObjectDiagram(river);

      ObjectSet graphElems = new ObjectSet();

      // try aggregation 
      LazyCloneOp lazyCloneOp = new LazyCloneOp().setMap(LRiverCreator.createIdMap("lazy"));
      lazyCloneOp.aggregate(graphElems, river);
      storyboard.add("kids of river");
      storyboard.addObjectDiagramOnlyWith(graphElems);


      // try lazy clone: clone the boat and move it to the other bank
      Object river2 = lazyCloneOp.clone(river);

      LCargo cloneGoat = (LCargo) lazyCloneOp.clone(goat);

      LBoat cloneBoat = (LBoat) lazyCloneOp.clone(boat);

      cloneGoat.withoutBank(cloneGoat.getBank().first());
      cloneGoat.withBoat(cloneBoat);

      ObjectSet cloneGraph = new ObjectSet();

      lazyCloneOp.aggregate(cloneGraph, river2);

      storyboard.add("kids of river clone");
      storyboard.addObjectDiagramOnlyWith(cloneGraph);

      storyboard.add("both graphs with shared components");
      storyboard.addObjectDiagram(river);

      storyboard.assertEquals("both graphs should have same size", graphElems.size(), cloneGraph.size());

      Object union = graphElems.union(cloneGraph);

      storyboard.assertEquals("union should have four more elements ", graphElems.size() + 4, ((Collection) union).size());


      // try a third graph
      lazyCloneOp.clear();

      LRiver river3 = (LRiver) lazyCloneOp.clone(river2);

      LCargo wolf3 = (LCargo) lazyCloneOp.clone(wolf);

      LBank right3 = (LBank) lazyCloneOp.clone(right);

      LBank left3 = river3.getBanks().createNameCondition("left").first();

      left3.withoutCargos(wolf3);
      right3.withCargos(wolf3);

      storyboard.add("three graphs with shared components");
      storyboard.addObjectDiagram(river);

      ObjectSet graph3 = new ObjectSet();
      lazyCloneOp.aggregate(graph3, river3);

      union = ((SimpleSet) union).union(graph3);
      storyboard.assertEquals("union should now have 8 additional elements ", graphElems.size() + 8, ((Collection) union).size());

      //    storyboard.add("compute certificates");
      //
      //    ReachableState rs1 = new ReachableState().withGraphRoot(river);
      //
      //    LRiverCreator cc = new LRiverCreator();
      //
      //    IdMap map = cc.createIdMap("s");
      //    map.with(ReachabilityGraphCreator.createIdMap("rg"));
      //
      //    String s1cert = rs1.computeCertificate(map);
      //
      //    storyboard.add(s1cert);
      //
      //    ReachabilityGraph reachabilityGraph = new ReachabilityGraph()
      //       .withMasterMap(map).withStates(rs1).withTodo(rs1).withStateMap(s1cert, rs1);
      //
      //    // ================================================
      //    // map.with(new ModelPatternCreator());
      //    // FlipBook flipBook = new FlipBook().withMap(map);
      //    // String id = map.getId(reachabilityGraph);
      //    //
      //    // ================================================
      //    // load boat rule
      //
      //    LRiverPO riverPO = new LRiverPO();
      //
      //    Pattern loadPattern = (Pattern) riverPO.getPattern().withName("load").withDebugMode(Kanban.DEBUG_ON);
      //    map.getId(loadPattern);
      //
      //    LBoatPO boatPO = riverPO.createBoatPO();
      //
      //    boatPO.startNAC().createCargoPO().endNAC();
      //
      //    LBankPO bankPO = boatPO.createBankPO();
      //
      //    LCargoPO cargoPO = bankPO.createCargosPO();
      //
      //    LCargoPO goatPO = bankPO.startNAC()
      //          .createCargosPO().createNameCondition("goat").hasMatchOtherThen(cargoPO);
      //    bankPO.createCargosPO().hasMatchOtherThen(cargoPO).hasMatchOtherThen(goatPO);
      //    bankPO.endNAC();
      //
      //    loadPattern.createCloneOp();
      //
      //    bankPO.createCargosLink(cargoPO, Pattern.DESTROY);
      //
      //    boatPO.createCargoLink(cargoPO, Pattern.CREATE);
      //
      //    storyboard.addPattern(loadPattern, false);
      //
      //    reachabilityGraph.addToRules(loadPattern);
      //
      //    // ================================================
      //    // move boat rule
      //
      //    riverPO = new LRiverPO();
      //
      //    Pattern movePattern = (Pattern) riverPO.getPattern().withName("move").withDebugMode(Kanban.DEBUG_ON);
      //
      //    boatPO = riverPO.createBoatPO();
      //
      //    LBankPO oldBankPO = boatPO.createBankPO();
      //
      //    LBankPO newBankPO = riverPO.createBanksPO().hasMatchOtherThen(oldBankPO);
      //
      //    // do not leave the goat allone with some other cargo
      //    goatPO = oldBankPO.startNAC().createCargosPO().createNameCondition("goat");
      //    oldBankPO.createCargosPO().hasMatchOtherThen(goatPO).endNAC();
      //
      //    movePattern.createCloneOp();
      //
      //    boatPO.createBankLink(oldBankPO, Pattern.DESTROY);
      //
      //    boatPO.createBankLink(newBankPO, Pattern.CREATE);
      //
      //    cargoPO = boatPO.startSubPattern().createCargoPO();
      //
      //    cargoPO.createBoatLink(boatPO, Pattern.DESTROY);
      //
      //    cargoPO.createBankLink(newBankPO, Pattern.CREATE).endSubPattern();
      //
      //    storyboard.addPattern(movePattern, false);
      //
      //    reachabilityGraph.addToRules(movePattern);
      //
      //    // ================================================
      //    long size = reachabilityGraph.explore();
      //    
      //    for (ReachableState s : reachabilityGraph.getStates())
      //    {
      //       storyboard.add("Reachable State " + s.getNumber());
      //       LRiver r = (LRiver) s.getGraphRoot();
      //       storyboard.addObjectDiagram(r, r.getBoat(), r.getBanks(), r.getBanks().getCargos());
      //    }
      //    
      //
      //    storyboard.assertEquals("Number of Reachable States expected: ", 27L, size);
      //
      //    storyboard.add("Small reachbility graph with hyperlinks to states: ");
      //    storyboard.add(reachabilityGraph.dumpDiagram("ferrymansproblemRG"));
      //
      //    storyboard.add("large reachbility graph with embedded states: ");
      //    storyboard.addObjectDiagram(map, reachabilityGraph, true);
      //
      //    LRiverSet rivers = new LRiverSet().with(reachabilityGraph.getStates().getGraphRoot());
      //    LBankSet banks = rivers.getBanks().createNameCondition("right");
      //
      //    for (LBank bank : banks)
      //    {
      //       if (bank.getCargos().size() == 3)
      //       {
      //          storyboard.add("Found a solution.");
      //          break;
      //       }
      //    }

      storyboard.dumpHTML();
   }


   /**
    * 
    * @see <a href='../../../../../../../../doc/LayzFerrymansProblemExample.html'>LayzFerrymansProblemExample.html</a>
    * @see <a href='../../../../../../../../doc/LayzFerrymansProblemRules.html'>LayzFerrymansProblemRules.html</a>
 */
   @Test
   public void LayzFerrymansProblemRules()
   {
      Storyboard storyboard = new Storyboard();

      // ================================================
      storyboard.add("initial situation:");

      LRiver river = new LRiver();

      LBoat boat = river.createBoat();

      LBank left = river.createBanks().withName("left").withBoat(boat);

      left.createCargos().withName("cabbage");
      LCargo goat = left.createCargos().withName("goat");
      LCargo wolf = left.createCargos().withName("wolf");

      LBank right = river.createBanks().withName("right");

      storyboard.addObjectDiagram(river);

      IdMap map = LRiverCreator.createIdMap("rg");
      map.with(ReachabilityGraphCreator.createIdMap("rg"));

      ReachabilityGraph reachabilityGraph = new ReachabilityGraph()
            .withMasterMap(map);
      reachabilityGraph.withLazyCloning();

      ReachableState rs1 = reachabilityGraph.createStates().withGraphRoot(river);
      reachabilityGraph.withTodo(rs1);


      // load boat rule
      LRiverPO riverPO = new LRiverPO();

      Pattern loadPattern = (Pattern) riverPO.getPattern().withName("load").withDebugMode(Kanban.DEBUG_ON);
      map.getId(loadPattern);

      LBoatPO boatPO = riverPO.createBoatPO();

      boatPO.startNAC().createCargoPO().endNAC();

      LBankPO bankPO = boatPO.createBankPO();

      LCargoPO cargoPO = bankPO.createCargosPO();

      LCargoPO goatPO = bankPO.startNAC()
            .createCargosPO().createNameCondition("goat").hasMatchOtherThen(cargoPO);
      bankPO.createCargosPO().hasMatchOtherThen(cargoPO).hasMatchOtherThen(goatPO);
      bankPO.endNAC();

      bankPO.createCargosLink(cargoPO, Pattern.DESTROY);

      boatPO.createCargoLink(cargoPO, Pattern.CREATE);

      reachabilityGraph.addToRules(loadPattern);

      storyboard.addPattern(loadPattern, false);


      // ================================================
      // move boat rule

      riverPO = new LRiverPO();

      Pattern movePattern = (Pattern) riverPO.getPattern().withName("move").withDebugMode(Kanban.DEBUG_ON);

      boatPO = riverPO.createBoatPO();

      LBankPO oldBankPO = boatPO.createBankPO();

      LBankPO newBankPO = riverPO.createBanksPO().hasMatchOtherThen(oldBankPO);

      // do not leave the goat allone with some other cargo
      goatPO = oldBankPO.startNAC().createCargosPO().createNameCondition("goat");
      oldBankPO.createCargosPO().hasMatchOtherThen(goatPO).endNAC();

      boatPO.createBankLink(oldBankPO, Pattern.DESTROY);

      boatPO.createBankLink(newBankPO, Pattern.CREATE);

      cargoPO = boatPO.startSubPattern().createCargoPO();

      boatPO.createCargoLink(cargoPO, Pattern.DESTROY);

      newBankPO.createCargosLink(cargoPO, Pattern.CREATE)
      .endSubPattern();

      storyboard.addPattern(movePattern, false);

      reachabilityGraph.addToRules(movePattern);

      
      // ================================================
      reachabilityGraph.explore();
      
      long size = reachabilityGraph.getStates().size();

      
      
      for (ReachableState s : reachabilityGraph.getStates())
      {
         storyboard.add("Reachable State " + s.getNumber());
         LRiver r = (LRiver) s.getGraphRoot();
         ObjectSet graph = new ObjectSet();
         reachabilityGraph.getLazyCloneOp().aggregate(graph, r);
         // storyboard.addObjectDiagram(graph);
         storyboard.addObjectDiagramOnlyWith(graph);
      }


      storyboard.assertEquals("Number of Reachable States expected: ", 26L, size);

      storyboard.add("Small reachbility graph with hyperlinks to states: ");
      storyboard.add(reachabilityGraph.dumpDiagram("ferrymansproblemRG"));

      storyboard.add("large reachbility graph with embedded states: ");
      storyboard.addObjectDiagram(map, reachabilityGraph, true);

      LRiverSet rivers = new LRiverSet().with(reachabilityGraph.getStates().getGraphRoot());
      LBankSet banks = rivers.getBanks().createNameCondition("right");

      for (LBank bank : banks)
      {
         if (bank.getCargos().size() == 3)
         {
            storyboard.add("Found a solution.");
            break;
         }
      }

      storyboard.dumpHTML();
   }



   /**
    * 
    * @see <a href='../../../../../../../../doc/LayzFerrymansProblemIsomorphim.html'>LayzFerrymansProblemIsomorphim.html</a>
    */

   @Test
   public void LayzFerrymansProblemIsomorphim()
   {
      Storyboard storyboard = new Storyboard();

      // ================================================
      storyboard.add("initial situation:");

      LRiver river = new LRiver();

      LBoat boat = river.createBoat();

      LBank left = river.createBanks().withName("left").withBoat(boat);

      left.createCargos().withName("cabbage");
      LCargo goat = left.createCargos().withName("goat");
      LCargo wolf = left.createCargos().withName("wolf");

      LBank right = river.createBanks().withName("right");

      storyboard.addObjectDiagram(river);

      ObjectSet graphElems = new ObjectSet();

      // try aggregation 
      LazyCloneOp lazyCloneOp = new LazyCloneOp().setMap(LRiverCreator.createIdMap("lazy"));

      LRiver river2 = (LRiver) lazyCloneOp.clone(river);

      storyboard.add("compute certificates");

      ReachableState rs1 = new ReachableState().withGraphRoot(river);

      LRiverCreator cc = new LRiverCreator();

      IdMap map = LRiverCreator.createIdMap("s");
      map.with(ReachabilityGraphCreator.createIdMap("rg"));

      ReachabilityGraph reachabilityGraph = new ReachabilityGraph()
            .withMasterMap(map).withStates(rs1).withTodo(rs1).setLazyCloneOp(lazyCloneOp);

      // add dummy rule computing a lazy clone. 
      // explore should recognize it as equal and terminate immediately

      LRiverPO riverPO = new LRiverPO();

      riverPO.createCondition(r -> lazyClone(riverPO, r));

      reachabilityGraph.withRules(riverPO);

      long size = reachabilityGraph.explore();

      //      // ================================================
      //      // map.with(new ModelPatternCreator());
      //      // FlipBook flipBook = new FlipBook().withMap(map);
      //      // String id = map.getId(reachabilityGraph);
      //      //
      //      // ================================================
      //      // load boat rule
      //
      //      LRiverPO riverPO = new LRiverPO();
      //
      //      Pattern loadPattern = (Pattern) riverPO.getPattern().withName("load").withDebugMode(Kanban.DEBUG_ON);
      //      map.getId(loadPattern);
      //
      //      LBoatPO boatPO = riverPO.createBoatPO();
      //
      //      boatPO.startNAC().createCargoPO().endNAC();
      //
      //      LBankPO bankPO = boatPO.createBankPO();
      //
      //      LCargoPO cargoPO = bankPO.createCargosPO();
      //
      //      LCargoPO goatPO = bankPO.startNAC()
      //            .createCargosPO().createNameCondition("goat").hasMatchOtherThen(cargoPO);
      //      bankPO.createCargosPO().hasMatchOtherThen(cargoPO).hasMatchOtherThen(goatPO);
      //      bankPO.endNAC();
      //
      //      loadPattern.createCloneOp();
      //
      //      bankPO.createCargosLink(cargoPO, Pattern.DESTROY);
      //
      //      boatPO.createCargoLink(cargoPO, Pattern.CREATE);
      //
      //      storyboard.addPattern(loadPattern, false);
      //
      //      reachabilityGraph.addToRules(loadPattern);
      //
      //      // ================================================
      //      // move boat rule
      //
      //      riverPO = new LRiverPO();
      //
      //      Pattern movePattern = (Pattern) riverPO.getPattern().withName("move").withDebugMode(Kanban.DEBUG_ON);
      //
      //      boatPO = riverPO.createBoatPO();
      //
      //      LBankPO oldBankPO = boatPO.createBankPO();
      //
      //      LBankPO newBankPO = riverPO.createBanksPO().hasMatchOtherThen(oldBankPO);
      //
      //      // do not leave the goat allone with some other cargo
      //      goatPO = oldBankPO.startNAC().createCargosPO().createNameCondition("goat");
      //      oldBankPO.createCargosPO().hasMatchOtherThen(goatPO).endNAC();
      //
      //      movePattern.createCloneOp();
      //
      //      boatPO.createBankLink(oldBankPO, Pattern.DESTROY);
      //
      //      boatPO.createBankLink(newBankPO, Pattern.CREATE);
      //
      //      cargoPO = boatPO.startSubPattern().createCargoPO();
      //
      //      cargoPO.createBoatLink(boatPO, Pattern.DESTROY);
      //
      //      cargoPO.createBankLink(newBankPO, Pattern.CREATE).endSubPattern();
      //
      //      storyboard.addPattern(movePattern, false);
      //
      //      reachabilityGraph.addToRules(movePattern);
      //
      //      // ================================================
      //      long size = reachabilityGraph.explore();
      //      
      //      for (ReachableState s : reachabilityGraph.getStates())
      //      {
      //         storyboard.add("Reachable State " + s.getNumber());
      //         LRiver r = (LRiver) s.getGraphRoot();
      //         storyboard.addObjectDiagram(r, r.getBoat(), r.getBanks(), r.getBanks().getCargos());
      //      }
      //      
      //
      //      storyboard.assertEquals("Number of Reachable States expected: ", 27L, size);
      //
      //      storyboard.add("Small reachbility graph with hyperlinks to states: ");
      //      storyboard.add(reachabilityGraph.dumpDiagram("ferrymansproblemRG"));
      //
      //      storyboard.add("large reachbility graph with embedded states: ");
      //      storyboard.addObjectDiagram(map, reachabilityGraph, true);
      //
      //      LRiverSet rivers = new LRiverSet().with(reachabilityGraph.getStates().getGraphRoot());
      //      LBankSet banks = rivers.getBanks().createNameCondition("right");
      //
      //      for (LBank bank : banks)
      //      {
      //         if (bank.getCargos().size() == 3)
      //         {
      //            storyboard.add("Found a solution.");
      //            break;
      //         }
      //      }

      storyboard.dumpHTML();
   }

   private boolean lazyClone(LRiverPO riverPO, LRiver r)
   {
      LazyCloneOp lazyCloneOp = riverPO.getPattern().getLazyCloneOp();

      LRiver r2 = (LRiver) lazyCloneOp.clone(r);

      riverPO.setCurrentMatch(r2);

      return true;
   }
}
