package org.sdmlib.examples.m2m.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.m2m.model.Relation;
import org.sdmlib.examples.m2m.model.Graph;
import org.sdmlib.examples.m2m.model.Person;

public class RelationCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Relation.PROPERTY_KIND,
      Relation.PROPERTY_GRAPH,
      Relation.PROPERTY_SRC,
      Relation.PROPERTY_TGT,
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
      if (Relation.PROPERTY_KIND.equalsIgnoreCase(attrName))
      {
         return ((Relation) target).getKind();
      }

      if (Relation.PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         return ((Relation) target).getGraph();
      }

      if (Relation.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         return ((Relation) target).getSrc();
      }

      if (Relation.PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         return ((Relation) target).getTgt();
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

