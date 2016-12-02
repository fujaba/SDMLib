package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.modelspace.CloudModelDirectory;

import de.uniks.networkparser.IdMap;

public class CloudModelDirectoryPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new CloudModelDirectoryPO(new CloudModelDirectory[]{});
      } else {
          return new CloudModelDirectoryPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.modelspace.util.CreatorCreator.createIdMap(sessionID);
   }
}
