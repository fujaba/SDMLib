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
   
package org.sdmlib.examples.m2m.model.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.examples.m2m.model.Graph;
import org.sdmlib.examples.m2m.model.Person;
import org.sdmlib.examples.m2m.model.Relation;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;

public class PersonSet extends SDMSet<Person>
{


   public PersonPO hasPersonPO()
   {
      return new PersonPO(this.toArray(new Person[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.m2m.model.Person";
   }


   @SuppressWarnings("unchecked")
   public PersonSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Person>)value);
      }
      else if (value != null)
      {
         this.add((Person) value);
      }
      
      return this;
   }
   
   public PersonSet without(Person value)
   {
      this.remove(value);
      return this;
   }

   public StringList getFirstName()
   {
      StringList result = new StringList();
      
      for (Person obj : this)
      {
         result.add(obj.getFirstName());
      }
      
      return result;
   }

   public PersonSet hasFirstName(String value)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (value.equals(obj.getFirstName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PersonSet withFirstName(String value)
   {
      for (Person obj : this)
      {
         obj.setFirstName(value);
      }
      
      return this;
   }

   public StringList getText()
   {
      StringList result = new StringList();
      
      for (Person obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public PersonSet hasText(String value)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (value.equals(obj.getText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PersonSet withText(String value)
   {
      for (Person obj : this)
      {
         obj.setText(value);
      }
      
      return this;
   }

   public GraphSet getGraph()
   {
      GraphSet result = new GraphSet();
      
      for (Person obj : this)
      {
         result.add(obj.getGraph());
      }
      
      return result;
   }

   public PersonSet hasGraph(Object value)
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
      
      PersonSet answer = new PersonSet();
      
      for (Person obj : this)
      {
         if (neighbors.contains(obj.getGraph()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PersonSet withGraph(Graph value)
   {
      for (Person obj : this)
      {
         obj.withGraph(value);
      }
      
      return this;
   }

   public RelationSet getOutEdges()
   {
      RelationSet result = new RelationSet();
      
      for (Person obj : this)
      {
         result.addAll(obj.getOutEdges());
      }
      
      return result;
   }

   public PersonSet hasOutEdges(Object value)
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
      
      PersonSet answer = new PersonSet();
      
      for (Person obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getOutEdges()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PersonSet withOutEdges(Relation value)
   {
      for (Person obj : this)
      {
         obj.withOutEdges(value);
      }
      
      return this;
   }

   public PersonSet withoutOutEdges(Relation value)
   {
      for (Person obj : this)
      {
         obj.withoutOutEdges(value);
      }
      
      return this;
   }

   public RelationSet getInEdges()
   {
      RelationSet result = new RelationSet();
      
      for (Person obj : this)
      {
         result.addAll(obj.getInEdges());
      }
      
      return result;
   }

   public PersonSet hasInEdges(Object value)
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
      
      PersonSet answer = new PersonSet();
      
      for (Person obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getInEdges()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PersonSet withInEdges(Relation value)
   {
      for (Person obj : this)
      {
         obj.withInEdges(value);
      }
      
      return this;
   }

   public PersonSet withoutInEdges(Relation value)
   {
      for (Person obj : this)
      {
         obj.withoutInEdges(value);
      }
      
      return this;
   }

   public PersonSet getKnows()
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         result.addAll(obj.getKnows());
      }
      
      return result;
   }

   public PersonSet hasKnows(Object value)
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
      
      PersonSet answer = new PersonSet();
      
      for (Person obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getKnows()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public PersonSet getKnowsTransitive()
   {
      PersonSet todo = new PersonSet().with(this);
      
      PersonSet result = new PersonSet();
      
      while ( ! todo.isEmpty())
      {
         Person current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getKnows().minus(result));
         }
      }
      
      return result;
   }

   public PersonSet withKnows(Person value)
   {
      for (Person obj : this)
      {
         obj.withKnows(value);
      }
      
      return this;
   }

   public PersonSet withoutKnows(Person value)
   {
      for (Person obj : this)
      {
         obj.withoutKnows(value);
      }
      
      return this;
   }

   public GraphSet getParent()
   {
      GraphSet result = new GraphSet();
      
      for (Person obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }

   public PersonSet hasParent(Object value)
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
      
      PersonSet answer = new PersonSet();
      
      for (Person obj : this)
      {
         if (neighbors.contains(obj.getParent()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PersonSet withParent(Graph value)
   {
      for (Person obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }


   public static final PersonSet EMPTY_SET = new PersonSet().withReadOnly(true);
}
