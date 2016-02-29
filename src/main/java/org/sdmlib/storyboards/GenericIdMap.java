package org.sdmlib.storyboards;

import java.lang.reflect.Method;

import org.sdmlib.CGUtil;
import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.IdMap;

public class GenericIdMap extends SDMLibJsonIdMap
{
   @Override
   public SendableEntityCreator getCreator(String className, boolean fullName) 
   {
      if (className.startsWith("java.util.Collections$"))
      {
         return null;
      }
      else if (className.startsWith("java.lang."))
      {
         return null;
      }
      else if (className.endsWith("Set") || className.endsWith("EList"))
      {
         String packageName = CGUtil.packageName(className);
         
         if (packageName.endsWith(".util"))
         {
            return null;
         }
         
      }
      
      SendableEntityCreator sendableEntityCreator = (SendableEntityCreator) this.creators.get(className);
      
      if (sendableEntityCreator == null)
      {
         // try to infer creator class
         String creatorClassName = CGUtil.helperClassName(className, "Creator");
         try
         {
            Class<?> creatorClass = this.getClass().getClassLoader().loadClass(creatorClassName);
            sendableEntityCreator = (SendableEntityCreator) creatorClass.newInstance();
         }
         catch (Exception e)
         {
            // did not work, thus generic must be enough
            // e.printStackTrace();
         }

         if (sendableEntityCreator == null)
         {
            // create generic creator
            sendableEntityCreator = new GenericCreator().withClassName(className);
         }

         this.creators.put(className, sendableEntityCreator);
      }
      
      return sendableEntityCreator;
   }
}
