package org.sdmlib.test.examples.reachabilitygraphs;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.AssociationTypes;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class ReachabilityGraphExampleModels
{
     /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphExampleModels.java' type='text/x-java'>SimpleReachabilityGraphModel</a></p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"Node",
    *          "attributes":[
    *             "num : int"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"SimpleState"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Node",
    *             "cardinality":"many",
    *             "property":"next"
    *          },
    *          "target":{
    *             "id":"Node",
    *             "cardinality":"many",
    *             "property":"prev"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Node",
    *             "cardinality":"many",
    *             "property":"nodes"
    *          },
    *          "target":{
    *             "id":"SimpleState",
    *             "cardinality":"many",
    *             "property":"graph"
    *          }
    *       },
    *       {
    *          "typ":"aggregation",
    *          "source":{
    *             "id":"SimpleState",
    *             "cardinality":"many",
    *             "property":"graph"
    *          },
    *          "target":{
    *             "id":"Node",
    *             "cardinality":"many",
    *             "property":"nodes"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasSimpleReachabilityGraphModelClassDiagram0", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
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
            
      state.createBidirectional(node, "nodes", Association.MANY, "graph", Association.MANY)
      .with(AssociationTypes.AGGREGATION);
      
      node.withBidirectional(node, "next", Association.MANY, "prev", Association.MANY);
      
      storyboard.addClassDiagram(model);

      // model.removeAllGeneratedCode();
      
      model.generate("src/test/java");
      
      storyboard.dumpHTML();
   }
   
     /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphExampleModels.java' type='text/x-java'>FerryMansProblemModel</a></p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"Bank",
    *          "attributes":[
    *             "age : int",
    *             "name : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Boat"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Cargo",
    *          "attributes":[
    *             "name : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"River"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Bank",
    *             "cardinality":"one",
    *             "property":"bank"
    *          },
    *          "target":{
    *             "id":"Boat",
    *             "cardinality":"one",
    *             "property":"boat"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Bank",
    *             "cardinality":"one",
    *             "property":"bank"
    *          },
    *          "target":{
    *             "id":"Cargo",
    *             "cardinality":"many",
    *             "property":"cargos"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Bank",
    *             "cardinality":"many",
    *             "property":"banks"
    *          },
    *          "target":{
    *             "id":"River",
    *             "cardinality":"one",
    *             "property":"river"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Boat",
    *             "cardinality":"one",
    *             "property":"boat"
    *          },
    *          "target":{
    *             "id":"River",
    *             "cardinality":"one",
    *             "property":"river"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Boat",
    *             "cardinality":"one",
    *             "property":"boat"
    *          },
    *          "target":{
    *             "id":"Bank",
    *             "cardinality":"one",
    *             "property":"bank"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Boat",
    *             "cardinality":"one",
    *             "property":"boat"
    *          },
    *          "target":{
    *             "id":"Cargo",
    *             "cardinality":"one",
    *             "property":"cargo"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Cargo",
    *             "cardinality":"one",
    *             "property":"cargo"
    *          },
    *          "target":{
    *             "id":"Boat",
    *             "cardinality":"one",
    *             "property":"boat"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Cargo",
    *             "cardinality":"many",
    *             "property":"cargos"
    *          },
    *          "target":{
    *             "id":"Bank",
    *             "cardinality":"one",
    *             "property":"bank"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"River",
    *             "cardinality":"one",
    *             "property":"river"
    *          },
    *          "target":{
    *             "id":"Boat",
    *             "cardinality":"one",
    *             "property":"boat"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"River",
    *             "cardinality":"one",
    *             "property":"river"
    *          },
    *          "target":{
    *             "id":"Bank",
    *             "cardinality":"many",
    *             "property":"banks"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasFerryMansProblemModelClassDiagram0", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * @see <a href='../../../../../../../../doc/FerryMansProblemModel.html'>FerryMansProblemModel.html</a>
*/
   @Test
   public void FerryMansProblemModel()
   {
      Storyboard storyboard = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem");
      
      Clazz river = model.createClazz("River");
      
      Clazz boat = model.createClazz("Boat");
            
      river.withBidirectional(boat, "boat", Association.ONE, "river", Association.ONE);
      
      Clazz bank = model.createClazz("Bank")
            .withAttribute("name", DataType.STRING)
            .withAttribute("age", DataType.INT);
            
      boat.withBidirectional(bank, "bank", Association.ONE, "boat", Association.ONE);
      
      river.withBidirectional(bank, "banks", Association.MANY, "river", Association.ONE);
      
      Clazz cargo = model.createClazz("Cargo")
            .withAttribute("name", DataType.STRING); 
            
      bank.withBidirectional(cargo, "cargos", Association.MANY, "bank", Association.ONE);
      
      // cargo.withAttribute("name", Cardinality.STRING);
      
      cargo.withBidirectional(boat, "boat", Association.ONE, "cargo", Association.ONE);
      
      storyboard.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      storyboard.dumpHTML();
   }

     /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphExampleModels.java' type='text/x-java'>UniDirectFerryMansProblemModel</a></p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"UBank",
    *          "attributes":[
    *             "name : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"UBoat"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"UCargo",
    *          "attributes":[
    *             "name : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"URiver"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"UBank",
    *             "cardinality":"one",
    *             "property":"bank"
    *          },
    *          "target":{
    *             "id":"UBoat",
    *             "cardinality":"one",
    *             "property":"uboat"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"UBank",
    *             "cardinality":"many",
    *             "property":"banks"
    *          },
    *          "target":{
    *             "id":"URiver",
    *             "cardinality":"one",
    *             "property":"uriver"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"UCargo",
    *             "cardinality":"many",
    *             "property":"cargos"
    *          },
    *          "target":{
    *             "id":"UBank",
    *             "cardinality":"one",
    *             "property":"ubank"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"UBoat",
    *             "cardinality":"one",
    *             "property":"boat"
    *          },
    *          "target":{
    *             "id":"URiver",
    *             "cardinality":"one",
    *             "property":"uriver"
    *          }
    *       },
    *       {
    *          "typ":"aggregation",
    *          "source":{
    *             "id":"UBoat",
    *             "cardinality":"one",
    *             "property":"uboat"
    *          },
    *          "target":{
    *             "id":"UBank",
    *             "cardinality":"one",
    *             "property":"bank"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"UCargo",
    *             "cardinality":"one",
    *             "property":"cargo"
    *          },
    *          "target":{
    *             "id":"UBoat",
    *             "cardinality":"one",
    *             "property":"uboat"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"UCargo",
    *             "cardinality":"one",
    *             "property":"cargo"
    *          },
    *          "target":{
    *             "id":"UBoat",
    *             "cardinality":"one",
    *             "property":"uboat"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"UCargo",
    *             "cardinality":"many",
    *             "property":"cargos"
    *          },
    *          "target":{
    *             "id":"UBank",
    *             "cardinality":"one",
    *             "property":"ubank"
    *          }
    *       },
    *       {
    *          "typ":"aggregation",
    *          "source":{
    *             "id":"URiver",
    *             "cardinality":"one",
    *             "property":"uriver"
    *          },
    *          "target":{
    *             "id":"UBoat",
    *             "cardinality":"one",
    *             "property":"boat"
    *          }
    *       },
    *       {
    *          "typ":"aggregation",
    *          "source":{
    *             "id":"URiver",
    *             "cardinality":"one",
    *             "property":"uriver"
    *          },
    *          "target":{
    *             "id":"UBank",
    *             "cardinality":"many",
    *             "property":"banks"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasUniDirectFerryMansProblemModelClassDiagram0", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * @see <a href='../../../../../../../../doc/UniDirectFerryMansProblemModel.html'>UniDirectFerryMansProblemModel.html</a>
 */
   @Test
   public void UniDirectFerryMansProblemModel()
   {
      Storyboard storyboard = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem");
      
      Clazz river = model.createClazz("URiver");
      
      Clazz boat = model.createClazz("UBoat");
            
      river.createUniDirectional(boat, "boat", Association.ONE).with(AssociationTypes.AGGREGATION);
      
      Clazz bank = model.createClazz("UBank")
            .withAttribute("name", DataType.STRING);
            
      boat.createUniDirectional(bank, "bank", Association.ONE).with(AssociationTypes.AGGREGATION);
      
      river.createUniDirectional(bank, "banks", Association.MANY).with(AssociationTypes.AGGREGATION);
      
      Clazz cargo = model.createClazz("UCargo")
            .withAttribute("name", DataType.STRING); 
            
      bank.createUniDirectional(cargo, "cargos", Association.MANY);
      
      boat.createUniDirectional(cargo, "cargo", Association.ONE);
      
      storyboard.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      storyboard.dumpHTML();
   }
}
