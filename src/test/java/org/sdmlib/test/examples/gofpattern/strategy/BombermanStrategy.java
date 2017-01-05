/*
   Copyright (c) 2015 zuendorf
   
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
   
package org.sdmlib.test.examples.gofpattern.strategy;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.gofpattern.strategy.util.BombermanStrategySet;

import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/gofpattern/StrategyModel.java'>StrategyModel.java</a>
*/
   public abstract class BombermanStrategy implements PropertyChangeInterface, SendableEntity
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
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }

   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }

	public boolean removePropertyChangeListener(PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(listener);
		}
		return true;
	}

	public boolean removePropertyChangeListener(String property, PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}
   
   //==========================================================================
   
   
   public void removeYou()
   {
   
      setSuccessor(null);
      setBombermanstrategy(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              many                       one
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

   
   /********************************************************************
    * <pre>
    *              one                       one
    * BombermanStrategy ----------------------------------- BombermanStrategy
    *              bombermanstrategy                   bombermanstrategy
    * </pre>
    */
   
   public static final String PROPERTY_BOMBERMANSTRATEGY = "bombermanstrategy";

   private BombermanStrategy bombermanstrategy = null;

   public BombermanStrategy getBombermanstrategy()
   {
      return this.bombermanstrategy;
   }
   public BombermanStrategySet getBombermanstrategyTransitive()
   {
      BombermanStrategySet result = new BombermanStrategySet().with(this);
      return result.getBombermanstrategyTransitive();
   }


   public boolean setBombermanstrategy(BombermanStrategy value)
   {
      boolean changed = false;
      
      if (this.bombermanstrategy != value)
      {
         BombermanStrategy oldValue = this.bombermanstrategy;
         
         
         this.bombermanstrategy = value;
         
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BOMBERMANSTRATEGY, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public BombermanStrategy withBombermanstrategy(BombermanStrategy value)
   {
      setBombermanstrategy(value);
      return this;
   } 

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   }
