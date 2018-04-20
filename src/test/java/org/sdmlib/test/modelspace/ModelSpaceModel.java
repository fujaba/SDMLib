package org.sdmlib.test.modelspace;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class ModelSpaceModel
{
     /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/modelspace/ModelSpaceModel.java' type='text/x-java'>ModelSpaceModel</a></p>
    * <p>ModelSpace provides incremental persistance and collaboration</p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"CloudModelDirectory"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"CloudModelFile",
    *          "attributes":[
    *             "fileName : String",
    *             "lastModifiedTime : long"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"ModelCloud",
    *          "attributes":[
    *             "acceptPort : int",
    *             "hostName : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"ModelCloudProxy",
    *          "attributes":[
    *             "hostName : String",
    *             "portNo : int"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"ModelSpace"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"ModelSpaceProxy",
    *          "attributes":[
    *             "location : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Task",
    *          "attributes":[
    *             "fileName : String",
    *             "lastModified : long",
    *             "spaceName : String",
    *             "state : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"TaskBoard"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"TaskLane",
    *          "attributes":[
    *             "hostName : String",
    *             "portNo : long"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"CloudModelDirectory",
    *             "cardinality":"one",
    *             "property":"dir"
    *          },
    *          "target":{
    *             "id":"CloudModelFile",
    *             "cardinality":"many",
    *             "property":"files"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"CloudModelFile",
    *             "cardinality":"many",
    *             "property":"files"
    *          },
    *          "target":{
    *             "id":"CloudModelDirectory",
    *             "cardinality":"one",
    *             "property":"dir"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"ModelCloud",
    *             "cardinality":"one",
    *             "property":"cloud"
    *          },
    *          "target":{
    *             "id":"ModelSpaceProxy",
    *             "cardinality":"many",
    *             "property":"modelSpaces"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"ModelCloud",
    *             "cardinality":"one",
    *             "property":"root"
    *          },
    *          "target":{
    *             "id":"ModelCloudProxy",
    *             "cardinality":"many",
    *             "property":"servers"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"ModelCloudProxy",
    *             "cardinality":"many",
    *             "property":"providingClouds"
    *          },
    *          "target":{
    *             "id":"ModelSpaceProxy",
    *             "cardinality":"many",
    *             "property":"providedSpaces"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"ModelCloudProxy",
    *             "cardinality":"many",
    *             "property":"servers"
    *          },
    *          "target":{
    *             "id":"ModelCloud",
    *             "cardinality":"one",
    *             "property":"root"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"ModelSpaceProxy",
    *             "cardinality":"many",
    *             "property":"modelSpaces"
    *          },
    *          "target":{
    *             "id":"ModelCloud",
    *             "cardinality":"one",
    *             "property":"cloud"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"ModelSpaceProxy",
    *             "cardinality":"many",
    *             "property":"providedSpaces"
    *          },
    *          "target":{
    *             "id":"ModelCloudProxy",
    *             "cardinality":"many",
    *             "property":"providingClouds"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Task",
    *             "cardinality":"many",
    *             "property":"myRequests"
    *          },
    *          "target":{
    *             "id":"TaskLane",
    *             "cardinality":"one",
    *             "property":"fileTargetCloud"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Task",
    *             "cardinality":"many",
    *             "property":"tasks"
    *          },
    *          "target":{
    *             "id":"TaskLane",
    *             "cardinality":"one",
    *             "property":"lane"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"TaskBoard",
    *             "cardinality":"one",
    *             "property":"board"
    *          },
    *          "target":{
    *             "id":"TaskLane",
    *             "cardinality":"many",
    *             "property":"lanes"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"TaskLane",
    *             "cardinality":"one",
    *             "property":"fileTargetCloud"
    *          },
    *          "target":{
    *             "id":"Task",
    *             "cardinality":"many",
    *             "property":"myRequests"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"TaskLane",
    *             "cardinality":"one",
    *             "property":"lane"
    *          },
    *          "target":{
    *             "id":"Task",
    *             "cardinality":"many",
    *             "property":"tasks"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"TaskLane",
    *             "cardinality":"many",
    *             "property":"lanes"
    *          },
    *          "target":{
    *             "id":"TaskBoard",
    *             "cardinality":"one",
    *             "property":"board"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasModelSpaceModelClassDiagram1", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * @see <a href='../../../../../../doc/ModelSpaceModel.html'>ModelSpaceModel.html</a>
* @see <a href='../../../../../../../doc/ModelSpaceModel.html'>ModelSpaceModel.html</a>
 */
   @Test
   public void ModelSpaceModel()
   {
      Storyboard story = new Storyboard();
      
      story.add("ModelSpace provides incremental persistance and collaboration");
      
      ClassModel model = new ClassModel("org.sdmlib.modelspace");
      
      model.createClazz("ModelSpace");
      
      Clazz cloud = model.createClazz("ModelCloud")
            .withAttribute("hostName", DataType.STRING)
            .withAttribute("acceptPort", DataType.INT); 
      
      Clazz cloudProxy = model.createClazz("ModelCloudProxy")
            .withAttribute("hostName", DataType.STRING)
            .withAttribute("portNo", DataType.INT);
      
      Clazz spaceProxy = model.createClazz("ModelSpaceProxy")
            .withAttribute("location", DataType.STRING);
      
      cloud.withBidirectional(cloudProxy, "servers", Cardinality.MANY, "root", Cardinality.ONE);
      cloud.withBidirectional(spaceProxy, "modelSpaces", Cardinality.MANY, "cloud", Cardinality.ONE);
      
      cloudProxy.withBidirectional(spaceProxy, "providedSpaces", Cardinality.MANY, "providingClouds", Cardinality.MANY);
      
      Clazz modelDir = model.createClazz("CloudModelDirectory");
      Clazz modelFile = model.createClazz("CloudModelFile")
            .withAttribute("fileName", DataType.STRING)
            .withAttribute("lastModifiedTime", DataType.LONG);
      
      modelDir.withBidirectional(modelFile, "files", Cardinality.MANY, "dir", Cardinality.ONE);
      
      Clazz taskBoard = model.createClazz("TaskBoard");
      
      Clazz taskLane = model.createClazz("TaskLane")
            .withAttribute("hostName", DataType.STRING)
            .withAttribute("portNo", DataType.LONG);
      
      Clazz task = model.createClazz("Task")
            .withAttribute("state", DataType.STRING)
            .withAttribute("spaceName", DataType.STRING)
            .withAttribute("fileName", DataType.STRING)
            .withAttribute("lastModified", DataType.LONG);
      
      taskBoard.withBidirectional(taskLane, "lanes", Cardinality.MANY, "board", Cardinality.ONE);
      
      taskLane.withBidirectional(task, "tasks", Cardinality.MANY, "lane", Cardinality.ONE);
      
      task.withBidirectional(taskLane, "fileTargetCloud", Cardinality.ONE, "myRequests", Cardinality.MANY);
      
      model.generate("src/main/replication");
      
      story.addClassDiagram(model);

      story.dumpHTML();
   }
}
