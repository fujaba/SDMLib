package org.sdmlib.examples.groupAccount.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class PersonPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new PersonPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PersonPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((PersonPO) target).set(attrName, value);
   }}

