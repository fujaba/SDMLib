package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.pattern.DestroyObjectElem;

public class DestroyObjectElemCreator implements EntityFactory
{
   private final String[] properties = new String[]
   {
      DestroyObjectElem.PROPERTY_MODIFIER,
      DestroyObjectElem.PROPERTY_HASMATCH,
      DestroyObjectElem.PROPERTY_PATTERNOBJECTNAME,
      DestroyObjectElem.PROPERTY_PATTERNOBJECT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new DestroyObjectElem();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((DestroyObjectElem) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((DestroyObjectElem) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((DestroyObjectElem) entity).removeYou();
   }
}


