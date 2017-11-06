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

import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/gofpattern/StrategyModel.java'>StrategyModel.java</a>
*/
   public  class BombermanPlayer implements PropertyChangeInterface, SendableEntity
{

   
   
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
   
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_XPOSITION = "xPosition";
   
   private int xPosition;

   public int getXPosition()
   {
      return this.xPosition;
   }
   
   public void setXPosition(int value)
   {
      if (this.xPosition != value) {
      
         int oldValue = this.xPosition;
         this.xPosition = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_XPOSITION, oldValue, value);
      }
   }
   
   public BombermanPlayer withXPosition(int value)
   {
      setXPosition(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getXPosition());
      result.append(" ").append(this.getYPosition());
      result.append(" ").append(this.getNumberOfBombs());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_YPOSITION = "yPosition";
   
   private int yPosition;

   public int getYPosition()
   {
      return this.yPosition;
   }
   
   public void setYPosition(int value)
   {
      if (this.yPosition != value) {
      
         int oldValue = this.yPosition;
         this.yPosition = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_YPOSITION, oldValue, value);
      }
   }
   
   public BombermanPlayer withYPosition(int value)
   {
      setYPosition(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_NUMBEROFBOMBS = "numberOfBombs";
   
   private int numberOfBombs;

   public int getNumberOfBombs()
   {
      return this.numberOfBombs;
   }
   
   public void setNumberOfBombs(int value)
   {
      if (this.numberOfBombs != value) {
      
         int oldValue = this.numberOfBombs;
         this.numberOfBombs = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NUMBEROFBOMBS, oldValue, value);
      }
   }
   
   public BombermanPlayer withNumberOfBombs(int value)
   {
      setNumberOfBombs(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_LASTKEY = "lastKey";
   
   private char lastKey;

   public char getLastKey()
   {
      return this.lastKey;
   }
   
   public void setLastKey(char value)
   {
      if (this.lastKey != value) {
      
         char oldValue = this.lastKey;
         this.lastKey = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LASTKEY, oldValue, value);
      }
   }
   
   public BombermanPlayer withLastKey(char value)
   {
      setLastKey(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_SHORTTEST = "shortTest";
   
   private short shortTest;

   public short getShortTest()
   {
      return this.shortTest;
   }
   
   public void setShortTest(short value)
   {
      if (this.shortTest != value) {
      
         short oldValue = this.shortTest;
         this.shortTest = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SHORTTEST, oldValue, value);
      }
   }
   
   public BombermanPlayer withShortTest(short value)
   {
      setShortTest(value);
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
   
   
   //==========================================================================
   public void keyPress( String key )
   {
      
   }
}
