package org.sdmlib.models.pattern.creators;


public class PatternLinkPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new PatternLinkPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PatternLinkPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((PatternLinkPO) target).set(attrName, value);
   }
}

