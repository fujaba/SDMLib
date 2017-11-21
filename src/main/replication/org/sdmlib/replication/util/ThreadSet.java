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

import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.replication.util.SharedSpaceSet;
import org.sdmlib.replication.ReplicationChannel;
import org.sdmlib.replication.util.ReplicationChannelSet;
import org.sdmlib.replication.ServerSocketAcceptThread;
import org.sdmlib.replication.util.ServerSocketAcceptThreadSet;
import org.sdmlib.replication.SeppelSpace;
import org.sdmlib.replication.util.SeppelSpaceSet;
import org.sdmlib.replication.SeppelChannel;
import org.sdmlib.replication.util.SeppelChannelSet;

public class ThreadSet extends SimpleSet<Thread>
{


   public ThreadPO hasThreadPO()
   {
      return new ThreadPO(this.toArray(new Thread[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public ThreadSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Thread>)value);
      }
      else if (value != null)
      {
         this.add((Thread) value);
      }
      
      return this;
   }
   
   public ThreadSet without(Thread value)
   {
      this.remove(value);
      return this;
   }


   public static final ThreadSet EMPTY_SET = new ThreadSet().withFlag(ThreadSet.READONLY);


   public ThreadPO filterThreadPO()
   {
      return new ThreadPO(this.toArray(new Thread[this.size()]));
   }


   public String getEntryType()
   {
      return "java.lang.Thread";
   }

   public ThreadSet()
   {
      // empty
   }

   public ThreadSet(Thread... objects)
   {
      for (Thread obj : objects)
      {
         this.add(obj);
      }
   }

   public ThreadSet(Collection<Thread> objects)
   {
      this.addAll(objects);
   }


   public ThreadPO createThreadPO()
   {
      return new ThreadPO(this.toArray(new Thread[this.size()]));
   }


   @Override
   public ThreadSet getNewList(boolean keyValue)
   {
      return new ThreadSet();
   }


   public ThreadSet filter(Condition<Thread> condition) {
      ThreadSet filterList = new ThreadSet();
      filterItems(filterList, condition);
      return filterList;
   }

   public SharedSpaceSet instanceOfSharedSpace()
   {
      SharedSpaceSet result = new SharedSpaceSet();
      
      for(Object obj : this)
      {
         if (obj instanceof SharedSpace)
         {
            result.with(obj);
         }
      }
      
      return result;
   }

   public ReplicationChannelSet instanceOfReplicationChannel()
   {
      ReplicationChannelSet result = new ReplicationChannelSet();
      
      for(Object obj : this)
      {
         if (obj instanceof ReplicationChannel)
         {
            result.with(obj);
         }
      }
      
      return result;
   }

   public ServerSocketAcceptThreadSet instanceOfServerSocketAcceptThread()
   {
      ServerSocketAcceptThreadSet result = new ServerSocketAcceptThreadSet();
      
      for(Object obj : this)
      {
         if (obj instanceof ServerSocketAcceptThread)
         {
            result.with(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceSet instanceOfSeppelSpace()
   {
      SeppelSpaceSet result = new SeppelSpaceSet();
      
      for(Object obj : this)
      {
         if (obj instanceof SeppelSpace)
         {
            result.with(obj);
         }
      }
      
      return result;
   }

   public SeppelChannelSet instanceOfSeppelChannel()
   {
      SeppelChannelSet result = new SeppelChannelSet();
      
      for(Object obj : this)
      {
         if (obj instanceof SeppelChannel)
         {
            result.with(obj);
         }
      }
      
      return result;
   }}
