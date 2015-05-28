/*
   Copyright (c) 2015 zasch 
   
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
   
package org.sdmlib.examples.gofpattern.strategy;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.examples.gofpattern.strategy.util.BombermanStrategySet;

public abstract class BombermanStrategy implements PropertyChangeInterface
{

   
   //==========================================================================
   public void handleMove(  )
   {
      
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      setSuccessor(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * BombermanStrategy ----------------------------------- BombermanStrategy
    *                                 successor
    * </pre>
    */
   
   public static final String PROPERTY_SUCCESSOR = "successor";

   private BombermanStrategy successor = null;

   public BombermanStrategy getSuccessor()
   {
      return this.successor;
   }
   public BombermanStrategySet getSuccessorTransitive()
   {
      BombermanStrategySet result = new BombermanStrategySet().with(this);
      return result.getSuccessorTransitive();
   }


   public boolean setSuccessor(BombermanStrategy value)
   {
      boolean changed = false;
      
      if (this.successor != value)
      {
         BombermanStrategy oldValue = this.successor;
         
         
         this.successor = value;
         
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SUCCESSOR, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public BombermanStrategy withSuccessor(BombermanStrategy value)
   {
      setSuccessor(value);
      return this;
   } 
}
