package org.sdmlib.models.objects.util;

import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.IdMap;

public class GenericLinkPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new GenericLinkPO(new GenericLink[]{});
      } else {
          return new GenericLinkPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
       return CreatorCreator.createIdMap(sessionID);
   }
}
