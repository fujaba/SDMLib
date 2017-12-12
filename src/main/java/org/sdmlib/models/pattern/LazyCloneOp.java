package org.sdmlib.models.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
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
import de.uniks.networkparser.list.SimpleList;

public class LazyCloneOp
{
   private IdMap map = null;
   
   private LinkedHashMap<Object, Object> origToCloneMap = new LinkedHashMap<Object, Object>();
   private LinkedHashMap<Object, Object> cloneToOrigMap = new LinkedHashMap<Object, Object>();
   
   public LinkedHashMap<Object, Object> getOrigToCloneMap()
   {
      return origToCloneMap;
   }
   
   public LinkedHashMap<Object, Object> getCloneToOrigMap()
   {
      return cloneToOrigMap;
   }
   
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
      
      SimpleKeyValueList graph = new SimpleKeyValueList();
      
      // try to compute clone graph from first entry of origToCloneMap 
      if ( ! origToCloneMap.isEmpty())
      {
         orig = origToCloneMap.keySet().iterator().next();
         clone = origToCloneMap.get(orig);
         aggregate(graph, clone, clone);
      }
      else
      {
         aggregate(graph, orig, orig);
      }
      
      
      while ( ! climbTodo.isEmpty())
      {
         orig = climbTodo.pollFirst();
         // climb up to root and add parents to todo list
         SendableEntityCreator simpleCreator = map.getCreatorClass(orig);
         
         if ( ! (simpleCreator instanceof AggregatedEntityCreator))
         {
            return cloneComponent(graph, orig);
         }
         
         AggregatedEntityCreator creator = (AggregatedEntityCreator) simpleCreator;
      
         if (creator.getUpProperties().length == 0 && creator.getDownProperties().length == 0)
         {
            // orig is not part of the aggregation tree. No sharing. Clone everything
            return cloneComponent(graph, orig);
         }
         
         Object parent = graph.get(orig);

         clone = origToCloneMap.get(parent);
         

         if (clone != null || orig == parent || cloneToOrigMap.get(parent) != null)
         {
            break;
         }
         climbTodo.add(parent);
         cloneTodo.add(parent);
         
         //         for (String upProp : creator.getUpProperties())
         //         {
         //            Object value = creator.getValue(orig, upProp);
         //         
         //            if (value != null && value instanceof Collection)
         //            {
         //               for (Object parent : (Collection) value)
         //               {
         //                  clone = origToCloneMap.get(parent);
         //                  
         //                  if (clone != null)
         //                  {
         //                     break;
         //                  }
         //                  
         //                  if (graph.contains(parent))
         //                  {
         //                     // this parent belongs to the clone graph and does not yet have a clone
         //                     climbTodo.add(parent);
         //                     cloneTodo.add(parent);
         //                  }
         //               }
         //            }
         //         }
      }
      
      
      while (! cloneTodo.isEmpty())
      {
         orig = cloneTodo.pollLast();

         AggregatedEntityCreator creator = (AggregatedEntityCreator) map.getCreatorClass(orig);

         // create clone
         clone = creator.getSendableInstance(false);
         
         graph.remove(orig);
         graph.put(clone, clone);

         origToCloneMap.put(orig, clone);
         cloneToOrigMap.put(clone, orig);

         // copy properties
         for ( String prop : creator.getProperties())
         {
            Object value = creator.getValue(orig, prop);

            if (value != null && value instanceof Collection)
            {
               // if our neighbor is currently cloned, we connect to that clone, only. 
               Object neighborOrig = null;
               Object neighborClone = null;
               ArrayList list = new ArrayList(((Collection)value).size());
               list.addAll((Collection)value);
               for (Object neighbor : (Collection)list)
               {
                  neighborClone = origToCloneMap.get(neighbor);
                  neighborOrig = cloneToOrigMap.get(neighbor);
                  
                  if (graph.get(neighbor) != null)
                  {
                     if (neighborOrig != null)
                     {
                        creator.setValue(orig, prop, neighbor, SendableEntityCreator.REMOVE);
                     }
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


   public Object cloneComponent(SimpleKeyValueList<Object, Object> graph, Object orig)
   {
      ObjectSet cloneGraph = new ObjectSet();
      
      // first clone objects, 
      for (Object obj : graph.keySet())
      {
         Object origObj = cloneToOrigMap.get(obj);
         if (origObj != null)
         {
            // obj is already a clone
            cloneGraph.add(obj);
            continue;
         }
         
         Object cloneObj = origToCloneMap.get(obj);
         if (cloneObj != null)
         {
            cloneGraph.add(cloneObj);
            continue;
         }
         
         // ok, let's clone
         SendableEntityCreator creator = map.getCreatorClass(obj);
         cloneObj = creator.getSendableInstance(false);
         cloneGraph.add(cloneObj);
         origToCloneMap.put(obj, cloneObj);
         cloneToOrigMap.put(cloneObj, obj);
      }
      
      // second clone properties
      for (Object clone : cloneGraph)
      {
         SendableEntityCreator creator = map.getCreatorClass(clone);
         
         Object origObj = cloneToOrigMap.get(clone);
         
         for (String prop : creator.getProperties())
         {
            Object value = creator.getValue(origObj, prop);
            
            if (value instanceof Collection)
            {
               for (Object valueObj : (Collection) value)
               {
                  Object cloneValue = origToCloneMap.get(valueObj);
                  creator.setValue(clone, prop, cloneValue, "new");
               }
            }
            else
            {
               Object cloneValue = origToCloneMap.get(value);
               if (cloneValue == null)
               {
                  creator.setValue(clone, prop, value, "new");
               }
               else
               {
                  creator.setValue(clone, prop, cloneValue, "new");
               }
            }
         }
      }
      
      return origToCloneMap.get(orig);
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


   public void aggregate(ObjectSet dynNodes, SimpleList<Object> dynEdges, ObjectSet staticNodes, Object root)
   {
      if (root == null)
      {
         return;
      }
      
      SendableEntityCreator plainCreator = map.getCreatorClass(root);
      
      Objects.requireNonNull(plainCreator);
      
      String[] downProperties = null;
      
      if (plainCreator instanceof AggregatedEntityCreator)
      {
         downProperties = ((AggregatedEntityCreator) plainCreator).getDownProperties();
      }
      
      for (String prop : plainCreator.getProperties())
      {
         Object value = plainCreator.getValue(root, prop);
         
         boolean isDownProp = isContained(downProperties, prop);
         
         if (value != null && value instanceof Collection)
         {
            for (Object elem : (Collection) value)
            {
               if (dynNodes.contains(root))
               {
                  dynEdges.add(root, prop, elem);
               }

               if (isDownProp)
               {
                  if (! dynNodes.contains(elem) && ! staticNodes.contains(elem))
                  {
                     dynNodes.add(elem);
                     aggregate(dynNodes, dynEdges, staticNodes, elem);
                  }
               }
               else
               {
                  if (! dynNodes.contains(elem) && ! staticNodes.contains(elem))
                  {
                     staticNodes.add(elem);
                     aggregate(dynNodes, dynEdges, staticNodes, elem);
                  }
               }
            }
         }
         else
         {
            if (isDownProp)
            {
               dynEdges.add(root, prop, value);
               if (! dynNodes.contains(value)  && ! staticNodes.contains(value))
               {
                  dynNodes.add(value);
                  aggregate(dynNodes, dynEdges, staticNodes, value);
               }
            }
            else
            {
               
               if ( value != null && ! value.getClass().getName().startsWith("java.lang.") && ! dynNodes.contains(value)  && ! staticNodes.contains(value))
               {
                  // model type
                  staticNodes.add(value);
                  aggregate(dynNodes, dynEdges, staticNodes, value);
               }
            }
         }
      }
   }
   
   private boolean isContained(String[] downProperties, String prop)
   {
      for (String p : downProperties)
      {
         if (p.equals(prop))
         {
            return true;
         }
      }
      
      return false;
   }

   public void aggregate(SimpleKeyValueList<Object, Object> graph, Object root, Object parent)
   {
      if (root == null || graph.get(root) != null)
      {
         return;
      }
      
      SendableEntityCreator plainCreator = map.getCreatorClass(root);
      
      Objects.requireNonNull(plainCreator);
      
      if ( ! (plainCreator instanceof AggregatedEntityCreator))
      {
         collectComponent(graph, root);
         
         return;
      }
      
      graph.put(root, parent);
      
      AggregatedEntityCreator creator = (AggregatedEntityCreator) map.getCreatorClass(root);
      
      String[] downProperties = creator.getDownProperties();
      
      for (String prop : downProperties)
      {
         Object value = creator.getValue(root, prop);
         
         if (value != null && value instanceof Collection)
         {
            for (Object elem : (Collection) value)
            {
               aggregate(graph, elem, root);
            }
         }
         else
         {
            aggregate(graph, value, root);
         }
      }
      
   }



   public void dynCollectComponent(ObjectSet dynNodes, ArrayList dynEdges, ObjectSet staticNodes, Object root)
   {
      if (root == null)
      {
         return;
      }

      // TODO collect edges
      // TODO deal with old staticNodes
      SendableEntityCreator creator = map.getCreatorClass(root);
      
      dynNodes.add(root);
      
      String[] properties = creator.getProperties();
      
      for (String prop : properties)
      {
         Object value = creator.getValue(root, prop);
         
         if (value != null && value instanceof Collection)
         {
            for (Object elem : (Collection) value)
            {
               dynCollectComponent(dynNodes, dynEdges, staticNodes, elem);
            }
         }
         else
         {
            SendableEntityCreator valueCreator = map.getCreatorClass(value);
            
            if (valueCreator != null)
            {
               dynCollectComponent(dynNodes, dynEdges, staticNodes, value);
            }
         }
      }
   }

   
   public void collectComponent(SimpleKeyValueList<Object, Object> graph, Object root)
   {
      if (root == null || graph.get(root) != null)
      {
         return;
      }
      
      SendableEntityCreator creator = map.getCreatorClass(root);
      
      graph.put(root, root);
      
      String[] properties = creator.getProperties();
      
      for (String prop : properties)
      {
         Object value = creator.getValue(root, prop);
         
         if (value != null && value instanceof Collection)
         {
            for (Object elem : (Collection) value)
            {
               collectComponent(graph, elem);
            }
         }
         else
         {
            SendableEntityCreator valueCreator = map.getCreatorClass(value);
            
            if (valueCreator != null)
            {
               collectComponent(graph, value);
            }
         }
      }
   }


   public LazyCloneOp clear()
   {
      origToCloneMap.clear();
      cloneToOrigMap.clear();
      
      return this;
   }
}
