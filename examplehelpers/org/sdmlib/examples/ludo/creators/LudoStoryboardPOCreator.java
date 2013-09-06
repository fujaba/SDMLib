package org.sdmlib.examples.ludo.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class LudoStoryboardPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new LudoStoryboardPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((LudoStoryboardPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((LudoStoryboardPO) target).set(attrName, value);
   }
}

