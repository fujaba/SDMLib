/*
   Copyright (c) 2016 christoph
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.models.pattern.util;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.models.pattern.AttributeConstraint;
import de.uniks.networkparser.IdMap;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;

public class AttributeConstraintCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      AttributeConstraint.PROPERTY_ATTRNAME,
      AttributeConstraint.PROPERTY_TGTVALUE,
      AttributeConstraint.PROPERTY_UPPERTGTVALUE,
      AttributeConstraint.PROPERTY_CMPOP,
      AttributeConstraint.PROPERTY_HOSTGRAPHSRCOBJECT,
      PatternElement.PROPERTY_MODIFIER,
      PatternElement.PROPERTY_HASMATCH,
      PatternElement.PROPERTY_PATTERNOBJECTNAME,
      PatternElement.PROPERTY_DOALLMATCHES,
      PatternElement.PROPERTY_PATTERN,
      AttributeConstraint.PROPERTY_SRC,
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
         return ((AttributeConstraint) target).getAttrName();
      }

      if (AttributeConstraint.PROPERTY_TGTVALUE.equalsIgnoreCase(attribute))
      {
         return ((AttributeConstraint) target).getTgtValue();
      }

      if (AttributeConstraint.PROPERTY_UPPERTGTVALUE.equalsIgnoreCase(attribute))
      {
         return ((AttributeConstraint) target).getUpperTgtValue();
      }

      if (AttributeConstraint.PROPERTY_CMPOP.equalsIgnoreCase(attribute))
      {
         return ((AttributeConstraint) target).getCmpOp();
      }

      if (AttributeConstraint.PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attribute))
      {
         return ((AttributeConstraint) target).getHostGraphSrcObject();
      }

      if (PatternElement.PROPERTY_MODIFIER.equalsIgnoreCase(attribute))
      {
         return ((PatternElement) target).getModifier();
      }

      if (PatternElement.PROPERTY_HASMATCH.equalsIgnoreCase(attribute))
      {
         return ((PatternElement) target).isHasMatch();
      }

      if (PatternElement.PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attribute))
      {
         return ((PatternElement) target).getPatternObjectName();
      }

      if (PatternElement.PROPERTY_DOALLMATCHES.equalsIgnoreCase(attribute))
      {
         return ((PatternElement) target).isDoAllMatches();
      }

      if (AttributeConstraint.PROPERTY_PATTERN.equalsIgnoreCase(attribute))
      {
         return ((AttributeConstraint) target).getPattern();
      }

      if (AttributeConstraint.PROPERTY_SRC.equalsIgnoreCase(attribute))
      {
         return ((AttributeConstraint) target).getSrc();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (PatternElement.PROPERTY_DOALLMATCHES.equalsIgnoreCase(attrName))
      {
         ((PatternElement) target).setDoAllMatches((Boolean) value);
         return true;
      }

      if (PatternElement.PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attrName))
      {
         ((PatternElement) target).setPatternObjectName((String) value);
         return true;
      }

      if (PatternElement.PROPERTY_HASMATCH.equalsIgnoreCase(attrName))
      {
         ((PatternElement) target).setHasMatch((Boolean) value);
         return true;
      }

      if (PatternElement.PROPERTY_MODIFIER.equalsIgnoreCase(attrName))
      {
         ((PatternElement) target).setModifier((String) value);
         return true;
      }

      if (AttributeConstraint.PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attrName))
      {
         ((AttributeConstraint) target).setHostGraphSrcObject((Object) value);
         return true;
      }

      if (AttributeConstraint.PROPERTY_CMPOP.equalsIgnoreCase(attrName))
      {
         ((AttributeConstraint) target).setCmpOp((String) value);
         return true;
      }

      if (AttributeConstraint.PROPERTY_UPPERTGTVALUE.equalsIgnoreCase(attrName))
      {
         ((AttributeConstraint) target).setUpperTgtValue((Object) value);
         return true;
      }

      if (AttributeConstraint.PROPERTY_TGTVALUE.equalsIgnoreCase(attrName))
      {
         ((AttributeConstraint) target).setTgtValue((Object) value);
         return true;
      }

      if (AttributeConstraint.PROPERTY_ATTRNAME.equalsIgnoreCase(attrName))
      {
         ((AttributeConstraint) target).setAttrName((String) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (AttributeConstraint.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         ((AttributeConstraint) target).setPattern((Pattern) value);
         return true;
      }

      if (AttributeConstraint.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         ((AttributeConstraint) target).setSrc((PatternObject) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.models.pattern.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((AttributeConstraint) entity).removeYou();
   }
}
