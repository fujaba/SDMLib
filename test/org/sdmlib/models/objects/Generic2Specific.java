package org.sdmlib.models.objects;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;

import de.kassel.roombook.Building;

public class Generic2Specific
{

   public Object convert(JsonIdMap jsonIdMap, String packageName, GenericObject root)
   {
      Object result = null; 
      
      // collect all objects and links
      LinkedHashSet<GenericObject> allObjects = new LinkedHashSet<GenericObject>();
      LinkedList<GenericObject> todoObjects = new LinkedList<GenericObject>();
      
      LinkedHashSet<GenericLink> allLinks = new LinkedHashSet<GenericLink>();
      
      todoObjects.add(root);
      
      while (! todoObjects.isEmpty())
      {
         GenericObject currentObject = todoObjects.pop();
         allObjects.add(currentObject);

         for (GenericLink currentLink : currentObject.getOutgoingLinks())
         {
            allLinks.add(currentLink);
            
            GenericObject neighbor = currentLink.getTgt();
            if ( ! allObjects.contains(neighbor))
            {
               todoObjects.add(neighbor);
            }
         }

         for (GenericLink currentLink : currentObject.getIncommingLinks())
         {
            allLinks.add(currentLink);
            
            GenericObject neighbor = currentLink.getSrc();
            if ( ! allObjects.contains(neighbor))
            {
               todoObjects.add(neighbor);
            }
         }
      }

      // now create specific objects
      LinkedHashMap<GenericObject, Object> gen2specObjTable = new LinkedHashMap<GenericObject, Object>();
      for (GenericObject genericObject : allObjects)
      {
         String type = genericObject.getType();
         
         if (type != null)
         {
            SendableEntityCreator creatorClass = jsonIdMap.getCreatorClasses(packageName + "." + type);
            
            if (creatorClass != null)
            {
               Object specObject = creatorClass.getSendableInstance(false);
               
               gen2specObjTable.put(genericObject, specObject);
               
               if (result == null)
               {
                  result = specObject;
               }
               
               // transfer the attributes
               for (GenericAttribute genericAttribute : genericObject.getAttrs())
               {
                  creatorClass.setValue(specObject, genericAttribute.getName(), genericAttribute.getValue());
               }
            }
         }
      }
      
      // now set up the links
      for (GenericLink genericLink : allLinks)
      {
         GenericObject genericSrc = genericLink.getSrc();
         Object specSrc = gen2specObjTable.get(genericSrc);
         GenericObject genericTgt = genericLink.getTgt();
         Object specTgt = gen2specObjTable.get(genericTgt);

         if (specSrc == null || specTgt == null)
         {
            continue;
         }
         
         if (genericLink.getTgtLabel() != null)
         {
            // use set at source object
            SendableEntityCreator srcCreatorClass = jsonIdMap.getCreatorClass(specSrc);
            srcCreatorClass.setValue(specSrc, genericLink.getTgtLabel(), specTgt);
         }
         else if (genericLink.getSrcLabel() != null)
         {
            // use set at target object
            SendableEntityCreator tgtCreatorClass = jsonIdMap.getCreatorClass(specTgt);
            tgtCreatorClass.setValue(specTgt, genericLink.getSrcLabel(), specSrc);
         }
      }
      return result;
   }

}
