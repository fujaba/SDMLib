package org.sdmlib.examples.studyrightWithAssignments.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class TeachingAssistantPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new TeachingAssistantPO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((TeachingAssistantPO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((TeachingAssistantPO) target).set(attrName, value);
   }
}
