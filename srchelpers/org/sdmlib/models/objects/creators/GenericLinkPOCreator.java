package org.sdmlib.models.objects.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class GenericLinkPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new GenericLinkPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GenericLinkPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((GenericLinkPO) target).set(attrName, value);
   }
}

