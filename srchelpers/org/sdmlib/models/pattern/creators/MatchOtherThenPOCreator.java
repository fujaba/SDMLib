package org.sdmlib.models.pattern.creators;


public class MatchOtherThenPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new MatchOtherThenPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((MatchOtherThenPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((MatchOtherThenPO) target).set(attrName, value);
   }
}

