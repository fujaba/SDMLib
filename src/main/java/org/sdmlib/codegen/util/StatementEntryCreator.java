package org.sdmlib.codegen.util;

import java.util.ArrayList;

import org.sdmlib.codegen.StatementEntry;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

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
      if (StatementEntry.PROPERTY_ASSIGNTARGETVARNAME.equalsIgnoreCase(attrName))
      {
         return ((StatementEntry)target).getAssignTargetVarName();
      }

      if (StatementEntry.PROPERTY_TOKENLIST.equalsIgnoreCase(attrName))
      {
         return ((StatementEntry)target).getTokenList();
      }

      if (StatementEntry.PROPERTY_KIND.equalsIgnoreCase(attrName))
      {
         return ((StatementEntry)target).getKind();
      }

      if (StatementEntry.PROPERTY_BODYSTATS.equalsIgnoreCase(attrName))
      {
         return ((StatementEntry)target).getBodyStats();
      }

      if (StatementEntry.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return ((StatementEntry)target).getParent();
      }

      if (StatementEntry.PROPERTY_STARTPOS.equalsIgnoreCase(attrName))
      {
         return ((StatementEntry)target).getStartPos();
      }

      if (StatementEntry.PROPERTY_ENDPOS.equalsIgnoreCase(attrName))
      {
         return ((StatementEntry)target).getEndPos();
      }
      return super.getValue(target, attrName);
   }
   
   @SuppressWarnings("unchecked")
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (StatementEntry.PROPERTY_ASSIGNTARGETVARNAME.equalsIgnoreCase(attrName))
      {
         ((StatementEntry)target).setAssignTargetVarName((String) value);
         return true;
      }

      if (StatementEntry.PROPERTY_TOKENLIST.equalsIgnoreCase(attrName))
      {
         ((StatementEntry)target).setTokenList((ArrayList<String>) value);
         return true;
      }

      if (StatementEntry.PROPERTY_KIND.equalsIgnoreCase(attrName))
      {
         ((StatementEntry)target).setKind((String) value);
         return true;
      }

      if (StatementEntry.PROPERTY_BODYSTATS.equalsIgnoreCase(attrName))
      {
         ((StatementEntry)target).addToBodyStats((StatementEntry) value);
         return true;
      }
      
      if ((StatementEntry.PROPERTY_BODYSTATS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((StatementEntry)target).removeFromBodyStats((StatementEntry) value);
         return true;
      }

      if (StatementEntry.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         ((StatementEntry)target).setParent((StatementEntry) value);
         return true;
      }

      if (StatementEntry.PROPERTY_STARTPOS.equalsIgnoreCase(attrName))
      {
         ((StatementEntry)target).setStartPos(Integer.parseInt(value.toString()));
         return true;
      }

      if (StatementEntry.PROPERTY_ENDPOS.equalsIgnoreCase(attrName))
      {
         ((StatementEntry)target).setEndPos(Integer.parseInt(value.toString()));
         return true;
      }
      return super.setValue(target, attrName, value, type);
   }

   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((StatementEntry) entity).removeYou();
   }
}




