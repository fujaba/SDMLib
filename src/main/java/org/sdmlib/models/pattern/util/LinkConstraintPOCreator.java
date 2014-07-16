package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.LinkConstraint;

import de.uniks.networkparser.json.JsonIdMap;

public class LinkConstraintPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new LinkConstraintPO(new LinkConstraint[]{});
      } else {
         return new LinkConstraintPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

