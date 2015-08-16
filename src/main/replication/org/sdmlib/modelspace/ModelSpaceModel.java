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
   @Test
   public void ModelSpaceModel()
   {
      Storyboard story = new Storyboard();
      
      story.add("ModelSpace provides incremental persistance and collaboration");
      
      ClassModel model = new ClassModel("org.sdmlib.modelspace");
      
      model.createClazz("ModelSpace");
      
      Clazz cloud = model.createClazz("ModelCloud")
            .withAttribute("acceptPort", DataType.INT); 
      
      Clazz cloudProxy = model.createClazz("ModelCloudProxy")
            .withAttribute("hostName", DataType.STRING)
            .withAttribute("portNo", DataType.INT);
      
      cloud.withAssoc(cloudProxy, "servers", Card.MANY, "root", Card.ONE);
      
      model.generate("src/main/replication");
      
      story.addClassDiagram(model);

      story.dumpHTML();
   }
}
