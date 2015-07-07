package org.sdmlib.test.examples.m2m;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;

public class BanfM2MModelGen
{
   public static ClassModel genModel()
   {
      ClassModel model = new ClassModel("org.sdmlib.examples.m2m.model");
      
      Clazz graphComponentClazz = model.createClazz("GraphComponent")
            .withAttribute("text", DataType.STRING );

      Clazz graphClazz = model.createClazz("Graph");

      Clazz nodeClazz = model.createClazz("Person")
            .withAttribute("firstName", DataType.STRING)
            .withAttribute("text", DataType.STRING);

      Clazz edgeClazz = model.createClazz("Relation")
            .withAttribute("kind", DataType.STRING );

      graphClazz.withAssoc(graphComponentClazz, "gcs", Card.MANY, "parent", Card.ONE);
      
      graphClazz.withAssoc(nodeClazz, "persons", Card.MANY, "graph", Card.ONE);

      graphClazz.withAssoc(edgeClazz, "relations", Card.MANY, "graph", Card.ONE);

      edgeClazz.withAssoc(nodeClazz, "src", Card.ONE, "outEdges", Card.MANY);

      edgeClazz.withAssoc(nodeClazz, "tgt", Card.ONE, "inEdges", Card.MANY);
      
      nodeClazz.withAssoc(nodeClazz, "knows", Card.MANY, "knows", Card.MANY);

      model.generate("src/test/java");
      
      return model;
   }
   
   @Test
   public void testBanGenModel(){
      genModel();
   }
}
