package org.sdmlib.codegen.creators;

import org.sdmlib.models.classes.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.codegen.LocalVarTableEntry;

public class LocalVarTableEntryCreator implements EntityFactory
{
   private final String[] properties = new String[]
   {
      LocalVarTableEntry.PROPERTY_NAME,
      LocalVarTableEntry.PROPERTY_TYPE,
      LocalVarTableEntry.PROPERTY_STARTPOS,
      LocalVarTableEntry.PROPERTY_ENDPOS,
      LocalVarTableEntry.PROPERTY_INITSEQUENCE,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new LocalVarTableEntry();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((LocalVarTableEntry) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((LocalVarTableEntry) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((LocalVarTableEntry) entity).removeYou();
   }
}


