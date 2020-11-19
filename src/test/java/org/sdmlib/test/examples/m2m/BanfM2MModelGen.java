package org.sdmlib.test.examples.m2m;

import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Association;
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

      graphClazz.withBidirectional(graphComponentClazz, "gcs", Association.MANY, "parent", Association.ONE);
      
      graphClazz.withBidirectional(nodeClazz, "persons", Association.MANY, "graph", Association.ONE);

      graphClazz.withBidirectional(edgeClazz, "relations", Association.MANY, "graph", Association.ONE);

      edgeClazz.withBidirectional(nodeClazz, "src", Association.ONE, "outEdges", Association.MANY);

      edgeClazz.withBidirectional(nodeClazz, "tgt", Association.ONE, "inEdges", Association.MANY);
      
      nodeClazz.withBidirectional(nodeClazz, "knows", Association.MANY, "knows", Association.MANY);

      model.generate("src/test/java");
      
      return model;
   }

   public static void main(String[] args)
   {
      genModel();
   }
}
