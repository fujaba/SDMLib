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
   
package org.sdmlib.replication.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.modelsets.longList;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.SharedSpace;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.replication.ReplicationServer;
import org.sdmlib.replication.util.ReplicationServerSet;
import org.sdmlib.replication.util.SharedSpaceSet;

public class ReplicationNodeSet extends SimpleSet<ReplicationNode>
{


   public ReplicationNodePO hasReplicationNodePO()
   {
      return new ReplicationNodePO(this.toArray(new ReplicationNode[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public ReplicationNodeSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ReplicationNode>)value);
      }
      else if (value != null)
      {
         this.add((ReplicationNode) value);
      }
      
      return this;
   }
   
   public ReplicationNodeSet without(ReplicationNode value)
   {
      this.remove(value);
      return this;
   }

   public StringList getSpaceId()
   {
      StringList result = new StringList();
      
      for (ReplicationNode obj : this)
      {
         result.add(obj.getSpaceId());
      }
      
      return result;
   }

   public ReplicationNodeSet hasSpaceId(String value)
   {
      ReplicationNodeSet result = new ReplicationNodeSet();
      
      for (ReplicationNode obj : this)
      {
         if (value.equals(obj.getSpaceId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationNodeSet hasSpaceId(String lower, String upper)
   {
      ReplicationNodeSet result = new ReplicationNodeSet();
      
      for (ReplicationNode obj : this)
      {
         if (lower.compareTo(obj.getSpaceId()) <= 0 && obj.getSpaceId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationNodeSet withSpaceId(String value)
   {
      for (ReplicationNode obj : this)
      {
         obj.setSpaceId(value);
      }
      
      return this;
   }

   public ChangeHistorySet getHistory()
   {
      ChangeHistorySet result = new ChangeHistorySet();
      
      for (ReplicationNode obj : this)
      {
         result.add(obj.getHistory());
      }
      
      return result;
   }

   public ReplicationNodeSet hasHistory(ChangeHistory value)
   {
      ReplicationNodeSet result = new ReplicationNodeSet();
      
      for (ReplicationNode obj : this)
      {
         if (value == obj.getHistory())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationNodeSet withHistory(ChangeHistory value)
   {
      for (ReplicationNode obj : this)
      {
         obj.setHistory(value);
      }
      
      return this;
   }

   public longList getLastChangeId()
   {
      longList result = new longList();
      
      for (ReplicationNode obj : this)
      {
         result.add(obj.getLastChangeId());
      }
      
      return result;
   }

   public ReplicationNodeSet hasLastChangeId(long value)
   {
      ReplicationNodeSet result = new ReplicationNodeSet();
      
      for (ReplicationNode obj : this)
      {
         if (value == obj.getLastChangeId())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationNodeSet hasLastChangeId(long lower, long upper)
   {
      ReplicationNodeSet result = new ReplicationNodeSet();
      
      for (ReplicationNode obj : this)
      {
         if (lower <= obj.getLastChangeId() && obj.getLastChangeId() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationNodeSet withLastChangeId(long value)
   {
      for (ReplicationNode obj : this)
      {
         obj.setLastChangeId(value);
      }
      
      return this;
   }

   public StringList getNodeId()
   {
      StringList result = new StringList();
      
      for (ReplicationNode obj : this)
      {
         result.add(obj.getNodeId());
      }
      
      return result;
   }

   public ReplicationNodeSet hasNodeId(String value)
   {
      ReplicationNodeSet result = new ReplicationNodeSet();
      
      for (ReplicationNode obj : this)
      {
         if (value.equals(obj.getNodeId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationNodeSet hasNodeId(String lower, String upper)
   {
      ReplicationNodeSet result = new ReplicationNodeSet();
      
      for (ReplicationNode obj : this)
      {
         if (lower.compareTo(obj.getNodeId()) <= 0 && obj.getNodeId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationNodeSet withNodeId(String value)
   {
      for (ReplicationNode obj : this)
      {
         obj.setNodeId(value);
      }
      
      return this;
   }

   public booleanList getJavaFXApplication()
   {
      booleanList result = new booleanList();
      
      for (ReplicationNode obj : this)
      {
         result.add(obj.isJavaFXApplication());
      }
      
      return result;
   }

   public ReplicationNodeSet hasJavaFXApplication(boolean value)
   {
      ReplicationNodeSet result = new ReplicationNodeSet();
      
      for (ReplicationNode obj : this)
      {
         if (value == obj.isJavaFXApplication())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationNodeSet withJavaFXApplication(boolean value)
   {
      for (ReplicationNode obj : this)
      {
         obj.setJavaFXApplication(value);
      }
      
      return this;
   }

   public SharedSpaceSet getSharedSpaces()
   {
      SharedSpaceSet result = new SharedSpaceSet();
      
      for (ReplicationNode obj : this)
      {
         result.addAll(obj.getSharedSpaces());
      }
      
      return result;
   }

   public ReplicationNodeSet hasSharedSpaces(Object value)
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
      
      ReplicationNodeSet answer = new ReplicationNodeSet();
      
      for (ReplicationNode obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getSharedSpaces()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ReplicationNodeSet withSharedSpaces(SharedSpace value)
   {
      for (ReplicationNode obj : this)
      {
         obj.withSharedSpaces(value);
      }
      
      return this;
   }

   public ReplicationNodeSet withoutSharedSpaces(SharedSpace value)
   {
      for (ReplicationNode obj : this)
      {
         obj.withoutSharedSpaces(value);
      }
      
      return this;
   }


   public static final ReplicationNodeSet EMPTY_SET = new ReplicationNodeSet().withFlag(ReplicationNodeSet.READONLY);


   public ReplicationNodePO filterReplicationNodePO()
   {
      return new ReplicationNodePO(this.toArray(new ReplicationNode[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.replication.ReplicationNode";
   }

   public ReplicationNodeSet()
   {
      // empty
   }

   public ReplicationNodeSet(ReplicationNode... objects)
   {
      for (ReplicationNode obj : objects)
      {
         this.add(obj);
      }
   }

   public ReplicationNodeSet(Collection<ReplicationNode> objects)
   {
      this.addAll(objects);
   }


   public ReplicationNodePO createReplicationNodePO()
   {
      return new ReplicationNodePO(this.toArray(new ReplicationNode[this.size()]));
   }


   @Override
   public ReplicationNodeSet getNewList(boolean keyValue)
   {
      return new ReplicationNodeSet();
   }


   public ReplicationNodeSet filter(Condition<ReplicationNode> condition) {
      ReplicationNodeSet filterList = new ReplicationNodeSet();
      filterItems(filterList, condition);
      return filterList;
   }

   public ReplicationServerSet instanceOfReplicationServer()
   {
      ReplicationServerSet result = new ReplicationServerSet();
      
      for(Object obj : this)
      {
         if (obj instanceof ReplicationServer)
         {
            result.with(obj);
         }
      }
      
      return result;
   }}
