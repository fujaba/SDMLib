package org.sdmlib.models;

import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.list.SimpleKeyValueList;
import org.sdmlib.storyboards.GenericCreator;

import java.util.ArrayList;
import java.util.Map;

public class CreatorMap
{
   Map<String, SendableEntityCreator> creatorMap = new SimpleKeyValueList<String, SendableEntityCreator>();

   private ArrayList<String> packageNames;

   public CreatorMap(String packageName)
   {
      ArrayList<String> packageNames = new ArrayList<>();

      packageNames.add(packageName);

      this.packageNames = packageNames;
   }

   public CreatorMap(ArrayList<String> packageNames)
   {
      this.packageNames = packageNames;
   }


   public SendableEntityCreator getCreator(Object newObject)
   {
      String simpleName = newObject.getClass().getSimpleName();

      return getCreator(simpleName);
   }


   public SendableEntityCreator getCreator(String clazzName)
   {
      // already known?
      SendableEntityCreator creator = creatorMap.get(clazzName);

      if (creator != null)
      {
         return creator;
      }

      // try reflection
      String creatorName = null;

      for (String packageName : packageNames)
      {
         String fullCreatorName = packageName + ".util." + clazzName + "Creator";

         try
         {
            Class<?> creatorClass = Class.forName(fullCreatorName);

            creator = (SendableEntityCreator) creatorClass.newInstance();

            if (creator != null)
            {
               creatorMap.put(clazzName, creator);
               return creator;
            }
         }
         catch (Exception e)
         {
            creator = null;
         }
      }

      // GenericCreator?
      for (String packageName : packageNames)
      {
         String fullClassName = packageName + "." + clazzName;

         try
         {
            Class<?> theClass = Class.forName(fullClassName);

            if (theClass != null)
            {
               creator = new GenericCreator().withClassName(fullClassName);
               creatorMap.put(clazzName, creator);
               return creator;
            }
         }
         catch (Exception e)
         {
            creator = null;
         }
      }




      return creator;
   }

}
