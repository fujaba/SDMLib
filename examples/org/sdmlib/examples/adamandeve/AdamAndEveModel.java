package org.sdmlib.examples.adamandeve;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.storyboards.Storyboard;

public class AdamAndEveModel
{
   @Test
   public void adamAndEveModel()
   {
      Storyboard storyboard = new Storyboard("examples");

      ClassModel model = new ClassModel("org.sdmlib.examples.adamandeve.model");
      
      model.createClazz("Eve");

      model.createClazz("Adam");

      Clazz taskFlowClazz = model.createClazz("org.sdmlib.logger.TaskFlow")
            .withAttribute("taskNo", DataType.INT) 
            .withExternal(true);
      
      model.createClazz(SDMLibJsonIdMap.class.getName()).withExternal(true);
      
      model.createClazz("org.sdmlib.logger.PeerProxy")
            .withAttribute("ip", DataType.STRING)
            .withAttribute("port", DataType.INT)
            .withAttribute("idMap", DataType.ref("org.sdmlib.serialization.SDMLibJsonIdMap"))
            .withExternal(true);
      
      Clazz clazzUpdate = model.createClazz("UpdateAdamFlow")
         .withAttribute("adam", DataType.ref("org.sdmlib.logger.PeerProxy"))
         .withAttribute("eve", DataType.ref("org.sdmlib.logger.PeerProxy"))
         .withAttribute("idMap", DataType.ref("org.sdmlib.serialization.SDMLibJsonIdMap"))
         .withAttribute("adamJarAtEveSiteLastModified", DataType.LONG)
         .withSuperClass(taskFlowClazz);
      
      clazzUpdate.createMethod("run");
      clazzUpdate.createMethod("getTaskNames")
                  .withReturnType(DataType.ref("Object[]"));
      
      model.generate("examples");

      storyboard.addSVGImage(model.dumpClassDiagram("AdamAndEveModel"));

      storyboard.dumpHTML();
   }
}
