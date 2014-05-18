package org.sdmlib.models.transformations.util;

import org.sdmlib.models.transformations.Match;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class MatchCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Match.PROPERTY_STARTPOS,
      Match.PROPERTY_ENDPOS,
      // Match.PROPERTY_FULLTEXT,
      Match.PROPERTY_MATCHTEXT,
      Match.PROPERTY_MODELOBJECT,
      Match.PROPERTY_TEMPLATE,
      Match.PROPERTY_PLACEHOLDER,
      Match.PROPERTY_SUBMATCHES,
      Match.PROPERTY_PARENTMATCH,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Match();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Match) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }
      return ((Match) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Match) entity).removeYou();
   }
}


