/*
   Copyright (c) 2012 NeTH 
   
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
   
package org.sdmlib.codegen;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;
import java.beans.PropertyChangeListener;

public class LocalVarTableEntry implements PropertyChangeInterface
{
   public String toString()
   {
   	//   	StringBuilder _ = new StringBuilder();
   	//   	_.append(" ").append(this.getName());
   	//      _.append(" ").append(this.getType());
   	//      _.append(" ").append(this.getStartPos());
   	//      _.append(" ").append(this.getEndPos());
      return "" + name + " : " + type;
   }
   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_INITSEQUENCE.equalsIgnoreCase(attrName))
      {
         return getInitSequence();
      }

      if (PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         return getType();
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_INITSEQUENCE.equalsIgnoreCase(attrName))
      {
         return getInitSequence();
      }

      if (PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         return getType();
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         return getType();
      }

      if (PROPERTY_INITSEQUENCE.equalsIgnoreCase(attrName))
      {
         return getInitSequence();
      }

      if (PROPERTY_STARTPOS.equalsIgnoreCase(attrName))
      {
         return getStartPos();
      }

      if (PROPERTY_ENDPOS.equalsIgnoreCase(attrName))
      {
         return getEndPos();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_INITSEQUENCE.equalsIgnoreCase(attrName))
      {
         setInitSequence((ArrayList<ArrayList<String>>) value);
         return true;
      }

      if (PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         setType((String) value);
         return true;
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_INITSEQUENCE.equalsIgnoreCase(attrName))
      {
         setInitSequence((ArrayList<ArrayList<String>>) value);
         return true;
      }

      if (PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         setType((String) value);
         return true;
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         setType((String) value);
         return true;
      }

      if (PROPERTY_INITSEQUENCE.equalsIgnoreCase(attrName))
      {
         setInitSequence((ArrayList<ArrayList<String>>) value);
         return true;
      }

      if (PROPERTY_STARTPOS.equalsIgnoreCase(attrName))
      {
         setStartPos((Integer) value);
         return true;
      }

      if (PROPERTY_ENDPOS.equalsIgnoreCase(attrName))
      {
         setEndPos((Integer) value);
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
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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
   
   public LocalVarTableEntry withName(String value)
   {
      setName(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_TYPE = "type";
   
   private String type;
   
   public String getType()
   {
      return this.type;
   }
   
   public void setType(String value)
   {
      if ( ! StrUtil.stringEquals(this.type, value))
      {
         String oldValue = this.type;
         this.type = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TYPE, oldValue, value);
      }
   }
   
   public LocalVarTableEntry withType(String value)
   {
      setType(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_INITSEQUENCE = "initSequence";
   
   private ArrayList<ArrayList<String>> initSequence;
   
   public ArrayList<ArrayList<String>> getInitSequence()
   {
      return this.initSequence;
   }
   
   
   
   //==========================================================================
   
   public static final String PROPERTY_STARTPOS = "startPos";
   
   private int startPos;

   public int getStartPos()
   {
      return this.startPos;
   }
   
   public void setStartPos(int value)
   {
      if (this.startPos != value)
      {
         int oldValue = this.startPos;
         this.startPos = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STARTPOS, oldValue, value);
      }
   }
   
   public LocalVarTableEntry withStartPos(int value)
   {
      setStartPos(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_ENDPOS = "endPos";
   
   private int endPos;

   public int getEndPos()
   {
      return this.endPos;
   }
   
   public void setEndPos(int value)
   {
      if (this.endPos != value)
      {
         int oldValue = this.endPos;
         this.endPos = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ENDPOS, oldValue, value);
      }
   }
   
   public LocalVarTableEntry withEndPos(int value)
   {
      setEndPos(value);
      return this;
   } 

   public void setInitSequence(ArrayList<ArrayList<String>> value)
   {
      if (this.initSequence != value)
      {
         ArrayList<ArrayList<String>> oldValue = this.initSequence;
         this.initSequence = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_INITSEQUENCE, oldValue, value);
      }
   }
   
   public LocalVarTableEntry withInitSequence(ArrayList<ArrayList<String>> value)
   {
      setInitSequence(value);
      return this;
   }

}

