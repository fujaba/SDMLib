/*
   Copyright (c) 2014 Stefan 
   
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

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.m2m.model.Relation;
import java.util.Collection;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.m2m.model.util.GraphSet;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.examples.m2m.model.Graph;
import org.sdmlib.examples.m2m.model.util.PersonSet;
import org.sdmlib.examples.m2m.model.Person;

public class RelationSet extends SDMSet<Relation>
{
        private static final long serialVersionUID = 1L;


   public RelationPO hasRelationPO()
   {
      return new RelationPO (this.toArray(new Relation[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.m2m.model.Relation";
   }


   public RelationSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
           Collection<?> collection = (Collection<?>) value;
           for(Object item : collection){
               this.add((Relation) item);
           }
      }
      else if (value != null)
      {
         this.add((Relation) value);
      }
      
      return this;
   }
   
   public RelationSet without(Relation value)
   {
      this.remove(value);
      return this;
   }

   public StringList getKind()
   {
      StringList result = new StringList();
      
      for (Relation obj : this)
      {
         result.add(obj.getKind());
      }
      
      return result;
   }

   public RelationSet hasKind(String value)
   {
      RelationSet result = new RelationSet();
      
      for (Relation obj : this)
      {
         if (value.equals(obj.getKind()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public RelationSet withKind(String value)
   {
      for (Relation obj : this)
      {
         obj.setKind(value);
      }
      
      return this;
   }

   public GraphSet getGraph()
   {
      GraphSet result = new GraphSet();
      
      for (Relation obj : this)
      {
         result.with(obj.getGraph());
      }
      
      return result;
   }

   public RelationSet hasGraph(Object value)
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
      
      RelationSet answer = new RelationSet();
      
      for (Relation obj : this)
      {
         if (neighbors.contains(obj.getGraph()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public RelationSet withGraph(Graph value)
   {
      for (Relation obj : this)
      {
         obj.withGraph(value);
      }
      
      return this;
   }

   public PersonSet getSrc()
   {
      PersonSet result = new PersonSet();
      
      for (Relation obj : this)
      {
         result.with(obj.getSrc());
      }
      
      return result;
   }

   public RelationSet hasSrc(Object value)
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
      
      RelationSet answer = new RelationSet();
      
      for (Relation obj : this)
      {
         if (neighbors.contains(obj.getSrc()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public RelationSet withSrc(Person value)
   {
      for (Relation obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }

   public PersonSet getTgt()
   {
      PersonSet result = new PersonSet();
      
      for (Relation obj : this)
      {
         result.with(obj.getTgt());
      }
      
      return result;
   }

   public RelationSet hasTgt(Object value)
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
      
      RelationSet answer = new RelationSet();
      
      for (Relation obj : this)
      {
         if (neighbors.contains(obj.getTgt()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public RelationSet withTgt(Person value)
   {
      for (Relation obj : this)
      {
         obj.withTgt(value);
      }
      
      return this;
   }

}








