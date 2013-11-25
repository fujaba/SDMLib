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
   
package org.sdmlib.replication.creators;

import java.util.LinkedHashSet;

import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.ServerSocketAcceptThread;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import java.util.List;

public class ServerSocketAcceptThreadSet extends LinkedHashSet<ServerSocketAcceptThread> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (ServerSocketAcceptThread elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.replication.ServerSocketAcceptThread";
   }


   public ServerSocketAcceptThreadSet with(ServerSocketAcceptThread value)
   {
      this.add(value);
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

   public ServerSocketAcceptThreadSet withReplicationNode(ReplicationNode value)
   {
      for (ServerSocketAcceptThread obj : this)
      {
         obj.setReplicationNode(value);
      }
      
      return this;
   }

}


