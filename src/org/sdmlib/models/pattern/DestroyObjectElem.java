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

import org.sdmlib.models.classes.Role.R;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.utils.PropertyChangeInterface;

public class DestroyObjectElem extends PatternElement implements PropertyChangeInterface
{
   
   
   //==========================================================================
   
   @Override
   public boolean findNextMatch()
   {
      if (this.getHasMatch())
      {
         // backtrack
         setHasMatch(false);
         
         return false;
      }
      else 
      {
         // forward
         if (this.getPattern().getHasMatch())
         {
            setHasMatch(true);
            
            Object currentMatch = this.getPatternObject().getCurrentMatch();
            
            EntityFactory creatorClass = (EntityFactory) this.getPattern().getJsonIdMap().getCreatorClass(currentMatch);
            
            creatorClass.removeObject(currentMatch);
            
            if (getTopPattern().getDebugMode() >= R.DEBUG_ON)
            {
               getTopPattern().addLogMsg(getPatternObject().getPatternObjectName() + ".removeYou(); // kill: " + dumpHostGraphObject(currentMatch));
            }
            
            return true;
         }
         else 
         {
            return false;
         }
      }
      
   }


   @Override
   public void resetSearch()
   {
      setHasMatch(false);
   }


   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_MODIFIER.equalsIgnoreCase(attribute))
      {
         return getModifier();
      }

      if (PROPERTY_HASMATCH.equalsIgnoreCase(attribute))
      {
         return getHasMatch();
      }

      if (PROPERTY_PATTERNOBJECT.equalsIgnoreCase(attrName))
      {
         return getPatternObject();
      }

      if (PROPERTY_DOALLMATCHES.equalsIgnoreCase(attribute))
      {
         return getDoAllMatches();
      }

      if (PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attribute))
      {
         return getPatternObjectName();
      }

      if (PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         return getPattern();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
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

      if (PROPERTY_PATTERNOBJECT.equalsIgnoreCase(attrName))
      {
         setPatternObject((PatternObject) value);
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

      if (PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         setPattern((Pattern) value);
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
      setPatternObject(null);
      setPattern(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * DestroyObjectElem ----------------------------------- PatternObject
    *              destroyElem                   patternObject
    * </pre>
    */
   
   public static final String PROPERTY_PATTERNOBJECT = "patternObject";
   
   private PatternObject patternObject = null;
   
   public PatternObject getPatternObject()
   {
      return this.patternObject;
   }
   
   public boolean setPatternObject(PatternObject value)
   {
      boolean changed = false;
      
      if (this.patternObject != value)
      {
         PatternObject oldValue = this.patternObject;
         
         if (this.patternObject != null)
         {
            this.patternObject = null;
            oldValue.setDestroyElem(null);
         }
         
         this.patternObject = value;
         
         if (value != null)
         {
            value.withDestroyElem(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PATTERNOBJECT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public DestroyObjectElem withPatternObject(PatternObject value)
   {
      setPatternObject(value);
      return this;
   } 
   
   public PatternObject createPatternObject()
   {
      PatternObject value = new PatternObject();
      withPatternObject(value);
      return value;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getModifier());
      _.append(" ").append(this.getPatternObjectName());
      return _.substring(1);
   }

}

