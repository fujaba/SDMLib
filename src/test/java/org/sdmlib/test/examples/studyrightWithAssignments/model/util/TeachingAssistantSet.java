/*
   Copyright (c) 2018 zuendorf
   
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

import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.TeachingAssistant;

import de.uniks.networkparser.list.BooleanList;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class TeachingAssistantSet extends SimpleSet<TeachingAssistant>
{
	public Class<?> getTypClass() {
		return TeachingAssistant.class;
	}

   public TeachingAssistantSet()
   {
      // empty
   }

   public TeachingAssistantSet(TeachingAssistant... objects)
   {
      for (TeachingAssistant obj : objects)
      {
         this.add(obj);
      }
   }

   public TeachingAssistantSet(Collection<TeachingAssistant> objects)
   {
      this.addAll(objects);
   }

   public static final TeachingAssistantSet EMPTY_SET = new TeachingAssistantSet().withFlag(TeachingAssistantSet.READONLY);


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.studyrightWithAssignments.model.TeachingAssistant";
   }


   @Override
   public TeachingAssistantSet getNewList(boolean keyValue)
   {
      return new TeachingAssistantSet();
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


   /**
    * Loop through the current set of TeachingAssistant objects and collect a list of the certified attribute values. 
    * 
    * @return List of boolean objects reachable via certified attribute
    */
   public BooleanList getCertified()
   {
      BooleanList result = new BooleanList();
      
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
   public TeachingAssistantSet createCertifiedCondition(boolean value)
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
    * Loop through the current set of TeachingAssistant objects and collect a list of the assignmentPoints attribute values. 
    * 
    * @return List of int objects reachable via assignmentPoints attribute
    */
   public NumberList getAssignmentPoints()
   {
      NumberList result = new NumberList();
      
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
   public TeachingAssistantSet createAssignmentPointsCondition(int value)
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
   public TeachingAssistantSet createAssignmentPointsCondition(int lower, int upper)
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
    * Loop through the current set of TeachingAssistant objects and collect a list of the credits attribute values. 
    * 
    * @return List of int objects reachable via credits attribute
    */
   public NumberList getCredits()
   {
      NumberList result = new NumberList();
      
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
   public TeachingAssistantSet createCreditsCondition(int value)
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
   public TeachingAssistantSet createCreditsCondition(int lower, int upper)
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
    * Loop through the current set of TeachingAssistant objects and collect a list of the id attribute values. 
    * 
    * @return List of String objects reachable via id attribute
    */
   public ObjectSet getId()
   {
      ObjectSet result = new ObjectSet();
      
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
   public TeachingAssistantSet createIdCondition(String value)
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
   public TeachingAssistantSet createIdCondition(String lower, String upper)
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
    * Loop through the current set of TeachingAssistant objects and collect a list of the motivation attribute values. 
    * 
    * @return List of int objects reachable via motivation attribute
    */
   public NumberList getMotivation()
   {
      NumberList result = new NumberList();
      
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
   public TeachingAssistantSet createMotivationCondition(int value)
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
   public TeachingAssistantSet createMotivationCondition(int lower, int upper)
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
    * Loop through the current set of TeachingAssistant objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
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
   public TeachingAssistantSet createNameCondition(String value)
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
   public TeachingAssistantSet createNameCondition(String lower, String upper)
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
    * @param value value    * @return The original set of ModelType objects now with the new neighbor attached to their Room attributes.
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
