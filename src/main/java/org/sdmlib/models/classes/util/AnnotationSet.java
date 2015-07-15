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
   
package org.sdmlib.models.classes.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.sdmlib.models.classes.Annotation;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;

public class AnnotationSet extends SDMSet<Annotation>
{

   public static final AnnotationSet EMPTY_SET = new AnnotationSet().withReadOnly(true);


   public AnnotationPO hasAnnotationPO()
   {
      return new AnnotationPO(this.toArray(new Annotation[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Annotation";
   }


   @SuppressWarnings("unchecked")
   public AnnotationSet with(Object value)
   {
      if (value instanceof java.util.Collection)
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

   public Set<String> getValues()
   {
      Set<String> result = new HashSet<String>();
      
      for (Annotation obj : this)
      {
         result.addAll(obj.getValues());
      }
      
      return result;
   }

   public AnnotationSet hasValues(Set<String> value)
   {
      AnnotationSet result = new AnnotationSet();
      
      for (Annotation obj : this)
      {
         if (value == obj.getValues())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public AnnotationSet withValues(Set<String> value)
   {
      for (Annotation obj : this)
      {
         obj.setValues(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Annotation obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public AnnotationSet hasName(String value)
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

   public AnnotationSet hasName(String lower, String upper)
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

   public AnnotationSet withName(String value)
   {
      for (Annotation obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }
}
