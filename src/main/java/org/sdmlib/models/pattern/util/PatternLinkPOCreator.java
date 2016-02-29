package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.PatternLink;

import de.uniks.networkparser.IdMap;


public class PatternLinkPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new PatternLinkPO(new PatternLink[]{});
      } else {
         return new PatternLinkPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

