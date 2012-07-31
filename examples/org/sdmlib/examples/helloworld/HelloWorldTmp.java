package org.sdmlib.examples.helloworld;

import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;

public class HelloWorldTmp
{
   @Test
   public void testTmp()
   {
ClassModel model = new ClassModel();
      
      Clazz graphClazz = new Clazz("org.sdmlib.examples.helloworld.Graph");
      
      Clazz edgeClazz = new Clazz("org.sdmlib.examples.helloworld.Edge");

      Clazz nodeClazz = new Clazz("org.sdmlib.examples.helloworld.Node")
      .withAttribute("name", "String");

      new Association()
      .withTarget(nodeClazz, "nodes", Role.MANY)
      .withSource(graphClazz, "graph", Role.ONE);
      
      new Association()
      .withTarget(edgeClazz, "edges", Role.MANY)
      .withSource(graphClazz, "graph", Role.ONE);
      
      new Association()
      .withTarget(nodeClazz, "src", Role.ONE)
      .withSource(edgeClazz, "outEdges", Role.MANY);

      new Association()
      .withTarget(nodeClazz, "tgt", Role.ONE)
      .withSource(edgeClazz, "inEdges", Role.MANY);

      // // model.removeAllGeneratedCode("examples", "examples", "examples");
      
      model.generate("examples", "examples");
      
   }
}
