package org.sdmlib.codegen.util;

import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.serialization.EntityFactory;

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
      SymTabEntry.PROPERTY_ANNOTATIONS,
      SymTabEntry.PROPERTY_PRECOMMENTSTARTPOS,
      SymTabEntry.PROPERTY_PRECOMMENTENDPOS,
      SymTabEntry.PROPERTY_ANNOTATIONSSTARTPOS,
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
      if (SymTabEntry.PROPERTY_MODIFIERS.equalsIgnoreCase(attrName))
      {
         return ((SymTabEntry)target).getModifiers();
      }

      if (SymTabEntry.PROPERTY_ENDPOS.equalsIgnoreCase(attrName))
      {
         return ((SymTabEntry)target).getEndPos();
      }

      if (SymTabEntry.PROPERTY_BODYSTARTPOS.equalsIgnoreCase(attrName))
      {
         return ((SymTabEntry)target).getBodyStartPos();
      }

      if (SymTabEntry.PROPERTY_STARTPOS.equalsIgnoreCase(attrName))
      {
         return ((SymTabEntry)target).getStartPos();
      }

      if (SymTabEntry.PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         return ((SymTabEntry)target).getType();
      }

      if (SymTabEntry.PROPERTY_MEMBERNAME.equalsIgnoreCase(attrName))
      {
         return ((SymTabEntry)target).getMemberName();
      }

      if (SymTabEntry.PROPERTY_KIND.equalsIgnoreCase(attrName))
      {
         return ((SymTabEntry)target).getKind();
      }

      if (SymTabEntry.PROPERTY_ANNOTATIONS.equalsIgnoreCase(attrName))
      {
         return ((SymTabEntry) target).getAnnotations();
      }

      if (SymTabEntry.PROPERTY_PRECOMMENTSTARTPOS.equalsIgnoreCase(attrName))
      {
         return ((SymTabEntry) target).getPreCommentStartPos();
      }

      if (SymTabEntry.PROPERTY_PRECOMMENTENDPOS.equalsIgnoreCase(attrName))
      {
         return ((SymTabEntry) target).getPreCommentEndPos();
      }

      if (SymTabEntry.PROPERTY_ANNOTATIONSSTARTPOS.equalsIgnoreCase(attrName))
      {
         return ((SymTabEntry) target).getAnnotationsStartPos();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (SymTabEntry.PROPERTY_MODIFIERS.equalsIgnoreCase(attrName))
      {
         ((SymTabEntry)target).setModifiers((String) value);
         return true;
      }

      if (SymTabEntry.PROPERTY_ENDPOS.equalsIgnoreCase(attrName))
      {
         ((SymTabEntry)target).setEndPos((Integer) value);
         return true;
      }

      if (SymTabEntry.PROPERTY_BODYSTARTPOS.equalsIgnoreCase(attrName))
      {
         ((SymTabEntry)target).setBodyStartPos((Integer) value);
         return true;
      }

      if (SymTabEntry.PROPERTY_STARTPOS.equalsIgnoreCase(attrName))
      {
         ((SymTabEntry)target).setStartPos((Integer) value);
         return true;
      }

      if (SymTabEntry.PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         ((SymTabEntry)target).setType((String) value);
         return true;
      }

      if (SymTabEntry.PROPERTY_MEMBERNAME.equalsIgnoreCase(attrName))
      {
         ((SymTabEntry)target).setMemberName((String) value);
         return true;
      }

      if (SymTabEntry.PROPERTY_KIND.equalsIgnoreCase(attrName))
      {
         ((SymTabEntry)target).setKind((String) value);
         return true;
      }

      if (SymTabEntry.PROPERTY_ANNOTATIONS.equalsIgnoreCase(attrName))
      {
         ((SymTabEntry) target).withAnnotations((String) value);
         return true;
      }

      if (SymTabEntry.PROPERTY_PRECOMMENTSTARTPOS.equalsIgnoreCase(attrName))
      {
         ((SymTabEntry) target).withPreCommentStartPos(Integer.parseInt(value.toString()));
         return true;
      }

      if (SymTabEntry.PROPERTY_PRECOMMENTENDPOS.equalsIgnoreCase(attrName))
      {
         ((SymTabEntry) target).withPreCommentEndPos(Integer.parseInt(value.toString()));
         return true;
      }

      if (SymTabEntry.PROPERTY_ANNOTATIONSSTARTPOS.equalsIgnoreCase(attrName))
      {
         ((SymTabEntry) target).withAnnotationsStartPos(Integer.parseInt(value.toString()));
         return true;
      }
      return super.setValue(target, attrName, value, type);
   }
   
  
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((SymTabEntry) entity).removeYou();
   }
}


