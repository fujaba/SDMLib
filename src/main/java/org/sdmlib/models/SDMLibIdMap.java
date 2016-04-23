package org.sdmlib.models;

import org.sdmlib.serialization.NullCreator;
import org.sdmlib.storyboards.GenericCreator;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class SDMLibIdMap extends IdMap
{
   public SDMLibIdMap(String sessionId)
   {
      this.withSessionId(sessionId);
   }

   @Override
   public SendableEntityCreator getCreator(String clazz, boolean fullName)
   {
      SendableEntityCreator result = super.getCreator(clazz, fullName);
      
      if (result == NullCreator.get())
      {
         return null;
      }
      
      if (result == null)
      {
         // try reflection
         String creatorName = null;
         
         // is it a PO class? 
         int utilPos = clazz.lastIndexOf(".util.");
         
         if (utilPos > 0 && clazz.endsWith("PO") )
         {
            creatorName = clazz + "Creator";
         }
         else
         {
            int splitPos = clazz.lastIndexOf('.');
            if (splitPos >= 0)
            {
               creatorName = clazz.substring(0, splitPos+1)
                  + "util." 
                  + clazz.substring(splitPos+1)
                  + "Creator";
            }
         }
         
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
            this.creators.put(clazz, NullCreator.get());
         }
      }
      
      return result;
   }
}
