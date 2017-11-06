/*
   Copyright (c) 2017 zuendorf
   
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
   
package org.sdmlib.test.examples.reachabilitygraphs.simplestates.util;

import de.uniks.networkparser.interfaces.AggregatedEntityCreator;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.Node;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.SimpleState;

public class NodeCreator implements AggregatedEntityCreator
{
   public static final NodeCreator it = new NodeCreator();
   
   private final String[] properties = new String[]
   {
      Node.PROPERTY_NUM,
      Node.PROPERTY_PREV,
      Node.PROPERTY_NEXT,
      Node.PROPERTY_GRAPH,
   };
   
   private final String[] upProperties = new String[]
   {
      Node.PROPERTY_GRAPH,
   };
   
   private final String[] downProperties = new String[]
   {
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public String[] getUpProperties()
   {
      return upProperties;
   }
   
   @Override
   public String[] getDownProperties()
   {
      return downProperties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Node();
   }
   
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (Node.PROPERTY_NUM.equalsIgnoreCase(attribute))
      {
         return ((Node) target).getNum();
      }

      if (Node.PROPERTY_PREV.equalsIgnoreCase(attribute))
      {
         return ((Node) target).getPrev();
      }

      if (Node.PROPERTY_NEXT.equalsIgnoreCase(attribute))
      {
         return ((Node) target).getNext();
      }

      if (Node.PROPERTY_GRAPH.equalsIgnoreCase(attribute))
      {
         return ((Node) target).getGraph();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Node.PROPERTY_NUM.equalsIgnoreCase(attrName))
      {
         ((Node) target).setNum(Integer.parseInt(value.toString()));
         return true;
      }

      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((Node)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Node.PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         ((Node) target).withPrev((Node) value);
         return true;
      }
      
      if ((Node.PROPERTY_PREV + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Node) target).withoutPrev((Node) value);
         return true;
      }

      if (Node.PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         ((Node) target).withNext((Node) value);
         return true;
      }
      
      if ((Node.PROPERTY_NEXT + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Node) target).withoutNext((Node) value);
         return true;
      }

      if (Node.PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         ((Node) target).withGraph((SimpleState) value);
         return true;
      }
      
      if ((Node.PROPERTY_GRAPH + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Node) target).withoutGraph((SimpleState) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Node) entity).removeYou();
   }
}
