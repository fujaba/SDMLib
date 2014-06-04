package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Role;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class AssociationCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Association.PROPERTY_SOURCE,
      Association.PROPERTY_TARGET
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Association();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }
      if (Association.PROPERTY_SOURCE.equalsIgnoreCase(attribute))
      {
         return ((Association)target).getSource();
      }

      if (Association.PROPERTY_TARGET.equalsIgnoreCase(attribute))
      {
         return ((Association)target).getTarget();
      }
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Association.PROPERTY_SOURCE.equalsIgnoreCase(attrName))
      {
         ((Association) target).setSource((Role) value);
         return true;
      }

      if (Association.PROPERTY_TARGET.equalsIgnoreCase(attrName))
      {
         ((Association) target).setTarget((Role) value);
         return true;
      }
      return false;
   }
   
   @Override
   public void removeObject(Object entity)
   {
      ((Association) entity).removeYou();
   }
}
