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
   
package org.sdmlib.examples.studyrightWithAssignments.creators;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.studyrightWithAssignments.University;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.examples.studyrightWithAssignments.creators.StudentSet;
import java.util.Collection;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.examples.studyrightWithAssignments.Student;
import org.sdmlib.examples.studyrightWithAssignments.creators.RoomSet;
import org.sdmlib.examples.studyrightWithAssignments.Room;

public class UniversitySet extends SDMSet<University>
{


   public UniversityPO startModelPattern()
   {
      org.sdmlib.examples.studyrightWithAssignments.creators.ModelPattern pattern = new org.sdmlib.examples.studyrightWithAssignments.creators.ModelPattern();
      
      UniversityPO patternObject = pattern.hasElementUniversityPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.studyrightWithAssignments.University";
   }


   public UniversitySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<University>)value);
      }
      else if (value != null)
      {
         this.add((University) value);
      }
      
      return this;
   }
   
   public UniversitySet without(University value)
   {
      this.remove(value);
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (University obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public UniversitySet hasName(String value)
   {
      UniversitySet result = new UniversitySet();
      
      for (University obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UniversitySet withName(String value)
   {
      for (University obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public StudentSet getStudents()
   {
      StudentSet result = new StudentSet();
      
      for (University obj : this)
      {
         result.with(obj.getStudents());
      }
      
      return result;
   }

   public UniversitySet hasStudents(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      UniversitySet answer = new UniversitySet();
      
      for (University obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getStudents()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
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

   public RoomSet getRooms()
   {
      RoomSet result = new RoomSet();
      
      for (University obj : this)
      {
         result.with(obj.getRooms());
      }
      
      return result;
   }

   public UniversitySet hasRooms(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      UniversitySet answer = new UniversitySet();
      
      for (University obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getRooms()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
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

}

