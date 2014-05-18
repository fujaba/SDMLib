package org.sdmlib.codegen.util;

import org.sdmlib.codegen.StatementEntry;
import org.sdmlib.serialization.EntityFactory;

public class StatementEntryCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      StatementEntry.PROPERTY_KIND,
      StatementEntry.PROPERTY_TOKENLIST,
      StatementEntry.PROPERTY_ASSIGNTARGETVARNAME,
      StatementEntry.PROPERTY_BODYSTATS,
      StatementEntry.PROPERTY_PARENT,
      StatementEntry.PROPERTY_STARTPOS,
      StatementEntry.PROPERTY_ENDPOS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new StatementEntry();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((StatementEntry) target).get(attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((StatementEntry) target).set(attrName, value);
   }

   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((StatementEntry) entity).removeYou();
   }
}




