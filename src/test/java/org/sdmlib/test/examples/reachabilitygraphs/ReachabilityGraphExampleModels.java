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
            
      state.createBidirectional(node, "nodes", Cardinality.MANY, "graph", Cardinality.MANY)
      .with(AssociationTypes.AGGREGATION);
      
      node.withBidirectional(node, "next", Cardinality.MANY, "prev", Cardinality.MANY);
      
      storyboard.addClassDiagram(model);

      // model.removeAllGeneratedCode();
      
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
    * @see <a href='../../../../../../../../doc/UniDirectFerryMansProblemModel.html'>UniDirectFerryMansProblemModel.html</a>
 */
   @Test
   public void UniDirectFerryMansProblemModel()
   {
      Storyboard storyboard = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem");
      
      Clazz river = model.createClazz("URiver");
      
      Clazz boat = model.createClazz("UBoat");
            
      river.createUniDirectional(boat, "boat", Cardinality.ONE).with(AssociationTypes.AGGREGATION);
      
      Clazz bank = model.createClazz("UBank")
            .withAttribute("name", DataType.STRING);
            
      boat.createUniDirectional(bank, "bank", Cardinality.ONE);
      
      river.createUniDirectional(bank, "banks", Cardinality.MANY).with(AssociationTypes.AGGREGATION);
      
      Clazz cargo = model.createClazz("UCargo")
            .withAttribute("name", DataType.STRING); 
            
      bank.createUniDirectional(cargo, "cargos", Cardinality.MANY);
      
      boat.createUniDirectional(cargo, "cargo", Cardinality.ONE);
      // cargo.withBidirectional(boat, "boat", Cardinality.ONE, "cargo", Cardinality.ONE);
      
      storyboard.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      storyboard.dumpHTML();
   }
}
