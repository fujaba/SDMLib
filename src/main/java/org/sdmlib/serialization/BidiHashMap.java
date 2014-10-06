package org.sdmlib.serialization;

import java.util.HashMap;

import de.uniks.networkparser.AbstractBidiMap;

public class BidiHashMap<K, V> extends AbstractBidiMap<K, V>
{

   public BidiHashMap()
   {
      super(new HashMap<K, V>(), new HashMap<V, K>());
   }
   
   @Override
   public BidiHashMap<K, V> with(K key, V value)
   {
      return (BidiHashMap<K, V>) super.with(key, value);
   }

   @Override
   public BidiHashMap<K, V> without(K key, V value)
   {
      return (BidiHashMap<K, V>) super.with(key, value);
   }

}
