package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class MalePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new MalePO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((MalePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((MalePO) target).set(attrName, value);
   }
}

