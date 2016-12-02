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

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.MatchOtherThen;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class PatternObjectCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      PatternObject.PROPERTY_CURRENTMATCH,
      PatternObject.PROPERTY_CANDIDATES,
      PatternElement.PROPERTY_MODIFIER,
      PatternElement.PROPERTY_HASMATCH,
      PatternElement.PROPERTY_PATTERNOBJECTNAME,
      PatternElement.PROPERTY_DOALLMATCHES,
      PatternElement.PROPERTY_PATTERN,
      PatternObject.PROPERTY_ATTRCONSTRAINTS,
      PatternObject.PROPERTY_DESTROYELEM,
      PatternObject.PROPERTY_CARDCONSTRAINTS,
      PatternObject.PROPERTY_MATCHOTHERTHEN,
      PatternObject.PROPERTY_EXCLUDERS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new PatternObject();
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

      if (PatternObject.PROPERTY_CURRENTMATCH.equalsIgnoreCase(attribute))
      {
         return ((PatternObject) target).getCurrentMatch();
      }

      if (PatternObject.PROPERTY_CANDIDATES.equalsIgnoreCase(attribute))
      {
         return ((PatternObject) target).getCandidates();
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

      if (PatternObject.PROPERTY_PATTERN.equalsIgnoreCase(attribute))
      {
         return ((PatternObject) target).getPattern();
      }

      if (PatternObject.PROPERTY_ATTRCONSTRAINTS.equalsIgnoreCase(attribute))
      {
         return ((PatternObject) target).getAttrConstraints();
      }

      if (PatternObject.PROPERTY_DESTROYELEM.equalsIgnoreCase(attribute))
      {
         return ((PatternObject) target).getDestroyElem();
      }

      if (PatternObject.PROPERTY_CARDCONSTRAINTS.equalsIgnoreCase(attribute))
      {
         return ((PatternObject) target).getCardConstraints();
      }

      if (PatternObject.PROPERTY_MATCHOTHERTHEN.equalsIgnoreCase(attribute))
      {
         return ((PatternObject) target).getMatchOtherThen();
      }

      if (PatternObject.PROPERTY_EXCLUDERS.equalsIgnoreCase(attribute))
      {
         return ((PatternObject) target).getExcluders();
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

      if (PatternObject.PROPERTY_CANDIDATES.equalsIgnoreCase(attrName))
      {
         ((PatternObject) target).setCandidates((Object) value);
         return true;
      }

      if (PatternObject.PROPERTY_CURRENTMATCH.equalsIgnoreCase(attrName))
      {
         ((PatternObject) target).setCurrentMatch((Object) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (PatternObject.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         ((PatternObject) target).setPattern((Pattern) value);
         return true;
      }

      if (PatternObject.PROPERTY_ATTRCONSTRAINTS.equalsIgnoreCase(attrName))
      {
         ((PatternObject) target).withAttrConstraints((AttributeConstraint) value);
         return true;
      }
      
      if ((PatternObject.PROPERTY_ATTRCONSTRAINTS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PatternObject) target).withoutAttrConstraints((AttributeConstraint) value);
         return true;
      }

      if (PatternObject.PROPERTY_DESTROYELEM.equalsIgnoreCase(attrName))
      {
         ((PatternObject) target).setDestroyElem((DestroyObjectElem) value);
         return true;
      }

      if (PatternObject.PROPERTY_CARDCONSTRAINTS.equalsIgnoreCase(attrName))
      {
         ((PatternObject) target).withCardConstraints((CardinalityConstraint) value);
         return true;
      }
      
      if ((PatternObject.PROPERTY_CARDCONSTRAINTS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PatternObject) target).withoutCardConstraints((CardinalityConstraint) value);
         return true;
      }

      if (PatternObject.PROPERTY_MATCHOTHERTHEN.equalsIgnoreCase(attrName))
      {
         ((PatternObject) target).withMatchOtherThen((MatchOtherThen) value);
         return true;
      }
      
      if ((PatternObject.PROPERTY_MATCHOTHERTHEN + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PatternObject) target).withoutMatchOtherThen((MatchOtherThen) value);
         return true;
      }

      if (PatternObject.PROPERTY_EXCLUDERS.equalsIgnoreCase(attrName))
      {
         ((PatternObject) target).withExcluders((MatchOtherThen) value);
         return true;
      }
      
      if ((PatternObject.PROPERTY_EXCLUDERS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PatternObject) target).withoutExcluders((MatchOtherThen) value);
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
      ((PatternObject) entity).removeYou();
   }
}
