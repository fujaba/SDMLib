package org.sdmlib.modelspace;

import static org.junit.Assert.*;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.storyboards.Storyboard;

public class ModelSpaceModel
{
     /**
    * 
    * @see <a href='../../../../../../doc/ModelSpaceModel.html'>ModelSpaceModel.html</a>
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
      
      cloud.withAssoc(cloudProxy, "servers", Card.MANY, "root", Card.ONE);
      cloud.withAssoc(spaceProxy, "modelSpaces", Card.MANY, "cloud", Card.ONE);
      
      cloudProxy.withAssoc(spaceProxy, "providedSpaces", Card.MANY, "providingClouds", Card.MANY);
      
      Clazz modelDir = model.createClazz("CloudModelDirectory");
      Clazz modelFile = model.createClazz("CloudModelFile")
            .withAttribute("fileName", DataType.STRING)
            .withAttribute("lastModifiedTime", DataType.LONG);
      
      modelDir.withAssoc(modelFile, "files", Card.MANY, "dir", Card.ONE);
      
      Clazz taskBoard = model.createClazz("TaskBoard");
      
      Clazz taskLane = model.createClazz("TaskLane")
            .withAttribute("hostName", DataType.STRING)
            .withAttribute("portNo", DataType.LONG);
      
      Clazz task = model.createClazz("Task")
            .withAttribute("state", DataType.STRING)
            .withAttribute("spaceName", DataType.STRING)
            .withAttribute("fileName", DataType.STRING)
            .withAttribute("lastModified", DataType.LONG);
      
      taskBoard.withAssoc(taskLane, "lanes", Card.MANY, "board", Card.ONE);
      
      taskLane.withAssoc(task, "tasks", Card.MANY, "lane", Card.ONE);
      
      task.withAssoc(taskLane, "fileTargetCloud", Card.ONE, "myRequests", Card.MANY);
      
      model.generate("src/main/replication");
      
      story.addClassDiagram(model);

      story.dumpHTML();
   }
}
