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
   
package org.sdmlib.model.test.consistence;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.utils.StrUtil;

public class Border implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_BORDERLOCATION.equalsIgnoreCase(attrName))
      {
         return getBorderLocation();
      }

      if (PROPERTY_CONNECTEDTO.equalsIgnoreCase(attrName))
      {
         return getConnectedTo();
      }

      if (PROPERTY_CONNECTEDTOREV.equalsIgnoreCase(attrName))
      {
         return getConnectedToRev();
      }

      if (PROPERTY_SERVERID.equalsIgnoreCase(attrName))
      {
         return getServerId();
      }

      if (PROPERTY_ROAD.equalsIgnoreCase(attrName))
      {
         return getRoad();
      }

      if (PROPERTY_FIELD.equalsIgnoreCase(attrName))
      {
         return getField();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_BORDERLOCATION.equalsIgnoreCase(attrName))
      {
         setBorderLocation((String) value);
         return true;
      }

      if (PROPERTY_CONNECTEDTO.equalsIgnoreCase(attrName))
      {
         setConnectedTo((String) value);
         return true;
      }

      if (PROPERTY_CONNECTEDTOREV.equalsIgnoreCase(attrName))
      {
         setConnectedToRev((String) value);
         return true;
      }

      if (PROPERTY_SERVERID.equalsIgnoreCase(attrName))
      {
         setServerId((String) value);
         return true;
      }

      if (PROPERTY_ROAD.equalsIgnoreCase(attrName))
      {
         setRoad((String) value);
         return true;
      }

      if (PROPERTY_FIELD.equalsIgnoreCase(attrName))
      {
         setField((Field) value);
         return true;
      }

      return false;
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
      setField(null);
      setConnectedTo(null);
      setConnectedToRev(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_BORDERLOCATION = "borderLocation";
   
   private String borderLocation;

   public String getBorderLocation()
   {
      return this.borderLocation;
   }
   
   public void setBorderLocation(String value)
   {
      if ( ! StrUtil.stringEquals(this.borderLocation, value))
      {
         String oldValue = this.borderLocation;
         this.borderLocation = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BORDERLOCATION, oldValue, value);
      }
   }
   
   public Border withBorderLocation(String value)
   {
      setBorderLocation(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getBorderLocation());
      _.append(" ").append(this.getConnectedTo());
      _.append(" ").append(this.getConnectedToRev());
      _.append(" ").append(this.getServerId());
      _.append(" ").append(this.getRoad());
      return _.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_CONNECTEDTO = "connectedTo";
   
   private String connectedTo;

   public String getConnectedTo()
   {
      return this.connectedTo;
   }
   
   public void setConnectedTo(String value)
   {
      if ( ! StrUtil.stringEquals(this.connectedTo, value))
      {
         String oldValue = this.connectedTo;
         this.connectedTo = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CONNECTEDTO, oldValue, value);
      }
   }
   
   public Border withConnectedTo(String value)
   {
      setConnectedTo(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_CONNECTEDTOREV = "connectedToRev";
   
   private String connectedToRev;

   public String getConnectedToRev()
   {
      return this.connectedToRev;
   }
   
   public void setConnectedToRev(String value)
   {
      if ( ! StrUtil.stringEquals(this.connectedToRev, value))
      {
         String oldValue = this.connectedToRev;
         this.connectedToRev = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CONNECTEDTOREV, oldValue, value);
      }
   }
   
   public Border withConnectedToRev(String value)
   {
      setConnectedToRev(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_SERVERID = "serverId";
   
   private String serverId;

   public String getServerId()
   {
      return this.serverId;
   }
   
   public void setServerId(String value)
   {
      if ( ! StrUtil.stringEquals(this.serverId, value))
      {
         String oldValue = this.serverId;
         this.serverId = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SERVERID, oldValue, value);
      }
   }
   
   public Border withServerId(String value)
   {
      setServerId(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_ROAD = "road";
   
   private String road;

   public String getRoad()
   {
      return this.road;
   }
   
   public void setRoad(String value)
   {
      if ( ! StrUtil.stringEquals(this.road, value))
      {
         String oldValue = this.road;
         this.road = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ROAD, oldValue, value);
      }
   }
   
   public Border withRoad(String value)
   {
      setRoad(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Border ----------------------------------- Field
    *              border                   field
    * </pre>
    */
   
   public static final String PROPERTY_FIELD = "field";
   
   private Field field = null;
   
   public Field getField()
   {
      return this.field;
   }
   
   public boolean setField(Field value)
   {
      boolean changed = false;
      
      if (this.field != value)
      {
         Field oldValue = this.field;
         
         if (this.field != null)
         {
            this.field = null;
            oldValue.setBorder(null);
         }
         
         this.field = value;
         
         if (value != null)
         {
            value.withBorder(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_FIELD, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Border withField(Field value)
   {
      setField(value);
      return this;
   } 
   
   public Field createField()
   {
      Field value = new Field();
      withField(value);
      return value;
   } 
}

