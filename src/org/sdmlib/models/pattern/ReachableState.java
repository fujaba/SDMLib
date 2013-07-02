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

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.models.pattern.creators.ReachableStateSet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;

public class ReachableState implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return getParent();
      }

      if (PROPERTY_SUCCESSOR.equalsIgnoreCase(attrName))
      {
         return getSuccessor();
      }

      if (PROPERTY_PREDECESSOR.equalsIgnoreCase(attrName))
      {
         return getPredecessor();
      }

      if (PROPERTY_MASTER.equalsIgnoreCase(attrName))
      {
         return getMaster();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         setParent((ReachabilityGraph) value);
         return true;
      }

      if (PROPERTY_SUCCESSOR.equalsIgnoreCase(attrName))
      {
         addToSuccessor((ReachableState) value);
         return true;
      }
      
      if ((PROPERTY_SUCCESSOR + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromSuccessor((ReachableState) value);
         return true;
      }

      if (PROPERTY_PREDECESSOR.equalsIgnoreCase(attrName))
      {
         addToPredecessor((ReachableState) value);
         return true;
      }
      
      if ((PROPERTY_PREDECESSOR + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromPredecessor((ReachableState) value);
         return true;
      }

      if (PROPERTY_MASTER.equalsIgnoreCase(attrName))
      {
         setMaster((ReachabilityGraph) value);
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
      setParent(null);
      removeAllFromSuccessor();
      removeAllFromPredecessor();
      setMaster(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   public static final ReachableStateSet EMPTY_SET = new ReachableStateSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * ReachableState ----------------------------------- ReachabilityGraph
    *              states                   parent
    * </pre>
    */
   
   public static final String PROPERTY_PARENT = "parent";
   
   private ReachabilityGraph parent = null;
   
   public ReachabilityGraph getParent()
   {
      return this.parent;
   }
   
   public boolean setParent(ReachabilityGraph value)
   {
      boolean changed = false;
      
      if (this.parent != value)
      {
         ReachabilityGraph oldValue = this.parent;
         
         if (this.parent != null)
         {
            this.parent = null;
            oldValue.withoutStates(this);
         }
         
         this.parent = value;
         
         if (value != null)
         {
            value.withStates(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PARENT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public ReachableState withParent(ReachabilityGraph value)
   {
      setParent(value);
      return this;
   } 
   
   public ReachabilityGraph createParent()
   {
      ReachabilityGraph value = new ReachabilityGraph();
      withParent(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * ReachableState ----------------------------------- ReachableState
    *              predecessor                   successor
    * </pre>
    */
   
   public static final String PROPERTY_SUCCESSOR = "successor";
   
   private ReachableStateSet successor = null;
   
   public ReachableStateSet getSuccessor()
   {
      if (this.successor == null)
      {
         return ReachableState.EMPTY_SET;
      }
   
      return this.successor;
   }
   
   public boolean addToSuccessor(ReachableState value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.successor == null)
         {
            this.successor = new ReachableStateSet();
         }
         
         changed = this.successor.add (value);
         
         if (changed)
         {
            value.withPredecessor(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_SUCCESSOR, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromSuccessor(ReachableState value)
   {
      boolean changed = false;
      
      if ((this.successor != null) && (value != null))
      {
         changed = this.successor.remove (value);
         
         if (changed)
         {
            value.withoutPredecessor(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_SUCCESSOR, value, null);
         }
      }
         
      return changed;   
   }
   
   public ReachableState withSuccessor(ReachableState value)
   {
      addToSuccessor(value);
      return this;
   } 
   
   public ReachableState withoutSuccessor(ReachableState value)
   {
      removeFromSuccessor(value);
      return this;
   } 
   
   public void removeAllFromSuccessor()
   {
      LinkedHashSet<ReachableState> tmpSet = new LinkedHashSet<ReachableState>(this.getSuccessor());
   
      for (ReachableState value : tmpSet)
      {
         this.removeFromSuccessor(value);
      }
   }
   
   public ReachableState createSuccessor()
   {
      ReachableState value = new ReachableState();
      withSuccessor(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * ReachableState ----------------------------------- ReachableState
    *              successor                   predecessor
    * </pre>
    */
   
   public static final String PROPERTY_PREDECESSOR = "predecessor";
   
   private ReachableStateSet predecessor = null;
   
   public ReachableStateSet getPredecessor()
   {
      if (this.predecessor == null)
      {
         return ReachableState.EMPTY_SET;
      }
   
      return this.predecessor;
   }
   
   public boolean addToPredecessor(ReachableState value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.predecessor == null)
         {
            this.predecessor = new ReachableStateSet();
         }
         
         changed = this.predecessor.add (value);
         
         if (changed)
         {
            value.withSuccessor(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PREDECESSOR, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromPredecessor(ReachableState value)
   {
      boolean changed = false;
      
      if ((this.predecessor != null) && (value != null))
      {
         changed = this.predecessor.remove (value);
         
         if (changed)
         {
            value.withoutSuccessor(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PREDECESSOR, value, null);
         }
      }
         
      return changed;   
   }
   
   public ReachableState withPredecessor(ReachableState value)
   {
      addToPredecessor(value);
      return this;
   } 
   
   public ReachableState withoutPredecessor(ReachableState value)
   {
      removeFromPredecessor(value);
      return this;
   } 
   
   public void removeAllFromPredecessor()
   {
      LinkedHashSet<ReachableState> tmpSet = new LinkedHashSet<ReachableState>(this.getPredecessor());
   
      for (ReachableState value : tmpSet)
      {
         this.removeFromPredecessor(value);
      }
   }
   
   public ReachableState createPredecessor()
   {
      ReachableState value = new ReachableState();
      withPredecessor(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * ReachableState ----------------------------------- ReachabilityGraph
    *              todo                   master
    * </pre>
    */
   
   public static final String PROPERTY_MASTER = "master";
   
   private ReachabilityGraph master = null;
   
   public ReachabilityGraph getMaster()
   {
      return this.master;
   }
   
   public boolean setMaster(ReachabilityGraph value)
   {
      boolean changed = false;
      
      if (this.master != value)
      {
         ReachabilityGraph oldValue = this.master;
         
         if (this.master != null)
         {
            this.master = null;
            oldValue.withoutTodo(this);
         }
         
         this.master = value;
         
         if (value != null)
         {
            value.withTodo(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MASTER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public ReachableState withMaster(ReachabilityGraph value)
   {
      setMaster(value);
      return this;
   } 
   
   public ReachabilityGraph createMaster()
   {
      ReachabilityGraph value = new ReachabilityGraph();
      withMaster(value);
      return value;
   } 
}

