/*
   Copyright (c) 2013 zuendorf 
   
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
import org.sdmlib.models.pattern.util.RuleApplicationSet;
import org.sdmlib.serialization.PropertyChangeInterface;

public class RuleApplication implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_DESCRIPTION.equalsIgnoreCase(attrName))
      {
         return getDescription();
      }

      if (PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         return getSrc();
      }

      if (PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         return getTgt();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_DESCRIPTION.equalsIgnoreCase(attrName))
      {
         setDescription((String) value);
         return true;
      }

      if (PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         setSrc((ReachableState) value);
         return true;
      }

      if (PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         setTgt((ReachableState) value);
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
      setSrc(null);
      setTgt(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_DESCRIPTION = "description";
   
   private String description;

   public String getDescription()
   {
      return this.description;
   }
   
   public void setDescription(String value)
   {
      if ( ! StrUtil.stringEquals(this.description, value))
      {
         String oldValue = this.description;
         this.description = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_DESCRIPTION, oldValue, value);
      }
   }
   
   public RuleApplication withDescription(String value)
   {
      setDescription(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getDescription());
      return _.substring(1);
   }


   
   public static final RuleApplicationSet EMPTY_SET = new RuleApplicationSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * RuleApplication ----------------------------------- ReachableState
    *              ruleapplications                   src
    * </pre>
    */
   
   public static final String PROPERTY_SRC = "src";
   
   private ReachableState src = null;
   
   public ReachableState getSrc()
   {
      return this.src;
   }
   
   public boolean setSrc(ReachableState value)
   {
      boolean changed = false;
      
      if (this.src != value)
      {
         ReachableState oldValue = this.src;
         
         if (this.src != null)
         {
            this.src = null;
            oldValue.withoutRuleapplications(this);
         }
         
         this.src = value;
         
         if (value != null)
         {
            value.withRuleapplications(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SRC, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public RuleApplication withSrc(ReachableState value)
   {
      setSrc(value);
      return this;
   } 
   
   public ReachableState createSrc()
   {
      ReachableState value = new ReachableState();
      withSrc(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * RuleApplication ----------------------------------- ReachableState
    *              resultOf                   tgt
    * </pre>
    */
   
   public static final String PROPERTY_TGT = "tgt";
   
   private ReachableState tgt = null;
   
   public ReachableState getTgt()
   {
      return this.tgt;
   }
   
   public boolean setTgt(ReachableState value)
   {
      boolean changed = false;
      
      if (this.tgt != value)
      {
         ReachableState oldValue = this.tgt;
         
         if (this.tgt != null)
         {
            this.tgt = null;
            oldValue.withoutResultOf(this);
         }
         
         this.tgt = value;
         
         if (value != null)
         {
            value.withResultOf(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TGT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public RuleApplication withTgt(ReachableState value)
   {
      setTgt(value);
      return this;
   } 
   
   public ReachableState createTgt()
   {
      ReachableState value = new ReachableState();
      withTgt(value);
      return value;
   } 
}

