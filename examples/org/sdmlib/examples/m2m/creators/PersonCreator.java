package org.sdmlib.examples.m2m.creators;

import org.sdmlib.examples.m2m.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.m2m.Person;
import org.sdmlib.examples.m2m.GraphComponent;

public class PersonCreator extends EntityFactory
{
   private final String[] properties = new String[]
   { Person.PROPERTY_FIRSTNAME, Person.PROPERTY_GRAPH,
         Person.PROPERTY_OUTEDGES, Person.PROPERTY_INEDGES,
         GraphComponent.PROPERTY_TEXT, GraphComponent.PROPERTY_PARENT,
         Person.PROPERTY_KNOWS, };

   public String[] getProperties()
   {
      return properties;
   }

   public Object getSendableInstance(boolean reference)
   {
      return new Person();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((Person) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }
      return ((Person) target).set(attrName, value);
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   // ==========================================================================

   @Override
   public void removeObject(Object entity)
   {
      ((Person) entity).removeYou();
   }
}
