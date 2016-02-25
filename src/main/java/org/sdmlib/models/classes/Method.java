/*
   Copyright (c) 2016 zuendorf
   
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

import org.sdmlib.models.classes.SDMLibClass;
import de.uniks.networkparser.graph.DataType;
import org.sdmlib.StrUtil;
import org.sdmlib.models.classes.util.ParameterSet;
import org.sdmlib.models.classes.Parameter;
   /**
    * 
    * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/ClassModelTest.java'>ClassModelTest.java</a>
 */
   public  class Method extends SDMLibClass
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
   
      super.removeYou();

      withoutParameter(this.getParameter().toArray(new Parameter[this.getParameter().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_RETURNTYPE = "returnType";
   
   private DataType returnType;

   public DataType getReturnType()
   {
      return this.returnType;
   }
   
   public void setReturnType(DataType value)
   {
      if (this.returnType != value) {
      
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

   
   //==========================================================================
   
   public static final String PROPERTY_BODY = "body";
   
   private String body;

   public String getBody()
   {
      return this.body;
   }
   
   public void setBody(String value)
   {
      if ( ! StrUtil.stringEquals(this.body, value)) {
      
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


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getBody());
      result.append(" ").append(this.getName());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       many
    * Method ----------------------------------- Parameter
    *              method                   parameter
    * </pre>
    */
   
   public static final String PROPERTY_PARAMETER = "parameter";

   private ParameterSet parameter = null;
   
   public ParameterSet getParameter()
   {
      if (this.parameter == null)
      {
         return ParameterSet.EMPTY_SET;
      }
   
      return this.parameter;
   }

   public Method withParameter(Parameter... value)
   {
      if(value==null){
         return this;
      }
      for (Parameter item : value)
      {
         if (item != null)
         {
            if (this.parameter == null)
            {
               this.parameter = new ParameterSet();
            }
            
            boolean changed = this.parameter.add (item);

            if (changed)
            {
               item.withMethod(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PARAMETER, null, item);
            }
         }
      }
      return this;
   } 

   public Method withoutParameter(Parameter... value)
   {
      for (Parameter item : value)
      {
         if ((this.parameter != null) && (item != null))
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

   public Parameter createParameter()
   {
      Parameter value = new Parameter();
      withParameter(value);
      return value;
   } 
}
