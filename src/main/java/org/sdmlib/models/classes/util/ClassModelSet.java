/*
   Copyright (c) 2014 zuendorf 
   
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
import java.util.Collections;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Enumeration;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.classes.util.ClazzSet;
import org.sdmlib.models.classes.util.EnumerationSet;

public class ClassModelSet extends SDMSet<ClassModel> implements org.sdmlib.models.modelsets.ModelSet
{
   public ClazzSet getClasses()
   {
      ClazzSet result = new ClazzSet();
      
      for (ClassModel obj : this)
      {
         result.addAll(obj.getClasses());
      }
      
      return result;
   }
   public ClassModelSet withClasses(Clazz value)
   {
      for (ClassModel obj : this)
      {
         obj.with(value);
      }
      
      return this;
   }

   public ClassModelSet withoutClasses(Clazz value)
   {
      for (ClassModel obj : this)
      {
         obj.without(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (ClassModel obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public ClassModelSet withName(String value)
   {
      for (ClassModel obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }



   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (ClassModel elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.classes.ClassModel";
   }


   public ClassModelPO startModelPattern()
   {
      return new ClassModelPO(this.toArray(new ClassModel[this.size()]));
   }


   @SuppressWarnings("unchecked")
   public ClassModelSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ClassModel>)value);
      }
      else if (value != null)
      {
         this.add((ClassModel) value);
      }
      
      return this;
   }
   
   public ClassModelSet without(ClassModel value)
   {
      this.remove(value);
      return this;
   }

   public ClassModelPO hasClassModelPO()
   {
      return new ClassModelPO(this.toArray(new ClassModel[this.size()]));
   }
   
   public EnumerationSet getEnumerations()
   {
      EnumerationSet result = new EnumerationSet();
      
      for (ClassModel obj : this)
      {
         result.addAll(obj.getEnumerations());
      }
      
      return result;
   }

   public ClassModelSet hasEnumerations(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      ClassModelSet answer = new ClassModelSet();
      
      for (ClassModel obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getEnumerations()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ClassModelSet withEnumerations(Enumeration value)
   {
      for (ClassModel obj : this)
      {
         obj.withEnumerations(value);
      }
      
      return this;
   }

   public ClassModelSet withoutEnumerations(Enumeration value)
   {
      for (ClassModel obj : this)
      {
         obj.withoutEnumerations(value);
      }
      
      return this;
   }


   public static final ClassModelSet EMPTY_SET = new ClassModelSet().withReadOnly(true);
}




