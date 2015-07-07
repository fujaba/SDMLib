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
   
package org.sdmlib.test.examples.m2m.model.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.test.examples.m2m.model.Graph;
import org.sdmlib.test.examples.m2m.model.GraphComponent;
import org.sdmlib.test.examples.m2m.model.Person;
import org.sdmlib.test.examples.m2m.model.Relation;

import de.uniks.networkparser.json.JsonIdMap;

public class RelationCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Relation.PROPERTY_KIND,
      Relation.PROPERTY_GRAPH,
      Relation.PROPERTY_SRC,
      Relation.PROPERTY_TGT,
      GraphComponent.PROPERTY_TEXT,
      GraphComponent.PROPERTY_PARENT,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Relation();
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

      if (Relation.PROPERTY_KIND.equalsIgnoreCase(attribute))
      {
         return ((Relation) target).getKind();
      }

      if (Relation.PROPERTY_GRAPH.equalsIgnoreCase(attribute))
      {
         return ((Relation) target).getGraph();
      }

      if (Relation.PROPERTY_SRC.equalsIgnoreCase(attribute))
      {
         return ((Relation) target).getSrc();
      }

      if (Relation.PROPERTY_TGT.equalsIgnoreCase(attribute))
      {
         return ((Relation) target).getTgt();
      }

      if (GraphComponent.PROPERTY_TEXT.equalsIgnoreCase(attribute))
      {
         return ((GraphComponent) target).getText();
      }

      if (Relation.PROPERTY_PARENT.equalsIgnoreCase(attribute))
      {
         return ((Relation) target).getParent();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Relation.PROPERTY_KIND.equalsIgnoreCase(attrName))
      {
         ((Relation) target).setKind((String) value);
         return true;
      }

      if (Relation.PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         ((Relation) target).setGraph((Graph) value);
         return true;
      }

      if (Relation.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         ((Relation) target).setSrc((Person) value);
         return true;
      }

      if (Relation.PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         ((Relation) target).setTgt((Person) value);
         return true;
      }

      if (GraphComponent.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         ((GraphComponent) target).setText((String) value);
         return true;
      }

      if (Relation.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         ((Relation) target).setParent((Graph) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Relation) entity).removeYou();
   }
}
