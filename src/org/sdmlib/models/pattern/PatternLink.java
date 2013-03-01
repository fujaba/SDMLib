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
import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.models.pattern.creators.PatternLinkSet;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class PatternLink extends PatternElement implements PropertyChangeInterface
{

   @Override
   public boolean findNextMatch()
   {
      if (this.getHostGraphSrcObject() == null)
      {
         // search forward
         this.setHostGraphSrcObject(this.getSrc().getCurrentMatch());
         
         if (hostGraphSrcObject != null)
         {
            SendableEntityCreator creatorClass = this.getPattern().getJsonIdMap().getCreatorClass(hostGraphSrcObject);
            Object value = creatorClass.getValue(hostGraphSrcObject, tgtRoleName);
            if (value != null && value instanceof Collection)
            {
               this.getTgt().setCandidates(new LinkedHashSet((Collection)value));
            }
            else
            {
               this.getTgt().setCandidates(value);
            }
            
            return true;
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


   @Override
   public void resetSearch()
   {
      this.setHostGraphSrcObject(null);
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

      if (PROPERTY_SRCROLENAME.equalsIgnoreCase(attrName))
      {
         return getSrcRoleName();
      }

      if (PROPERTY_TGTROLENAME.equalsIgnoreCase(attrName))
      {
         return getTgtRoleName();
      }

      if (PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         return getTgt();
      }

      if (PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         return getSrc();
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
      if (PROPERTY_SRCROLENAME.equalsIgnoreCase(attrName))
      {
         setSrcRoleName((String) value);
         return true;
      }

      if (PROPERTY_TGTROLENAME.equalsIgnoreCase(attrName))
      {
         setTgtRoleName((String) value);
         return true;
      }

      if (PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         setTgt((PatternObject) value);
         return true;
      }

      if (PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         setSrc((PatternObject) value);
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
      setTgt(null);
      setSrc(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   
   //==========================================================================
   
   public static final String PROPERTY_SRCROLENAME = "srcRoleName";
   
   private String srcRoleName;

   public String getSrcRoleName()
   {
      return this.srcRoleName;
   }
   
   public void setSrcRoleName(String value)
   {
      if ( ! StrUtil.stringEquals(this.srcRoleName, value))
      {
         String oldValue = this.srcRoleName;
         this.srcRoleName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SRCROLENAME, oldValue, value);
      }
   }
   
   public PatternLink withSrcRoleName(String value)
   {
      setSrcRoleName(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_TGTROLENAME = "tgtRoleName";
   
   private String tgtRoleName;

   public String getTgtRoleName()
   {
      return this.tgtRoleName;
   }
   
   public void setTgtRoleName(String value)
   {
      if ( ! StrUtil.stringEquals(this.tgtRoleName, value))
      {
         String oldValue = this.tgtRoleName;
         this.tgtRoleName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TGTROLENAME, oldValue, value);
      }
   }
   
   public PatternLink withTgtRoleName(String value)
   {
      setTgtRoleName(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * PatternLink ----------------------------------- PatternObject
    *              incomming                   tgt
    * </pre>
    */
   
   public static final String PROPERTY_TGT = "tgt";
   
   private PatternObject tgt = null;
   
   public PatternObject getTgt()
   {
      return this.tgt;
   }
   
   public boolean setTgt(PatternObject value)
   {
      boolean changed = false;
      
      if (this.tgt != value)
      {
         PatternObject oldValue = this.tgt;
         
         if (this.tgt != null)
         {
            this.tgt = null;
            oldValue.withoutIncomming(this);
         }
         
         this.tgt = value;
         
         if (value != null)
         {
            value.withIncomming(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TGT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public PatternLink withTgt(PatternObject value)
   {
      setTgt(value);
      return this;
   } 

   
   public static final PatternLinkSet EMPTY_SET = new PatternLinkSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * PatternLink ----------------------------------- PatternObject
    *              outgoing                   src
    * </pre>
    */
   
   public static final String PROPERTY_SRC = "src";
   
   private PatternObject src = null;
   
   public PatternObject getSrc()
   {
      return this.src;
   }
   
   public boolean setSrc(PatternObject value)
   {
      boolean changed = false;
      
      if (this.src != value)
      {
         PatternObject oldValue = this.src;
         
         if (this.src != null)
         {
            this.src = null;
            oldValue.withoutOutgoing(this);
         }
         
         this.src = value;
         
         if (value != null)
         {
            value.withOutgoing(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SRC, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public PatternLink withSrc(PatternObject value)
   {
      setSrc(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_HOSTGRAPHSRCOBJECT = "hostGraphSrcObject";
   
   private Object hostGraphSrcObject;

   public Object getHostGraphSrcObject()
   {
      return this.hostGraphSrcObject;
   }
   
   public void setHostGraphSrcObject(Object value)
   {
      if (this.hostGraphSrcObject != value)
      {
         Object oldValue = this.hostGraphSrcObject;
         this.hostGraphSrcObject = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HOSTGRAPHSRCOBJECT, oldValue, value);
      }
   }
   
   public PatternLink withHostGraphSrcObject(Object value)
   {
      setHostGraphSrcObject(value);
      return this;
   } 
}

