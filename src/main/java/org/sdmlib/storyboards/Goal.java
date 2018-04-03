/*
   Copyright (c) 2018 zuendorf
   
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
   
package org.sdmlib.storyboards;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import de.uniks.networkparser.EntityUtil;
import org.sdmlib.storyboards.util.GoalSet;
   /**
    * 
    * @see <a href='../../../../../../src/test/java/org/sdmlib/test/mikado/MikadoMethodModel.java'>MikadoMethodModel.java</a>
 */
   public  class Goal implements SendableEntity
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
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(listener);
   	}
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
      withoutParents(this.getParents().toArray(new Goal[this.getParents().size()]));
      withoutPreGoals(this.getPreGoals().toArray(new Goal[this.getPreGoals().size()]));
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
   
   public Goal withDescription(String value)
   {
      setDescription(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getDescription());
      result.append(" ").append(this.getHoursDone());
      result.append(" ").append(this.getHoursTodo());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_HOURSDONE = "hoursDone";
   
   private double hoursDone;

   public double getHoursDone()
   {
      return this.hoursDone;
   }
   
   public void setHoursDone(double value)
   {
      if (this.hoursDone != value) {
      
         double oldValue = this.hoursDone;
         this.hoursDone = value;
         this.firePropertyChange(PROPERTY_HOURSDONE, oldValue, value);
      }
   }
   
   public Goal withHoursDone(double value)
   {
      setHoursDone(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_HOURSTODO = "hoursTodo";
   
   private double hoursTodo = 4;

   public double getHoursTodo()
   {
      return this.hoursTodo;
   }
   
   public void setHoursTodo(double value)
   {
      if (this.hoursTodo != value) {
      
         double oldValue = this.hoursTodo;
         this.hoursTodo = value;
         this.firePropertyChange(PROPERTY_HOURSTODO, oldValue, value);
      }
   }
   
   public Goal withHoursTodo(double value)
   {
      setHoursTodo(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Goal ----------------------------------- Goal
    *              preGoals                   parents
    * </pre>
    */
   
   public static final String PROPERTY_PARENTS = "parents";

   private GoalSet parents = null;
   
   public GoalSet getParents()
   {
      // always return empty set in order to clip to subtree for story.addObjectDiagram
      return GoalSet.EMPTY_SET;

//      if (this.parents == null)
//      {
//         return GoalSet.EMPTY_SET;
//      }
   
//      return this.parents;
   }
   public GoalSet getParentsTransitive()
   {
      GoalSet result = new GoalSet().with(this);
      return result.getParentsTransitive();
   }


   public Goal withParents(Goal... value)
   {
      if(value==null){
         return this;
      }
      for (Goal item : value)
      {
         if (item != null)
         {
            if (this.parents == null)
            {
               this.parents = new GoalSet();
            }
            
            boolean changed = this.parents.add (item);

            if (changed)
            {
               item.withPreGoals(this);
               firePropertyChange(PROPERTY_PARENTS, null, item);
            }
         }
      }
      return this;
   } 

   public Goal withoutParents(Goal... value)
   {
      for (Goal item : value)
      {
         if ((this.parents != null) && (item != null))
         {
            if (this.parents.remove(item))
            {
               item.withoutPreGoals(this);
               firePropertyChange(PROPERTY_PARENTS, item, null);
            }
         }
      }
      return this;
   }

   public Goal createParents()
   {
      Goal value = new Goal();
      withParents(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Goal ----------------------------------- Goal
    *              parents                   preGoals
    * </pre>
    */
   
   public static final String PROPERTY_PREGOALS = "preGoals";

   private GoalSet preGoals = null;
   
   public GoalSet getPreGoals()
   {
      if (this.preGoals == null)
      {
         return GoalSet.EMPTY_SET;
      }
   
      return this.preGoals;
   }
   public GoalSet getPreGoalsTransitive()
   {
      GoalSet result = new GoalSet().with(this);
      return result.getPreGoalsTransitive();
   }


   public Goal withPreGoals(Goal... value)
   {
      if(value==null){
         return this;
      }
      for (Goal item : value)
      {
         if (item != null)
         {
            if (this.preGoals == null)
            {
               this.preGoals = new GoalSet();
            }
            
            boolean changed = this.preGoals.add (item);

            if (changed)
            {
               item.withParents(this);
               firePropertyChange(PROPERTY_PREGOALS, null, item);
            }
         }
      }
      return this;
   } 

   public Goal withoutPreGoals(Goal... value)
   {
      for (Goal item : value)
      {
         if ((this.preGoals != null) && (item != null))
         {
            if (this.preGoals.remove(item))
            {
               item.withoutParents(this);
               firePropertyChange(PROPERTY_PREGOALS, item, null);
            }
         }
      }
      return this;
   }

   public Goal createPreGoals()
   {
      Goal value = new Goal();
      withPreGoals(value);
      return value;
   }

     /**
    * 
    * @see <a href='../../../../../../src/test/java/org/sdmlib/test/doc/TestJavaDocStories.java'>TestJavaDocStories.java</a>
 * @see org.sdmlib.test.doc.TestJavaDocStories#testJavaDocStoriesMikadoPlan
 */
   public Goal clipDone()
   {
      Goal done = new Goal().withDescription("done").withHoursTodo(0);

      clipDone(done);

      return done;
   }

     /**
    * 
    * @see <a href='../../../../../../src/test/java/org/sdmlib/test/doc/TestJavaDocStories.java'>TestJavaDocStories.java</a>
 * @see org.sdmlib.test.doc.TestJavaDocStories#testJavaDocStoriesMikadoPlan
 */
   private void clipDone(Goal done)
   {
      ArrayList<Goal> preGoals = new ArrayList<>();

      preGoals.addAll(this.getPreGoals());

      for (Goal pre : preGoals)
      {
         if (pre.getHoursTodo() == 0)
         {
            this.withoutPreGoals(pre);

            done.withPreGoals(pre);
         }
         else
         {
            pre.clipDone(done);
         }
      }
   }
}
