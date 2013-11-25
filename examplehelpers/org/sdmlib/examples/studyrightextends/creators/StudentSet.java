/*
   Copyright (c) 2013 zuendorf 
   
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
   
package org.sdmlib.examples.studyrightextends.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyrightextends.Student;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.examples.studyrightextends.creators.LectureSet;
import org.sdmlib.examples.studyrightextends.Lecture;

public class StudentSet extends LinkedHashSet<Student> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Student elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.studyrightextends.Student";
   }


   public StudentSet with(Student value)
   {
      this.add(value);
      return this;
   }
   
   public StudentSet without(Student value)
   {
      this.remove(value);
      return this;
   }
   
   //==========================================================================
   
   public StudentSet findMyPosition()
   {
      for (Student obj : this)
      {
         obj.findMyPosition();
      }
      return this;
   }

   
   //==========================================================================
   
   public StudentSet findMyPosition(String p0)
   {
      for (Student obj : this)
      {
         obj.findMyPosition( p0);
      }
      return this;
   }

   
   //==========================================================================
   
   public StudentSet findMyPosition(String p0, int p1)
   {
      for (Student obj : this)
      {
         obj.findMyPosition( p0,  p1);
      }
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Student obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public StudentSet withName(String value)
   {
      for (Student obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public intList getMatrNo()
   {
      intList result = new intList();
      
      for (Student obj : this)
      {
         result.add(obj.getMatrNo());
      }
      
      return result;
   }

   public StudentSet withMatrNo(int value)
   {
      for (Student obj : this)
      {
         obj.setMatrNo(value);
      }
      
      return this;
   }

   public LectureSet getLecture()
   {
      LectureSet result = new LectureSet();
      
      for (Student obj : this)
      {
         result.addAll(obj.getLecture());
      }
      
      return result;
   }

   public StudentSet withLecture(Lecture value)
   {
      for (Student obj : this)
      {
         obj.withLecture(value);
      }
      
      return this;
   }

   public StudentSet withoutLecture(Lecture value)
   {
      for (Student obj : this)
      {
         obj.withoutLecture(value);
      }
      
      return this;
   }

}

