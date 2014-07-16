package org.sdmlib.codegen.util;

import java.util.ArrayList;

import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.serialization.EntityFactory;

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
     int pos = attrName.indexOf('.');
     String attribute;
     if (pos > 0)
      {
        attribute = attrName.substring(0, pos);
      }else{
        attribute = attrName;
      }
     
      if (LocalVarTableEntry.PROPERTY_INITSEQUENCE.equalsIgnoreCase(attribute))
      {
         return ((LocalVarTableEntry)target).getInitSequence();
      }

      if (LocalVarTableEntry.PROPERTY_TYPE.equalsIgnoreCase(attribute))
      {
         return ((LocalVarTableEntry)target).getType();
      }

      if (LocalVarTableEntry.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((LocalVarTableEntry)target).getName();
      }
      if (LocalVarTableEntry.PROPERTY_STARTPOS.equalsIgnoreCase(attribute))
      {
         return ((LocalVarTableEntry)target).getStartPos();
      }

      if (LocalVarTableEntry.PROPERTY_ENDPOS.equalsIgnoreCase(attribute))
      {
         return ((LocalVarTableEntry)target).getEndPos();
      }
      return super.getValue(target, attribute);
   }
   
   @SuppressWarnings("unchecked")
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (LocalVarTableEntry.PROPERTY_INITSEQUENCE.equalsIgnoreCase(attrName))
      {
         ((LocalVarTableEntry)target).setInitSequence((ArrayList<ArrayList<String>>) value);
         return true;
      }

      if (LocalVarTableEntry.PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         ((LocalVarTableEntry)target).setType((String) value);
         return true;
      }

      if (LocalVarTableEntry.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((LocalVarTableEntry)target).setName((String) value);
         return true;
      }

      if (LocalVarTableEntry.PROPERTY_STARTPOS.equalsIgnoreCase(attrName))
      {
         ((LocalVarTableEntry)target).setStartPos((Integer) value);
         return true;
      }

      if (LocalVarTableEntry.PROPERTY_ENDPOS.equalsIgnoreCase(attrName))
      {
         ((LocalVarTableEntry)target).setEndPos((Integer) value);
         return true;
      }
      return super.setValue(target, attrName, value, type);
   }

   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((LocalVarTableEntry) entity).removeYou();
   }
}


