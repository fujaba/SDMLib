package org.sdmlib.models.transformations.util;

import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.Template;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class PlaceHolderDescriptionCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      PlaceHolderDescription.PROPERTY_TEXTFRAGMENT,
      PlaceHolderDescription.PROPERTY_VALUE,
      PlaceHolderDescription.PROPERTY_ATTRNAME,
      PlaceHolderDescription.PROPERTY_ISKEYATTRIBUTE,
      PlaceHolderDescription.PROPERTY_OWNERS,
      PlaceHolderDescription.PROPERTY_MATCHES,
      PlaceHolderDescription.PROPERTY_SUBTEMPLATE,
      PlaceHolderDescription.PROPERTY_PREFIX,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new PlaceHolderDescription();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (PlaceHolderDescription.PROPERTY_TEXTFRAGMENT.equalsIgnoreCase(attrName))
      {
         return ((PlaceHolderDescription)target).getTextFragment();
      }

      if (PlaceHolderDescription.PROPERTY_VALUE.equalsIgnoreCase(attrName))
      {
         return ((PlaceHolderDescription)target).getValue();
      }

      if (PlaceHolderDescription.PROPERTY_ATTRNAME.equalsIgnoreCase(attrName))
      {
         return ((PlaceHolderDescription)target).getAttrName();
      }

      if (PlaceHolderDescription.PROPERTY_ISKEYATTRIBUTE.equalsIgnoreCase(attrName))
      {
         return ((PlaceHolderDescription)target).getIsKeyAttribute();
      }

      if (PlaceHolderDescription.PROPERTY_OWNERS.equalsIgnoreCase(attrName))
      {
         return ((PlaceHolderDescription)target).getOwners();
      }

      if (PlaceHolderDescription.PROPERTY_SUBTEMPLATE.equalsIgnoreCase(attrName))
      {
         return ((PlaceHolderDescription)target).getSubTemplate();
      }

      if (PlaceHolderDescription.PROPERTY_MATCHES.equalsIgnoreCase(attrName))
      {
         return ((PlaceHolderDescription)target).getMatches();
      }

      if (PlaceHolderDescription.PROPERTY_PREFIX.equalsIgnoreCase(attrName))
      {
         return ((PlaceHolderDescription)target).getPrefix();
      }
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (PlaceHolderDescription.PROPERTY_TEXTFRAGMENT.equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription)target).setTextFragment((String) value);
         return true;
      }

      if (PlaceHolderDescription.PROPERTY_VALUE.equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription)target).setValue((String) value);
         return true;
      }

      if (PlaceHolderDescription.PROPERTY_ATTRNAME.equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription)target).setAttrName((String) value);
         return true;
      }

     if (PlaceHolderDescription.PROPERTY_ISKEYATTRIBUTE.equalsIgnoreCase(attrName))
      {
        ((PlaceHolderDescription)target).setIsKeyAttribute((Boolean) value);
         return true;
      }

      if (PlaceHolderDescription.PROPERTY_OWNERS.equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription)target).addToOwners((Template) value);
         return true;
      }
      
      if ((PlaceHolderDescription.PROPERTY_OWNERS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription)target).removeFromOwners((Template) value);
         return true;
      }

      if (PlaceHolderDescription.PROPERTY_SUBTEMPLATE.equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription)target).setSubTemplate((Template) value);
         return true;
      }

      if (PlaceHolderDescription.PROPERTY_MATCHES.equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription)target).addToMatches((Match) value);
         return true;
      }
      
      if ((PlaceHolderDescription.PROPERTY_MATCHES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription)target).removeFromMatches((Match) value);
         return true;
      }

      if (PlaceHolderDescription.PROPERTY_PREFIX.equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription)target).setPrefix((String) value);
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
      ((PlaceHolderDescription) entity).removeYou();
   }
}


