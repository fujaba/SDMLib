package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Association;
import org.sdmlib.serialization.interfaces.EntityFactory;

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
      return ((Association) target).get(attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Association) target).set(attrName, value);
   }
   
   @Override
   public void removeObject(Object entity)
   {
      ((Association) entity).removeYou();
   }
   
   
}


