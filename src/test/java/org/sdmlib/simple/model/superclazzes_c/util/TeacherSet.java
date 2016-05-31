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
   
package org.sdmlib.simple.model.superclazzes_c.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.simple.model.superclazzes_c.Teacher;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;

public class TeacherSet extends SDMSet<Teacher>
{

   public TeacherSet()
   {
      // empty
   }

   public TeacherSet(Teacher... objects)
   {
      for (Teacher obj : objects)
      {
         this.add(obj);
      }
   }

   public TeacherSet(Collection<Teacher> objects)
   {
      this.addAll(objects);
   }

   public static final TeacherSet EMPTY_SET = new TeacherSet().withFlag(TeacherSet.READONLY);


   public TeacherPO filterTeacherPO()
   {
      return new TeacherPO(this.toArray(new Teacher[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.superclazzes_c.Teacher";
   }


   @SuppressWarnings("unchecked")
   public TeacherSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Teacher>)value);
      }
      else if (value != null)
      {
         this.add((Teacher) value);
      }
      
      return this;
   }
   
   public TeacherSet without(Teacher value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public TeacherSet filter(Condition<Teacher> newValue) {
      TeacherSet filterList = new TeacherSet();
      filterItems(filterList, newValue);
      return filterList;
   }
}
