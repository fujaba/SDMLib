package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.PatternElement;

import de.uniks.networkparser.IdMap;

public class PatternElementPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new PatternElementPO(new PatternElement[]{});
      } else {
         return new PatternElementPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

