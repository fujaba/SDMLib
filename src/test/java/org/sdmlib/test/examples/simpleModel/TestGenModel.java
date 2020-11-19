package org.sdmlib.test.examples.simpleModel;

import org.junit.Test;
import org.sdmlib.SimpleSDMLib;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Modifier;

public class TestGenModel
{
   @Test
   public void testSimpleModel(){
      ClassModel model = new ClassModel("org.sdmlib.test.examples.simpleModel.model");
      
      Clazz arrayListClazz = model.createClazz("java.util.ArrayList").withExternal(true);
      Clazz createClazz = model.createClazz("MacList");
      createClazz.createAttribute("serialVersionUID", DataType.LONG).withValue("1L").with(Modifier.STATIC, Modifier.FINAL, Modifier.PRIVATE);
      
      createClazz.withAttribute("Name", DataType.STRING);
      createClazz.withSuperClazz(arrayListClazz);
      
      // Enable Special Thinks
      model.generate("src/test/java");
   }
   
     /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/examples/simpleModel/TestGenModel.java' type='text/x-java'>UniDirectionalAssoc</a></p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"BigBrother"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Person",
    *          "attributes":[
    *             "name : String"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Person",
    *             "cardinality":"one",
    *             "property":"noOne"
    *          },
    *          "target":{
    *             "id":"BigBrother",
    *             "cardinality":"one",
    *             "property":"bigbrother"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Person",
    *             "cardinality":"many",
    *             "property":"suspects"
    *          },
    *          "target":{
    *             "id":"BigBrother",
    *             "cardinality":"one",
    *             "property":"bigbrother"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Person",
    *             "cardinality":"one",
    *             "property":"noOne"
    *          },
    *          "target":{
    *             "id":"BigBrother",
    *             "cardinality":"one",
    *             "property":"bigbrother"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Person",
    *             "cardinality":"many",
    *             "property":"suspects"
    *          },
    *          "target":{
    *             "id":"BigBrother",
    *             "cardinality":"one",
    *             "property":"bigbrother"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasUniDirectionalAssocClassDiagram0", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * @see <a href='../../../../../../../../doc/UniDirectionalAssoc.html'>UniDirectionalAssoc.html</a>
*/
   @Test
   public void testUniDirectionalAssoc()
   {
      Storyboard story = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.simpleModel.model");
      
      Clazz bigBrother = model.createClazz("BigBrother");
      
      Clazz person = model.createClazz("Person")
            .withAttribute("name", DataType.STRING);
      
      bigBrother.withUniDirectional(person, "noOne", Association.ONE);
      bigBrother.withUniDirectional(person, "suspects", Association.MANY);
      
      story.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      story.dumpHTML();
      
   }

     /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/examples/simpleModel/TestGenModel.java' type='text/x-java'>UniDirectionalAssocToObject</a></p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"BigBrother"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Object"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Object",
    *             "cardinality":"many",
    *             "property":"kids"
    *          },
    *          "target":{
    *             "id":"BigBrother",
    *             "cardinality":"one",
    *             "property":"bigbrother"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Object",
    *             "cardinality":"many",
    *             "property":"kids"
    *          },
    *          "target":{
    *             "id":"BigBrother",
    *             "cardinality":"one",
    *             "property":"bigbrother"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasUniDirectionalAssocToObjectClassDiagram0", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * @see <a href='../../../../../../../../doc/UniDirectionalAssoc.html'>UniDirectionalAssoc.html</a>
* @see <a href='../../../../../../../../doc/UniDirectionalAssocToObject.html'>UniDirectionalAssocToObject.html</a>
*/
   @Test
   public void testUniDirectionalAssocToObject()
   {
	   if(SimpleSDMLib.ENABLE() == false) {
		   return;
	   }
	   
      Storyboard story = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.simpleModel.model");
      
      Clazz bigBrother = model.createClazz("BigBrother");
      
      Clazz person = model.createClazz(Object.class.getName()).withExternal(true);
      
      bigBrother.withUniDirectional(person, "kids", Association.MANY);
      
      story.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      story.dumpHTML();
      
   }
}
