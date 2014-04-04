/*
   Copyright (c) 2012 zuendorf 
   
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

import java.beans.PropertyChangeSupport;

import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;
import java.beans.PropertyChangeListener;

public class GreetingMessage implements PropertyChangeInterface
{

   // ==========================================================================

   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;

      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_TEXT.equalsIgnoreCase(attribute))
      {
         return getText();
      }

      if (PROPERTY_GREETING.equalsIgnoreCase(attrName))
      {
         return getGreeting();
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         setText((String) value);
         return true;
      }

      if (PROPERTY_GREETING.equalsIgnoreCase(attrName))
      {
         setGreeting((Greeting) value);
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

   // ==========================================================================

   public void removeYou()
   {
      setGreeting(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   // ==========================================================================

   public static final String PROPERTY_TEXT = "text";

   private String text;

   public String getText()
   {
      return this.text;
   }

   public void setText(String value)
   {
      if (!StrUtil.stringEquals(this.text, value))
      {
         String oldValue = this.text;
         this.text = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TEXT, oldValue,
            value);
      }
   }

   public GreetingMessage withText(String value)
   {
      setText(value);
      return this;
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

         getPropertyChangeSupport().firePropertyChange(PROPERTY_GREETING,
            oldValue, value);
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

   public String toString()
   {
      StringBuilder _ = new StringBuilder();

      _.append(" ").append(this.getText());
      return _.substring(1);
   }

}
