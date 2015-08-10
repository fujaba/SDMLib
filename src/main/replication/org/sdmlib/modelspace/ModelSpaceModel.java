package org.sdmlib.modelspace;

import static org.junit.Assert.*;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
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
      
      model.generate("src/main/replication");
      
      story.addClassDiagram(model);

      story.dumpHTML();
   }
}
