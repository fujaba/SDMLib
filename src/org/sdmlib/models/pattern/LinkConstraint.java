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

import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;

public class LinkConstraint extends PatternLink implements PropertyChangeInterface
{

   
   //==========================================================================
   
   @Override
   public boolean findNextMatch()
   {
      if (Pattern.CREATE.equals(getModifier()))
      {
         if ( ! this.getPattern().getHasMatch())
         {
            return false;
         }
         
         if (this.getHasMatch())
         {
            this.setHasMatch(false);
            return false;
         }
         else
         {
            Object srcObj = this.getSrc().getCurrentMatch();
            SendableEntityCreator creatorClass = this.getPattern().getJsonIdMap().getCreatorClass(srcObj);
            creatorClass.setValue(srcObj, this.getTgtRoleName(), this.getTgt().getCurrentMatch(), "");
            this.setHasMatch(true);
            return true;
         }
      }
      
      // real search
      if (this.getHostGraphSrcObject() == null)
      {
         // search forward
         this.setHostGraphSrcObject(this.getSrc().getCurrentMatch());
         
         if (getHostGraphSrcObject() != null)
         {
            SendableEntityCreator creatorClass = this.getPattern().getJsonIdMap().getCreatorClass(getHostGraphSrcObject());
            Object value = creatorClass.getValue(getHostGraphSrcObject(), getTgtRoleName());
            Object hostGraphTgtObject = this.getTgt().getCurrentMatch();
            
            if (hostGraphTgtObject != null && hostGraphTgtObject == value)
            {
               return true;
            }
            else
            {
               this.setHostGraphSrcObject(null);
               return false;
            }
         }
         else 
         {
            return false;
         }
      }
      else
      {
         this.setHostGraphSrcObject(null);
      
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

      if (PROPERTY_TGTROLENAME.equalsIgnoreCase(attrName))
      {
         return getTgtRoleName();
      }

      if (PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attrName))
      {
         return getHostGraphSrcObject();
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
      
      return super.get(attrName);
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_TGTROLENAME.equalsIgnoreCase(attrName))
      {
         setTgtRoleName((String) value);
         return true;
      }

      if (PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attrName))
      {
         setHostGraphSrcObject((Object) value);
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

      return super.set(attrName, value);
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

