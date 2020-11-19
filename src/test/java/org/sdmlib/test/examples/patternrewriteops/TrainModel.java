package org.sdmlib.test.examples.patternrewriteops;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;

public class TrainModel
{
	
   /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainModel.java' type='text/x-java'>TrainModel</a></p>
    * <p>Example model for testing destroy with model patterns: </p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"Person"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"SignalFlag"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Station"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Train"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Person",
    *             "cardinality":"many",
    *             "property":"passengers"
    *          },
    *          "target":{
    *             "id":"Train",
    *             "cardinality":"one",
    *             "property":"train"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Person",
    *             "cardinality":"many",
    *             "property":"people"
    *          },
    *          "target":{
    *             "id":"Station",
    *             "cardinality":"one",
    *             "property":"station"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"SignalFlag",
    *             "cardinality":"one",
    *             "property":"flag"
    *          },
    *          "target":{
    *             "id":"Station",
    *             "cardinality":"many",
    *             "property":"station"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Station",
    *             "cardinality":"one",
    *             "property":"next"
    *          },
    *          "target":{
    *             "id":"Station",
    *             "cardinality":"one",
    *             "property":"prev"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Station",
    *             "cardinality":"one",
    *             "property":"station"
    *          },
    *          "target":{
    *             "id":"Train",
    *             "cardinality":"many",
    *             "property":"trains"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Station",
    *             "cardinality":"one",
    *             "property":"station"
    *          },
    *          "target":{
    *             "id":"Person",
    *             "cardinality":"many",
    *             "property":"people"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Station",
    *             "cardinality":"many",
    *             "property":"station"
    *          },
    *          "target":{
    *             "id":"SignalFlag",
    *             "cardinality":"one",
    *             "property":"flag"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Train",
    *             "cardinality":"one",
    *             "property":"train"
    *          },
    *          "target":{
    *             "id":"Person",
    *             "cardinality":"many",
    *             "property":"passengers"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Train",
    *             "cardinality":"many",
    *             "property":"trains"
    *          },
    *          "target":{
    *             "id":"Station",
    *             "cardinality":"one",
    *             "property":"station"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasTrainModelClassDiagram1", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * @see <a href='../../../../../../../../doc/TrainModel.html'>TrainModel.html</a>
*/
   @Test 
	public void TrainModel()
	{
      Storyboard storyboard = new Storyboard();
      
      storyboard.add("Example model for testing destroy with model patterns: ");
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.patternrewriteops.model");
            
      Clazz trainClass = model.createClazz("Train");
      trainClass.setClassModel(model);
      
      Clazz stationClass = model.createClazz("Station").withBidirectional(trainClass, "trains", Association.MANY, "station", Association.ONE);

      stationClass.withBidirectional(stationClass, "next", Association.ONE, "prev", Association.ONE);
      
      Clazz personClass = model.createClazz("Person").withBidirectional(stationClass, "station", Association.ONE, "people", Association.MANY);
      
      trainClass.withBidirectional(personClass, "passengers", Association.MANY, "train", Association.ONE);
      
      model.createClazz("SignalFlag").withBidirectional(stationClass, "station", Association.MANY, "flag", Association.ONE);
      
      storyboard.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      storyboard.dumpHTML();
	}
}
