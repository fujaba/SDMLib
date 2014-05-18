/*
   Copyright (c) 2014 Stefan 
   
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
   
package org.sdmlib.examples.ludo.model;

import org.sdmlib.serialization.PropertyChangeInterface;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class Player implements PropertyChangeInterface
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
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
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_COLOR = "color";
   
   private String color;

   public String getColor()
   {
      return this.color;
   }
   
   public void setColor(String value)
   {
      if (this.color != value)
      {
         String oldValue = this.color;
         this.color = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_COLOR, oldValue, value);
      }
   }
   
   public Player withColor(String value)
   {
      setColor(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getColor());
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getX());
      _.append(" ").append(this.getY());
      return _.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_ENUMCOLOR = "enumColor";
   
   private org.sdmlib.examples.ludo.LudoModel.LudoColor enumColor;

   public org.sdmlib.examples.ludo.LudoModel.LudoColor getEnumColor()
   {
      return this.enumColor;
   }
   
   public void setEnumColor(org.sdmlib.examples.ludo.LudoModel.LudoColor value)
   {
      if (this.enumColor != value)
      {
         org.sdmlib.examples.ludo.LudoModel.LudoColor oldValue = this.enumColor;
         this.enumColor = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ENUMCOLOR, oldValue, value);
      }
   }
   
   public Player withEnumColor(org.sdmlib.examples.ludo.LudoModel.LudoColor value)
   {
      setEnumColor(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;

   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if (this.name != value)
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public Player withName(String value)
   {
      setName(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_X = "x";
   
   private int x;

   public int getX()
   {
      return this.x;
   }
   
   public void setX(int value)
   {
      if (this.x != value)
      {
         int oldValue = this.x;
         this.x = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_X, oldValue, value);
      }
   }
   
   public Player withX(int value)
   {
      setX(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_Y = "y";
   
   private int y;

   public int getY()
   {
      return this.y;
   }
   
   public void setY(int value)
   {
      if (this.y != value)
      {
         int oldValue = this.y;
         this.y = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_Y, oldValue, value);
      }
   }
   
   public Player withY(int value)
   {
      setY(value);
      return this;
   } 
}

