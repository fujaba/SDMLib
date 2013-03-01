/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.examples.studyright.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.studyright.Room;
import org.sdmlib.examples.studyright.Student;
import org.sdmlib.examples.studyright.University;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;

public class StudentSet extends LinkedHashSet<Student>
{
	private static final long serialVersionUID = 1L;
public StringList getName()
   {
      StringList result = new StringList();
      
      for (Student obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
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

   public UniversitySet getUni()
   {
      UniversitySet result = new UniversitySet();
      
      for (Student obj : this)
      {
         result.add(obj.getUni());
      }
      
      return result;
   }
   public RoomSet getIn()
   {
      RoomSet result = new RoomSet();
      
      for (Student obj : this)
      {
         result.add(obj.getIn());
      }
      
      return result;
   }
   public StudentSet withName(String value)
   {
      for (Student obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public StudentSet withMatrNo(int value)
   {
      for (Student obj : this)
      {
         obj.withMatrNo(value);
      }
      
      return this;
   }

   public StudentSet withUni(University value)
   {
      for (Student obj : this)
      {
         obj.withUni(value);
      }
      
      return this;
   }

   public StudentSet withIn(Room value)
   {
      for (Student obj : this)
      {
         obj.withIn(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Student elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
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


   public String getEntryType()
   {
      return "org.sdmlib.examples.studyright.Student";
   }
}




