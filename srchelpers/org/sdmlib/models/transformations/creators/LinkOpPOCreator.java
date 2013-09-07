package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class LinkOpPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new LinkOpPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((LinkOpPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((LinkOpPO) target).set(attrName, value);
   }
}

