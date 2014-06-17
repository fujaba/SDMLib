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
import org.sdmlib.models.classes.util.MethodSet;
import org.sdmlib.models.classes.util.ParameterSet;

public class Method extends SDMLibClass
{
   public static final String PROPERTY_RETURNTYPE = "returnType";
   public static final String PROPERTY_PARAMETER = "parameter";
   public static final String PROPERTY_BODY = "body";
   public static final String PROPERTY_CLAZZ = "clazz";
   public static final String PROPERTY_MODIFIER = "modifier";
   public static final MethodSet EMPTY_SET = new MethodSet().withReadonly(true);
   
   private Visibility modifier = Visibility.PUBLIC;
   private Clazz clazz = null;
   private String body;
   private ParameterSet parameter = null;
   private DataType returnType = DataType.VOID;

   public String getSignature(boolean includeName){
      StringBuilder sb=new StringBuilder();
      
      sb.append(this.getName()+"(");
      boolean first=true;
      int i = 0;
      
      for(Parameter parameter : getParameter()){
        
         if(first){
            sb.append(getParameterSignature(includeName, parameter, i));
            first=false;
         }else{
            sb.append(getParameterSignature(includeName, parameter, i));
         }
         
         if ( i < getParameter().size()-1 ) {
            if(includeName){
               sb.append(", ");
            }else{
               sb.append(",");
            }
         }
         i++;
      }
      sb.append(")");
      return sb.toString();
   }
   private String getParameterSignature(boolean includeName, Parameter parameter, int i){
      String param=parameter.getType().getValue();
      if(!includeName){
         return param;
      }
      String name="";
      if(parameter.getName()!=null){
         name = parameter.getName().trim();
      }
      if(name!=""){
         return param+" "+name;
      }
      return param+" p" + i;
   }
     
   protected Method()
   {
      
   }
   
   public Method(String name, DataType returnType, Parameter... parameters)
   {
      this.setName(name);
      this.with(parameters);
      this.setReturnType(returnType);
   }
   
   public Method(String name, Parameter... parameters)
   {
      this.with(parameters);
      this.setName(name);
   }

   @Override
   public Method withName(String name)
   {
      setName(name);
      return this;
   }
   
   public Method with(Parameter... value)
   {
      if(value==null){
         return this;
      }
      if (this.parameter == null)
      {
         this.parameter = new ParameterSet();
      }
      for (Parameter item : value)
      {
         if (item != null)
         {
            if (this.parameter.add (item))
            {
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PARAMETER, null, item);
            }
         }
      }
      return this;
   }
   
   public Method withParameter(String paramName, DataType dataType)
   {
      this.createParameter().withName(paramName).with(dataType);
      return this;
   }

   public Method without(Parameter... value)
   {
      if (this.parameter == null || value==null){
         return this;
      }
      for (Parameter item : value)
      {
         if(item != null)
         {
            if (this.parameter.remove(item))
            {
               item.setMethod(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PARAMETER, item, null);
            }
         }
      }
      return this;
   }

   public Visibility getModifier()
   {
      return this.modifier;
   }

   public void setModifier(Visibility value)
   {
      this.modifier = value;
   }

   public Method with(Visibility value)
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
            oldValue.without(this);
         }
         
         this.clazz = value;
         
         if (value != null)
         {
            value.with(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CLAZZ, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Method with(Clazz value)
   {
      setClazz(value);
      return this;
   }
   //==========================================================================

   @Override
   public void removeYou()
   {
      super.removeYou();
      setClazz(null);
      without(this.getParameter().toArray(new Parameter[this.getParameter().size()]));
      withoutParameter(this.getParameter().toArray(new Parameter[this.getParameter().size()]));
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
      _.append(" ").append(this.getModifier());
      _.append(" ").append(this.getReturnType());
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getParameter());
      if(this.body!=null){
         _.append(" ").append(this.getBody());
      }
      return _.substring(1);
   }

   public String getBody()
   {
      return this.body;
   }
   
   public boolean setBody(String value)
   {
      if ( ! StrUtil.stringEquals(this.body, value))
      {
         String oldValue = this.body;
         this.body = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BODY, oldValue, value);
         return true;
      }
      return false;
   }
   
   public Method withBody(String value)
   {
      setBody(value);
      return this;
   }

   public ParameterSet getParameter()
   {
      if (this.parameter == null)
      {
         return Parameter.EMPTY_SET;
      }
   
      return this.parameter;
   }

   public Parameter createParameter(DataType type)
   {
      Parameter value = new Parameter(type);
      with(value);
      return value;
   } 

   Method withParameter(Parameter... value)
   {
      return with(value);
   } 

   Method withoutParameter(Parameter... value)
   {
      return without(value);
   }

   Parameter createParameter()
   {
      Parameter value = new Parameter();
      withParameter(value);
      return value;
   } 

   Method withClazz(Clazz value)
   {
      setClazz(value);
      return this;
   } 

   Clazz createClazz()
   {
      Clazz value = new Clazz(null);
      withClazz(value);
      return value;
   } 
}

