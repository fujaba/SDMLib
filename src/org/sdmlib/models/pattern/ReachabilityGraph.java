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
import org.sdmlib.models.pattern.creators.PatternSet;

public class ReachabilityGraph implements PropertyChangeInterface
{


   //==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_STATES.equalsIgnoreCase(attrName))
      {
         return getStates();
      }

      if (PROPERTY_TODO.equalsIgnoreCase(attrName))
      {
         return getTodo();
      }

      if (PROPERTY_RULES.equalsIgnoreCase(attrName))
      {
         return getRules();
      }

      return null;
   }


   //==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_STATES.equalsIgnoreCase(attrName))
      {
         addToStates((ReachableState) value);
         return true;
      }

      if ((PROPERTY_STATES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromStates((ReachableState) value);
         return true;
      }

      if (PROPERTY_TODO.equalsIgnoreCase(attrName))
      {
         addToTodo((ReachableState) value);
         return true;
      }

      if ((PROPERTY_TODO + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromTodo((ReachableState) value);
         return true;
      }

      if (PROPERTY_RULES.equalsIgnoreCase(attrName))
      {
         addToRules((Pattern) value);
         return true;
      }

      if ((PROPERTY_RULES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromRules((Pattern) value);
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
      removeAllFromStates();
      removeAllFromTodo();
      removeAllFromRules();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }


   /********************************************************************
    * <pre>
    *              one                       many
    * ReachabilityGraph ----------------------------------- ReachableState
    *              parent                   states
    * </pre>
    */

   public static final String PROPERTY_STATES = "states";

   private ReachableStateSet states = null;

   public ReachableStateSet getStates()
   {
      if (this.states == null)
      {
         return ReachableState.EMPTY_SET;
      }

      return this.states;
   }

   public boolean addToStates(ReachableState value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.states == null)
         {
            this.states = new ReachableStateSet();
         }

         changed = this.states.add (value);

         if (changed)
         {
            value.withParent(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_STATES, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromStates(ReachableState value)
   {
      boolean changed = false;

      if ((this.states != null) && (value != null))
      {
         changed = this.states.remove (value);

         if (changed)
         {
            value.setParent(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_STATES, value, null);
         }
      }

      return changed;   
   }

   public ReachabilityGraph withStates(ReachableState value)
   {
      addToStates(value);
      return this;
   } 

   public ReachabilityGraph withoutStates(ReachableState value)
   {
      removeFromStates(value);
      return this;
   } 

   public void removeAllFromStates()
   {
      LinkedHashSet<ReachableState> tmpSet = new LinkedHashSet<ReachableState>(this.getStates());

      for (ReachableState value : tmpSet)
      {
         this.removeFromStates(value);
      }
   }

   public ReachableState createStates()
   {
      ReachableState value = new ReachableState();
      withStates(value);
      return value;
   } 


   /********************************************************************
    * <pre>
    *              one                       many
    * ReachabilityGraph ----------------------------------- ReachableState
    *              master                   todo
    * </pre>
    */

   public static final String PROPERTY_TODO = "todo";

   private ReachableStateSet todo = null;

   public ReachableStateSet getTodo()
   {
      if (this.todo == null)
      {
         return ReachableState.EMPTY_SET;
      }

      return this.todo;
   }

   public boolean addToTodo(ReachableState value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.todo == null)
         {
            this.todo = new ReachableStateSet();
         }

         changed = this.todo.add (value);

         if (changed)
         {
            value.withMaster(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_TODO, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromTodo(ReachableState value)
   {
      boolean changed = false;

      if ((this.todo != null) && (value != null))
      {
         changed = this.todo.remove (value);

         if (changed)
         {
            value.setMaster(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_TODO, value, null);
         }
      }

      return changed;   
   }

   public ReachabilityGraph withTodo(ReachableState value)
   {
      addToTodo(value);
      return this;
   } 

   public ReachabilityGraph withoutTodo(ReachableState value)
   {
      removeFromTodo(value);
      return this;
   } 

   public void removeAllFromTodo()
   {
      LinkedHashSet<ReachableState> tmpSet = new LinkedHashSet<ReachableState>(this.getTodo());

      for (ReachableState value : tmpSet)
      {
         this.removeFromTodo(value);
      }
   }

   public ReachableState createTodo()
   {
      ReachableState value = new ReachableState();
      withTodo(value);
      return value;
   } 


   /********************************************************************
    * <pre>
    *              one                       many
    * ReachabilityGraph ----------------------------------- Pattern
    *              rgraph                   rules
    * </pre>
    */

   public static final String PROPERTY_RULES = "rules";

   private PatternSet rules = null;

   public PatternSet getRules()
   {
      if (this.rules == null)
      {
         return Pattern.EMPTY_SET;
      }

      return this.rules;
   }

   public boolean addToRules(Pattern value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.rules == null)
         {
            this.rules = new PatternSet();
         }

         changed = this.rules.add (value);

         if (changed)
         {
            value.withRgraph(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_RULES, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromRules(Pattern value)
   {
      boolean changed = false;

      if ((this.rules != null) && (value != null))
      {
         changed = this.rules.remove (value);

         if (changed)
         {
            value.setRgraph(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_RULES, value, null);
         }
      }

      return changed;   
   }

   public ReachabilityGraph withRules(Pattern value)
   {
      addToRules(value);
      return this;
   } 

   public ReachabilityGraph withoutRules(Pattern value)
   {
      removeFromRules(value);
      return this;
   } 

   public void removeAllFromRules()
   {
      LinkedHashSet<Pattern> tmpSet = new LinkedHashSet<Pattern>(this.getRules());

      for (Pattern value : tmpSet)
      {
         this.removeFromRules(value);
      }
   }

   public Pattern createRules()
   {
      Pattern value = new Pattern();
      withRules(value);
      return value;
   }


   public void explore()
   {
      explore(Long.MAX_VALUE);

   }

   private long noOfNewStates;

   public void explore(long maxNoOfNewStates)
   {
      // take a todo state and apply all rules at all places until maxNoOfNewStates 
      // is reached
      noOfNewStates = 0;

      while (!getTodo().isEmpty() && noOfNewStates < maxNoOfNewStates)
      {
         ReachableState first = getTodo().first();

         this.withoutTodo(first);

         for (Pattern rule : getRules())
         {
            PatternObject firstPO = (PatternObject) rule.getElements().first();

            rule.resetSearch();

            ((PatternObject) firstPO.withModifier(Pattern.BOUND)).setCurrentMatch(first);

            while (rule.findMatch())
            {
               // do all matches           
            }
         }
      }
   } 
}

