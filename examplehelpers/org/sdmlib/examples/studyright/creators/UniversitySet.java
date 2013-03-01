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

public class UniversitySet extends LinkedHashSet<University>
{
	private static final long serialVersionUID = 1L;
public StringList getName()
   {
      StringList result = new StringList();
      
      for (University obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public StudentSet getStudents()
   {
      StudentSet result = new StudentSet();
      
      for (University obj : this)
      {
         result.addAll(obj.getStudents());
      }
      
      return result;
   }
   public RoomSet getRooms()
   {
      RoomSet result = new RoomSet();
      
      for (University obj : this)
      {
         result.addAll(obj.getRooms());
      }
      
      return result;
   }
   public UniversitySet withName(String value)
   {
      for (University obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public UniversitySet withStudents(Student value)
   {
      for (University obj : this)
      {
         obj.withStudents(value);
      }
      
      return this;
   }

   public UniversitySet withoutStudents(Student value)
   {
      for (University obj : this)
      {
         obj.withoutStudents(value);
      }
      
      return this;
   }

   public UniversitySet withRooms(Room value)
   {
      for (University obj : this)
      {
         obj.withRooms(value);
      }
      
      return this;
   }

   public UniversitySet withoutRooms(Room value)
   {
      for (University obj : this)
      {
         obj.withoutRooms(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (University elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public UniversitySet with(University value)
   {
      this.add(value);
      return this;
   }
   
   public UniversitySet without(University value)
   {
      this.remove(value);
      return this;
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.studyright.University";
   }
}




