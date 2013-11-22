package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.transformations.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.transformations.PlaceHolderDescription;

public class PlaceHolderDescriptionCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      PlaceHolderDescription.PROPERTY_TEXTFRAGMENT,
      PlaceHolderDescription.PROPERTY_VALUE,
      PlaceHolderDescription.PROPERTY_ATTRNAME,
      PlaceHolderDescription.PROPERTY_TEMPLATE,
      PlaceHolderDescription.PROPERTY_SUBTEMPLATE,
      PlaceHolderDescription.PROPERTY_ISKEYATTRIBUTE,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new PlaceHolderDescription();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PlaceHolderDescription) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }
      return ((PlaceHolderDescription) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((PlaceHolderDescription) entity).removeYou();
   }
}


