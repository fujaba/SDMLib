package org.sdmlib.examples.m2m.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class RelationPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new RelationPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((RelationPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((RelationPO) target).set(attrName, value);
   }
}

