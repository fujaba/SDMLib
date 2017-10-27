package org.sdmlib.models.pattern;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Objects;

import org.sdmlib.models.tables.util.ObjectSet;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.list.SimpleKeyValueList;

public class LazyCloneOp
{
   private IdMap map = null;
   
   private LinkedHashMap<Object, Object> origToCloneMap = new LinkedHashMap<Object, Object>();
   
   public Object clone(Object orig)
   {
      Objects.requireNonNull(map);
      
      SendableEntityCreator creator = map.getCreatorClass(orig);
      
      Object clone = creator.getSendableInstance(false);
      
      origToCloneMap.put(orig, clone);
      
      // copy properties
      for ( String prop : creator.getProperties())
      {
         Object value = creator.getValue(orig, prop);
         
         if (value != null && value instanceof Collection)
         {
            // if one of our neighbors is currently cloned, we connect to that clone, only. 
            Object neighborOrig = null;
            Object neighborClone = null; 
            for (Object obj : (Collection)value)
            {
               neighborClone = origToCloneMap.get(obj);
               if (neighborClone != null)
               {
                  neighborOrig = obj;
                  break;
               }
            }
            
            if (neighborClone != null)
            {
               creator.setValue(orig, prop, neighborClone, SendableEntityCreator.REMOVE);
               creator.setValue(clone, prop, neighborClone, "new");
            }
            else
            {
               for (Object elem : (Collection)value)
               {
                  creator.setValue(clone, prop, elem, "new");
               }
            }
         }
         else
         {
            creator.setValue(clone, prop, value, "new");
         }
      }
      
      
      return clone;
   }


   public IdMap getMap()
   {
      return map;
   }


   public LazyCloneOp setMap(IdMap map)
   {
      this.map = map;
      
      return this;
   }
}
