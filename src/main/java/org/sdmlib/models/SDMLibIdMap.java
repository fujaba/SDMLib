package org.sdmlib.models;

import org.sdmlib.serialization.NullCreator;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class SDMLibIdMap extends IdMap
{
   public SDMLibIdMap(String session)
   {
      this.withSession(session);
      this.withTimeStamp(1);
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
            // EMF Impl class?
            int implPos = clazz.lastIndexOf(".impl.");

            if (implPos > 0 && clazz.endsWith("Impl") )
            {
               creatorName = clazz.replace(".impl.", ".util.");
               creatorName = creatorName.substring(0, creatorName.length()-"Impl".length());
               creatorName = creatorName + "Creator";
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
