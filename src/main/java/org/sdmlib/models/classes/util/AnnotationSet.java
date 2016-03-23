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
   
package org.sdmlib.models.classes.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.classes.Annotation;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.classes.util.AnnotationSet;
import org.sdmlib.models.modelsets.StringList;

public class AnnotationSet extends SDMSet<Annotation>
{

   public static final AnnotationSet EMPTY_SET = new AnnotationSet().withFlag(AnnotationSet.READONLY);


   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Annotation";
   }


   @SuppressWarnings("unchecked")
   public AnnotationSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Annotation>)value);
      }
      else if (value != null)
      {
         this.add((Annotation) value);
      }
      
      return this;
   }
   
   public AnnotationSet without(Annotation value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public AnnotationSet filter(Condition<Annotation> newValue) {
      AnnotationSet filterList = new AnnotationSet();
      filterItems(filterList, newValue);
      return filterList;
   }
   
   //==========================================================================
   
   public AnnotationSet createOverrideAnnotation()
   {
      AnnotationSet result = new AnnotationSet();
      for (Annotation obj : this)
      {
         result.add(obj.createOverrideAnnotation());
      }
      return result;
   }

   
   //==========================================================================
   
   public AnnotationSet createDeprecatedAnnotation()
   {
      AnnotationSet result = new AnnotationSet();
      for (Annotation obj : this)
      {
         result.add(obj.createDeprecatedAnnotation());
      }
      return result;
   }

   
   //==========================================================================
   
   public AnnotationSet createSuppressWarningsAnnotation(Object... values)
   {
      AnnotationSet result = new AnnotationSet();
      for (Annotation obj : this)
      {
         result.add(obj.createSuppressWarningsAnnotation(values));
      }
      return result;
   }

   
   //==========================================================================
   
   public AnnotationSet createSafeVarargsAnnotation()
   {
      AnnotationSet result = new AnnotationSet();
      for (Annotation obj : this)
      {
         result.add(obj.createSafeVarargsAnnotation());
      }
      return result;
   }


   /**
    * Loop through the current set of Annotation objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Annotation obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Annotation objects and collect those Annotation objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Annotation objects that match the parameter
    */
   public AnnotationSet filterName(String value)
   {
      AnnotationSet result = new AnnotationSet();
      
      for (Annotation obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Annotation objects and collect those Annotation objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Annotation objects that match the parameter
    */
   public AnnotationSet filterName(String lower, String upper)
   {
      AnnotationSet result = new AnnotationSet();
      
      for (Annotation obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Annotation objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Annotation objects now with new attribute values.
    */
   public AnnotationSet withName(String value)
   {
      for (Annotation obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public AnnotationSet createSuppressWarningsAnnotation(String... values)
   {
      AnnotationSet result = new AnnotationSet();
      for (Annotation obj : this)
      {
         result.add(obj.createSuppressWarningsAnnotation(values));
      }
      return result;
   }

}
