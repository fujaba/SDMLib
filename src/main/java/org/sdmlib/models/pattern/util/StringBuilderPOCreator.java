package org.sdmlib.models.pattern.util;

import de.uniks.networkparser.IdMap;

public class StringBuilderPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new StringBuilderPO(new StringBuilder[]{});
      } else {
         return new StringBuilderPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

