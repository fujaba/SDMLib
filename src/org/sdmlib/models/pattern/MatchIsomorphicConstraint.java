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

import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.LinkedHashSet;

public class MatchIsomorphicConstraint extends PatternElement implements PropertyChangeInterface
{
   @Override
   public boolean findNextMatch()
   {
      if ( ! this.getPattern().getHasMatch())
      {
         return false;
      }
      
      if (this.getHasMatch())
      {
         // backtracking
         this.setHasMatch(false);
         return false;
      }
      else
      {
         // forward 
         LinkedHashSet<Object> usedObjects = new LinkedHashSet<Object>();

         for (PatternElement patElem : this.getPattern().getElements())
         {
            if (patElem instanceof PatternObject)
            {
               Object currentMatch = ((PatternObject) patElem).getCurrentMatch();
               if (usedObjects.contains(currentMatch))
               {
                  return false;
               }
               else
               {
                  usedObjects.add(currentMatch);
               }
            }
         }
         
         // no double match, match is isomorphic
         this.setHasMatch(true);
         
         return true;
      }
   }
   
   

   
   //==========================================================================
   
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

      if (PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return getName();
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

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
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
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }
}

