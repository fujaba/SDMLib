package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;

import de.uniks.networkparser.json.JsonIdMap;

public class AttributeConstraintCreator extends PatternElementCreator
{
   private final String[] properties = new String[]
   {
      AttributeConstraint.PROPERTY_ATTRNAME,
      AttributeConstraint.PROPERTY_TGTVALUE,
      AttributeConstraint.PROPERTY_HOSTGRAPHSRCOBJECT,
      AttributeConstraint.PROPERTY_SRC,
      PatternElement.PROPERTY_MODIFIER,
      PatternElement.PROPERTY_PATTERNOBJECTNAME,
      PatternElement.PROPERTY_HASMATCH,
      PatternElement.PROPERTY_DOALLMATCHES,
      PatternElement.PROPERTY_PATTERN,
      AttributeConstraint.PROPERTY_CMPOP,
      AttributeConstraint.PROPERTY_UPPERTGTVALUE,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new AttributeConstraint();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (AttributeConstraint.PROPERTY_ATTRNAME.equalsIgnoreCase(attribute))
      {
         return ((AttributeConstraint)target).getAttrName();
      }

      if (AttributeConstraint.PROPERTY_TGTVALUE.equalsIgnoreCase(attribute))
      {
         return ((AttributeConstraint)target).getTgtValue();
      }

      if (AttributeConstraint.PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attribute))
      {
         return ((AttributeConstraint)target).getHostGraphSrcObject();
      }

      if (AttributeConstraint.PROPERTY_SRC.equalsIgnoreCase(attribute))
      {
         return ((AttributeConstraint)target).getSrc();
      }

      if (AttributeConstraint.PROPERTY_CMPOP.equalsIgnoreCase(attribute))
      {
         return ((AttributeConstraint)target).getCmpOp();
      }

      if (AttributeConstraint.PROPERTY_UPPERTGTVALUE.equalsIgnoreCase(attribute))
      {
         return ((AttributeConstraint)target).getUpperTgtValue();
      }

      if (AttributeConstraint.PROPERTY_PATTERN.equalsIgnoreCase(attribute))
      {
         return ((AttributeConstraint) target).getPattern();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (AttributeConstraint.PROPERTY_ATTRNAME.equalsIgnoreCase(attrName))
      {
         ((AttributeConstraint)target).setAttrName((String) value);
         return true;
      }

      if (AttributeConstraint.PROPERTY_TGTVALUE.equalsIgnoreCase(attrName))
      {
         ((AttributeConstraint)target).setTgtValue(value);
         return true;
      }

      if (AttributeConstraint.PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attrName))
      {
         ((AttributeConstraint)target).setHostGraphSrcObject((Object) value);
         return true;
      }

      if (AttributeConstraint.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         ((AttributeConstraint)target).setSrc((PatternObject<?,?>) value);
         return true;
      }

      if (AttributeConstraint.PROPERTY_CMPOP.equalsIgnoreCase(attrName))
      {
         ((AttributeConstraint)target).setCmpOp((String) value);
         return true;
      }

      if (AttributeConstraint.PROPERTY_UPPERTGTVALUE.equalsIgnoreCase(attrName))
      {
         ((AttributeConstraint)target).setUpperTgtValue((Object) value);
         return true;
      }

      if (AttributeConstraint.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         ((AttributeConstraint) target).setPattern((Pattern<?>) value);
         return true;
      }
      return super.setValue(target, attrName, value, type);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((AttributeConstraint) entity).removeYou();
   }
}






