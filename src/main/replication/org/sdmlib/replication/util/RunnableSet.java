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

import org.sdmlib.replication.RemoteTask;

import de.uniks.networkparser.list.SimpleSet;

public class RunnableSet extends SimpleSet<Runnable>
{


   public RunnablePO hasRunnablePO()
   {
      return new RunnablePO(this.toArray(new Runnable[this.size()]));
   }


   @SuppressWarnings("unchecked")
   public RunnableSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Runnable>)value);
      }
      else if (value != null)
      {
         this.add((Runnable) value);
      }
      
      return this;
   }
   
   public RunnableSet without(Runnable value)
   {
      this.remove(value);
      return this;
   }


   public static final RunnableSet EMPTY_SET = new RunnableSet().withFlag(RunnableSet.READONLY);


   public RunnablePO filterRunnablePO()
   {
      return new RunnablePO(this.toArray(new Runnable[this.size()]));
   }


   public String getEntryType()
   {
      return "java.lang.Runnable";
   }

   public RunnableSet()
   {
      // empty
   }

   public RunnableSet(Runnable... objects)
   {
      for (Runnable obj : objects)
      {
         this.add(obj);
      }
   }

   public RunnableSet(Collection<Runnable> objects)
   {
      this.addAll(objects);
   }


   public RunnablePO createRunnablePO()
   {
      return new RunnablePO(this.toArray(new Runnable[this.size()]));
   }


   @Override
   public RunnableSet getNewList(boolean keyValue)
   {
      return new RunnableSet();
   }

   public RemoteTaskSet instanceOfRemoteTask()
   {
      RemoteTaskSet result = new RemoteTaskSet();
      
      for(Object obj : this)
      {
         if (obj instanceof RemoteTask)
         {
            result.with(obj);
         }
      }
      
      return result;
   }}
