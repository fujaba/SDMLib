/*
   Copyright (c) 2012 Albert Zündorf

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

public class ClassModel
{
   public static ClassModel classModel = null;
   
   public ClassModel()
   {
      classModel = this;
   }

   public ClassModel generate(String rootDir)
   {
      for (Clazz clazz : getClasses())
      {
         clazz.generate(rootDir);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public static final String PROPERTY_CLASSES = "classes";
   
   private LinkedHashSet<Clazz> classes;
   
   public LinkedHashSet<Clazz> getClasses()
   {
      if (classes == null)
      {
         return Clazz.EMPTY_SET;
      }
      return this.classes;
   }
   
   public ClassModel withClasses(Clazz value)
   {
      addToClasses(value);
      return this;
   }

   public void addToClasses(Clazz value)
   {
      if (this.classes == null)
      {
         this.classes = new LinkedHashSet<Clazz>();
      }
      
      this.classes.add(value);
   } 
}

