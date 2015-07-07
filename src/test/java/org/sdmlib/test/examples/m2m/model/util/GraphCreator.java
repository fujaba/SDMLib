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

public class GraphCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Graph.PROPERTY_GCS,
      Graph.PROPERTY_PERSONS,
      Graph.PROPERTY_RELATIONS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Graph();
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

      if (Graph.PROPERTY_GCS.equalsIgnoreCase(attribute))
      {
         return ((Graph) target).getGcs();
      }

      if (Graph.PROPERTY_PERSONS.equalsIgnoreCase(attribute))
      {
         return ((Graph) target).getPersons();
      }

      if (Graph.PROPERTY_RELATIONS.equalsIgnoreCase(attribute))
      {
         return ((Graph) target).getRelations();
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

      if (Graph.PROPERTY_GCS.equalsIgnoreCase(attrName))
      {
         ((Graph) target).addToGcs((GraphComponent) value);
         return true;
      }
      
      if ((Graph.PROPERTY_GCS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Graph) target).removeFromGcs((GraphComponent) value);
         return true;
      }

      if (Graph.PROPERTY_PERSONS.equalsIgnoreCase(attrName))
      {
         ((Graph) target).addToPersons((Person) value);
         return true;
      }
      
      if ((Graph.PROPERTY_PERSONS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Graph) target).removeFromPersons((Person) value);
         return true;
      }

      if (Graph.PROPERTY_RELATIONS.equalsIgnoreCase(attrName))
      {
         ((Graph) target).addToRelations((Relation) value);
         return true;
      }
      
      if ((Graph.PROPERTY_RELATIONS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Graph) target).removeFromRelations((Relation) value);
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
      ((Graph) entity).removeYou();
   }
}
