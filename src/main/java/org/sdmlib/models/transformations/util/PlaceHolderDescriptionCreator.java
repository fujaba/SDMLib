/*
   Copyright (c) 2015 christoph 
   
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

package org.sdmlib.models.transformations.util;

import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.Template;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

public class PlaceHolderDescriptionCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
         PlaceHolderDescription.PROPERTY_TEXTFRAGMENT,
         PlaceHolderDescription.PROPERTY_VALUE,
         PlaceHolderDescription.PROPERTY_ATTRNAME,
         PlaceHolderDescription.PROPERTY_ISKEYATTRIBUTE,
         PlaceHolderDescription.PROPERTY_PREFIX,
         PlaceHolderDescription.PROPERTY_OWNERS,
         PlaceHolderDescription.PROPERTY_MATCHES,
         PlaceHolderDescription.PROPERTY_SUBTEMPLATE,
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
      int pos = attrName.indexOf('.');
      String attribute = attrName;

      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PlaceHolderDescription.PROPERTY_TEXTFRAGMENT.equalsIgnoreCase(attribute))
      {
         return ((PlaceHolderDescription) target).getTextFragment();
      }

      if (PlaceHolderDescription.PROPERTY_VALUE.equalsIgnoreCase(attribute))
      {
         return ((PlaceHolderDescription) target).getValue();
      }

      if (PlaceHolderDescription.PROPERTY_ATTRNAME.equalsIgnoreCase(attribute))
      {
         return ((PlaceHolderDescription) target).getAttrName();
      }

      if (PlaceHolderDescription.PROPERTY_ISKEYATTRIBUTE.equalsIgnoreCase(attribute))
      {
         return ((PlaceHolderDescription) target).getIsKeyAttribute();
      }

      if (PlaceHolderDescription.PROPERTY_PREFIX.equalsIgnoreCase(attribute))
      {
         return ((PlaceHolderDescription) target).getPrefix();
      }

      if (PlaceHolderDescription.PROPERTY_OWNERS.equalsIgnoreCase(attribute))
      {
         return ((PlaceHolderDescription) target).getOwners();
      }

      if (PlaceHolderDescription.PROPERTY_MATCHES.equalsIgnoreCase(attribute))
      {
         return ((PlaceHolderDescription) target).getMatches();
      }

      if (PlaceHolderDescription.PROPERTY_SUBTEMPLATE.equalsIgnoreCase(attribute))
      {
         return ((PlaceHolderDescription) target).getSubTemplate();
      }

      return null;
   }

   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (IdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (PlaceHolderDescription.PROPERTY_TEXTFRAGMENT.equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription) target).withTextFragment((String) value);
         return true;
      }

      if (PlaceHolderDescription.PROPERTY_VALUE.equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription) target).withValue((String) value);
         return true;
      }

      if (PlaceHolderDescription.PROPERTY_ATTRNAME.equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription) target).withAttrName((String) value);
         return true;
      }

      if (PlaceHolderDescription.PROPERTY_ISKEYATTRIBUTE.equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription) target).withIsKeyAttribute((Boolean) value);
         return true;
      }

      if (PlaceHolderDescription.PROPERTY_PREFIX.equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription) target).withPrefix((String) value);
         return true;
      }

      if (PlaceHolderDescription.PROPERTY_OWNERS.equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription) target).withOwners((Template) value);
         return true;
      }

      if ((PlaceHolderDescription.PROPERTY_OWNERS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription) target).withoutOwners((Template) value);
         return true;
      }

      if (PlaceHolderDescription.PROPERTY_MATCHES.equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription) target).withMatches((Match) value);
         return true;
      }

      if ((PlaceHolderDescription.PROPERTY_MATCHES + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription) target).withoutMatches((Match) value);
         return true;
      }

      if (PlaceHolderDescription.PROPERTY_SUBTEMPLATE.equalsIgnoreCase(attrName))
      {
         ((PlaceHolderDescription) target).setSubTemplate((Template) value);
         return true;
      }

      return false;
   }

   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.models.transformations.util.CreatorCreator.createIdMap(sessionID);
   }

   // ==========================================================================

   @Override
   public void removeObject(Object entity)
   {
      ((PlaceHolderDescription) entity).removeYou();
   }
}
