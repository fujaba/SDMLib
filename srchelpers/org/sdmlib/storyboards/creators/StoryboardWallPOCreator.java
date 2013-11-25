package org.sdmlib.storyboards.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class StoryboardWallPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new StoryboardWallPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((StoryboardWallPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((StoryboardWallPO) target).set(attrName, value);
   }
}

