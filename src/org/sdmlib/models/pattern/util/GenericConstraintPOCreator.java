package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.GenericConstraint;

import de.uniks.networkparser.json.JsonIdMap;

public class GenericConstraintPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new GenericConstraintPO(new GenericConstraint[]{});
      } else {
         return new GenericConstraintPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

