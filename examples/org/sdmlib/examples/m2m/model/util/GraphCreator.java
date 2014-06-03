package org.sdmlib.examples.m2m.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.m2m.model.Graph;
import org.sdmlib.examples.m2m.model.GraphComponent;
import org.sdmlib.examples.m2m.model.Person;
import org.sdmlib.examples.m2m.model.Relation;

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
      if (Graph.PROPERTY_GCS.equalsIgnoreCase(attrName))
      {
         return ((Graph) target).getGcs();
      }

      if (Graph.PROPERTY_PERSONS.equalsIgnoreCase(attrName))
      {
         return ((Graph) target).getPersons();
      }

      if (Graph.PROPERTY_RELATIONS.equalsIgnoreCase(attrName))
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

