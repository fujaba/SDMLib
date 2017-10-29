package org.sdmlib.models.pattern;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Objects;

import org.sdmlib.models.modelsets.doubleList;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.AggregatedEntityCreator;
import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleEntity;
import de.uniks.networkparser.list.SimpleKeyValueList;

public class LazyCloneOp
{
   private IdMap map = null;
   
   private LinkedHashMap<Object, Object> origToCloneMap = new LinkedHashMap<Object, Object>();
   
   public Object clone(Object orig)
   {
      Objects.requireNonNull(map);
      
      Object clone = origToCloneMap.get(orig);

      if (clone != null)
      {
         // already done
         return clone;
      }

      LinkedList<Object> climbTodo = new LinkedList<Object>();
      LinkedList<Object> cloneTodo = new LinkedList<Object>();

      climbTodo.add(orig);
      cloneTodo.add(orig);
      
      ObjectSet graph = new ObjectSet();
      
      // try to compute clone graph from first entry of origToCloneMap 
      if ( ! origToCloneMap.isEmpty())
      {
         orig = origToCloneMap.keySet().iterator().next();
         clone = origToCloneMap.get(orig);
         aggregate(graph, clone);
      }
      
      
      while ( ! climbTodo.isEmpty())
      {
         orig = climbTodo.pollFirst();
         // climb up to root and add parents to todo list
         AggregatedEntityCreator creator = (AggregatedEntityCreator) map.getCreatorClass(orig);
      
         for (String upProp : creator.getUpProperties())
         {
            Object value = creator.getValue(orig, upProp);
         
            if (value != null && value instanceof Collection)
            {
               for (Object parent : (Collection) value)
               {
                  clone = origToCloneMap.get(parent);
                  
                  if (clone != null)
                  {
                     break;
                  }
                  
                  if (graph.contains(parent))
                  {
                     // this parent belongs to the clone graph and does not yet have a clone
                     climbTodo.add(parent);
                     cloneTodo.add(parent);
                  }
               }
            }
         }
      }
      
      
      while (! cloneTodo.isEmpty())
      {
         orig = cloneTodo.pollLast();

         AggregatedEntityCreator creator = (AggregatedEntityCreator) map.getCreatorClass(orig);

         // create clone
         clone = creator.getSendableInstance(false);

         origToCloneMap.put(orig, clone);

         // copy properties
         for ( String prop : creator.getProperties())
         {
            Object value = creator.getValue(orig, prop);

            if (value != null && value instanceof Collection)
            {
               // if our neighbor is currently cloned, we connect to that clone, only. 
               Object neighborOrig = null;
               Object neighborClone = null; 
               for (Object neighbor : (Collection)value)
               {
                  neighborClone = origToCloneMap.get(neighbor);
                  
                  if (neighborClone != null)
                  {
                     creator.setValue(orig, prop, neighborClone, SendableEntityCreator.REMOVE);
                     creator.setValue(clone, prop, neighborClone, "new");
                  }
                  else if (arrayContains(creator.getUpProperties(), prop))
                  {
                     // do not copy as clone gets parent in new graph only
                  }
                  else
                  {
                     creator.setValue(clone, prop, neighbor, "new");
                  }
               }
            }
            else
            {
               creator.setValue(clone, prop, value, "new");
            }
         }

      }
      
      return clone;
   }


   private boolean arrayContains(String[] upProperties, String prop)
   {
      for (String elem : upProperties)
      {
         if (elem.equals(prop))
         {
            return true;
         }
      }
      return false;
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


   public void aggregate(ObjectSet graph, Object root)
   {
      if (root == null || graph.contains(root))
      {
         return;
      }
      
      graph.add(root);
      
      AggregatedEntityCreator creator = (AggregatedEntityCreator) map.getCreatorClass(root);
      
      String[] downProperties = creator.getDownProperties();
      
      for (String prop : downProperties)
      {
         Object value = creator.getValue(root, prop);
         
         if (value != null && value instanceof Collection)
         {
            for (Object elem : (Collection) value)
            {
               aggregate(graph, elem);
            }
         }
         else
         {
            aggregate(graph, value);
         }
      }
      
   }


   public void clear()
   {
      origToCloneMap.clear();
   }
}
