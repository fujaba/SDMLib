/*
   Copyright (c) 2015 zuendorf
   
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
   
package org.sdmlib.test.examples.studyrightWithAssignments.model.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Assignment;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.test.examples.studyrightWithAssignments.model.TeachingAssistant;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;

public class TeachingAssistantSet extends SDMSet<TeachingAssistant>
{

   public static final TeachingAssistantSet EMPTY_SET = new TeachingAssistantSet().withFlag(TeachingAssistantSet.READONLY);


   public TeachingAssistantPO hasTeachingAssistantPO()
   {
      return new TeachingAssistantPO(this.toArray(new TeachingAssistant[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.studyrightWithAssignments.model.TeachingAssistant";
   }


   @SuppressWarnings("unchecked")
   public TeachingAssistantSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<TeachingAssistant>)value);
      }
      else if (value != null)
      {
         this.add((TeachingAssistant) value);
      }
      
      return this;
   }
   
   public TeachingAssistantSet without(TeachingAssistant value)
   {
      this.remove(value);
      return this;
   }

   public booleanList getCertified()
   {
      booleanList result = new booleanList();
      
      for (TeachingAssistant obj : this)
      {
         result.add(obj.isCertified());
      }
      
      return result;
   }

   public TeachingAssistantSet hasCertified(boolean value)
   {
      TeachingAssistantSet result = new TeachingAssistantSet();
      
      for (TeachingAssistant obj : this)
      {
         if (value == obj.isCertified())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TeachingAssistantSet withCertified(boolean value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.setCertified(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (TeachingAssistant obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public TeachingAssistantSet hasName(String value)
   {
      TeachingAssistantSet result = new TeachingAssistantSet();
      
      for (TeachingAssistant obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TeachingAssistantSet hasName(String lower, String upper)
   {
      TeachingAssistantSet result = new TeachingAssistantSet();
      
      for (TeachingAssistant obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TeachingAssistantSet withName(String value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public StringList getId()
   {
      StringList result = new StringList();
      
      for (TeachingAssistant obj : this)
      {
         result.add(obj.getId());
      }
      
      return result;
   }

   public TeachingAssistantSet hasId(String value)
   {
      TeachingAssistantSet result = new TeachingAssistantSet();
      
      for (TeachingAssistant obj : this)
      {
         if (value.equals(obj.getId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TeachingAssistantSet hasId(String lower, String upper)
   {
      TeachingAssistantSet result = new TeachingAssistantSet();
      
      for (TeachingAssistant obj : this)
      {
         if (lower.compareTo(obj.getId()) <= 0 && obj.getId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TeachingAssistantSet withId(String value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.setId(value);
      }
      
      return this;
   }

   public intList getAssignmentPoints()
   {
      intList result = new intList();
      
      for (TeachingAssistant obj : this)
      {
         result.add(obj.getAssignmentPoints());
      }
      
      return result;
   }

   public TeachingAssistantSet hasAssignmentPoints(int value)
   {
      TeachingAssistantSet result = new TeachingAssistantSet();
      
      for (TeachingAssistant obj : this)
      {
         if (value == obj.getAssignmentPoints())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TeachingAssistantSet hasAssignmentPoints(int lower, int upper)
   {
      TeachingAssistantSet result = new TeachingAssistantSet();
      
      for (TeachingAssistant obj : this)
      {
         if (lower <= obj.getAssignmentPoints() && obj.getAssignmentPoints() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TeachingAssistantSet withAssignmentPoints(int value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.setAssignmentPoints(value);
      }
      
      return this;
   }

   public intList getMotivation()
   {
      intList result = new intList();
      
      for (TeachingAssistant obj : this)
      {
         result.add(obj.getMotivation());
      }
      
      return result;
   }

   public TeachingAssistantSet hasMotivation(int value)
   {
      TeachingAssistantSet result = new TeachingAssistantSet();
      
      for (TeachingAssistant obj : this)
      {
         if (value == obj.getMotivation())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TeachingAssistantSet hasMotivation(int lower, int upper)
   {
      TeachingAssistantSet result = new TeachingAssistantSet();
      
      for (TeachingAssistant obj : this)
      {
         if (lower <= obj.getMotivation() && obj.getMotivation() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TeachingAssistantSet withMotivation(int value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.setMotivation(value);
      }
      
      return this;
   }

   public intList getCredits()
   {
      intList result = new intList();
      
      for (TeachingAssistant obj : this)
      {
         result.add(obj.getCredits());
      }
      
      return result;
   }

   public TeachingAssistantSet hasCredits(int value)
   {
      TeachingAssistantSet result = new TeachingAssistantSet();
      
      for (TeachingAssistant obj : this)
      {
         if (value == obj.getCredits())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TeachingAssistantSet hasCredits(int lower, int upper)
   {
      TeachingAssistantSet result = new TeachingAssistantSet();
      
      for (TeachingAssistant obj : this)
      {
         if (lower <= obj.getCredits() && obj.getCredits() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TeachingAssistantSet withCredits(int value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.setCredits(value);
      }
      
      return this;
   }

   public UniversitySet getUniversity()
   {
      UniversitySet result = new UniversitySet();
      
      for (TeachingAssistant obj : this)
      {
         result.add(obj.getUniversity());
      }
      
      return result;
   }

   public TeachingAssistantSet hasUniversity(Object value)
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
      
      TeachingAssistantSet answer = new TeachingAssistantSet();
      
      for (TeachingAssistant obj : this)
      {
         if (neighbors.contains(obj.getUniversity()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public TeachingAssistantSet withUniversity(University value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.withUniversity(value);
      }
      
      return this;
   }

   public RoomSet getIn()
   {
      RoomSet result = new RoomSet();
      
      for (TeachingAssistant obj : this)
      {
         result.add(obj.getIn());
      }
      
      return result;
   }

   public TeachingAssistantSet hasIn(Object value)
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
      
      TeachingAssistantSet answer = new TeachingAssistantSet();
      
      for (TeachingAssistant obj : this)
      {
         if (neighbors.contains(obj.getIn()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public TeachingAssistantSet withIn(Room value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.withIn(value);
      }
      
      return this;
   }

   public AssignmentSet getDone()
   {
      AssignmentSet result = new AssignmentSet();
      
      for (TeachingAssistant obj : this)
      {
         result.addAll(obj.getDone());
      }
      
      return result;
   }

   public TeachingAssistantSet hasDone(Object value)
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
      
      TeachingAssistantSet answer = new TeachingAssistantSet();
      
      for (TeachingAssistant obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getDone()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public TeachingAssistantSet withDone(Assignment value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.withDone(value);
      }
      
      return this;
   }

   public TeachingAssistantSet withoutDone(Assignment value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.withoutDone(value);
      }
      
      return this;
   }

   public StudentSet getFriends()
   {
      StudentSet result = new StudentSet();
      
      for (TeachingAssistant obj : this)
      {
         result.addAll(obj.getFriends());
      }
      
      return result;
   }

   public TeachingAssistantSet hasFriends(Object value)
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
      
      TeachingAssistantSet answer = new TeachingAssistantSet();
      
      for (TeachingAssistant obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getFriends()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public StudentSet getFriendsTransitive()
   {
      StudentSet todo = new StudentSet().with(this);
      
      StudentSet result = new StudentSet();
      
      while ( ! todo.isEmpty())
      {
         Student current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getFriends().minus(result));
         }
      }
      
      return result;
   }

   public TeachingAssistantSet withFriends(Student value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.withFriends(value);
      }
      
      return this;
   }

   public TeachingAssistantSet withoutFriends(Student value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.withoutFriends(value);
      }
      
      return this;
   }

   public RoomSet getRoom()
   {
      RoomSet result = new RoomSet();
      
      for (TeachingAssistant obj : this)
      {
         result.add(obj.getRoom());
      }
      
      return result;
   }

   public TeachingAssistantSet hasRoom(Object value)
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
      
      TeachingAssistantSet answer = new TeachingAssistantSet();
      
      for (TeachingAssistant obj : this)
      {
         if (neighbors.contains(obj.getRoom()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public TeachingAssistantSet withRoom(Room value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.withRoom(value);
      }
      
      return this;
   }

}
