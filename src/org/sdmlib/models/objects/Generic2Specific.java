package org.sdmlib.models.objects;

import java.util.LinkedHashMap;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonIdMap;

public class Generic2Specific
{

   public Object convert(JsonIdMap jsonIdMap, String packageName, GenericGraph graph)
   {
      Object result = null; 
      
      // now create specific objects
      LinkedHashMap<GenericObject, Object> gen2specObjTable = new LinkedHashMap<GenericObject, Object>();
      
      for (GenericObject genericObject : graph.getObjects())
      {
         String type = genericObject.getType();
         
         if (type != null)
         {
            if (packageName != null)
            {
               type = packageName + "." + type;
            }
            
            SendableEntityCreator creatorClass = jsonIdMap.getCreator(type, true);
            
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
                  creatorClass.setValue(specObject, genericAttribute.getName(), genericAttribute.getValue(), "");
               }
            }
         }
      }
      
      // now set up the links
      for (GenericLink genericLink : graph.getLinks())
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
            srcCreatorClass.setValue(specSrc, genericLink.getTgtLabel(), specTgt, "");
         }
         else if (genericLink.getSrcLabel() != null)
         {
            // use set at target object
            SendableEntityCreator tgtCreatorClass = jsonIdMap.getCreatorClass(specTgt);
            tgtCreatorClass.setValue(specTgt, genericLink.getSrcLabel(), specSrc, "");
         }
      }
      
      return result;
   }

}
