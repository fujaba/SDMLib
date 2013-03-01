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


import org.sdmlib.models.classes.Role.R;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.serialization.json.JsonIdMap;

import org.sdmlib.utils.PropertyChangeInterface;

public class OptionalSubPattern extends Pattern implements PropertyChangeInterface
{
   @Override
   public boolean findNextMatch()
   {
      
      if (matchForward)
      {
         // last time this subpattern has run backward thus we run forward now
         // thus some earlier pattern elements have been rematched.
         // check the subpattern / NAC again
         resetSearch();
         
         if (getTopPattern().getDebugMode() >= R.DEBUG_ON)
         {
            getTopPattern().addLogMsg("// (re)startSubPattern " + getPatternObjectName() + ";");
         }
         
         boolean nacHasMatch = findMatch();
         
         if (getDoAllMatches())
         {
            while (getHasMatch())
            {
               if (getTopPattern().getDebugMode() >= R.DEBUG_ON)
               {
                  getTopPattern().addLogMsg("// " + getPatternObjectName() + " allMatches?");
               }
               
               findMatch();
            }
         }
         
         // next time backtrack
         setMatchForward(false);
         
         // optional sub pattern are always successful
         return true;
      }
      else
      {
         // backtracking, 
         setMatchForward(true);
         
         return false;
      }
   }

   
   //==========================================================================
   
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

      if (PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attribute))
      {
         return getPatternObjectName();
      }

      if (PROPERTY_DOALLMATCHES.equalsIgnoreCase(attribute))
      {
         return getDoAllMatches();
      }

      if (PROPERTY_MATCHFORWARD.equalsIgnoreCase(attribute))
      {
         return getMatchForward();
      }

      if (PROPERTY_CURRENTSUBPATTERN.equalsIgnoreCase(attribute))
      {
         return getCurrentSubPattern();
      }

      if (PROPERTY_DEBUGMODE.equalsIgnoreCase(attrName))
      {
         return getDebugMode();
      }

      if (PROPERTY_ELEMENTS.equalsIgnoreCase(attrName))
      {
         return getElements();
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

      if (PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attrName))
      {
         setPatternObjectName((String) value);
         return true;
      }

      if (PROPERTY_DOALLMATCHES.equalsIgnoreCase(attrName))
      {
         setDoAllMatches((Boolean) value);
         return true;
      }

      if (PROPERTY_MATCHFORWARD.equalsIgnoreCase(attrName))
      {
         setMatchForward((Boolean) value);
         return true;
      }

      if (PROPERTY_CURRENTSUBPATTERN.equalsIgnoreCase(attrName))
      {
         setCurrentSubPattern((Pattern) value);
         return true;
      }

      if (PROPERTY_DEBUGMODE.equalsIgnoreCase(attrName))
      {
         setDebugMode(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_ELEMENTS.equalsIgnoreCase(attrName))
      {
         addToElements((PatternElement) value);
         return true;
      }
      
      if ((PROPERTY_ELEMENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromElements((PatternElement) value);
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
      removeAllFromElements();
      setPattern(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   
   //==========================================================================
   
   public static final String PROPERTY_MATCHFORWARD = "matchForward";
   
   private boolean matchForward = true;

   public boolean getMatchForward()
   {
      return this.matchForward;
   }
   
   public void setMatchForward(boolean value)
   {
      if (this.matchForward != value)
      {
         boolean oldValue = this.matchForward;
         this.matchForward = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MATCHFORWARD, oldValue, value);
      }
   }
   
   public OptionalSubPattern withMatchForward(boolean value)
   {
      setMatchForward(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getDebugMode());
      _.append(" ").append(this.getModifier());
      _.append(" ").append(this.getPatternObjectName());
      return _.substring(1);
   }

}

