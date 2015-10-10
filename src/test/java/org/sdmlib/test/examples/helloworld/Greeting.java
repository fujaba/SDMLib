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
   
package org.sdmlib.test.examples.helloworld;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.helloworld.util.GreetingSet;
   /**
    * 
    * @see <a href='../../../../../../../../src/test/java/org/sdmlib/test/examples/helloworld/HelloWorldTTC2011.java'>HelloWorldTTC2011.java</a>
    */
public class Greeting implements PropertyChangeInterface
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
      setGreetingMessage(null);
      setPerson(null);
      setTgt(null);
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
   
   public Greeting withText(String value)
   {
      setText(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getText());
      return s.substring(1);
   }

   

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Greeting ----------------------------------- GreetingMessage
    *              greeting                   greetingMessage
    * </pre>
    */
   
   public static final String PROPERTY_GREETINGMESSAGE = "greetingMessage";

   private GreetingMessage greetingMessage = null;

   public GreetingMessage getGreetingMessage()
   {
      return this.greetingMessage;
   }

   public boolean setGreetingMessage(GreetingMessage value)
   {
      boolean changed = false;
      
      if (this.greetingMessage != value)
      {
         GreetingMessage oldValue = this.greetingMessage;
         
         if (this.greetingMessage != null)
         {
            this.greetingMessage = null;
            oldValue.setGreeting(null);
         }
         
         this.greetingMessage = value;
         
         if (value != null)
         {
            value.withGreeting(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GREETINGMESSAGE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Greeting withGreetingMessage(GreetingMessage value)
   {
      setGreetingMessage(value);
      return this;
   } 

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/helloworld/HelloWorldTTC2011.java'>HelloWorldTTC2011.java</a>
*/
   public GreetingMessage createGreetingMessage()
   {
      GreetingMessage value = new GreetingMessage();
      withGreetingMessage(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Greeting ----------------------------------- Person
    *              greeting                   person
    * </pre>
    */
   
   public static final String PROPERTY_PERSON = "person";

   private Person person = null;

   public Person getPerson()
   {
      return this.person;
   }

   public boolean setPerson(Person value)
   {
      boolean changed = false;
      
      if (this.person != value)
      {
         Person oldValue = this.person;
         
         if (this.person != null)
         {
            this.person = null;
            oldValue.setGreeting(null);
         }
         
         this.person = value;
         
         if (value != null)
         {
            value.withGreeting(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PERSON, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Greeting withPerson(Person value)
   {
      setPerson(value);
      return this;
   } 

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/helloworld/HelloWorldTTC2011.java'>HelloWorldTTC2011.java</a>
*/
   public Person createPerson()
   {
      Person value = new Person();
      withPerson(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Greeting ----------------------------------- Greeting
    *                                 tgt
    * </pre>
    */
   
   public static final String PROPERTY_TGT = "tgt";

   private Greeting tgt = null;

   public Greeting getTgt()
   {
      return this.tgt;
   }
   public GreetingSet getTgtTransitive()
   {
      GreetingSet result = new GreetingSet().with(this);
      return result.getTgtTransitive();
   }


   public boolean setTgt(Greeting value)
   {
      boolean changed = false;
      
      if (this.tgt != value)
      {
         Greeting oldValue = this.tgt;
         
         
         this.tgt = value;
         
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TGT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Greeting withTgt(Greeting value)
   {
      setTgt(value);
      return this;
   } 

   public Greeting createTgt()
   {
      Greeting value = new Greeting();
      withTgt(value);
      return value;
   } 
}

