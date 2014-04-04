package org.sdmlib.models.patterns.example.ferrmansproblem.creators;

import org.sdmlib.models.pattern.creators.PatternCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class ModelPatternCreator extends PatternCreator
{
   private static ModelPattern prototype = null;

   public Object getSendableInstance(boolean reference)
   {
      if (prototype == null)
      {
         prototype = new ModelPattern();
      }
      return prototype;
   }

   public Object getValue(Object target, String attrName)
   {
      return ((ModelPattern) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((ModelPattern) target).set(attrName, value);
   }

}
