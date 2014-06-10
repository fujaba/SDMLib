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
   
package org.sdmlib.examples.m2m.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.m2m.model.Person;
import org.sdmlib.examples.m2m.model.Graph;
import org.sdmlib.examples.m2m.model.Relation;
import org.sdmlib.examples.m2m.model.GraphComponent;

public class PersonCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Person.PROPERTY_FIRSTNAME,
      Person.PROPERTY_TEXT,
      Person.PROPERTY_GRAPH,
      Person.PROPERTY_OUTEDGES,
      Person.PROPERTY_INEDGES,
      Person.PROPERTY_KNOWS,
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
      return new Person();
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

      if (Person.PROPERTY_FIRSTNAME.equalsIgnoreCase(attribute))
      {
         return ((Person) target).getFirstName();
      }

      if (Person.PROPERTY_TEXT.equalsIgnoreCase(attribute))
      {
         return ((Person) target).getText();
      }

      if (Person.PROPERTY_GRAPH.equalsIgnoreCase(attribute))
      {
         return ((Person) target).getGraph();
      }

      if (Person.PROPERTY_OUTEDGES.equalsIgnoreCase(attribute))
      {
         return ((Person) target).getOutEdges();
      }

      if (Person.PROPERTY_INEDGES.equalsIgnoreCase(attribute))
      {
         return ((Person) target).getInEdges();
      }

      if (Person.PROPERTY_KNOWS.equalsIgnoreCase(attribute))
      {
         return ((Person) target).getKnows();
      }

      if (Person.PROPERTY_PARENT.equalsIgnoreCase(attribute))
      {
         return ((Person) target).getParent();
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

      if (Person.PROPERTY_FIRSTNAME.equalsIgnoreCase(attrName))
      {
         ((Person) target).setFirstName((String) value);
         return true;
      }

      if (Person.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         ((Person) target).setText((String) value);
         return true;
      }

      if (Person.PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         ((Person) target).setGraph((Graph) value);
         return true;
      }

      if (Person.PROPERTY_OUTEDGES.equalsIgnoreCase(attrName))
      {
         ((Person) target).addToOutEdges((Relation) value);
         return true;
      }
      
      if ((Person.PROPERTY_OUTEDGES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Person) target).removeFromOutEdges((Relation) value);
         return true;
      }

      if (Person.PROPERTY_INEDGES.equalsIgnoreCase(attrName))
      {
         ((Person) target).addToInEdges((Relation) value);
         return true;
      }
      
      if ((Person.PROPERTY_INEDGES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Person) target).removeFromInEdges((Relation) value);
         return true;
      }

      if (Person.PROPERTY_KNOWS.equalsIgnoreCase(attrName))
      {
         ((Person) target).addToKnows((Person) value);
         return true;
      }
      
      if ((Person.PROPERTY_KNOWS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Person) target).removeFromKnows((Person) value);
         return true;
      }

      if (Person.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         ((Person) target).setParent((Graph) value);
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
      ((Person) entity).removeYou();
   }
}
