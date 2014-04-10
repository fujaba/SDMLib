/*
   Copyright (c) 2014 christian 
   
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
   
package org.sdmlib.replication.creators;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.replication.ReplicationRoot;
import org.sdmlib.models.modelsets.StringList;
import java.util.Collection;
import java.util.List;
import org.sdmlib.replication.creators.ReplicationRootSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;

public class ReplicationRootSet extends SDMSet<ReplicationRoot>
{


   public ReplicationRootPO hasReplicationRootPO()
   {
      org.sdmlib.replication.creators.ModelPattern pattern = new org.sdmlib.replication.creators.ModelPattern();
      
      ReplicationRootPO patternObject = pattern.hasElementReplicationRootPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.replication.ReplicationRoot";
   }


   public ReplicationRootSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ReplicationRoot>)value);
      }
      else if (value != null)
      {
         this.add((ReplicationRoot) value);
      }
      
      return this;
   }
   
   public ReplicationRootSet without(ReplicationRoot value)
   {
      this.remove(value);
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (ReplicationRoot obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public ReplicationRootSet hasName(String value)
   {
      ReplicationRootSet result = new ReplicationRootSet();
      
      for (ReplicationRoot obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationRootSet hasName(String lower, String upper)
   {
      ReplicationRootSet result = new ReplicationRootSet();
      
      for (ReplicationRoot obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationRootSet withName(String value)
   {
      for (ReplicationRoot obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public ObjectSet getApplicationObject()
   {
      ObjectSet result = new ObjectSet();
      
      for (ReplicationRoot obj : this)
      {
         result.add(obj.getApplicationObject());
      }
      
      return result;
   }

   public ReplicationRootSet hasApplicationObject(Object value)
   {
      ReplicationRootSet result = new ReplicationRootSet();
      
      for (ReplicationRoot obj : this)
      {
         if (value == obj.getApplicationObject())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationRootSet withApplicationObject(Object value)
   {
      for (ReplicationRoot obj : this)
      {
         obj.setApplicationObject(value);
      }
      
      return this;
   }

   public ReplicationRootSet getKids()
   {
      ReplicationRootSet result = new ReplicationRootSet();
      
      for (ReplicationRoot obj : this)
      {
         result.with(obj.getKids());
      }
      
      return result;
   }

   public ReplicationRootSet hasKids(Object value)
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
      
      ReplicationRootSet answer = new ReplicationRootSet();
      
      for (ReplicationRoot obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getKids()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public ReplicationRootSet getKidsTransitive()
   {
      ReplicationRootSet todo = new ReplicationRootSet().with(this);
      
      ReplicationRootSet result = new ReplicationRootSet();
      
      while ( ! todo.isEmpty())
      {
         ReplicationRoot current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getKids().minus(result));
         }
      }
      
      return result;
   }

   public ReplicationRootSet withKids(ReplicationRoot value)
   {
      for (ReplicationRoot obj : this)
      {
         obj.withKids(value);
      }
      
      return this;
   }

   public ReplicationRootSet withoutKids(ReplicationRoot value)
   {
      for (ReplicationRoot obj : this)
      {
         obj.withoutKids(value);
      }
      
      return this;
   }

   public ReplicationRootSet getParent()
   {
      ReplicationRootSet result = new ReplicationRootSet();
      
      for (ReplicationRoot obj : this)
      {
         result.with(obj.getParent());
      }
      
      return result;
   }

   public ReplicationRootSet hasParent(Object value)
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
      
      ReplicationRootSet answer = new ReplicationRootSet();
      
      for (ReplicationRoot obj : this)
      {
         if (neighbors.contains(obj.getParent()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public ReplicationRootSet getParentTransitive()
   {
      ReplicationRootSet todo = new ReplicationRootSet().with(this);
      
      ReplicationRootSet result = new ReplicationRootSet();
      
      while ( ! todo.isEmpty())
      {
         ReplicationRoot current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getParent()))
            {
               todo.with(current.getParent());
            }
         }
      }
      
      return result;
   }

   public ReplicationRootSet withParent(ReplicationRoot value)
   {
      for (ReplicationRoot obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

}

