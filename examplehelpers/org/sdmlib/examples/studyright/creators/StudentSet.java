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
   
package org.sdmlib.examples.studyright.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.examples.studyright.Assignment;
import org.sdmlib.examples.studyright.Room;
import org.sdmlib.examples.studyright.Student;
import org.sdmlib.examples.studyright.University;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;

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
      return "org.sdmlib.examples.studyright.Student";
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

   public intList getCredits()
   {
      intList result = new intList();
      
      for (Student obj : this)
      {
         result.add(obj.getCredits());
      }
      
      return result;
   }

   public StudentSet withCredits(int value)
   {
      for (Student obj : this)
      {
         obj.setCredits(value);
      }
      
      return this;
   }

   public intList getMotivation()
   {
      intList result = new intList();
      
      for (Student obj : this)
      {
         result.add(obj.getMotivation());
      }
      
      return result;
   }

   public StudentSet withMotivation(int value)
   {
      for (Student obj : this)
      {
         obj.setMotivation(value);
      }
      
      return this;
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

   public StudentSet withUni(University value)
   {
      for (Student obj : this)
      {
         obj.withUni(value);
      }
      
      return this;
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

   public StudentSet withIn(Room value)
   {
      for (Student obj : this)
      {
         obj.withIn(value);
      }
      
      return this;
   }

   public AssignmentSet getDone()
   {
      AssignmentSet result = new AssignmentSet();
      
      for (Student obj : this)
      {
         result.addAll(obj.getDone());
      }
      
      return result;
   }

   public StudentSet withDone(Assignment value)
   {
      for (Student obj : this)
      {
         obj.withDone(value);
      }
      
      return this;
   }

   public StudentSet withoutDone(Assignment value)
   {
      for (Student obj : this)
      {
         obj.withoutDone(value);
      }
      
      return this;
   }



   public StudentPO startModelPattern()
   {
      org.sdmlib.examples.studyright.creators.ModelPattern pattern = new org.sdmlib.examples.studyright.creators.ModelPattern();
      
      StudentPO patternObject = pattern.hasElementStudentPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public StudentSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Student>)value);
      }
      else if (value != null)
      {
         this.add((Student) value);
      }
      
      return this;
   }
   



   public StudentPO hasStudentPO()
   {
      org.sdmlib.examples.studyright.creators.ModelPattern pattern = new org.sdmlib.examples.studyright.creators.ModelPattern();
      
      StudentPO patternObject = pattern.hasElementStudentPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}



