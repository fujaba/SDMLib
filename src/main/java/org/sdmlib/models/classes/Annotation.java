/*
   Copyright (c) 2015 christian 
   
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

import java.util.HashSet;
import java.util.Set;
import org.sdmlib.StrUtil;

public class Annotation extends SDMLibClass
{

   // ==========================================================================

   @Override
   public void removeYou()
   {
      super.removeYou();

      setClazz(null);
      setMethod(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   /********************************************************************
    * <pre>
    *              many                       one
    * Annotation ----------------------------------- Clazz
    *              annotations                   clazz
    * </pre>
    */

   public static final String PROPERTY_CLAZZ = "clazz";

   private Clazz clazz = null;

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
            oldValue.withoutAnnotations(this);
         }

         this.clazz = value;

         if (value != null)
         {
            value.withAnnotations(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_CLAZZ, oldValue, value);
         changed = true;
      }

      return changed;
   }

   public Annotation withClazz(Clazz value)
   {
      setClazz(value);
      return this;
   }

   public Clazz createClazz()
   {
      Clazz value = new Clazz(null);
      withClazz(value);
      return value;
   }

   /********************************************************************
    * <pre>
    *              many                       one
    * Annotation ----------------------------------- Method
    *              annotations                   method
    * </pre>
    */

   public static final String PROPERTY_METHOD = "method";

   private Method method = null;

   public Method getMethod()
   {
      return this.method;
   }

   public boolean setMethod(Method value)
   {
      boolean changed = false;

      if (this.method != value)
      {
         Method oldValue = this.method;

         if (this.method != null)
         {
            this.method = null;
            oldValue.withoutAnnotations(this);
         }

         this.method = value;

         if (value != null)
         {
            value.withAnnotations(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_METHOD, oldValue, value);
         changed = true;
      }

      return changed;
   }

   public Annotation withMethod(Method value)
   {
      setMethod(value);
      return this;
   }

   public Method createMethod()
   {
      Method value = new Method();
      withMethod(value);
      return value;
   }

   @Override
   public Annotation withName(String value)
   {
      setName(value);
      return this;
   }

   // ==========================================================================
   public static Annotation createOverrideAnnotation()
   {
      return new Annotation().withName(OVERRIDE);
   }

   // ==========================================================================
   public static Annotation createDeprecatedAnnotation()
   {
      return new Annotation().withName(DEPRECATED);
   }

   // ==========================================================================
   public static Annotation createSuppressWarningsAnnotation(String... values)
   {
      return new Annotation().withName(SUPPRESS_WARNINGS).withValues(values);
   }

   // ==========================================================================
   public static Annotation createSafeVarargsAnnotation()
   {
      return new Annotation().withName(SAFE_VARGARGS);
   }

   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();

      result.append(" ").append(this.getName());
      return result.substring(1);
   }

   // ==========================================================================
   public static final String PROPERTY_VALUES = "values";

   private Set<String> values;

   public Set<String> createValues()
   {
      if (values == null)
      {
         values = new HashSet<String>();
      }
      return values;
   }

   public Set<String> getValues()
   {
      if (values == null)
      {
         values = new HashSet<String>();
      }
      return values;
   }

   public void setValues(Set<String> values)
   {
      Set<String> oldValues = this.values;
      this.values = values;
      getPropertyChangeSupport().firePropertyChange(PROPERTY_VALUES, oldValues, values);
   }

   public Annotation withValues(String... values)
   {
      if (this.values == null)
      {
         this.values = new HashSet<String>();
      }
      for (String value : values)
      {
         if (this.values.add(value))
         {
            getPropertyChangeSupport().firePropertyChange(PROPERTY_VALUES, null, value);
         }
      }
      return this;
   }

   public Annotation withoutValues(String... values)
   {
      if (this.values == null)
      {
         this.values = new HashSet<String>();
      }
      for (String value : values)
      {
         if (this.values.remove(value))
         {
            getPropertyChangeSupport().firePropertyChange(PROPERTY_VALUES, value, null);
         }
      }
      return this;
   }

   // ==========================================================================

   public static final String DEPRECATED = "Deprecated";

   // ==========================================================================

   public static final String OVERRIDE = "Override";

   // ==========================================================================

   public static final String SAFE_VARGARGS = "SafeVarargs";

   // ==========================================================================

   public static final String SUPPRESS_WARNINGS = "SuppressWarnings";



   }
