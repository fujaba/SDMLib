package org.sdmlib.storyboards.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class StoryboardStepPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new StoryboardStepPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((StoryboardStepPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((StoryboardStepPO) target).set(attrName, value);
   }
}

