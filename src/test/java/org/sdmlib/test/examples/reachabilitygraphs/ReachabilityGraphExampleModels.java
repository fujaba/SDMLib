package org.sdmlib.test.examples.reachabilitygraphs;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.AssociationTypes;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class ReachabilityGraphExampleModels
{
     /**
    * 
    * @see <a href='../../../../../../../../doc/SimpleReachabilityGraphModel.html'>SimpleReachabilityGraphModel.html</a>
*/
   @Test
   public void SimpleReachabilityGraphModel()
   {
      Storyboard storyboard = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.reachabilitygraphs.simplestates");
      
      Clazz state = model.createClazz("SimpleState");
      
      Clazz node = model.createClazz("Node")
            .withAttribute("num", DataType.INT);
            
      state.withBidirectional(node, "nodes", Cardinality.MANY, "graph", Cardinality.ONE);
      
      node.withBidirectional(node, "next", Cardinality.MANY, "prev", Cardinality.MANY);
      
      storyboard.addClassDiagram(model);

      model.generate("src/test/java");
      
      storyboard.dumpHTML();
   }
   
     /**
    * 
    * @see <a href='../../../../../../../../doc/FerryMansProblemModel.html'>FerryMansProblemModel.html</a>
*/
   @Test
   public void FerryMansProblemModel()
   {
      Storyboard storyboard = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem");
      
      Clazz river = model.createClazz("River");
      
      Clazz boat = model.createClazz("Boat");
            
      river.withBidirectional(boat, "boat", Cardinality.ONE, "river", Cardinality.ONE);
      
      Clazz bank = model.createClazz("Bank")
            .withAttribute("name", DataType.STRING)
            .withAttribute("age", DataType.INT);
            
      boat.withBidirectional(bank, "bank", Cardinality.ONE, "boat", Cardinality.ONE);
      
      river.withBidirectional(bank, "banks", Cardinality.MANY, "river", Cardinality.ONE);
      
      Clazz cargo = model.createClazz("Cargo")
            .withAttribute("name", DataType.STRING); 
            
      bank.withBidirectional(cargo, "cargos", Cardinality.MANY, "bank", Cardinality.ONE);
      
      // cargo.withAttribute("name", Cardinality.STRING);
      
      cargo.withBidirectional(boat, "boat", Cardinality.ONE, "cargo", Cardinality.ONE);
      
      storyboard.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      storyboard.dumpHTML();
   }

     /**
    * 
    * @see <a href='../../../../../../../../doc/LazyFerryMansProblemModel.html'>LazyFerryMansProblemModel.html</a>
 */
   @Test
   public void LazyFerryMansProblemModel()
   {
      Storyboard storyboard = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem");
      
      Clazz river = model.createClazz("LRiver");
      
      Clazz boat = model.createClazz("LBoat");
            
      river.createBidirectional(boat, "boat", Cardinality.ONE, "river", Cardinality.MANY)
      .with(AssociationTypes.AGGREGATION);
      
      Clazz bank = model.createClazz("LBank")
            .withAttribute("name", DataType.STRING)
            .withAttribute("age", DataType.INT);
            
      boat.withBidirectional(bank, "bank", Cardinality.ONE, "boat", Cardinality.MANY);
      
      river.createBidirectional(bank, "banks", Cardinality.MANY, "river", Cardinality.MANY)
      .with(AssociationTypes.AGGREGATION);
      
      Clazz cargo = model.createClazz("LCargo")
            .withAttribute("name", DataType.STRING); 
            
      bank.createBidirectional(cargo, "cargos", Cardinality.MANY, "bank", Cardinality.MANY)
      .with(AssociationTypes.AGGREGATION);
      
      boat.createBidirectional(cargo, "cargo", Cardinality.ONE, "boat", Cardinality.MANY)
      .with(AssociationTypes.AGGREGATION);
      
      storyboard.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      storyboard.dumpHTML();
   }
}
