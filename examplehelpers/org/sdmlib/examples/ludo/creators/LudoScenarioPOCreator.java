package org.sdmlib.examples.ludo.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class LudoScenarioPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new LudoScenarioPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((LudoScenarioPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((LudoScenarioPO) target).set(attrName, value);
   }
}

