package org.sdmlib.test.examples.m2m;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class BanfM2MModelGen
{
   public static ClassModel genModel()
   {
      ClassModel model = new ClassModel("org.sdmlib.test.examples.m2m.model");
      
      Clazz graphComponentClazz = model.createClazz("GraphComponent");
      graphComponentClazz.createAttribute("text", DataType.STRING );

      Clazz graphClazz = model.createClazz("Graph");

      Clazz nodeClazz = model.createClazz("Person");
      nodeClazz.createAttribute("firstName", DataType.STRING);
      nodeClazz.createAttribute("text", DataType.STRING);

      Clazz edgeClazz = model.createClazz("Relation");
      edgeClazz.createAttribute("kind", DataType.STRING );

      graphClazz.withBidirectional(graphComponentClazz, "gcs", Cardinality.MANY, "parent", Cardinality.ONE);
      
      graphClazz.withBidirectional(nodeClazz, "persons", Cardinality.MANY, "graph", Cardinality.ONE);

      graphClazz.withBidirectional(edgeClazz, "relations", Cardinality.MANY, "graph", Cardinality.ONE);

      edgeClazz.withBidirectional(nodeClazz, "src", Cardinality.ONE, "outEdges", Cardinality.MANY);

      edgeClazz.withBidirectional(nodeClazz, "tgt", Cardinality.ONE, "inEdges", Cardinality.MANY);
      
      nodeClazz.withBidirectional(nodeClazz, "knows", Cardinality.MANY, "knows", Cardinality.MANY);

      model.generate("src/test/java");
      
      return model;
   }
   
   @Test
   public void testBanGenModel(){
      genModel();
   }
}
