package org.sdmlib.models;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonIdMap;

public class SDMLibIdMap extends JsonIdMap
{
   @Override
   public SendableEntityCreator getCreator(String clazz, boolean fullName)
   {
      SendableEntityCreator result = super.getCreator(clazz, fullName);
      
      if (result == null)
      {
         // try reflection
         int splitPos = clazz.lastIndexOf('.');
         if (splitPos >= 0)
         {
            String creatorName = clazz.substring(0, splitPos+1)
                  + "util." 
                  + clazz.substring(splitPos+1)
                  + "Creator";
            try
            {
               Class<?> creatorClass = Class.forName(creatorName);
               result = (SendableEntityCreator) creatorClass.newInstance();
               if (result != null)
               {
                  this.creators.put(clazz, result);
               }
            }
            catch (Exception e)
            {
               result = null;
            }
         }
      }
      
      return result;
   }
}
