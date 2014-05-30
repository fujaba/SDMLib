package org.sdmlib.models.transformations.util;

import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.Template;
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
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Match();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Match.PROPERTY_STARTPOS.equalsIgnoreCase(attrName))
      {
         return ((Match)target).getStartPos();
      }

      if (Match.PROPERTY_ENDPOS.equalsIgnoreCase(attrName))
      {
         return ((Match)target).getEndPos();
      }

      if (Match.PROPERTY_FULLTEXT.equalsIgnoreCase(attrName))
      {
         return ((Match)target).getFullText();
      }

      if (Match.PROPERTY_MATCHTEXT.equalsIgnoreCase(attrName))
      {
         return ((Match)target).getMatchText();
      }

      if (Match.PROPERTY_MODELOBJECT.equalsIgnoreCase(attrName))
      {
         return ((Match)target).getModelObject();
      }

      if (Match.PROPERTY_TEMPLATE.equalsIgnoreCase(attrName))
      {
         return ((Match)target).getTemplate();
      }

      if (Match.PROPERTY_PLACEHOLDER.equalsIgnoreCase(attrName))
      {
         return ((Match)target).getPlaceholder();
      }

      if (Match.PROPERTY_SUBMATCHES.equalsIgnoreCase(attrName))
      {
         return ((Match)target).getSubMatches();
      }

      if (Match.PROPERTY_PARENTMATCH.equalsIgnoreCase(attrName))
      {
         return ((Match)target).getParentMatch();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Match.PROPERTY_STARTPOS.equalsIgnoreCase(attrName))
      {
         ((Match)target).setStartPos(Integer.parseInt(value.toString()));
         return true;
      }

      if (Match.PROPERTY_ENDPOS.equalsIgnoreCase(attrName))
      {
         ((Match)target).setEndPos(Integer.parseInt(value.toString()));
         return true;
      }

      if (Match.PROPERTY_FULLTEXT.equalsIgnoreCase(attrName))
      {
         ((Match)target).setFullText((String) value);
         return true;
      }

      if (Match.PROPERTY_MATCHTEXT.equalsIgnoreCase(attrName))
      {
         ((Match)target).setMatchText((String) value);
         return true;
      }

      if (Match.PROPERTY_MODELOBJECT.equalsIgnoreCase(attrName))
      {
         ((Match)target).setModelObject((Object) value);
         return true;
      }

      if (Match.PROPERTY_TEMPLATE.equalsIgnoreCase(attrName))
      {
         ((Match)target).setTemplate((Template) value);
         return true;
      }

      if (Match.PROPERTY_PLACEHOLDER.equalsIgnoreCase(attrName))
      {
         ((Match)target).setPlaceholder((PlaceHolderDescription) value);
         return true;
      }

      if (Match.PROPERTY_SUBMATCHES.equalsIgnoreCase(attrName))
      {
         ((Match)target).addToSubMatches((Match) value);
         return true;
      }
      
      if ((Match.PROPERTY_SUBMATCHES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Match)target).removeFromSubMatches((Match) value);
         return true;
      }

      if (Match.PROPERTY_PARENTMATCH.equalsIgnoreCase(attrName))
      {
         ((Match)target).setParentMatch((Match) value);
         return true;
      }
      return super.setValue(target, attrName, value, type);
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


