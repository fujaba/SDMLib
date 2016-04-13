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
   
package org.sdmlib.test.examples.studyrightWithAssignments.model.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Assignment;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.test.examples.studyrightWithAssignments.model.TeachingAssistant;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;

import de.uniks.networkparser.interfaces.Condition;

public class TeachingAssistantSet extends StudentSet<TeachingAssistant>
{

   public static final TeachingAssistantSet EMPTY_SET = new TeachingAssistantSet().withFlag(TeachingAssistantSet.READONLY);


   public TeachingAssistantPO filterTeachingAssistantPO()
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
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
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

   @Override
   public TeachingAssistantSet filter(Condition<TeachingAssistant> newValue) {
      TeachingAssistantSet filterList = new TeachingAssistantSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of TeachingAssistant objects and collect a list of the certified attribute values. 
    * 
    * @return List of boolean objects reachable via certified attribute
    */
   public booleanList getCertified()
   {
      booleanList result = new booleanList();
      
      for (TeachingAssistant obj : this)
      {
         result.add(obj.isCertified());
      }
      
      return result;
   }


   /**
    * Loop through the current set of TeachingAssistant objects and collect those TeachingAssistant objects where the certified attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of TeachingAssistant objects that match the parameter
    */
   public TeachingAssistantSet filterCertified(boolean value)
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


   /**
    * Loop through the current set of TeachingAssistant objects and assign value to the certified attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of TeachingAssistant objects now with new attribute values.
    */
   public TeachingAssistantSet withCertified(boolean value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.setCertified(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of TeachingAssistant objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (TeachingAssistant obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of TeachingAssistant objects and collect those TeachingAssistant objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of TeachingAssistant objects that match the parameter
    */
   public TeachingAssistantSet filterName(String value)
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


   /**
    * Loop through the current set of TeachingAssistant objects and collect those TeachingAssistant objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of TeachingAssistant objects that match the parameter
    */
   public TeachingAssistantSet filterName(String lower, String upper)
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


   /**
    * Loop through the current set of TeachingAssistant objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of TeachingAssistant objects now with new attribute values.
    */
   public TeachingAssistantSet withName(String value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of TeachingAssistant objects and collect a list of the id attribute values. 
    * 
    * @return List of String objects reachable via id attribute
    */
   public StringList getId()
   {
      StringList result = new StringList();
      
      for (TeachingAssistant obj : this)
      {
         result.add(obj.getId());
      }
      
      return result;
   }


   /**
    * Loop through the current set of TeachingAssistant objects and collect those TeachingAssistant objects where the id attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of TeachingAssistant objects that match the parameter
    */
   public TeachingAssistantSet filterId(String value)
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


   /**
    * Loop through the current set of TeachingAssistant objects and collect those TeachingAssistant objects where the id attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of TeachingAssistant objects that match the parameter
    */
   public TeachingAssistantSet filterId(String lower, String upper)
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


   /**
    * Loop through the current set of TeachingAssistant objects and assign value to the id attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of TeachingAssistant objects now with new attribute values.
    */
   public TeachingAssistantSet withId(String value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.setId(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of TeachingAssistant objects and collect a list of the assignmentPoints attribute values. 
    * 
    * @return List of int objects reachable via assignmentPoints attribute
    */
   public intList getAssignmentPoints()
   {
      intList result = new intList();
      
      for (TeachingAssistant obj : this)
      {
         result.add(obj.getAssignmentPoints());
      }
      
      return result;
   }


   /**
    * Loop through the current set of TeachingAssistant objects and collect those TeachingAssistant objects where the assignmentPoints attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of TeachingAssistant objects that match the parameter
    */
   public TeachingAssistantSet filterAssignmentPoints(int value)
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


   /**
    * Loop through the current set of TeachingAssistant objects and collect those TeachingAssistant objects where the assignmentPoints attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of TeachingAssistant objects that match the parameter
    */
   public TeachingAssistantSet filterAssignmentPoints(int lower, int upper)
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


   /**
    * Loop through the current set of TeachingAssistant objects and assign value to the assignmentPoints attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of TeachingAssistant objects now with new attribute values.
    */
   public TeachingAssistantSet withAssignmentPoints(int value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.setAssignmentPoints(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of TeachingAssistant objects and collect a list of the motivation attribute values. 
    * 
    * @return List of int objects reachable via motivation attribute
    */
   public intList getMotivation()
   {
      intList result = new intList();
      
      for (TeachingAssistant obj : this)
      {
         result.add(obj.getMotivation());
      }
      
      return result;
   }


   /**
    * Loop through the current set of TeachingAssistant objects and collect those TeachingAssistant objects where the motivation attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of TeachingAssistant objects that match the parameter
    */
   public TeachingAssistantSet filterMotivation(int value)
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


   /**
    * Loop through the current set of TeachingAssistant objects and collect those TeachingAssistant objects where the motivation attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of TeachingAssistant objects that match the parameter
    */
   public TeachingAssistantSet filterMotivation(int lower, int upper)
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


   /**
    * Loop through the current set of TeachingAssistant objects and assign value to the motivation attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of TeachingAssistant objects now with new attribute values.
    */
   public TeachingAssistantSet withMotivation(int value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.setMotivation(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of TeachingAssistant objects and collect a list of the credits attribute values. 
    * 
    * @return List of int objects reachable via credits attribute
    */
   public intList getCredits()
   {
      intList result = new intList();
      
      for (TeachingAssistant obj : this)
      {
         result.add(obj.getCredits());
      }
      
      return result;
   }


   /**
    * Loop through the current set of TeachingAssistant objects and collect those TeachingAssistant objects where the credits attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of TeachingAssistant objects that match the parameter
    */
   public TeachingAssistantSet filterCredits(int value)
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


   /**
    * Loop through the current set of TeachingAssistant objects and collect those TeachingAssistant objects where the credits attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of TeachingAssistant objects that match the parameter
    */
   public TeachingAssistantSet filterCredits(int lower, int upper)
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


   /**
    * Loop through the current set of TeachingAssistant objects and assign value to the credits attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of TeachingAssistant objects now with new attribute values.
    */
   public TeachingAssistantSet withCredits(int value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.setCredits(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of TeachingAssistant objects and collect a set of the University objects reached via university. 
    * 
    * @return Set of University objects reachable via university
    */
   public UniversitySet getUniversity()
   {
      UniversitySet result = new UniversitySet();
      
      for (TeachingAssistant obj : this)
      {
         result.with(obj.getUniversity());
      }
      
      return result;
   }

   /**
    * Loop through the current set of TeachingAssistant objects and collect all contained objects with reference university pointing to the object passed as parameter. 
    * 
    * @param value The object required as university neighbor of the collected results. 
    * 
    * @return Set of University objects referring to value via university
    */
   public TeachingAssistantSet filterUniversity(Object value)
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
         if (neighbors.contains(obj.getUniversity()) || (neighbors.isEmpty() && obj.getUniversity() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the TeachingAssistant object passed as parameter to the University attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their University attributes.
    */
   public TeachingAssistantSet withUniversity(University value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.withUniversity(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of TeachingAssistant objects and collect a set of the Room objects reached via in. 
    * 
    * @return Set of Room objects reachable via in
    */
   public RoomSet getIn()
   {
      RoomSet result = new RoomSet();
      
      for (TeachingAssistant obj : this)
      {
         result.with(obj.getIn());
      }
      
      return result;
   }

   /**
    * Loop through the current set of TeachingAssistant objects and collect all contained objects with reference in pointing to the object passed as parameter. 
    * 
    * @param value The object required as in neighbor of the collected results. 
    * 
    * @return Set of Room objects referring to value via in
    */
   public TeachingAssistantSet filterIn(Object value)
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
         if (neighbors.contains(obj.getIn()) || (neighbors.isEmpty() && obj.getIn() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the TeachingAssistant object passed as parameter to the In attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their In attributes.
    */
   public TeachingAssistantSet withIn(Room value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.withIn(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of TeachingAssistant objects and collect a set of the Student objects reached via friends. 
    * 
    * @return Set of Student objects reachable via friends
    */
   public StudentSet getFriends()
   {
      StudentSet result = new StudentSet();
      
      for (TeachingAssistant obj : this)
      {
         result.with(obj.getFriends());
      }
      
      return result;
   }

   /**
    * Loop through the current set of TeachingAssistant objects and collect all contained objects with reference friends pointing to the object passed as parameter. 
    * 
    * @param value The object required as friends neighbor of the collected results. 
    * 
    * @return Set of Student objects referring to value via friends
    */
   public TeachingAssistantSet filterFriends(Object value)
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

   /**
    * Follow friends reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Student objects reachable via friends transitively (including the start set)
    */
   public StudentSet<Student> getFriendsTransitive()
   {
      StudentSet<Student> todo = new StudentSet<Student>().with(this);
      
      StudentSet result = new StudentSet();
      
      while ( ! todo.isEmpty())
      {
         Student current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getFriends()).minus(result);
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the TeachingAssistant object passed as parameter to the Friends attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Friends attributes.
    */
   public TeachingAssistantSet withFriends(Student value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.withFriends(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the TeachingAssistant object passed as parameter from the Friends attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TeachingAssistantSet withoutFriends(Student value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.withoutFriends(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of TeachingAssistant objects and collect a set of the Assignment objects reached via done. 
    * 
    * @return Set of Assignment objects reachable via done
    */
   public AssignmentSet getDone()
   {
      AssignmentSet result = new AssignmentSet();
      
      for (TeachingAssistant obj : this)
      {
         result.with(obj.getDone());
      }
      
      return result;
   }

   /**
    * Loop through the current set of TeachingAssistant objects and collect all contained objects with reference done pointing to the object passed as parameter. 
    * 
    * @param value The object required as done neighbor of the collected results. 
    * 
    * @return Set of Assignment objects referring to value via done
    */
   public TeachingAssistantSet filterDone(Object value)
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

   /**
    * Loop through current set of ModelType objects and attach the TeachingAssistant object passed as parameter to the Done attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Done attributes.
    */
   public TeachingAssistantSet withDone(Assignment value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.withDone(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the TeachingAssistant object passed as parameter from the Done attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TeachingAssistantSet withoutDone(Assignment value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.withoutDone(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of TeachingAssistant objects and collect a set of the Room objects reached via room. 
    * 
    * @return Set of Room objects reachable via room
    */
   public RoomSet getRoom()
   {
      RoomSet result = new RoomSet();
      
      for (TeachingAssistant obj : this)
      {
         result.with(obj.getRoom());
      }
      
      return result;
   }

   /**
    * Loop through the current set of TeachingAssistant objects and collect all contained objects with reference room pointing to the object passed as parameter. 
    * 
    * @param value The object required as room neighbor of the collected results. 
    * 
    * @return Set of Room objects referring to value via room
    */
   public TeachingAssistantSet filterRoom(Object value)
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
         if (neighbors.contains(obj.getRoom()) || (neighbors.isEmpty() && obj.getRoom() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the TeachingAssistant object passed as parameter to the Room attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Room attributes.
    */
   public TeachingAssistantSet withRoom(Room value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.withRoom(value);
      }
      
      return this;
   }

}
