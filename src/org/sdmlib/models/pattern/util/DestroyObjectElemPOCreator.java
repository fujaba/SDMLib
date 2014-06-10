package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.DestroyObjectElem;

import de.uniks.networkparser.json.JsonIdMap;

public class DestroyObjectElemPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new DestroyObjectElemPO(new DestroyObjectElem[]{});
      } else {
         return new DestroyObjectElemPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

