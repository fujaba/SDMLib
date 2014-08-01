/*
   Copyright (c) 2014 christian 
   
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
import org.sdmlib.models.classes.util.MethodSet;
import org.sdmlib.models.classes.util.EnumerationValueSet;
import org.sdmlib.models.classes.util.EnumerationSet;

public class Enumeration extends SDMLibClass
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      super.removeYou();

      withoutMethods(this.getMethods().toArray(new Method[this.getMethods().size()]));
      withoutValues(this.getValues().toArray(new EnumerationValue[this.getValues().size()]));
      setClassModel(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       many
    * Enumeration ----------------------------------- Method
    *              enumeration                   methods
    * </pre>
    */
   
   public static final String PROPERTY_METHODS = "methods";

   private MethodSet methods = null;
   
   public MethodSet getMethods()
   {
      if (this.methods == null)
      {
         return Method.EMPTY_SET;
      }
   
      return this.methods;
   }

   public Enumeration withMethods(Method... value)
   {
      if(value==null){
         return this;
      }
      for (Method item : value)
      {
         if (item != null)
         {
            if (this.methods == null)
            {
               this.methods = new MethodSet();
            }
            
            boolean changed = this.methods.add (item);

            if (changed)
            {
               item.withEnumeration(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_METHODS, null, item);
            }
         }
      }
      return this;
   } 

   public Enumeration withoutMethods(Method... value)
   {
      for (Method item : value)
      {
         if ((this.methods != null) && (item != null))
         {
            if (this.methods.remove(item))
            {
               item.setEnumeration(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_METHODS, item, null);
            }
         }
      }
      return this;
   }

   public Method createMethods()
   {
      Method value = new Method();
      withMethods(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Enumeration ----------------------------------- EnumerationValue
    *              enumeration                   values
    * </pre>
    */
   
   public static final String PROPERTY_VALUES = "values";

   private EnumerationValueSet values = null;
   
   public EnumerationValueSet getValues()
   {
      if (this.values == null)
      {
         return EnumerationValue.EMPTY_SET;
      }
   
      return this.values;
   }

   public Enumeration withValues(EnumerationValue... value)
   {
      if(value==null){
         return this;
      }
      for (EnumerationValue item : value)
      {
         if (item != null)
         {
            if (this.values == null)
            {
               this.values = new EnumerationValueSet();
            }
            
            boolean changed = this.values.add (item);

            if (changed)
            {
               item.withEnumeration(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_VALUES, null, item);
            }
         }
      }
      return this;
   } 

   public Enumeration withoutValues(EnumerationValue... value)
   {
      for (EnumerationValue item : value)
      {
         if ((this.values != null) && (item != null))
         {
            if (this.values.remove(item))
            {
               item.setEnumeration(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_VALUES, item, null);
            }
         }
      }
      return this;
   }

   public EnumerationValue createValues()
   {
      EnumerationValue value = new EnumerationValue();
      withValues(value);
      return value;
   }


   @Override
   public SDMLibClass withName(String value)
   {
      setName(value);
      return this;
   } 

   
   public static final EnumerationSet EMPTY_SET = new EnumerationSet().withReadonly(true);

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Enumeration ----------------------------------- ClassModel
    *              enumerations                   classModel
    * </pre>
    */
   
   public static final String PROPERTY_CLASSMODEL = "classModel";

   private ClassModel classModel = null;

   public ClassModel getClassModel()
   {
      return this.classModel;
   }

   public boolean setClassModel(ClassModel value)
   {
      boolean changed = false;
      
      if (this.classModel != value)
      {
         ClassModel oldValue = this.classModel;
         
         if (this.classModel != null)
         {
            this.classModel = null;
            oldValue.withoutEnumerations(this);
         }
         
         this.classModel = value;
         
         if (value != null)
         {
            value.withEnumerations(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CLASSMODEL, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Enumeration withClassModel(ClassModel value)
   {
      setClassModel(value);
      return this;
   } 

   public ClassModel createClassModel()
   {
      ClassModel value = new ClassModel();
      withClassModel(value);
      return value;
   } 
}
