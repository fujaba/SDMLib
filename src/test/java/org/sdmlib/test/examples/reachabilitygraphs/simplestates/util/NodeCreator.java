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
   
package org.sdmlib.test.examples.reachabilitygraphs.simplestates.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.Node;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.SimpleState;

import de.uniks.networkparser.IdMap;

public class NodeCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Node.PROPERTY_NUM,
      Node.PROPERTY_GRAPH,
      Node.PROPERTY_NEXT,
      Node.PROPERTY_PREV,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
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

      if (Node.PROPERTY_GRAPH.equalsIgnoreCase(attribute))
      {
         return ((Node) target).getGraph();
      }

      if (Node.PROPERTY_NEXT.equalsIgnoreCase(attribute))
      {
         return ((Node) target).getNext();
      }

      if (Node.PROPERTY_PREV.equalsIgnoreCase(attribute))
      {
         return ((Node) target).getPrev();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (IdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Node.PROPERTY_NUM.equalsIgnoreCase(attrName))
      {
         ((Node) target).withNum(Integer.parseInt(value.toString()));
         return true;
      }

      if (Node.PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         ((Node) target).setGraph((SimpleState) value);
         return true;
      }

      if (Node.PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         ((Node) target).withNext((Node) value);
         return true;
      }
      
      if ((Node.PROPERTY_NEXT + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Node) target).withoutNext((Node) value);
         return true;
      }

      if (Node.PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         ((Node) target).withPrev((Node) value);
         return true;
      }
      
      if ((Node.PROPERTY_PREV + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Node) target).withoutPrev((Node) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Node) entity).removeYou();
   }
}
