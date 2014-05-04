/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.examples.helloworld;

import org.sdmlib.serialization.util.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class GreetingMessage implements PropertyChangeInterface
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
      setGreeting(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_TEXT = "text";
   
   private String text;

   public String getText()
   {
      return this.text;
   }
   
   public void setText(String value)
   {
      if (this.text != value)
      {
         String oldValue = this.text;
         this.text = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TEXT, oldValue, value);
      }
   }
   
   public GreetingMessage withText(String value)
   {
      setText(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getText());
      return _.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       one
    * GreetingMessage ----------------------------------- Greeting
    *              greetingMessage                   greeting
    * </pre>
    */
   
   public static final String PROPERTY_GREETING = "greeting";

   private Greeting greeting = null;

   public Greeting getGreeting()
   {
      return this.greeting;
   }

   public boolean setGreeting(Greeting value)
   {
      boolean changed = false;
      
      if (this.greeting != value)
      {
         Greeting oldValue = this.greeting;
         
         if (this.greeting != null)
         {
            this.greeting = null;
            oldValue.setGreetingMessage(null);
         }
         
         this.greeting = value;
         
         if (value != null)
         {
            value.withGreetingMessage(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GREETING, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public GreetingMessage withGreeting(Greeting value)
   {
      setGreeting(value);
      return this;
   } 

   public Greeting createGreeting()
   {
      Greeting value = new Greeting();
      withGreeting(value);
      return value;
   } 
}

