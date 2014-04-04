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

package org.sdmlib.examples.m2m.creators;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

import org.sdmlib.examples.m2m.Graph;
import org.sdmlib.examples.m2m.GraphComponent;
import org.sdmlib.examples.m2m.Person;
import org.sdmlib.examples.m2m.Relation;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.m2m.creators.PersonSet;
import org.sdmlib.examples.m2m.creators.RelationSet;
import org.sdmlib.examples.m2m.creators.GraphComponentSet;

public class GraphSet extends LinkedHashSet<Graph> implements
      org.sdmlib.models.modelsets.ModelSet
{
   public Graph first()
   {
      for (Graph obj : this)
      {
         return obj;
      }

      return null;
   }

   public String toString()
   {
      StringList stringList = new StringList();

      for (Graph elem : this)
      {
         stringList.add(elem.toString());
      }

      return "(" + stringList.concat(", ") + ")";
   }

   public String getEntryType()
   {
      return "org.sdmlib.examples.m2m.Graph";
   }

   public PersonSet getPersons()
   {
      PersonSet result = new PersonSet();

      for (Graph obj : this)
      {
         result.addAll(obj.getPersons());
      }

      return result;
   }

   public GraphSet hasPersons(Object value)
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

      GraphSet answer = new GraphSet();

      for (Graph obj : this)
      {
         if (!Collections.disjoint(neighbors, obj.getPersons()))
         {
            answer.add(obj);
         }
      }

      return answer;
   }

   public GraphSet withPersons(Person value)
   {
      for (Graph obj : this)
      {
         obj.withPersons(value);
      }

      return this;
   }

   public GraphSet withoutPersons(Person value)
   {
      for (Graph obj : this)
      {
         obj.withoutPersons(value);
      }

      return this;
   }

   public RelationSet getRelations()
   {
      RelationSet result = new RelationSet();

      for (Graph obj : this)
      {
         result.addAll(obj.getRelations());
      }

      return result;
   }

   public GraphSet hasRelations(Object value)
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

      GraphSet answer = new GraphSet();

      for (Graph obj : this)
      {
         if (!Collections.disjoint(neighbors, obj.getRelations()))
         {
            answer.add(obj);
         }
      }

      return answer;
   }

   public GraphSet withRelations(Relation value)
   {
      for (Graph obj : this)
      {
         obj.withRelations(value);
      }

      return this;
   }

   public GraphSet withoutRelations(Relation value)
   {
      for (Graph obj : this)
      {
         obj.withoutRelations(value);
      }

      return this;
   }

   public GraphComponentSet getGcs()
   {
      GraphComponentSet result = new GraphComponentSet();

      for (Graph obj : this)
      {
         result.addAll(obj.getGcs());
      }

      return result;
   }

   public GraphSet hasGcs(Object value)
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

      GraphSet answer = new GraphSet();

      for (Graph obj : this)
      {
         if (!Collections.disjoint(neighbors, obj.getGcs()))
         {
            answer.add(obj);
         }
      }

      return answer;
   }

   public GraphSet withGcs(GraphComponent value)
   {
      for (Graph obj : this)
      {
         obj.withGcs(value);
      }

      return this;
   }

   public GraphSet withoutGcs(GraphComponent value)
   {
      for (Graph obj : this)
      {
         obj.withoutGcs(value);
      }

      return this;
   }

   public GraphPO startModelPattern()
   {
      org.sdmlib.examples.m2m.creators.ModelPattern pattern = new org.sdmlib.examples.m2m.creators.ModelPattern();

      GraphPO patternObject = pattern.hasElementGraphPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }

   public GraphSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Graph>) value);
      }
      else if (value != null)
      {
         this.add((Graph) value);
      }

      return this;
   }

   public GraphSet without(Graph value)
   {
      this.remove(value);
      return this;
   }

   public GraphPO hasGraphPO()
   {
      org.sdmlib.examples.m2m.creators.ModelPattern pattern = new org.sdmlib.examples.m2m.creators.ModelPattern();

      GraphPO patternObject = pattern.hasElementGraphPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }
}
