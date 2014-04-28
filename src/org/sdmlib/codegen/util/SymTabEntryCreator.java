package org.sdmlib.codegen.util;

import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.serialization.interfaces.EntityFactory;

public class SymTabEntryCreator extends EntityFactory
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
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new SymTabEntry();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((SymTabEntry) target).get(attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((SymTabEntry) target).set(attrName, value);
   }
   
  
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((SymTabEntry) entity).removeYou();
   }
}


