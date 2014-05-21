package org.sdmlib.examples.m2m;

import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;

public class BanfM2MModelGen
{
   private Clazz edgeClazz;
   private Clazz nodeClazz;
   private Clazz graphClazz;
   private ClassModel genModel(){
      ClassModel model = new ClassModel("org.sdmlib.examples.m2m.model");
      
      graphClazz = new Clazz("Graph").withClassModel(model);
      
      nodeClazz = new Clazz("Person")
         .withAttribute("firstName", DataType.STRING);
      
      edgeClazz = new Clazz("Relation").withAttribute("kind", DataType.STRING );

      new Association()
      .withTarget(nodeClazz, "persons", Card.MANY)
      .withSource(graphClazz, "graph", Card.ONE);
      
      new Association()
      .withTarget(edgeClazz, "relations", Card.MANY)
      .withSource(graphClazz, "graph", Card.ONE);
      
      new Association()
      .withTarget(nodeClazz, "src", Card.ONE)
      .withSource(edgeClazz, "outEdges", Card.MANY);

      new Association()
      .withTarget(nodeClazz, "tgt", Card.ONE)
      .withSource(edgeClazz, "inEdges", Card.MANY);
      
      model.generate("examples");
      return model;
   }
   
   @Test
   public void testBanGenModel(){
      genModel();
   }
}
