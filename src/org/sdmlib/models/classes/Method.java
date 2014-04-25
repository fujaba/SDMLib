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

import java.util.LinkedHashSet;

import org.sdmlib.StrUtil;
import org.sdmlib.models.classes.logic.GenMethod;
import org.sdmlib.models.classes.util.AttributeSet;
import org.sdmlib.models.classes.util.MethodSet;

public class Method extends SDMLibClass
{
   public static final String PROPERTY_RETURNTYPE = "returnType";
   public static final String PROPERTY_PARAMETERS = "parameter";
   public static final MethodSet EMPTY_SET = new MethodSet();
   public static final String PROPERTY_BODY = "body";
   public static final String PROPERTY_CLAZZ = "clazz";
   public static final String PROPERTY_MODIFIER = "modifier";
   public static final String PROPERTY_NAME = "name";
   
   private String modifier = "public";
   private String name;
   private Clazz clazz = null;
   private String body;
   private AttributeSet parameters = null;
   private DataType returnType = DataType.VOID;

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
   
   //TODO Right so
   public String getSignature(){
      StringBuilder sb=new StringBuilder();
      
      sb.append(this.getName()+"(");
      boolean first=true;
      for(Attribute attribute : parameters){
         if(first){
            sb.append(attribute.getType().getValue()+" "+attribute.getName());
            first=false;
         }else{
            sb.append(","+attribute.getType().getValue()+" "+attribute.getName());
         }
      }
      return sb.toString();
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
   
   public Method(DataType returnType, Attribute... parameters)
   {
      this.withParameters(parameters);
      this.setReturnType(returnType);
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public Method withName(String name)
   {
      setName(name);
      return this;
   }
   
   
   /********************************************************************
    * <pre>
    *              one                       many
    * Clazz ----------------------------------- Attribute
    *              clazz                   attributes
    * </pre>
    */
   public AttributeSet getParameters()
   {
      if (this.parameters == null)
      {
         return new AttributeSet();
      }

      return this.parameters;
   }

   public boolean addToParameter(Attribute value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.parameters == null)
         {
            this.parameters = new AttributeSet();
         }

         changed = this.parameters.add (value);

         if (changed)
         {
//FIXME            value.setClazz(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PARAMETERS, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromParameter(Attribute value)
   {
      boolean changed = false;

      if ((this.parameters != null) && (value != null))
      {
         changed = this.parameters.remove (value);

         if (changed)
         {
            value.setClazz(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PARAMETERS, value, null);
         }
      }

      return changed;   
   }

   public Method withParameter(Attribute value)
   {
      addToParameter(value);
      return this;
   }
   
   public Method withParameters(Attribute... value)
   {
      for(Attribute parameter : value){
         addToParameter(parameter);
      }
      return this;
   }
   

   public Method withoutParameter(Attribute value)
   {
      removeFromParameter(value);
      return this;
   } 

   public void removeAllFromParameter()
   {
      LinkedHashSet<Attribute> tmpSet = new LinkedHashSet<Attribute>(this.getParameters());

      for (Attribute value : tmpSet)
      {
         this.removeFromParameter(value);
      }
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

   public DataType getReturnType()
   {
      return this.returnType;
   }
   
   public void setReturnType(DataType value)
   {
      if (( this.returnType==null && value!=null) || (this.returnType!=null && this.returnType!=value))
      {
         DataType oldValue = this.returnType;
         this.returnType = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_RETURNTYPE, oldValue, value);
      }
   }
   
   public Method withReturnType(DataType value)
   {
      setReturnType(value);
      return this;
   } 

   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getParameters());
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
