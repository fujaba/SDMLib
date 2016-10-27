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

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeListener;
import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/ClassModelTest.java'>ClassModelTest.java</a>
*/
   public class SymTabEntry implements PropertyChangeInterface, SendableEntity 
{   
   public static final String PROPERTY_KIND = "kind";
   public static final String PROPERTY_MEMBERNAME = "memberName";
   public static final String PROPERTY_TYPE = "type";
   public static final String PROPERTY_STARTPOS = "startPos";
   public static final String PROPERTY_BODYSTARTPOS = "bodyStartPos";
   public static final String PROPERTY_ENDPOS = "endPos";
   public static final String PROPERTY_MODIFIERS = "modifiers";
   
   private String modifiers;
   private int endPos;
   private int bodyStartPos;
   private int startPos;
   private String type;
   private String throwsTags;
   private String memberName;
   private String kind;
   
   @Override
   public String toString()
   {
//      TODO :  don't know
//      StringBuilder _ = new StringBuilder();
//      s.append(" ").append(this.getKind());
//      s.append(" ").append(this.getMemberName());
//      s.append(" ").append(this.getType());
//      s.append(" ").append(this.getStartPos());
//      s.append(" ").append(this.getBodyStartPos());
//      s.append(" ").append(this.getEndPos());
//      s.append(" ").append(this.getModifiers());
//      result.append(" ").append(this.getAnnotations());
//      result.append(" ").append(this.getPreCommentStartPos());
//      result.append(" ").append(this.getPreCommentEndPos());
//      result.append(" ").append(this.getAnnotationsStartPos());
      return "" + type;
   }   
   //==========================================================================
   
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
		if (listeners != null) {
			listeners.removePropertyChangeListener(listener);
		}
		return true;
	}

	public boolean removePropertyChangeListener(String property,
			PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}
   
   //==========================================================================
   
   public void removeYou()
   {
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
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
   
   public SymTabEntry withThrowsTags(String value)
   {
      this.throwsTags = value;
      return this;
   } 
   
   public String getThrowsTags() {
		return throwsTags;
	} 

   
   //==========================================================================
   
   public static final String PROPERTY_ANNOTATIONS = "annotations";
   
   private String annotations;

   public String getAnnotations()
   {
      return this.annotations;
   }
   
   public void setAnnotations(String value)
   {
      if ( ! StrUtil.stringEquals(this.annotations, value))
      {
         String oldValue = this.annotations;
         this.annotations = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ANNOTATIONS, oldValue, value);
      }
   }
   
   public SymTabEntry withAnnotations(String value)
   {
      setAnnotations(value);
      return this;
   }

   
   //==========================================================================
   
   public static final String PROPERTY_PRECOMMENTSTARTPOS = "preCommentStartPos";
   
   private int preCommentStartPos;

   public int getPreCommentStartPos()
   {
      return this.preCommentStartPos;
   }
   
   public void setPreCommentStartPos(int value)
   {
      if (this.preCommentStartPos != value) {
      
         int oldValue = this.preCommentStartPos;
         this.preCommentStartPos = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PRECOMMENTSTARTPOS, oldValue, value);
      }
   }
   
   public SymTabEntry withPreCommentStartPos(int value)
   {
      setPreCommentStartPos(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_PRECOMMENTENDPOS = "preCommentEndPos";
   
   private int preCommentEndPos;

   public int getPreCommentEndPos()
   {
      return this.preCommentEndPos;
   }
   
   public void setPreCommentEndPos(int value)
   {
      if (this.preCommentEndPos != value) {
      
         int oldValue = this.preCommentEndPos;
         this.preCommentEndPos = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PRECOMMENTENDPOS, oldValue, value);
      }
   }
   
   public SymTabEntry withPreCommentEndPos(int value)
   {
      setPreCommentEndPos(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_ANNOTATIONSSTARTPOS = "annotationsStartPos";
   
   private int annotationsStartPos;

   public int getAnnotationsStartPos()
   {
      return this.annotationsStartPos;
   }
   
   public void setAnnotationsStartPos(int value)
   {
      if (this.annotationsStartPos != value) {
      
         int oldValue = this.annotationsStartPos;
         this.annotationsStartPos = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ANNOTATIONSSTARTPOS, oldValue, value);
      }
   }
   
   public SymTabEntry withAnnotationsStartPos(int value)
   {
      setAnnotationsStartPos(value);
      return this;
   } 
}
