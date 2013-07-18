package org.sdmlib.examples.studyright.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class TopicPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new TopicPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((TopicPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((TopicPO) target).set(attrName, value);
   }
}

