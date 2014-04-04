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
import org.sdmlib.models.modelsets.StringList;

import java.util.List;

import org.sdmlib.models.modelsets.intList;
import org.sdmlib.examples.studyright.creators.RoomSet;
import org.sdmlib.examples.studyright.Room;
import org.sdmlib.examples.studyright.creators.StudentSet;
import org.sdmlib.examples.studyright.Student;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;

public class AssignmentSet extends LinkedHashSet<Assignment> implements
      org.sdmlib.models.modelsets.ModelSet
{

   public String toString()
   {
      StringList stringList = new StringList();

      for (Assignment elem : this)
      {
         stringList.add(elem.toString());
      }

      return "(" + stringList.concat(", ") + ")";
   }

   public String getEntryType()
   {
      return "org.sdmlib.examples.studyright.Assignment";
   }

   public AssignmentSet without(Assignment value)
   {
      this.remove(value);
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();

      for (Assignment obj : this)
      {
         result.add(obj.getName());
      }

      return result;
   }

   public AssignmentSet withName(String value)
   {
      for (Assignment obj : this)
      {
         obj.setName(value);
      }

      return this;
   }

   public intList getPoints()
   {
      intList result = new intList();

      for (Assignment obj : this)
      {
         result.add(obj.getPoints());
      }

      return result;
   }

   public AssignmentSet withPoints(int value)
   {
      for (Assignment obj : this)
      {
         obj.setPoints(value);
      }

      return this;
   }

   public RoomSet getRoom()
   {
      RoomSet result = new RoomSet();

      for (Assignment obj : this)
      {
         result.add(obj.getRoom());
      }

      return result;
   }

   public AssignmentSet withRoom(Room value)
   {
      for (Assignment obj : this)
      {
         obj.withRoom(value);
      }

      return this;
   }

   public StudentSet getStudents()
   {
      StudentSet result = new StudentSet();

      for (Assignment obj : this)
      {
         result.add(obj.getStudents());
      }

      return result;
   }

   public AssignmentSet withStudents(Student value)
   {
      for (Assignment obj : this)
      {
         obj.withStudents(value);
      }

      return this;
   }

   public AssignmentPO startModelPattern()
   {
      org.sdmlib.examples.studyright.creators.ModelPattern pattern = new org.sdmlib.examples.studyright.creators.ModelPattern();

      AssignmentPO patternObject = pattern.hasElementAssignmentPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }

   public AssignmentSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Assignment>) value);
      }
      else if (value != null)
      {
         this.add((Assignment) value);
      }

      return this;
   }

   public AssignmentPO hasAssignmentPO()
   {
      org.sdmlib.examples.studyright.creators.ModelPattern pattern = new org.sdmlib.examples.studyright.creators.ModelPattern();

      AssignmentPO patternObject = pattern.hasElementAssignmentPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }
}
