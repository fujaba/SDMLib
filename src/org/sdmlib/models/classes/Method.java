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

package org.sdmlib.models.classes;

import org.sdmlib.StrUtil;
import org.sdmlib.models.classes.logic.GenMethod;
import org.sdmlib.models.classes.util.MethodSet;

public class Method extends SDMLibClass
{
   public static final String PROPERTY_RETURNTYPE = "returnType";
   public static final String PROPERTY_SIGNATURE = "signature";
   public static final MethodSet EMPTY_SET = new MethodSet();
   public static final String PROPERTY_BODY = "body";
   public static final String PROPERTY_CLAZZ = "clazz";
   public static final String PROPERTY_MODIFIER = "modifier";

   private String modifier = "public";
   private Clazz clazz = null;
   private String body;
   private String signature;  
   private String returnType = "void";

   private GenMethod generator;

   public void setGenerator(GenMethod value)
   {
      if (this.generator != value)
      {
         GenMethod oldValue = this.generator;
         if (this.generator != null)
         {
            this.generator = null;
            oldValue.setModel(null);
         }
         this.generator = value;
         if (value != null)
         {
            value.setModel(this);
         }
      }
   }
   
   public GenMethod getGenerator(){
      if(generator==null){
         this.setGenerator(new GenMethod());
      }
      return generator;
   }

   
   public Method()
   {
      
   }
   
   public Method(String signature, String returnType)
   {
      this.setSignature(signature);
      this.setReturnType(returnType);
   }

   public String getSignature()
   {
      return this.signature;
   }

   public void setSignature(String value)
   {
	  value = value.replaceAll(" ", "");
      this.signature = value;
   }

   public Method withSignature(String value)
   {
      setSignature(value);
      return this;
   } 
   
   public String getModifier()
   {
      return this.modifier;
   }

   public void setModifier(String value)
   {
      this.modifier = value;
   }

   public Method withModifier(String value)
   {
      setModifier(value);
      return this;
   }


   /********************************************************************
    * <pre>
    *              many                       one
    * Method ----------------------------------- Clazz
    *              methods                   clazz
    * </pre>
    */
   public Clazz getClazz()
   {
      return this.clazz;
   }

   public boolean setClazz(Clazz value)
   {
      boolean changed = false;

      if (this.clazz != value)
      {
         Clazz oldValue = this.clazz;

         if (this.clazz != null)
         {
            this.clazz = null;
            oldValue.withoutMethods(this);
         }

         this.clazz = value;

         if (value != null)
         {
            value.withMethods(this);
         }

         // getPropertyChangeSupport().firePropertyChange(PROPERTY_CLAZZ, null, value);
         changed = true;
      }

      return changed;
   }

   public Method withClazz(Clazz value)
   {
      setClazz(value);
      return this;
   }

   //==========================================================================

   public boolean set(String attrName, Object value)
   {

      return false;
   }
   
   //==========================================================================

   public void removeYou()
   {
      setClazz(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   public String getReturnType()
   {
      return this.returnType;
   }
   
   public void setReturnType(String value)
   {
      if ( ! StrUtil.stringEquals(this.returnType, value))
      {
         String oldValue = this.returnType;
         this.returnType = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_RETURNTYPE, oldValue, value);
      }
   }
   
   public Method withReturnType(String value)
   {
      setReturnType(value);
      return this;
   } 

   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getSignature());
      _.append(" ").append(this.getReturnType());
      _.append(" ").append(this.getBody());
      return _.substring(1);
   }

   public String getBody()
   {
      return this.body;
   }
   
   public void setBody(String value)
   {
      if ( ! StrUtil.stringEquals(this.body, value))
      {
         String oldValue = this.body;
         this.body = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BODY, oldValue, value);
      }
   }
   
   public Method withBody(String value)
   {
      setBody(value);
      return this;
   }
}
