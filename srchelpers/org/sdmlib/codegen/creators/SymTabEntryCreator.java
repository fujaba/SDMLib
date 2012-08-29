package org.sdmlib.codegen.creators;

import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class SymTabEntryCreator implements EntityFactory
{
   private final String[] properties = new String[]
   {
      SymTabEntry.PROPERTY_KIND,
      SymTabEntry.PROPERTY_MEMBERNAME,
      SymTabEntry.PROPERTY_TYPE,
      SymTabEntry.PROPERTY_STARTPOS,
      SymTabEntry.PROPERTY_BODYSTARTPOS,
      SymTabEntry.PROPERTY_ENDPOS,
      SymTabEntry.PROPERTY_MODIFIERS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new SymTabEntry();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((SymTabEntry) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((SymTabEntry) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((SymTabEntry) entity).removeYou();
   }
}


