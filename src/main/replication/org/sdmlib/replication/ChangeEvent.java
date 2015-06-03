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
   
package org.sdmlib.replication;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.StrUtil;

public  class ChangeEvent implements PropertyChangeInterface
{

   
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
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_OBJECTID = "objectId";
   
   private String objectId;

   public String getObjectId()
   {
      return this.objectId;
   }
   
   public void setObjectId(String value)
   {
      if ( ! StrUtil.stringEquals(this.objectId, value))
      {
         String oldValue = this.objectId;
         this.objectId = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_OBJECTID, oldValue, value);
      }
   }
   
   public ChangeEvent withObjectId(String value)
   {
      setObjectId(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getObjectId());
      result.append(" ").append(this.getObjectType());
      result.append(" ").append(this.getProperty());
      result.append(" ").append(this.getNewValue());
      result.append(" ").append(this.getOldValue());
      result.append(" ").append(this.getValueType());
      result.append(" ").append(this.getOpCode());
      result.append(" ").append(this.getChangeNo());
      result.append(" ").append(this.getSessionId());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_OBJECTTYPE = "objectType";
   
   private String objectType;

   public String getObjectType()
   {
      return this.objectType;
   }
   
   public void setObjectType(String value)
   {
      if ( ! StrUtil.stringEquals(this.objectType, value))
      {
         String oldValue = this.objectType;
         this.objectType = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_OBJECTTYPE, oldValue, value);
      }
   }
   
   public ChangeEvent withObjectType(String value)
   {
      setObjectType(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_PROPERTY = "property";
   
   private String property;

   public String getProperty()
   {
      return this.property;
   }
   
   public void setProperty(String value)
   {
      if ( ! StrUtil.stringEquals(this.property, value))
      {
         String oldValue = this.property;
         this.property = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PROPERTY, oldValue, value);
      }
   }
   
   public ChangeEvent withProperty(String value)
   {
      setProperty(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_NEWVALUE = "newValue";
   
   private String newValue;

   public String getNewValue()
   {
      return this.newValue;
   }
   
   public void setNewValue(String value)
   {
      if ( ! StrUtil.stringEquals(this.newValue, value))
      {
         String oldValue = this.newValue;
         this.newValue = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NEWVALUE, oldValue, value);
      }
   }
   
   public ChangeEvent withNewValue(String value)
   {
      setNewValue(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_OLDVALUE = "oldValue";
   
   private String oldValue;

   public String getOldValue()
   {
      return this.oldValue;
   }
   
   public void setOldValue(String value)
   {
      if ( ! StrUtil.stringEquals(this.oldValue, value))
      {
         String oldValue = this.oldValue;
         this.oldValue = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_OLDVALUE, oldValue, value);
      }
   }
   
   public ChangeEvent withOldValue(String value)
   {
      setOldValue(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_VALUETYPE = "valueType";
   
   private String valueType;

   public String getValueType()
   {
      return this.valueType;
   }
   
   public void setValueType(String value)
   {
      if ( ! StrUtil.stringEquals(this.valueType, value))
      {
         String oldValue = this.valueType;
         this.valueType = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_VALUETYPE, oldValue, value);
      }
   }
   
   public ChangeEvent withValueType(String value)
   {
      setValueType(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_OPCODE = "opCode";
   
   private String opCode;

   public String getOpCode()
   {
      return this.opCode;
   }
   
   public void setOpCode(String value)
   {
      if ( ! StrUtil.stringEquals(this.opCode, value))
      {
         String oldValue = this.opCode;
         this.opCode = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_OPCODE, oldValue, value);
      }
   }
   
   public ChangeEvent withOpCode(String value)
   {
      setOpCode(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_CHANGENO = "changeNo";
   
   private String changeNo;

   public String getChangeNo()
   {
      return this.changeNo;
   }
   
   public void setChangeNo(String value)
   {
      if ( ! StrUtil.stringEquals(this.changeNo, value))
      {
         String oldValue = this.changeNo;
         this.changeNo = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CHANGENO, oldValue, value);
      }
   }
   
   public ChangeEvent withChangeNo(String value)
   {
      setChangeNo(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_SESSIONID = "sessionId";
   
   private String sessionId;

   public String getSessionId()
   {
      return this.sessionId;
   }
   
   public void setSessionId(String value)
   {
      if ( ! StrUtil.stringEquals(this.sessionId, value))
      {
         String oldValue = this.sessionId;
         this.sessionId = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SESSIONID, oldValue, value);
      }
   }
   
   public ChangeEvent withSessionId(String value)
   {
      setSessionId(value);
      return this;
   } 
}
