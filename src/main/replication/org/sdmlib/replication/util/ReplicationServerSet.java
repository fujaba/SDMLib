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
import org.sdmlib.replication.ReplicationServer;
import org.sdmlib.replication.SharedSpace;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.replication.util.SharedSpaceSet;

public class ReplicationServerSet extends SimpleSet<ReplicationServer>
{


   public ReplicationServerPO hasReplicationServerPO()
   {
      return new ReplicationServerPO(this.toArray(new ReplicationServer[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public ReplicationServerSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ReplicationServer>)value);
      }
      else if (value != null)
      {
         this.add((ReplicationServer) value);
      }
      
      return this;
   }
   
   public ReplicationServerSet without(ReplicationServer value)
   {
      this.remove(value);
      return this;
   }

   public StringList getSpaceId()
   {
      StringList result = new StringList();
      
      for (ReplicationServer obj : this)
      {
         result.add(obj.getSpaceId());
      }
      
      return result;
   }

   public ReplicationServerSet hasSpaceId(String value)
   {
      ReplicationServerSet result = new ReplicationServerSet();
      
      for (ReplicationServer obj : this)
      {
         if (value.equals(obj.getSpaceId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationServerSet hasSpaceId(String lower, String upper)
   {
      ReplicationServerSet result = new ReplicationServerSet();
      
      for (ReplicationServer obj : this)
      {
         if (lower.compareTo(obj.getSpaceId()) <= 0 && obj.getSpaceId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationServerSet withSpaceId(String value)
   {
      for (ReplicationServer obj : this)
      {
         obj.setSpaceId(value);
      }
      
      return this;
   }

   public ChangeHistorySet getHistory()
   {
      ChangeHistorySet result = new ChangeHistorySet();
      
      for (ReplicationServer obj : this)
      {
         result.add(obj.getHistory());
      }
      
      return result;
   }

   public ReplicationServerSet hasHistory(ChangeHistory value)
   {
      ReplicationServerSet result = new ReplicationServerSet();
      
      for (ReplicationServer obj : this)
      {
         if (value == obj.getHistory())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationServerSet withHistory(ChangeHistory value)
   {
      for (ReplicationServer obj : this)
      {
         obj.setHistory(value);
      }
      
      return this;
   }

   public longList getLastChangeId()
   {
      longList result = new longList();
      
      for (ReplicationServer obj : this)
      {
         result.add(obj.getLastChangeId());
      }
      
      return result;
   }

   public ReplicationServerSet hasLastChangeId(long value)
   {
      ReplicationServerSet result = new ReplicationServerSet();
      
      for (ReplicationServer obj : this)
      {
         if (value == obj.getLastChangeId())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationServerSet hasLastChangeId(long lower, long upper)
   {
      ReplicationServerSet result = new ReplicationServerSet();
      
      for (ReplicationServer obj : this)
      {
         if (lower <= obj.getLastChangeId() && obj.getLastChangeId() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationServerSet withLastChangeId(long value)
   {
      for (ReplicationServer obj : this)
      {
         obj.setLastChangeId(value);
      }
      
      return this;
   }

   public StringList getNodeId()
   {
      StringList result = new StringList();
      
      for (ReplicationServer obj : this)
      {
         result.add(obj.getNodeId());
      }
      
      return result;
   }

   public ReplicationServerSet hasNodeId(String value)
   {
      ReplicationServerSet result = new ReplicationServerSet();
      
      for (ReplicationServer obj : this)
      {
         if (value.equals(obj.getNodeId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationServerSet hasNodeId(String lower, String upper)
   {
      ReplicationServerSet result = new ReplicationServerSet();
      
      for (ReplicationServer obj : this)
      {
         if (lower.compareTo(obj.getNodeId()) <= 0 && obj.getNodeId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationServerSet withNodeId(String value)
   {
      for (ReplicationServer obj : this)
      {
         obj.setNodeId(value);
      }
      
      return this;
   }

   public booleanList getJavaFXApplication()
   {
      booleanList result = new booleanList();
      
      for (ReplicationServer obj : this)
      {
         result.add(obj.isJavaFXApplication());
      }
      
      return result;
   }

   public ReplicationServerSet hasJavaFXApplication(boolean value)
   {
      ReplicationServerSet result = new ReplicationServerSet();
      
      for (ReplicationServer obj : this)
      {
         if (value == obj.isJavaFXApplication())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationServerSet withJavaFXApplication(boolean value)
   {
      for (ReplicationServer obj : this)
      {
         obj.setJavaFXApplication(value);
      }
      
      return this;
   }

   public SharedSpaceSet getSharedSpaces()
   {
      SharedSpaceSet result = new SharedSpaceSet();
      
      for (ReplicationServer obj : this)
      {
         result.addAll(obj.getSharedSpaces());
      }
      
      return result;
   }

   public ReplicationServerSet hasSharedSpaces(Object value)
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
      
      ReplicationServerSet answer = new ReplicationServerSet();
      
      for (ReplicationServer obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getSharedSpaces()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ReplicationServerSet withSharedSpaces(SharedSpace value)
   {
      for (ReplicationServer obj : this)
      {
         obj.withSharedSpaces(value);
      }
      
      return this;
   }

   public ReplicationServerSet withoutSharedSpaces(SharedSpace value)
   {
      for (ReplicationServer obj : this)
      {
         obj.withoutSharedSpaces(value);
      }
      
      return this;
   }


   public static final ReplicationServerSet EMPTY_SET = new ReplicationServerSet().withFlag(ReplicationServerSet.READONLY);


   public ReplicationServerPO filterReplicationServerPO()
   {
      return new ReplicationServerPO(this.toArray(new ReplicationServer[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.replication.ReplicationServer";
   }

   public ReplicationServerSet()
   {
      // empty
   }

   public ReplicationServerSet(ReplicationServer... objects)
   {
      for (ReplicationServer obj : objects)
      {
         this.add(obj);
      }
   }

   public ReplicationServerSet(Collection<ReplicationServer> objects)
   {
      this.addAll(objects);
   }


   public ReplicationServerPO createReplicationServerPO()
   {
      return new ReplicationServerPO(this.toArray(new ReplicationServer[this.size()]));
   }


   @Override
   public ReplicationServerSet getNewList(boolean keyValue)
   {
      return new ReplicationServerSet();
   }


   public ReplicationServerSet filter(Condition<ReplicationServer> condition) {
      ReplicationServerSet filterList = new ReplicationServerSet();
      filterItems(filterList, condition);
      return filterList;
   }}
