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

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class PatternLinkCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
         PatternLink.PROPERTY_TGTROLENAME,
         PatternLink.PROPERTY_HOSTGRAPHSRCOBJECT,
         PatternLink.PROPERTY_SRC,
         PatternLink.PROPERTY_TGT,
         PatternElement.PROPERTY_MODIFIER,
         PatternElement.PROPERTY_HASMATCH,
         PatternElement.PROPERTY_PATTERNOBJECTNAME,
         PatternElement.PROPERTY_DOALLMATCHES,
         PatternElement.PROPERTY_PATTERN,
   };


   @Override
   public String[] getProperties()
   {
      return properties;
   }


   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new PatternLink();
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

      if (PatternLink.PROPERTY_TGTROLENAME.equalsIgnoreCase(attribute))
      {
         return ((PatternLink) target).getTgtRoleName();
      }

      if (PatternLink.PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attribute))
      {
         return ((PatternLink) target).getHostGraphSrcObject();
      }

      if (PatternLink.PROPERTY_SRC.equalsIgnoreCase(attribute))
      {
         return ((PatternLink) target).getSrc();
      }

      if (PatternLink.PROPERTY_TGT.equalsIgnoreCase(attribute))
      {
         return ((PatternLink) target).getTgt();
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

      if (PatternLink.PROPERTY_PATTERN.equalsIgnoreCase(attribute))
      {
         return ((PatternLink) target).getPattern();
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

      if (PatternLink.PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attrName))
      {
         ((PatternLink) target).setHostGraphSrcObject((Object) value);
         return true;
      }

      if (PatternLink.PROPERTY_TGTROLENAME.equalsIgnoreCase(attrName))
      {
         ((PatternLink) target).setTgtRoleName((String) value);
         return true;
      }

      if (PatternLink.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         ((PatternLink) target).setSrc((PatternObject) value);
         return true;
      }

      if (PatternLink.PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         ((PatternLink) target).setTgt((PatternObject) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (PatternLink.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         ((PatternLink) target).setPattern((Pattern) value);
         return true;
      }

      return false;
   }


   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.models.pattern.util.CreatorCreator.createIdMap(sessionID);
   }


   // ==========================================================================
   public void removeObject(Object entity)
   {
      ((PatternLink) entity).removeYou();
   }
}
