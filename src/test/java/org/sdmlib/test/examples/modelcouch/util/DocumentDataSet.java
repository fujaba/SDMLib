/*
   Copyright (c) 2016 deeptought
   
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
   
package org.sdmlib.test.examples.modelcouch.util;

import de.uniks.networkparser.list.SDMSet;
import org.sdmlib.test.examples.modelcouch.DocumentData;
import java.util.Collection;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.ObjectSet;
import java.util.Collections;
import org.sdmlib.test.examples.modelcouch.util.DocumentDataSet;
import org.sdmlib.test.examples.modelcouch.util.TaskSet;
import org.sdmlib.test.examples.modelcouch.Task;
import org.sdmlib.test.examples.modelcouch.util.PersonSet;
import org.sdmlib.test.examples.modelcouch.Person;

public class DocumentDataSet extends SDMSet<DocumentData>
{

   public static final DocumentDataSet EMPTY_SET = new DocumentDataSet().withReadOnly();


   public DocumentDataPO hasDocumentDataPO()
   {
      return new DocumentDataPO(this.toArray(new DocumentData[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.modelcouch.DocumentData";
   }


   @SuppressWarnings("unchecked")
   public DocumentDataSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<DocumentData>)value);
      }
      else if (value != null)
      {
         this.add((DocumentData) value);
      }
      
      return this;
   }
   
   public DocumentDataSet without(DocumentData value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of DocumentData objects and collect a list of the tag attribute values. 
    * 
    * @return List of String objects reachable via tag attribute
    */
   public StringList getTag()
   {
      StringList result = new StringList();
      
      for (DocumentData obj : this)
      {
         result.add(obj.getTag());
      }
      
      return result;
   }


   /**
    * Loop through the current set of DocumentData objects and collect those DocumentData objects where the tag attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of DocumentData objects that match the parameter
    */
   public DocumentDataSet hasTag(String value)
   {
      DocumentDataSet result = new DocumentDataSet();
      
      for (DocumentData obj : this)
      {
         if (value.equals(obj.getTag()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of DocumentData objects and collect those DocumentData objects where the tag attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of DocumentData objects that match the parameter
    */
   public DocumentDataSet hasTag(String lower, String upper)
   {
      DocumentDataSet result = new DocumentDataSet();
      
      for (DocumentData obj : this)
      {
         if (lower.compareTo(obj.getTag()) <= 0 && obj.getTag().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of DocumentData objects and assign value to the tag attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of DocumentData objects now with new attribute values.
    */
   public DocumentDataSet withTag(String value)
   {
      for (DocumentData obj : this)
      {
         obj.setTag(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of DocumentData objects and collect a list of the value attribute values. 
    * 
    * @return List of String objects reachable via value attribute
    */
   public StringList getValue()
   {
      StringList result = new StringList();
      
      for (DocumentData obj : this)
      {
         result.add(obj.getValue());
      }
      
      return result;
   }


   /**
    * Loop through the current set of DocumentData objects and collect those DocumentData objects where the value attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of DocumentData objects that match the parameter
    */
   public DocumentDataSet hasValue(String value)
   {
      DocumentDataSet result = new DocumentDataSet();
      
      for (DocumentData obj : this)
      {
         if (value.equals(obj.getValue()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of DocumentData objects and collect those DocumentData objects where the value attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of DocumentData objects that match the parameter
    */
   public DocumentDataSet hasValue(String lower, String upper)
   {
      DocumentDataSet result = new DocumentDataSet();
      
      for (DocumentData obj : this)
      {
         if (lower.compareTo(obj.getValue()) <= 0 && obj.getValue().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of DocumentData objects and assign value to the value attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of DocumentData objects now with new attribute values.
    */
   public DocumentDataSet withValue(String value)
   {
      for (DocumentData obj : this)
      {
         obj.setValue(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of DocumentData objects and collect a list of the type attribute values. 
    * 
    * @return List of String objects reachable via type attribute
    */
   public StringList getType()
   {
      StringList result = new StringList();
      
      for (DocumentData obj : this)
      {
         result.add(obj.getType());
      }
      
      return result;
   }


   /**
    * Loop through the current set of DocumentData objects and collect those DocumentData objects where the type attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of DocumentData objects that match the parameter
    */
   public DocumentDataSet hasType(String value)
   {
      DocumentDataSet result = new DocumentDataSet();
      
      for (DocumentData obj : this)
      {
         if (value.equals(obj.getType()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of DocumentData objects and collect those DocumentData objects where the type attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of DocumentData objects that match the parameter
    */
   public DocumentDataSet hasType(String lower, String upper)
   {
      DocumentDataSet result = new DocumentDataSet();
      
      for (DocumentData obj : this)
      {
         if (lower.compareTo(obj.getType()) <= 0 && obj.getType().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of DocumentData objects and assign value to the type attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of DocumentData objects now with new attribute values.
    */
   public DocumentDataSet withType(String value)
   {
      for (DocumentData obj : this)
      {
         obj.setType(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of DocumentData objects and collect a list of the lastEditor attribute values. 
    * 
    * @return List of String objects reachable via lastEditor attribute
    */
   public StringList getLastEditor()
   {
      StringList result = new StringList();
      
      for (DocumentData obj : this)
      {
         result.add(obj.getLastEditor());
      }
      
      return result;
   }


   /**
    * Loop through the current set of DocumentData objects and collect those DocumentData objects where the lastEditor attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of DocumentData objects that match the parameter
    */
   public DocumentDataSet hasLastEditor(String value)
   {
      DocumentDataSet result = new DocumentDataSet();
      
      for (DocumentData obj : this)
      {
         if (value.equals(obj.getLastEditor()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of DocumentData objects and collect those DocumentData objects where the lastEditor attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of DocumentData objects that match the parameter
    */
   public DocumentDataSet hasLastEditor(String lower, String upper)
   {
      DocumentDataSet result = new DocumentDataSet();
      
      for (DocumentData obj : this)
      {
         if (lower.compareTo(obj.getLastEditor()) <= 0 && obj.getLastEditor().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of DocumentData objects and assign value to the lastEditor attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of DocumentData objects now with new attribute values.
    */
   public DocumentDataSet withLastEditor(String value)
   {
      for (DocumentData obj : this)
      {
         obj.setLastEditor(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of DocumentData objects and collect a list of the lastModified attribute values. 
    * 
    * @return List of String objects reachable via lastModified attribute
    */
   public StringList getLastModified()
   {
      StringList result = new StringList();
      
      for (DocumentData obj : this)
      {
         result.add(obj.getLastModified());
      }
      
      return result;
   }


   /**
    * Loop through the current set of DocumentData objects and collect those DocumentData objects where the lastModified attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of DocumentData objects that match the parameter
    */
   public DocumentDataSet hasLastModified(String value)
   {
      DocumentDataSet result = new DocumentDataSet();
      
      for (DocumentData obj : this)
      {
         if (value.equals(obj.getLastModified()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of DocumentData objects and collect those DocumentData objects where the lastModified attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of DocumentData objects that match the parameter
    */
   public DocumentDataSet hasLastModified(String lower, String upper)
   {
      DocumentDataSet result = new DocumentDataSet();
      
      for (DocumentData obj : this)
      {
         if (lower.compareTo(obj.getLastModified()) <= 0 && obj.getLastModified().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of DocumentData objects and assign value to the lastModified attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of DocumentData objects now with new attribute values.
    */
   public DocumentDataSet withLastModified(String value)
   {
      for (DocumentData obj : this)
      {
         obj.setLastModified(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of DocumentData objects and collect a set of the DocumentData objects reached via subData. 
    * 
    * @return Set of DocumentData objects reachable via subData
    */
   public DocumentDataSet getSubData()
   {
      DocumentDataSet result = new DocumentDataSet();
      
      for (DocumentData obj : this)
      {
         result.addAll(obj.getSubData());
      }
      
      return result;
   }

   /**
    * Loop through the current set of DocumentData objects and collect all contained objects with reference subData pointing to the object passed as parameter. 
    * 
    * @param value The object required as subData neighbor of the collected results. 
    * 
    * @return Set of DocumentData objects referring to value via subData
    */
   public DocumentDataSet hasSubData(Object value)
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
      
      DocumentDataSet answer = new DocumentDataSet();
      
      for (DocumentData obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getSubData()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow subData reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of DocumentData objects reachable via subData transitively (including the start set)
    */
   public DocumentDataSet getSubDataTransitive()
   {
      DocumentDataSet todo = new DocumentDataSet().with(this);
      
      DocumentDataSet result = new DocumentDataSet();
      
      while ( ! todo.isEmpty())
      {
         DocumentData current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getSubData().minus(result));
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the DocumentData object passed as parameter to the SubData attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their SubData attributes.
    */
   public DocumentDataSet withSubData(DocumentData value)
   {
      for (DocumentData obj : this)
      {
         obj.withSubData(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the DocumentData object passed as parameter from the SubData attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public DocumentDataSet withoutSubData(DocumentData value)
   {
      for (DocumentData obj : this)
      {
         obj.withoutSubData(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of DocumentData objects and collect a set of the DocumentData objects reached via parentData. 
    * 
    * @return Set of DocumentData objects reachable via parentData
    */
   public DocumentDataSet getParentData()
   {
      DocumentDataSet result = new DocumentDataSet();
      
      for (DocumentData obj : this)
      {
         result.addAll(obj.getParentData());
      }
      
      return result;
   }

   /**
    * Loop through the current set of DocumentData objects and collect all contained objects with reference parentData pointing to the object passed as parameter. 
    * 
    * @param value The object required as parentData neighbor of the collected results. 
    * 
    * @return Set of DocumentData objects referring to value via parentData
    */
   public DocumentDataSet hasParentData(Object value)
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
      
      DocumentDataSet answer = new DocumentDataSet();
      
      for (DocumentData obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getParentData()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow parentData reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of DocumentData objects reachable via parentData transitively (including the start set)
    */
   public DocumentDataSet getParentDataTransitive()
   {
      DocumentDataSet todo = new DocumentDataSet().with(this);
      
      DocumentDataSet result = new DocumentDataSet();
      
      while ( ! todo.isEmpty())
      {
         DocumentData current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getParentData().minus(result));
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the DocumentData object passed as parameter to the ParentData attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their ParentData attributes.
    */
   public DocumentDataSet withParentData(DocumentData value)
   {
      for (DocumentData obj : this)
      {
         obj.withParentData(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the DocumentData object passed as parameter from the ParentData attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public DocumentDataSet withoutParentData(DocumentData value)
   {
      for (DocumentData obj : this)
      {
         obj.withoutParentData(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of DocumentData objects and collect a set of the Task objects reached via tasks. 
    * 
    * @return Set of Task objects reachable via tasks
    */
   public TaskSet getTasks()
   {
      TaskSet result = new TaskSet();
      
      for (DocumentData obj : this)
      {
         result.addAll(obj.getTasks());
      }
      
      return result;
   }

   /**
    * Loop through the current set of DocumentData objects and collect all contained objects with reference tasks pointing to the object passed as parameter. 
    * 
    * @param value The object required as tasks neighbor of the collected results. 
    * 
    * @return Set of Task objects referring to value via tasks
    */
   public DocumentDataSet hasTasks(Object value)
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
      
      DocumentDataSet answer = new DocumentDataSet();
      
      for (DocumentData obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getTasks()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the DocumentData object passed as parameter to the Tasks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Tasks attributes.
    */
   public DocumentDataSet withTasks(Task value)
   {
      for (DocumentData obj : this)
      {
         obj.withTasks(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the DocumentData object passed as parameter from the Tasks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public DocumentDataSet withoutTasks(Task value)
   {
      for (DocumentData obj : this)
      {
         obj.withoutTasks(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of DocumentData objects and collect a set of the Person objects reached via persons. 
    * 
    * @return Set of Person objects reachable via persons
    */
   public PersonSet getPersons()
   {
      PersonSet result = new PersonSet();
      
      for (DocumentData obj : this)
      {
         result.addAll(obj.getPersons());
      }
      
      return result;
   }

   /**
    * Loop through the current set of DocumentData objects and collect all contained objects with reference persons pointing to the object passed as parameter. 
    * 
    * @param value The object required as persons neighbor of the collected results. 
    * 
    * @return Set of Person objects referring to value via persons
    */
   public DocumentDataSet hasPersons(Object value)
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
      
      DocumentDataSet answer = new DocumentDataSet();
      
      for (DocumentData obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPersons()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the DocumentData object passed as parameter to the Persons attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Persons attributes.
    */
   public DocumentDataSet withPersons(Person value)
   {
      for (DocumentData obj : this)
      {
         obj.withPersons(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the DocumentData object passed as parameter from the Persons attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public DocumentDataSet withoutPersons(Person value)
   {
      for (DocumentData obj : this)
      {
         obj.withoutPersons(value);
      }
      
      return this;
   }

}
