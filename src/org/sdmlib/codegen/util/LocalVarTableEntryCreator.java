package org.sdmlib.codegen.util;

import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.serialization.interfaces.EntityFactory;

public class LocalVarTableEntryCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      LocalVarTableEntry.PROPERTY_NAME,
      LocalVarTableEntry.PROPERTY_TYPE,
      LocalVarTableEntry.PROPERTY_STARTPOS,
      LocalVarTableEntry.PROPERTY_ENDPOS,
      LocalVarTableEntry.PROPERTY_INITSEQUENCE,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new LocalVarTableEntry();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((LocalVarTableEntry) target).get(attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((LocalVarTableEntry) target).set(attrName, value);
   }

   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((LocalVarTableEntry) entity).removeYou();
   }
}


