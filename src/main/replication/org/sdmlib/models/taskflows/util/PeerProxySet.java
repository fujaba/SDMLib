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

import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.taskflows.PeerProxy;
import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.NumberList;

public class PeerProxySet extends SimpleSet<PeerProxy>
{


   public PeerProxyPO hasPeerProxyPO()
   {
      return new PeerProxyPO(this.toArray(new PeerProxy[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public PeerProxySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<PeerProxy>)value);
      }
      else if (value != null)
      {
         this.add((PeerProxy) value);
      }
      
      return this;
   }
   
   public PeerProxySet without(PeerProxy value)
   {
      this.remove(value);
      return this;
   }

   public StringList getIp()
   {
      StringList result = new StringList();
      
      for (PeerProxy obj : this)
      {
         result.add(obj.getIp());
      }
      
      return result;
   }

   public PeerProxySet hasIp(String value)
   {
      PeerProxySet result = new PeerProxySet();
      
      for (PeerProxy obj : this)
      {
         if (value.equals(obj.getIp()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PeerProxySet hasIp(String lower, String upper)
   {
      PeerProxySet result = new PeerProxySet();
      
      for (PeerProxy obj : this)
      {
         if (lower.compareTo(obj.getIp()) <= 0 && obj.getIp().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PeerProxySet withIp(String value)
   {
      for (PeerProxy obj : this)
      {
         obj.setIp(value);
      }
      
      return this;
   }

   public intList getPort()
   {
      intList result = new intList();
      
      for (PeerProxy obj : this)
      {
         result.add(obj.getPort());
      }
      
      return result;
   }

   public PeerProxySet hasPort(int value)
   {
      PeerProxySet result = new PeerProxySet();
      
      for (PeerProxy obj : this)
      {
         if (value == obj.getPort())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PeerProxySet hasPort(int lower, int upper)
   {
      PeerProxySet result = new PeerProxySet();
      
      for (PeerProxy obj : this)
      {
         if (lower <= obj.getPort() && obj.getPort() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PeerProxySet withPort(int value)
   {
      for (PeerProxy obj : this)
      {
         obj.setPort(value);
      }
      
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (PeerProxy obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public PeerProxySet hasIdMap(SDMLibJsonIdMap value)
   {
      PeerProxySet result = new PeerProxySet();
      
      for (PeerProxy obj : this)
      {
         if (value == obj.getIdMap())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PeerProxySet withIdMap(SDMLibJsonIdMap value)
   {
      for (PeerProxy obj : this)
      {
         obj.setIdMap(value);
      }
      
      return this;
   }


   public static final PeerProxySet EMPTY_SET = new PeerProxySet().withFlag(PeerProxySet.READONLY);


   public PeerProxyPO filterPeerProxyPO()
   {
      return new PeerProxyPO(this.toArray(new PeerProxy[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.taskflows.PeerProxy";
   }

   /**
    * Loop through the current set of PeerProxy objects and collect those PeerProxy objects where the ip attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PeerProxy objects that match the parameter
    */
   public PeerProxySet filterIp(String value)
   {
      PeerProxySet result = new PeerProxySet();
      
      for (PeerProxy obj : this)
      {
         if (value.equals(obj.getIp()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PeerProxy objects and collect those PeerProxy objects where the ip attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PeerProxy objects that match the parameter
    */
   public PeerProxySet filterIp(String lower, String upper)
   {
      PeerProxySet result = new PeerProxySet();
      
      for (PeerProxy obj : this)
      {
         if (lower.compareTo(obj.getIp()) <= 0 && obj.getIp().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PeerProxy objects and collect those PeerProxy objects where the port attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PeerProxy objects that match the parameter
    */
   public PeerProxySet filterPort(int value)
   {
      PeerProxySet result = new PeerProxySet();
      
      for (PeerProxy obj : this)
      {
         if (value == obj.getPort())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PeerProxy objects and collect those PeerProxy objects where the port attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PeerProxy objects that match the parameter
    */
   public PeerProxySet filterPort(int lower, int upper)
   {
      PeerProxySet result = new PeerProxySet();
      
      for (PeerProxy obj : this)
      {
         if (lower <= obj.getPort() && obj.getPort() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PeerProxy objects and collect those PeerProxy objects where the idMap attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PeerProxy objects that match the parameter
    */
   public PeerProxySet filterIdMap(SDMLibJsonIdMap value)
   {
      PeerProxySet result = new PeerProxySet();
      
      for (PeerProxy obj : this)
      {
         if (value == obj.getIdMap())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public PeerProxySet()
   {
      // empty
   }

   public PeerProxySet(PeerProxy... objects)
   {
      for (PeerProxy obj : objects)
      {
         this.add(obj);
      }
   }

   public PeerProxySet(Collection<PeerProxy> objects)
   {
      this.addAll(objects);
   }


   public PeerProxyPO createPeerProxyPO()
   {
      return new PeerProxyPO(this.toArray(new PeerProxy[this.size()]));
   }


   @Override
   public PeerProxySet getNewList(boolean keyValue)
   {
      return new PeerProxySet();
   }


   public PeerProxySet filter(Condition<PeerProxy> condition) {
      PeerProxySet filterList = new PeerProxySet();
      filterItems(filterList, condition);
      return filterList;
   }
   /**
    * Loop through the current set of PeerProxy objects and collect those PeerProxy objects where the idMap attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PeerProxy objects that match the parameter
    */
   public PeerProxySet createIdMapCondition(SDMLibJsonIdMap value)
   {
      PeerProxySet result = new PeerProxySet();
      
      for (PeerProxy obj : this)
      {
         if (value == obj.getIdMap())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PeerProxy objects and collect those PeerProxy objects where the ip attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PeerProxy objects that match the parameter
    */
   public PeerProxySet createIpCondition(String value)
   {
      PeerProxySet result = new PeerProxySet();
      
      for (PeerProxy obj : this)
      {
         if (value.equals(obj.getIp()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PeerProxy objects and collect those PeerProxy objects where the ip attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PeerProxy objects that match the parameter
    */
   public PeerProxySet createIpCondition(String lower, String upper)
   {
      PeerProxySet result = new PeerProxySet();
      
      for (PeerProxy obj : this)
      {
         if (lower.compareTo(obj.getIp()) <= 0 && obj.getIp().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PeerProxy objects and collect those PeerProxy objects where the port attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PeerProxy objects that match the parameter
    */
   public PeerProxySet createPortCondition(int value)
   {
      PeerProxySet result = new PeerProxySet();
      
      for (PeerProxy obj : this)
      {
         if (value == obj.getPort())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PeerProxy objects and collect those PeerProxy objects where the port attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PeerProxy objects that match the parameter
    */
   public PeerProxySet createPortCondition(int lower, int upper)
   {
      PeerProxySet result = new PeerProxySet();
      
      for (PeerProxy obj : this)
      {
         if (lower <= obj.getPort() && obj.getPort() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
