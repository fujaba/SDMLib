package org.sdmlib.scenarios;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class GenericIdMap extends SDMLibJsonIdMap
{
   @Override
   public SendableEntityCreator getCreatorClasses(String className) 
   {
      if (className.startsWith("java.util.Collections$"))
      {
         return null;
      }
      else if (className.startsWith("java.lang."))
      {
         return null;
      }
      
      SendableEntityCreator sendableEntityCreator = this.creators.get(className);
      
      if (sendableEntityCreator == null)
      {
         // create generic creator
         sendableEntityCreator = new GenericCreator().withClassName(className);
         
         this.creators.put(className, sendableEntityCreator);
      }
      
      return sendableEntityCreator;
   }
}
