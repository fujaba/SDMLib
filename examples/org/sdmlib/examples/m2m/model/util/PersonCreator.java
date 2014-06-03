package org.sdmlib.examples.m2m.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.m2m.model.Person;
import org.sdmlib.examples.m2m.model.Graph;
import org.sdmlib.examples.m2m.model.Relation;

public class PersonCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Person.PROPERTY_FIRSTNAME,
      Person.PROPERTY_GRAPH,
      Person.PROPERTY_OUTEDGES,
      Person.PROPERTY_INEDGES,
      Person.PROPERTY_KNOWS,
      Person.PROPERTY_TEXT,
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
      if (Person.PROPERTY_FIRSTNAME.equalsIgnoreCase(attrName))
      {
         return ((Person) target).getFirstName();
      }

      if (Person.PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         return ((Person) target).getGraph();
      }

      if (Person.PROPERTY_OUTEDGES.equalsIgnoreCase(attrName))
      {
         return ((Person) target).getOutEdges();
      }

      if (Person.PROPERTY_INEDGES.equalsIgnoreCase(attrName))
      {
         return ((Person) target).getInEdges();
      }

      if (Person.PROPERTY_KNOWS.equalsIgnoreCase(attrName))
      {
         return ((Person) target).getKnows();
      }

      if (Person.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         return ((Person) target).getText();
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

      if (Person.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         ((Person) target).setText((String) value);
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



