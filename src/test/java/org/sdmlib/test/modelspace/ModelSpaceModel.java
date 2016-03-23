package org.sdmlib.test.modelspace;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.StoryPage;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class ModelSpaceModel
{
     /**
    * 
    * @see <a href='../../../../../../doc/ModelSpaceModel.html'>ModelSpaceModel.html</a>
* @see <a href='../../../../../../../doc/ModelSpaceModel.html'>ModelSpaceModel.html</a>
 */
   @Test
   public void ModelSpaceModel()
   {
      StoryPage story = new StoryPage();
      
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
