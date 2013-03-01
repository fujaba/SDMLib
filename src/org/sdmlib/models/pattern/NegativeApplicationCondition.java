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

import org.sdmlib.utils.PropertyChangeInterface;

public class NegativeApplicationCondition extends Pattern implements PropertyChangeInterface
{
   public NegativeApplicationCondition()
   {
      super();
      setHasMatch(true);
   }
   
   //==========================================================================
   
   @Override
   public boolean findMatch()
   {
      // start matching only if nac is complete 
      if (this.getPattern().getCurrentSubPattern() != null)
      {
         return true;
      }
      else
      {
         return super.findMatch();
      }
   }


   @Override
   public boolean findNextMatch()
   {
      // start matching only if nac is complete 
      if (this.getPattern().getCurrentSubPattern() != null)
      {
         return true;
      }
      else if (getHasMatch())
      {
         // last time this NAC has found a match and thus it was violated and has caused backtracking
         // thus some earlier pattern elements have been rematched.
         // check the NAC again
         resetSearch();
         boolean nacHasMatch = findMatch();
         return ! nacHasMatch;
      }
      else
      {
         // backtracking, the NAC has no alternative choice
         this.setHasMatch(true);
         return false;
      }
   }


   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_HASMATCH.equalsIgnoreCase(attrName))
      {
         return getHasMatch();
      }

      if (PROPERTY_MODIFIER.equalsIgnoreCase(attribute))
      {
         return getModifier();
      }

      if (PROPERTY_DOALLMATCHES.equalsIgnoreCase(attribute))
      {
         return getDoAllMatches();
      }

      if (PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attribute))
      {
         return getPatternObjectName();
      }

      if (PROPERTY_CURRENTSUBPATTERN.equalsIgnoreCase(attribute))
      {
         return getCurrentSubPattern();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_HASMATCH.equalsIgnoreCase(attrName))
      {
         setHasMatch((Boolean) value);
         return true;
      }

      if (PROPERTY_MODIFIER.equalsIgnoreCase(attrName))
      {
         setModifier((String) value);
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

      if (PROPERTY_CURRENTSUBPATTERN.equalsIgnoreCase(attrName))
      {
         setCurrentSubPattern((Pattern) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
}

