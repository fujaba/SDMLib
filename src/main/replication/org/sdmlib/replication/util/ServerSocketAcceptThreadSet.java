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

import org.sdmlib.models.modelsets.intList;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.ServerSocketAcceptThread;

import de.uniks.networkparser.list.SimpleSet;

public class ServerSocketAcceptThreadSet extends SimpleSet<ServerSocketAcceptThread>
{


   public ServerSocketAcceptThreadPO hasServerSocketAcceptThreadPO()
   {
      return new ServerSocketAcceptThreadPO(this.toArray(new ServerSocketAcceptThread[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public ServerSocketAcceptThreadSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ServerSocketAcceptThread>)value);
      }
      else if (value != null)
      {
         this.add((ServerSocketAcceptThread) value);
      }
      
      return this;
   }
   
   public ServerSocketAcceptThreadSet without(ServerSocketAcceptThread value)
   {
      this.remove(value);
      return this;
   }

   public intList getPort()
   {
      intList result = new intList();
      
      for (ServerSocketAcceptThread obj : this)
      {
         result.add(obj.getPort());
      }
      
      return result;
   }

   public ServerSocketAcceptThreadSet hasPort(int value)
   {
      ServerSocketAcceptThreadSet result = new ServerSocketAcceptThreadSet();
      
      for (ServerSocketAcceptThread obj : this)
      {
         if (value == obj.getPort())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ServerSocketAcceptThreadSet hasPort(int lower, int upper)
   {
      ServerSocketAcceptThreadSet result = new ServerSocketAcceptThreadSet();
      
      for (ServerSocketAcceptThread obj : this)
      {
         if (lower <= obj.getPort() && obj.getPort() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ServerSocketAcceptThreadSet withPort(int value)
   {
      for (ServerSocketAcceptThread obj : this)
      {
         obj.setPort(value);
      }
      
      return this;
   }

   public ReplicationNodeSet getReplicationNode()
   {
      ReplicationNodeSet result = new ReplicationNodeSet();
      
      for (ServerSocketAcceptThread obj : this)
      {
         result.add(obj.getReplicationNode());
      }
      
      return result;
   }

   public ServerSocketAcceptThreadSet hasReplicationNode(ReplicationNode value)
   {
      ServerSocketAcceptThreadSet result = new ServerSocketAcceptThreadSet();
      
      for (ServerSocketAcceptThread obj : this)
      {
         if (value == obj.getReplicationNode())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ServerSocketAcceptThreadSet withReplicationNode(ReplicationNode value)
   {
      for (ServerSocketAcceptThread obj : this)
      {
         obj.setReplicationNode(value);
      }
      
      return this;
   }


   public static final ServerSocketAcceptThreadSet EMPTY_SET = new ServerSocketAcceptThreadSet().withFlag(ServerSocketAcceptThreadSet.READONLY);


   public ServerSocketAcceptThreadPO filterServerSocketAcceptThreadPO()
   {
      return new ServerSocketAcceptThreadPO(this.toArray(new ServerSocketAcceptThread[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.replication.ServerSocketAcceptThread";
   }

   /**
    * Loop through the current set of ServerSocketAcceptThread objects and collect those ServerSocketAcceptThread objects where the port attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ServerSocketAcceptThread objects that match the parameter
    */
   public ServerSocketAcceptThreadSet filterPort(int value)
   {
      ServerSocketAcceptThreadSet result = new ServerSocketAcceptThreadSet();
      
      for (ServerSocketAcceptThread obj : this)
      {
         if (value == obj.getPort())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ServerSocketAcceptThread objects and collect those ServerSocketAcceptThread objects where the port attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ServerSocketAcceptThread objects that match the parameter
    */
   public ServerSocketAcceptThreadSet filterPort(int lower, int upper)
   {
      ServerSocketAcceptThreadSet result = new ServerSocketAcceptThreadSet();
      
      for (ServerSocketAcceptThread obj : this)
      {
         if (lower <= obj.getPort() && obj.getPort() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ServerSocketAcceptThread objects and collect those ServerSocketAcceptThread objects where the replicationNode attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ServerSocketAcceptThread objects that match the parameter
    */
   public ServerSocketAcceptThreadSet filterReplicationNode(ReplicationNode value)
   {
      ServerSocketAcceptThreadSet result = new ServerSocketAcceptThreadSet();
      
      for (ServerSocketAcceptThread obj : this)
      {
         if (value == obj.getReplicationNode())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public ServerSocketAcceptThreadSet()
   {
      // empty
   }

   public ServerSocketAcceptThreadSet(ServerSocketAcceptThread... objects)
   {
      for (ServerSocketAcceptThread obj : objects)
      {
         this.add(obj);
      }
   }

   public ServerSocketAcceptThreadSet(Collection<ServerSocketAcceptThread> objects)
   {
      this.addAll(objects);
   }
}
