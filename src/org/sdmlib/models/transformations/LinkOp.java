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
   
package org.sdmlib.models.transformations;

import java.beans.PropertyChangeSupport;

import org.sdmlib.models.transformations.creators.LinkOpSet;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class LinkOp implements PropertyChangeInterface
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

      if (PROPERTY_SRCTEXT.equalsIgnoreCase(attrName))
      {
         return getSrcText();
      }

      if (PROPERTY_TGTTEXT.equalsIgnoreCase(attrName))
      {
         return getTgtText();
      }

      if (PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         return getSrc();
      }

      if (PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         return getTgt();
      }

      if (PROPERTY_TRANSFORMOP.equalsIgnoreCase(attrName))
      {
         return getTransformOp();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_SRCTEXT.equalsIgnoreCase(attrName))
      {
         setSrcText((String) value);
         return true;
      }

      if (PROPERTY_TGTTEXT.equalsIgnoreCase(attrName))
      {
         setTgtText((String) value);
         return true;
      }

      if (PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         setSrc((OperationObject) value);
         return true;
      }

      if (PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         setTgt((OperationObject) value);
         return true;
      }

      if (PROPERTY_TRANSFORMOP.equalsIgnoreCase(attrName))
      {
         setTransformOp((TransformOp) value);
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
      setSrc(null);
      setTgt(null);
      setTransformOp(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_SRCTEXT = "srcText";
   
   private String srcText = "";
   
   public String getSrcText()
   {
      return this.srcText;
   }
   
   public void setSrcText(String value)
   {
      if ( ! StrUtil.stringEquals(this.srcText, value))
      {
         String oldValue = this.srcText;
         this.srcText = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SRCTEXT, oldValue, value);
      }
   }
   
   public LinkOp withSrcText(String value)
   {
      setSrcText(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_TGTTEXT = "tgtText";
   
   private String tgtText = "";
   
   public String getTgtText()
   {
      return this.tgtText;
   }
   
   public void setTgtText(String value)
   {
      if ( ! StrUtil.stringEquals(this.tgtText, value))
      {
         String oldValue = this.tgtText;
         this.tgtText = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TGTTEXT, oldValue, value);
      }
   }
   
   public LinkOp withTgtText(String value)
   {
      setTgtText(value);
      return this;
   } 

   
   public static final LinkOpSet EMPTY_SET = new LinkOpSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * LinkOp ----------------------------------- OperationObject
    *              outgoings                   src
    * </pre>
    */
   
   public static final String PROPERTY_SRC = "src";
   
   private OperationObject src = null;
   
   public OperationObject getSrc()
   {
      return this.src;
   }
   
   public boolean setSrc(OperationObject value)
   {
      boolean changed = false;
      
      if (this.src != value)
      {
         OperationObject oldValue = this.src;
         
         if (this.src != null)
         {
            this.src = null;
            oldValue.withoutOutgoings(this);
         }
         
         this.src = value;
         
         if (value != null)
         {
            value.withOutgoings(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SRC, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public LinkOp withSrc(OperationObject value)
   {
      setSrc(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * LinkOp ----------------------------------- OperationObject
    *              incomings                   tgt
    * </pre>
    */
   
   public static final String PROPERTY_TGT = "tgt";
   
   private OperationObject tgt = null;
   
   public OperationObject getTgt()
   {
      return this.tgt;
   }
   
   public boolean setTgt(OperationObject value)
   {
      boolean changed = false;
      
      if (this.tgt != value)
      {
         OperationObject oldValue = this.tgt;
         
         if (this.tgt != null)
         {
            this.tgt = null;
            oldValue.withoutIncomings(this);
         }
         
         this.tgt = value;
         
         if (value != null)
         {
            value.withIncomings(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TGT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public LinkOp withTgt(OperationObject value)
   {
      setTgt(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * LinkOp ----------------------------------- TransformOp
    *              linkOps                   transformOp
    * </pre>
    */
   
   public static final String PROPERTY_TRANSFORMOP = "transformOp";
   
   private TransformOp transformOp = null;
   
   public TransformOp getTransformOp()
   {
      return this.transformOp;
   }
   
   public boolean setTransformOp(TransformOp value)
   {
      boolean changed = false;
      
      if (this.transformOp != value)
      {
         TransformOp oldValue = this.transformOp;
         
         if (this.transformOp != null)
         {
            this.transformOp = null;
            oldValue.withoutLinkOps(this);
         }
         
         this.transformOp = value;
         
         if (value != null)
         {
            value.withLinkOps(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TRANSFORMOP, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public LinkOp withTransformOp(TransformOp value)
   {
      setTransformOp(value);
      return this;
   } 
}

