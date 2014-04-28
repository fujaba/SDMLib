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

package org.sdmlib.storyboards;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class StoryboardWall implements PropertyChangeInterface
{

   // ==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_STORYBOARD.equalsIgnoreCase(attrName))
      {
         return getStoryboard();
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_STORYBOARD.equalsIgnoreCase(attrName))
      {
         setStoryboard((Storyboard) value);
         return true;
      }

      return false;
   }

   // ==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   public void addPropertyChangeListener(PropertyChangeListener listener)
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   // ==========================================================================

   public void removeYou()
   {
      setStoryboard(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   /********************************************************************
    * <pre>
    *              one                       one
    * StoryboardWall ----------------------------------- Storyboard
    *              wall                   storyboard
    * </pre>
    */

   public static final String PROPERTY_STORYBOARD = "storyboard";

   private Storyboard storyboard = null;

   public Storyboard getStoryboard()
   {
      return this.storyboard;
   }

   public boolean setStoryboard(Storyboard value)
   {
      boolean changed = false;

      if (this.storyboard != value)
      {
         Storyboard oldValue = this.storyboard;

         if (this.storyboard != null)
         {
            this.storyboard = null;
            oldValue.setWall(null);
         }

         this.storyboard = value;

         if (value != null)
         {
            value.withWall(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_STORYBOARD,
            oldValue, value);
         changed = true;
      }

      return changed;
   }

   public StoryboardWall withStoryboard(Storyboard value)
   {
      setStoryboard(value);
      return this;
   }

   public Storyboard createStoryboard()
   {
      Storyboard value = new Storyboard();
      withStoryboard(value);
      return value;
   }
}
