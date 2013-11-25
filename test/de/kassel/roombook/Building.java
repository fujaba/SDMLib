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
   
package de.kassel.roombook;

import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

import de.kassel.roombook.creators.FloorSet;
import java.beans.PropertyChangeListener;

public class Building implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_ID.equalsIgnoreCase(attrName))
      {
         return getId();
      }

      if (PROPERTY_HAS.equalsIgnoreCase(attrName))
      {
         return getHas();
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_ID.equalsIgnoreCase(attrName))
      {
         setId((String) value);
         return true;
      }

      if (PROPERTY_HAS.equalsIgnoreCase(attrName))
      {
         addToHas((Floor) value);
         return true;
      }
      
      if ((PROPERTY_HAS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromHas((Floor) value);
         return true;
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      removeAllFromHas();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_ID = "id";
   
   private String id;
   
   public String getId()
   {
      return this.id;
   }
   
   public void setId(String value)
   {
      if ( ! StrUtil.stringEquals(this.id, value))
      {
         String oldValue = this.id;
         this.id = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ID, oldValue, value);
      }
   }
   
   public Building withId(String value)
   {
      setId(value);
      return this;
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
         changed = this.has.remove (value);
         
         if (changed)
         {
            value.setBuildings(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_HAS, value, null);
         }
      }
         
      return changed;   
   }
   
   public Building withHas(Floor value)
   {
      addToHas(value);
      return this;
   } 
   
   public Building withoutHas(Floor value)
   {
      removeFromHas(value);
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

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;
   
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

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      return _.substring(1);
   }

}

