package org.sdmlib.models.transformations.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.models.transformations.PlaceHolderDescription;

import de.uniks.networkparser.IdMap;

public class PlaceHolderDescriptionPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new PlaceHolderDescriptionPO(new PlaceHolderDescription[]{});
      } else {
          return new PlaceHolderDescriptionPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.models.transformations.util.CreatorCreator.createIdMap(sessionID);
   }
}
