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
   
package org.sdmlib.models.taskflows.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.taskflows.SocketThread;
import org.sdmlib.serialization.SDMLibJsonIdMap;

public class SocketThreadSet extends SDMSet<SocketThread>
{


   public SocketThreadPO hasSocketThreadPO()
   {
      return new SocketThreadPO(this.toArray(new SocketThread[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.taskflows.SocketThread";
   }


   @SuppressWarnings("unchecked")
   public SocketThreadSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<SocketThread>)value);
      }
      else if (value != null)
      {
         this.add((SocketThread) value);
      }
      
      return this;
   }
   
   public SocketThreadSet without(SocketThread value)
   {
      this.remove(value);
      return this;
   }

   public StringList getIp()
   {
      StringList result = new StringList();
      
      for (SocketThread obj : this)
      {
         result.add(obj.getIp());
      }
      
      return result;
   }

   public SocketThreadSet hasIp(String value)
   {
      SocketThreadSet result = new SocketThreadSet();
      
      for (SocketThread obj : this)
      {
         if (value.equals(obj.getIp()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SocketThreadSet hasIp(String lower, String upper)
   {
      SocketThreadSet result = new SocketThreadSet();
      
      for (SocketThread obj : this)
      {
         if (lower.compareTo(obj.getIp()) <= 0 && obj.getIp().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SocketThreadSet withIp(String value)
   {
      for (SocketThread obj : this)
      {
         obj.setIp(value);
      }
      
      return this;
   }

   public intList getPort()
   {
      intList result = new intList();
      
      for (SocketThread obj : this)
      {
         result.add(obj.getPort());
      }
      
      return result;
   }

   public SocketThreadSet hasPort(int value)
   {
      SocketThreadSet result = new SocketThreadSet();
      
      for (SocketThread obj : this)
      {
         if (value == obj.getPort())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SocketThreadSet hasPort(int lower, int upper)
   {
      SocketThreadSet result = new SocketThreadSet();
      
      for (SocketThread obj : this)
      {
         if (lower <= obj.getPort() && obj.getPort() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SocketThreadSet withPort(int value)
   {
      for (SocketThread obj : this)
      {
         obj.setPort(value);
      }
      
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (SocketThread obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public SocketThreadSet hasIdMap(SDMLibJsonIdMap value)
   {
      SocketThreadSet result = new SocketThreadSet();
      
      for (SocketThread obj : this)
      {
         if (value == obj.getIdMap())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SocketThreadSet withIdMap(SDMLibJsonIdMap value)
   {
      for (SocketThread obj : this)
      {
         obj.setIdMap(value);
      }
      
      return this;
   }

   public ObjectSet getDefaultTargetThread()
   {
      ObjectSet result = new ObjectSet();
      
      for (SocketThread obj : this)
      {
         result.add(obj.getDefaultTargetThread());
      }
      
      return result;
   }

   public SocketThreadSet hasDefaultTargetThread(Object value)
   {
      SocketThreadSet result = new SocketThreadSet();
      
      for (SocketThread obj : this)
      {
         if (value == obj.getDefaultTargetThread())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SocketThreadSet withDefaultTargetThread(Object value)
   {
      for (SocketThread obj : this)
      {
         obj.setDefaultTargetThread(value);
      }
      
      return this;
   }


   public static final SocketThreadSet EMPTY_SET = new SocketThreadSet().withReadOnly(true);
}
