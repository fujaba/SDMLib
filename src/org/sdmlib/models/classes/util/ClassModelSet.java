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
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.modelsets.StringList;
import java.util.Collections;
import org.sdmlib.models.classes.util.ClazzSet;
import org.sdmlib.models.modelsets.ObjectSet;

public class ClassModelSet extends LinkedHashSet<ClassModel> implements org.sdmlib.models.modelsets.ModelSet
{
   private static final long serialVersionUID = 1L;

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
         obj.withClasses(value);
      }
      
      return this;
   }

   public ClassModelSet withoutClasses(Clazz value)
   {
      for (ClassModel obj : this)
      {
         obj.withoutClasses(value);
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


   public ClassModelSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         for(Iterator<?> i = ((Collection<?>)value).iterator();i.hasNext();){
            this.add((ClassModel) i.next());
         }
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
}
