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

import java.net.Socket;
import java.util.Collection;

import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.replication.SeppelChannel;
import org.sdmlib.replication.SeppelSpaceProxy;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class SeppelChannelSet extends SimpleSet<SeppelChannel>
{

   public static final SeppelChannelSet EMPTY_SET = new SeppelChannelSet().withFlag(SeppelChannelSet.READONLY);


   public SeppelChannelPO hasSeppelChannelPO()
   {
      return new SeppelChannelPO(this.toArray(new SeppelChannel[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public SeppelChannelSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<SeppelChannel>)value);
      }
      else if (value != null)
      {
         this.add((SeppelChannel) value);
      }
      
      return this;
   }
   
   public SeppelChannelSet without(SeppelChannel value)
   {
      this.remove(value);
      return this;
   }

   public SocketSet getSocket()
   {
      SocketSet result = new SocketSet();
      
      for (SeppelChannel obj : this)
      {
         result.add(obj.getSocket());
      }
      
      return result;
   }

   public SeppelChannelSet hasSocket(Socket value)
   {
      SeppelChannelSet result = new SeppelChannelSet();
      
      for (SeppelChannel obj : this)
      {
         if (value == obj.getSocket())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelChannelSet withSocket(Socket value)
   {
      for (SeppelChannel obj : this)
      {
         obj.setSocket(value);
      }
      
      return this;
   }

   public booleanList getLoginValidated()
   {
      booleanList result = new booleanList();
      
      for (SeppelChannel obj : this)
      {
         result.add(obj.isLoginValidated());
      }
      
      return result;
   }

   public SeppelChannelSet hasLoginValidated(boolean value)
   {
      SeppelChannelSet result = new SeppelChannelSet();
      
      for (SeppelChannel obj : this)
      {
         if (value == obj.isLoginValidated())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelChannelSet withLoginValidated(boolean value)
   {
      for (SeppelChannel obj : this)
      {
         obj.setLoginValidated(value);
      }
      
      return this;
   }

   public SeppelSpaceProxySet getSeppelSpaceProxy()
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      for (SeppelChannel obj : this)
      {
         result.add(obj.getSeppelSpaceProxy());
      }
      
      return result;
   }

   public SeppelChannelSet hasSeppelSpaceProxy(Object value)
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
      
      SeppelChannelSet answer = new SeppelChannelSet();
      
      for (SeppelChannel obj : this)
      {
         if (neighbors.contains(obj.getSeppelSpaceProxy()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public SeppelChannelSet withSeppelSpaceProxy(SeppelSpaceProxy value)
   {
      for (SeppelChannel obj : this)
      {
         obj.withSeppelSpaceProxy(value);
      }
      
      return this;
   }



   public SeppelChannelPO filterSeppelChannelPO()
   {
      return new SeppelChannelPO(this.toArray(new SeppelChannel[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.replication.SeppelChannel";
   }

   /**
    * Loop through the current set of SeppelChannel objects and collect those SeppelChannel objects where the socket attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of SeppelChannel objects that match the parameter
    */
   public SeppelChannelSet filterSocket(Socket value)
   {
      SeppelChannelSet result = new SeppelChannelSet();
      
      for (SeppelChannel obj : this)
      {
         if (value == obj.getSocket())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of SeppelChannel objects and collect those SeppelChannel objects where the loginValidated attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of SeppelChannel objects that match the parameter
    */
   public SeppelChannelSet filterLoginValidated(boolean value)
   {
      SeppelChannelSet result = new SeppelChannelSet();
      
      for (SeppelChannel obj : this)
      {
         if (value == obj.isLoginValidated())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public SeppelChannelSet()
   {
      // empty
   }

   public SeppelChannelSet(SeppelChannel... objects)
   {
      for (SeppelChannel obj : objects)
      {
         this.add(obj);
      }
   }

   public SeppelChannelSet(Collection<SeppelChannel> objects)
   {
      this.addAll(objects);
   }
}
