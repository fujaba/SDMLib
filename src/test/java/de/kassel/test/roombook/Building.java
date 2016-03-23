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
   
package de.kassel.test.roombook;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.kassel.test.roombook.util.FloorSet;
import de.uniks.networkparser.interfaces.SendableEntity;
import de.kassel.test.roombook.Floor;
   /**
    * 
    * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/models/objects/GenericObjectsTest.java'>GenericObjectsTest.java</a>
*/
   public class Building implements PropertyChangeInterface, SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
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
      getPropertyChangeSupport().removePropertyChangeListener(listener);
      return true;
   }

   //==========================================================================
   
   public void removeYou()
   {
      removeAllFromHas();
      withoutHas(this.getHas().toArray(new Floor[this.getHas().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;


   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getName());
      return s.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       many
    * Building ----------------------------------- Floor
    *              buildings                   has
    * </pre>
    */
   
   public static final String PROPERTY_HAS = "has";

   private FloorSet has = null;
   
   public FloorSet getHas()
   {
      if (this.has == null)
      {
         return Floor.EMPTY_SET;
      }
   
      return this.has;
   }

   public boolean addToHas(Floor value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.has == null)
         {
            this.has = new FloorSet();
         }
         
         changed = this.has.add (value);
         
         if (changed)
         {
            value.withBuildings(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_HAS, null, value);
         }
      }
         
      return changed;   
   }

   public boolean removeFromHas(Floor value)
   {
      boolean changed = false;
      
      if ((this.has != null) && (value != null))
      {
         changed = this.has.remove(value);
         
         if (changed)
         {
            value.setBuildings(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_HAS, value, null);
         }
      }
         
      return changed;   
   }

   public Building withHas(Floor... value)
   {
      if(value==null){
         return this;
      }
      for (Floor item : value)
      {
         addToHas(item);
      }
      return this;
   } 

   public Building withoutHas(Floor... value)
   {
      for (Floor item : value)
      {
         removeFromHas(item);
      }
      return this;
   }

   public void removeAllFromHas()
   {
      LinkedHashSet<Floor> tmpSet = new LinkedHashSet<Floor>(this.getHas());
   
      for (Floor value : tmpSet)
      {
         this.removeFromHas(value);
      }
   }

   public Floor createHas()
   {
      Floor value = new Floor();
      withHas(value);
      return value;
   } 

   
   //==========================================================================
   
   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if ( ! StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public Building withName(String value)
   {
      setName(value);
      return this;
   } 
}

