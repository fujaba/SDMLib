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

import org.sdmlib.StrUtil;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.pattern.util.PatternElementSet;
import org.sdmlib.serialization.PropertyChangeInterface;

public class PatternElement<PEC> implements PropertyChangeInterface
{
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   public static final PatternElementSet EMPTY_SET = new PatternElementSet();
   public static final String PROPERTY_PATTERN = "pattern";
   public static final String PROPERTY_MODIFIER = "modifier";
   public static final String PROPERTY_HASMATCH = "hasMatch";
   public static final String PROPERTY_DOALLMATCHES = "doAllMatches";
   public static final String PROPERTY_PATTERNOBJECTNAME = "patternObjectName";
   
   private String patternObjectName;
   private boolean doAllMatches;
   protected boolean hasMatch = false;
   private String modifier;
   private Pattern<PatternElement<?>> pattern = null;
  
   @Override
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
   
   /********************************************************************
    * <pre>
    *              many                       one
    * PatternElement ----------------------------------- Pattern
    *              elements                   pattern
    * </pre>
    */
   public Pattern<PatternElement<?>> getPattern()
   {
      return this.pattern;
   }
   
   public boolean setPattern(Pattern<PatternElement<?>> value)
   {
      boolean changed = false;
      
      if (this.pattern != value)
      {
         Pattern<?> oldValue = this.pattern;
         
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
   
   public PEC withPattern(Pattern<PatternElement<?>> value)
   {
      setPattern(value);
      return (PEC)this;
   }
   
   public Pattern<PatternElement<?>> getTopPattern()
   {
      Pattern<PatternElement<?>> result = this.getPattern();
      if(this instanceof Pattern<?>){
         result = (Pattern<PatternElement<?>>)this;
      }
      while (result.getPattern() != null)
      {
         result = result.getPattern();
      }
      
      return result;
   }


   public boolean findNextMatch(){
      return false;
   }
   
   //==========================================================================
   
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
   
   public PEC withModifier(String value)
   {
      setModifier(value);
      return (PEC) this;
   } 
   
   //==========================================================================
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


   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getModifier());
      _.append(" ").append(this.getPatternObjectName());
      return _.substring(1);
   }


   public Pattern createPatternNew()
   {
      Pattern value = new Pattern();
      withPattern(value);
      return value;
   } 

   public Object createPattern()
   {
      Pattern value = new Pattern();
      withPattern(value);
      return value;
   } 
}
