package org.sdmlib.storyboards.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class StoryboardPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new StoryboardPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((StoryboardPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((StoryboardPO) target).set(attrName, value);
   }
}

