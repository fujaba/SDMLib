/*
   Copyright (c) 2012 Albert Zündorf

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

import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class SymTabEntry implements PropertyChangeInterface 
{   
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      _.append(" ").append(this.getKind());
      _.append(" ").append(this.getMemberName());
      _.append(" ").append(this.getType());
      _.append(" ").append(this.getStartPos());
      _.append(" ").append(this.getBodyStartPos());
      _.append(" ").append(this.getEndPos());
      _.append(" ").append(this.getModifiers());
      return "" + type;
   }
   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_MODIFIERS.equalsIgnoreCase(attrName))
      {
         return getModifiers();
      }

      if (PROPERTY_ENDPOS.equalsIgnoreCase(attrName))
      {
         return getEndPos();
      }

      if (PROPERTY_BODYSTARTPOS.equalsIgnoreCase(attrName))
      {
         return getBodyStartPos();
      }

      if (PROPERTY_STARTPOS.equalsIgnoreCase(attrName))
      {
         return getStartPos();
      }

      if (PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         return getType();
      }

      if (PROPERTY_MEMBERNAME.equalsIgnoreCase(attrName))
      {
         return getMemberName();
      }

      if (PROPERTY_KIND.equalsIgnoreCase(attrName))
      {
         return getKind();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_MODIFIERS.equalsIgnoreCase(attrName))
      {
         setModifiers((String) value);
         return true;
      }

      if (PROPERTY_ENDPOS.equalsIgnoreCase(attrName))
      {
         setEndPos((Integer) value);
         return true;
      }

      if (PROPERTY_BODYSTARTPOS.equalsIgnoreCase(attrName))
      {
         setBodyStartPos((Integer) value);
         return true;
      }

      if (PROPERTY_STARTPOS.equalsIgnoreCase(attrName))
      {
         setStartPos((Integer) value);
         return true;
      }

      if (PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         setType((String) value);
         return true;
      }

      if (PROPERTY_MEMBERNAME.equalsIgnoreCase(attrName))
      {
         setMemberName((String) value);
         return true;
      }

      if (PROPERTY_KIND.equalsIgnoreCase(attrName))
      {
         setKind((String) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   public static final String PROPERTY_KIND = "kind";
   
   private String kind;
   
   public String getKind()
   {
      return this.kind;
   }
   
   public void setKind(String value)
   {
      this.kind = value;
   }
   
   public SymTabEntry withKind(String value)
   {
      setKind(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_MEMBERNAME = "memberName";
   
   private String memberName;
   
   public String getMemberName()
   {
      return this.memberName;
   }
   
   public void setMemberName(String value)
   {
      this.memberName = value;
   }
   
   public SymTabEntry withMemberName(String value)
   {
      setMemberName(value);
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
      this.type = value;
   }
   
   public SymTabEntry withType(String value)
   {
      setType(value);
      return this;
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
      this.startPos = value;
   }
   
   public SymTabEntry withStartPos(int value)
   {
      setStartPos(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_BODYSTARTPOS = "bodyStartPos";
   
   private int bodyStartPos;
   
   public int getBodyStartPos()
   {
      return this.bodyStartPos;
   }
   
   public void setBodyStartPos(int value)
   {
      this.bodyStartPos = value;
   }
   
   public SymTabEntry withBodyStartPos(int value)
   {
      setBodyStartPos(value);
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
      this.endPos = value;
   }
   
   public SymTabEntry withEndPos(int value)
   {
      setEndPos(value);
      return this;
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
   
   public static final String PROPERTY_MODIFIERS = "modifiers";
   
   private String modifiers;
   
   public String getModifiers()
   {
      return this.modifiers;
   }
   
   public void setModifiers(String value)
   {
      if ( ! StrUtil.stringEquals(this.modifiers, value))
      {
         String oldValue = this.modifiers;
         this.modifiers = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MODIFIERS, oldValue, value);
      }
   }
   
   public SymTabEntry withModifiers(String value)
   {
      setModifiers(value);
      return this;
   } 

}

