package org.sdmlib.serialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ArrayMap<Key, Value> implements Map<Key, Value>
{
   private ArrayList<Key> keyList = new ArrayList<Key>();
   private ArrayList<Value> valueList = new ArrayList<Value>();

   @Override
   public int size()
   {
      return keyList.size();
   }

   @Override
   public boolean isEmpty()
   {
      return keyList.isEmpty();
   }

   @Override
   public boolean containsKey(Object key)
   {
      return keyList.contains(key);
   }

   @Override
   public boolean containsValue(Object value)
   {
      return valueList.contains(value);
   }

   @Override
   public Value get(Object key)
   {
      int pos = keyList.indexOf(key);
      return valueList.get(pos);
   }

   @Override
   public Value put(Key key, Value value)
   {
      int pos = keyList.indexOf(key);

      if (pos >= 0)
      {
         Value oldValue = valueList.get(pos);
         valueList.set(pos, value);
         return oldValue;
      }

      keyList.add(key);
      valueList.add(value);

      return null;
   }

   @Override
   public Value remove(Object key)
   {
      int pos = keyList.indexOf(key);

      if (pos >= 0)
      {
         keyList.remove(pos);
         Value oldValue = valueList.get(pos);
         valueList.remove(pos);
         return oldValue;
      }
      return null;
   }

   @Override
   public void putAll(Map<? extends Key, ? extends Value> m)
   {
      for (java.util.Map.Entry<? extends Key, ? extends Value> entry : m.entrySet())
      {
         this.put(entry.getKey(), entry.getValue());
      }
   }

   @Override
   public void clear()
   {
      keyList.clear();
      valueList.clear();
   }

   @Override
   public Set<Key> keySet()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ArrayList<Key> keyList()
   {
      return keyList;
   }

   @Override
   public Collection<Value> values()
   {
      // TODO Auto-generated method stub
      return valueList;
   }

   @Override
   public Set<java.util.Map.Entry<Key, Value>> entrySet()
   {
      // TODO Auto-generated method stub
      return null;
   }

}
