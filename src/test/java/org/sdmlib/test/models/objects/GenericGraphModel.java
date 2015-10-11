package org.sdmlib.test.models.objects;

import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.storyboards.Storyboard;

public class GenericGraphModel
{
     /**
    * 
    * @see <a href='../../../../../../../../doc/GenericGraphModel.html'>GenericGraphModel.html</a>
*/
   @Test
   public void testGenericGraphModel()
   {
      Storyboard storyboard = new Storyboard();
      
      ClassModel genericModel = new ClassModel("org.sdmlib.models.objects");
      
      Clazz genericGraph = genericModel.createClazz("org.sdmlib.models.objects.GenericGraph");
      
      Clazz genericObjectClazz = genericModel.createClazz("org.sdmlib.models.objects.GenericObject")
      .withAttribute("name", DataType.STRING)
      .withAttribute("type", DataType.STRING)
      .withAttribute("icon", DataType.STRING);
      
      new Association()
      .withTarget(genericObjectClazz, "objects", Card.MANY)
      .withSource(genericGraph, "graph", Card.ONE);
      
      Clazz genericAttributeClazz = genericModel.createClazz("org.sdmlib.models.objects.GenericAttribute")
      .withAttribute("name", DataType.STRING)
      .withAttribute("value", DataType.STRING);
      
      new Association()
      .withSource(genericObjectClazz, "owner", Card.ONE)
      .withTarget(genericAttributeClazz, "attrs", Card.MANY);
      
      Clazz genericLinkClazz = genericModel.createClazz("org.sdmlib.models.objects.GenericLink")
      .withAttribute("tgtLabel", DataType.STRING)
      .withAttribute("srcLabel", DataType.STRING);
      
      new Association()
      .withSource(genericObjectClazz, "src", Card.ONE)
      .withTarget(genericLinkClazz, "outgoingLinks", Card.MANY);
      
      new Association()
      .withSource(genericObjectClazz, "tgt", Card.ONE)
      .withTarget(genericLinkClazz, "incommingLinks", Card.MANY);
      
      new Association()
      .withTarget(genericLinkClazz, "links", Card.MANY)
      .withSource(genericGraph, "graph", Card.ONE);
      
      storyboard.addClassDiagram(genericModel);
      
      // genericModel.removeAllGeneratedCode("test", "src", "srchelpers");
     
      genericModel.generate("src/main/java");
      
      storyboard.dumpHTML();

   }
}
