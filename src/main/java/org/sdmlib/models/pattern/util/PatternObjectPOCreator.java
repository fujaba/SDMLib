 package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.PatternObject;

import de.uniks.networkparser.IdMap;


public class PatternObjectPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new PatternObjectPO(new PatternObject[]{});
      } else {
         return new PatternObjectPO();
      }
    }
   
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

