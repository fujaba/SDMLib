package org.sdmlib.models.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.logging.Logger;

import org.sdmlib.models.modelsets.doubleList;
import org.sdmlib.test.examples.reachabilitygraphs.ReachbilityGraphSimpleExamples;

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
   
   public void reset()
   {
      origToCloneMap.clear();
      cloneToOrigMap.clear();
   }

   
   public LinkedHashMap<Object, Object> getOrigToCloneMap()
   {
      return origToCloneMap;
   }
   
   public LinkedHashMap<Object, Object> getCloneToOrigMap()
   {
      return cloneToOrigMap;
   }
   
   
   private ReachabilityGraph reachabilityGraph = null;
   
   public LazyCloneOp(ReachabilityGraph rg)
   {
      this.reachabilityGraph = rg;
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
      
      ObjectSet dynNodes = new ObjectSet();
     
      
      SimpleList<Object> dynEdges = new SimpleList<Object>();
      
      // try to compute clone graph from first entry of origToCloneMap 
      if ( ! origToCloneMap.isEmpty())
      {
         orig = origToCloneMap.keySet().iterator().next();
         clone = origToCloneMap.get(orig);
         dynNodes.add(clone);
         aggregate(dynNodes, dynEdges, reachabilityGraph.getStaticNodes(), clone);
      }
      else
      {
         dynNodes.add(orig);
         aggregate(dynNodes, dynEdges, reachabilityGraph.getStaticNodes(), orig);
      }
      
      
      while ( ! climbTodo.isEmpty())
      {
         orig = climbTodo.pollFirst();

         // for any dyn edge that points to orig clone its src
         for (int i = 0; i < dynEdges.size(); i += 3)
         {
            Object src = dynEdges.get(i);
            Object prop = dynEdges.get(i+1);
            Object tgt = dynEdges.get(i+2);
            
            if (tgt == orig)
            {
               Object srcOrig = cloneToOrigMap.get(src);
               Object srcClone = origToCloneMap.get(src);
               if (! cloneTodo.contains(src) && srcOrig == null && srcClone == null)
               {
                  climbTodo.add(src);
                  cloneTodo.add(src);
               }
            }
         }
      }
      
      for ( Object o : cloneTodo)
      {
         SendableEntityCreator creator =  map.getCreatorClass(o);

         // create clone
         clone = creator.getSendableInstance(false);
         
         origToCloneMap.put(o, clone);
         cloneToOrigMap.put(clone, o);
      }
      
      
      for ( Object o : cloneTodo)
      {
         clone = origToCloneMap.get(o);
         
         Objects.requireNonNull(clone);
         
         SendableEntityCreator creator =  map.getCreatorClass(o);

         // copy properties
         for ( String prop : creator.getProperties())
         {
            Object value = creator.getValue(o, prop);

            Object neighborOrig = null;
            Object neighborClone = null;
            
            if (value != null && value instanceof Collection)
            {
               // if our neighbor is currently cloned, we connect to that clone, only. 
               ArrayList list = new ArrayList(((Collection)value).size());
               list.addAll((Collection)value);
               for (Object neighbor : (Collection)list)
               {
                  neighborClone = origToCloneMap.get(neighbor);
                  neighborOrig = cloneToOrigMap.get(neighbor);
                  
                  if (neighborOrig != null)
                  {
                     Logger.getGlobal().warning("x is about to be cloned, but points already to a clone. That should not happen");
                  }
                  else if (neighborClone == null)
                  {
                     // our clone should point to the neigbor
                     creator.setValue(clone, prop, neighbor, "new");
                  }
                  else
                  {
                     // our clone should point to the neighborClone
                     creator.setValue(clone, prop, neighborClone, "new");
                  }
               }
            }
            else
            {
               neighborClone = origToCloneMap.get(value);
               
               if (neighborClone == null)
               {
                  // our clone should point to the value
                  creator.setValue(clone, prop, value, "new");
               }
               else
               {
                  // our clone should point to the neighborClone
                  creator.setValue(clone, prop, neighborClone, "new");
               }
            }
         }
         
         // redirect incoming unidirectional edges
         for (int i = 0; i < dynEdges.size(); i += 3)
         {
            Object src = dynEdges.get(i);
            String prop = (String) dynEdges.get(i+1);
            Object tgt = dynEdges.get(i+2);
            
            if (tgt == o)
            {
               // if source is a clone
               Object srcOrig = cloneToOrigMap.get(src);
               if (srcOrig != null)
               {
                  // src should point to clone
                  SendableEntityCreator srcCreator = map.getCreatorClass(src);
                  
                  // in case of to-many remove old edge, first
                  Object value = srcCreator.getValue(src, prop);
                  
                  if (value instanceof Collection)
                  {
                     srcCreator.setValue(src,  prop, o, SendableEntityCreator.REMOVE);
                  }
                  
                  srcCreator.setValue(src,  prop, clone, "new");
               }
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
      
      for (String prop : plainCreator.getProperties())
      {
         Object value = plainCreator.getValue(root, prop);
         
         if (value != null && value instanceof Collection)
         {
            for (Object elem : (Collection) value)
            {
               if ( ! staticNodes.contains(elem))
               {
                  dynEdges.add(root, prop, elem);

                  if (! dynNodes.contains(elem))
                  {
                     dynNodes.add(elem);
                     aggregate(dynNodes, dynEdges, staticNodes, elem);
                  }
               }
            }
         }
         else
         {
            if ( value != null && ! value.getClass().getName().startsWith("java.lang.") && ! staticNodes.contains(value))
            {
               // model type
               dynEdges.add(root, prop, value);
               
               if ( ! dynNodes.contains(value))
               {
                  dynNodes.add(value);
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
