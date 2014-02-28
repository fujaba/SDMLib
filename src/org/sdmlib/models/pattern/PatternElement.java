/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.models.pattern;

import java.beans.PropertyChangeSupport;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.pattern.creators.PatternElementSet;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;
import java.beans.PropertyChangeListener;

public class PatternElement<PEC> implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         return getPattern();
      }

      if (PROPERTY_MODIFIER.equalsIgnoreCase(attribute))
      {
         return getModifier();
      }

      if (PROPERTY_HASMATCH.equalsIgnoreCase(attribute))
      {
         return getHasMatch();
      }

      if (PROPERTY_DOALLMATCHES.equalsIgnoreCase(attribute))
      {
         return getDoAllMatches();
      }

      if (PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attribute))
      {
         return getPatternObjectName();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         setPattern((Pattern) value);
         return true;
      }

      if (PROPERTY_MODIFIER.equalsIgnoreCase(attrName))
      {
         setModifier((String) value);
         return true;
      }

      if (PROPERTY_HASMATCH.equalsIgnoreCase(attrName))
      {
         setHasMatch((Boolean) value);
         return true;
      }

      if (PROPERTY_DOALLMATCHES.equalsIgnoreCase(attrName))
      {
         setDoAllMatches((Boolean) value);
         return true;
      }

      if (PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attrName))
      {
         setPatternObjectName((String) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setPattern(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   public static final PatternElementSet EMPTY_SET = new PatternElementSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * PatternElement ----------------------------------- Pattern
    *              elements                   pattern
    * </pre>
    */
   
   public static final String PROPERTY_PATTERN = "pattern";
   
   private Pattern pattern = null;
   
   public Pattern getPattern()
   {
      return this.pattern;
   }
   
   public boolean setPattern(Pattern value)
   {
      boolean changed = false;
      
      if (this.pattern != value)
      {
         Pattern oldValue = this.pattern;
         
         if (this.pattern != null)
         {
            this.pattern = null;
            oldValue.withoutElements(this);
         }
         
         this.pattern = value;
         
         if (value != null)
         {
            value.withElements(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PATTERN, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public PEC withPattern(Pattern value)
   {
      setPattern(value);
      return (PEC) this;
   }
   
   public Pattern getTopPattern()
   {
      PatternElement result = this;
      
      while (result.getPattern() != null)
      {
         result = result.getPattern();
      }
      
      return (Pattern) result;
   }


   public boolean findNextMatch()
   {
      return false;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_MODIFIER = "modifier";
   
   private String modifier;

   public String getModifier()
   {
      return this.modifier;
   }
   
   public void setModifier(String value)
   {
      if ( ! StrUtil.stringEquals(this.modifier, value))
      {
         String oldValue = this.modifier;
         this.modifier = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MODIFIER, oldValue, value);
      }
   }
   
   public PatternElement withModifier(String value)
   {
      setModifier(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_HASMATCH = "hasMatch";
   
   protected boolean hasMatch = false;

   public boolean getHasMatch()
   {
      return this.hasMatch;
   }
   
   public void setHasMatch(boolean value)
   {
      if (this.hasMatch != value)
      {
         boolean oldValue = this.hasMatch;
         this.hasMatch = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HASMATCH, oldValue, value);
      }
   }
   
   public PatternElement withHasMatch(boolean value)
   {
      setHasMatch(value);
      return this;
   }


   public void resetSearch()
   {
      // please override
   } 

   

   //==========================================================================
   
   public static final String PROPERTY_DOALLMATCHES = "doAllMatches";
   
   private boolean doAllMatches;

   public boolean getDoAllMatches()
   {
      return this.doAllMatches;
   }
   
   public void setDoAllMatches(boolean value)
   {
      if (this.doAllMatches != value)
      {
         boolean oldValue = this.doAllMatches;
         this.doAllMatches = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_DOALLMATCHES, oldValue, value);
      }
   }
   
   public PatternElement withDoAllMatches(boolean value)
   {
      setDoAllMatches(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_PATTERNOBJECTNAME = "patternObjectName";
   
   private String patternObjectName;

   public String getPatternObjectName()
   {
      return this.patternObjectName;
   }
   
   public void setPatternObjectName(String value)
   {
      if ( ! StrUtil.stringEquals(this.patternObjectName, value))
      {
         String oldValue = this.patternObjectName;
         this.patternObjectName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PATTERNOBJECTNAME, oldValue, value);
      }
   }
   
   public PEC withPatternObjectName(String value)
   {
      setPatternObjectName(value);
      return (PEC) this;
   } 
   
   
   public String valueSetString(Object value)
   {
      StringList valueList = new StringList();
      for (Object elem : (Iterable) value)
      {
         valueList.add(dumpHostGraphObject(elem));
      }
      String valueSet = "{" + valueList.concat(", ") + "}";
      return valueSet;
   }

   public String dumpHostGraphObject(Object hostGraphObject)
   {
      return getTopPattern().getJsonIdMap().getId(hostGraphObject) + " " + hostGraphObject.toString();
   }


   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getModifier());
      _.append(" ").append(this.getPatternObjectName());
      return _.substring(1);
   }


   public Pattern createPattern()
   {
      Pattern value = new Pattern();
      withPattern(value);
      return value;
   } 
}

