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
   
package org.sdmlib.models.pattern;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

import de.uniks.networkparser.EntityUtil;
import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../src/PatternModelCodeGen.java'>PatternModelCodeGen.java</a>
 * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/PatternModelCodeGen.java'>PatternModelCodeGen.java</a>
 */
   public  class RuleApplication implements SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = null;
   
   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(listener);
   	return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(propertyName, listener);
   	return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners.removePropertyChangeListener(listener);
   	}
   	listeners.removePropertyChangeListener(listener);
   	return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(propertyName, listener);
   	}
   	return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      setRule(null);
      setSrc(null);
      setTgt(null);
      firePropertyChange("REMOVE_YOU", this, null);
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
      if ( ! EntityUtil.stringEquals(this.description, value)) {
      
         String oldValue = this.description;
         this.description = value;
         this.firePropertyChange(PROPERTY_DESCRIPTION, oldValue, value);
      }
   }
   
   public RuleApplication withDescription(String value)
   {
      setDescription(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getDescription());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       one
    * RuleApplication ----------------------------------- Pattern
    *              ruleapplication                   rule
    * </pre>
    */
   
   public static final String PROPERTY_RULE = "rule";

   private Pattern rule = null;

   public Pattern getRule()
   {
      return this.rule;
   }

   public boolean setRule(Pattern value)
   {
      boolean changed = false;
      
      if (this.rule != value)
      {
         Pattern oldValue = this.rule;
         
         
         this.rule = value;
         
         
         firePropertyChange(PROPERTY_RULE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public RuleApplication withRule(Pattern value)
   {
      setRule(value);
      return this;
   } 

   public Pattern createRule()
   {
      Pattern value = new Pattern();
      withRule(value);
      return value;
   } 

   
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
         
         firePropertyChange(PROPERTY_SRC, oldValue, value);
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
         
         firePropertyChange(PROPERTY_TGT, oldValue, value);
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
   
   HashMap<PatternElement, Object> srcMatch;


   /**
    * @return the srcMatch
    */
   public HashMap<PatternElement, Object> getSrcMatch()
   {
      return srcMatch;
   }


   /**
    * @param srcMatch the srcMatch to set
    */
   public void setSrcMatch(HashMap<PatternElement, Object> srcMatch)
   {
      this.srcMatch = srcMatch;
   }


   /**
    * @param srcMatch the srcMatch to set
    */
   public RuleApplication withSrcMatch(HashMap<PatternElement, Object> srcMatch)
   {
      this.srcMatch = srcMatch;
      return this;
   }

   HashMap<PatternElement, Object> tgtMatch;


   /**
    * @return the tgtMatch
    */
   public HashMap<PatternElement, Object> getTgtMatch()
   {
      return tgtMatch;
   }


   /**
    * @param tgtMatch the tgtMatch to set
    */
   public void setTgtMatch(HashMap<PatternElement, Object> tgtMatch)
   {
      this.tgtMatch = tgtMatch;
   }


   /**
    * @param tgtMatch the tgtMatch to set
    */
   public RuleApplication withTgtMatch(HashMap<PatternElement, Object> tgtMatch)
   {
      this.tgtMatch = tgtMatch;
      return this;
   }

}
