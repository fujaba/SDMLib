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

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.President;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.UniversitySet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;

public class PresidentSet extends SimpleSet<President>
{
	public Class<?> getTypClass() {
		return President.class;
	}

   public PresidentSet()
   {
      // empty
   }

   public PresidentSet(President... objects)
   {
      for (President obj : objects)
      {
         this.add(obj);
      }
   }

   public PresidentSet(Collection<President> objects)
   {
      this.addAll(objects);
   }

   public static final PresidentSet EMPTY_SET = new PresidentSet().withFlag(PresidentSet.READONLY);


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.studyrightWithAssignments.model.President";
   }


   @Override
   public PresidentSet getNewList(boolean keyValue)
   {
      return new PresidentSet();
   }


   @SuppressWarnings("unchecked")
   public PresidentSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<President>)value);
      }
      else if (value != null)
      {
         this.add((President) value);
      }
      
      return this;
   }
   
   public PresidentSet without(President value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of President objects and collect a set of the University objects reached via university. 
    * 
    * @return Set of University objects reachable via university
    */
   public UniversitySet getUniversity()
   {
      UniversitySet result = new UniversitySet();
      
      for (President obj : this)
      {
         result.with(obj.getUniversity());
      }
      
      return result;
   }

   /**
    * Loop through the current set of President objects and collect all contained objects with reference university pointing to the object passed as parameter. 
    * 
    * @param value The object required as university neighbor of the collected results. 
    * 
    * @return Set of University objects referring to value via university
    */
   public PresidentSet filterUniversity(Object value)
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
      
      PresidentSet answer = new PresidentSet();
      
      for (President obj : this)
      {
         if (neighbors.contains(obj.getUniversity()) || (neighbors.isEmpty() && obj.getUniversity() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the President object passed as parameter to the University attribute of each of it. 
    * 
    * @param value value    * @return The original set of ModelType objects now with the new neighbor attached to their University attributes.
    */
   public PresidentSet withUniversity(University value)
   {
      for (President obj : this)
      {
         obj.withUniversity(value);
      }
      
      return this;
   }

}
