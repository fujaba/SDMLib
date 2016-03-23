package org.sdmlib.test.models.objects;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.StoryPage;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class GenericGraphModel
{
     /**
    * 
    * @see <a href='../../../../../../../../doc/GenericGraphModel.html'>GenericGraphModel.html</a>
*/
   @Test
   public void testGenericGraphModel()
   {
      StoryPage storyboard = new StoryPage();
      
      ClassModel genericModel = new ClassModel("org.sdmlib.models.objects");
      
      Clazz genericGraph = genericModel.createClazz("org.sdmlib.models.objects.GenericGraph");
      
      Clazz genericObjectClazz = genericModel.createClazz("org.sdmlib.models.objects.GenericObject")
      .withAttribute("name", DataType.STRING)
      .withAttribute("type", DataType.STRING)
      .withAttribute("icon", DataType.STRING);
           
      new Association(genericObjectClazz)
      		.with("objects")
      		.with(Cardinality.MANY)
      		.with(new Association(genericGraph)
      				.with("graph")
      				.with(Cardinality.ONE)
      				);
      
      Clazz genericAttributeClazz = genericModel.createClazz("org.sdmlib.models.objects.GenericAttribute")
      .withAttribute("name", DataType.STRING)
      .withAttribute("value", DataType.STRING);
      
      
      new Association(genericObjectClazz).with("owner").with(Cardinality.ONE)
      .with(new Association(genericAttributeClazz).with("attrs").with(Cardinality.MANY));
      
      Clazz genericLinkClazz = genericModel.createClazz("org.sdmlib.models.objects.GenericLink")
      .withAttribute("tgtLabel", DataType.STRING)
      .withAttribute("srcLabel", DataType.STRING);

      new Association(genericObjectClazz).with("src").with(Cardinality.ONE)
      .with(new Association(genericAttributeClazz).with("outgoingLinks").with(Cardinality.MANY));

      new Association(genericObjectClazz).with("tgt").with(Cardinality.ONE)
      .with(new Association(genericAttributeClazz).with("incommingLinks").with(Cardinality.MANY));

      new Association(genericLinkClazz).with("links").with(Cardinality.MANY)
      .with(new Association(genericGraph).with("graph").with(Cardinality.ONE));

      storyboard.addClassDiagram(genericModel);
      
      // genericModel.removeAllGeneratedCode("test", "src", "srchelpers");
     
      genericModel.generate("src/main/java");
      
      storyboard.dumpHTML();

   }
}
