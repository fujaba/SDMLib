package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.modelspace.ModelSpace;

import de.uniks.networkparser.IdMap;

public class ModelSpacePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ModelSpacePO(new ModelSpace[]{});
      } else {
          return new ModelSpacePO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.modelspace.util.CreatorCreator.createIdMap(sessionID);
   }
}
