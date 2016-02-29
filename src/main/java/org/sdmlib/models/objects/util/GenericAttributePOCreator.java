package org.sdmlib.models.objects.util;

import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.IdMap;

public class GenericAttributePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new GenericAttributePO(new GenericAttribute[]{});
      } else {
          return new GenericAttributePO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
       return CreatorCreator.createIdMap(sessionID);
   }
}
