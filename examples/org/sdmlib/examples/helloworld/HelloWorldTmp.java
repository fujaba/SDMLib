package org.sdmlib.examples.helloworld;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;

public class HelloWorldTmp
{
   @Test
   public void testTmp()
   {
      ClassModel model = new ClassModel();
      
      Clazz greetingClazz = new Clazz("org.sdmlib.examples.helloworld.Greeting")
      .withAttribute("text", "String");
      
      model.generate("examples", "examples");
      
      
   }
}
