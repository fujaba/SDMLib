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

public class MatchCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Match.PROPERTY_STARTPOS,
      Match.PROPERTY_ENDPOS,
      Match.PROPERTY_FULLTEXT,
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
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (Match.PROPERTY_STARTPOS.equalsIgnoreCase(attribute))
      {
         return ((Match) target).getStartPos();
      }

      if (Match.PROPERTY_ENDPOS.equalsIgnoreCase(attribute))
      {
         return ((Match) target).getEndPos();
      }

      if (Match.PROPERTY_FULLTEXT.equalsIgnoreCase(attribute))
      {
         return ((Match) target).getFullText();
      }

      if (Match.PROPERTY_MATCHTEXT.equalsIgnoreCase(attribute))
      {
         return ((Match) target).getMatchText();
      }

      if (Match.PROPERTY_MODELOBJECT.equalsIgnoreCase(attribute))
      {
         return ((Match) target).getModelObject();
      }

      if (Match.PROPERTY_TEMPLATE.equalsIgnoreCase(attribute))
      {
         return ((Match) target).getTemplate();
      }

      if (Match.PROPERTY_PLACEHOLDER.equalsIgnoreCase(attribute))
      {
         return ((Match) target).getPlaceholder();
      }

      if (Match.PROPERTY_SUBMATCHES.equalsIgnoreCase(attribute))
      {
         return ((Match) target).getSubMatches();
      }

      if (Match.PROPERTY_PARENTMATCH.equalsIgnoreCase(attribute))
      {
         return ((Match) target).getParentMatch();
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

      if (Match.PROPERTY_STARTPOS.equalsIgnoreCase(attrName))
      {
         ((Match) target).withStartPos(Integer.parseInt(value.toString()));
         return true;
      }

      if (Match.PROPERTY_ENDPOS.equalsIgnoreCase(attrName))
      {
         ((Match) target).withEndPos(Integer.parseInt(value.toString()));
         return true;
      }

      if (Match.PROPERTY_FULLTEXT.equalsIgnoreCase(attrName))
      {
         ((Match) target).withFullText((String) value);
         return true;
      }

      if (Match.PROPERTY_MATCHTEXT.equalsIgnoreCase(attrName))
      {
         ((Match) target).withMatchText((String) value);
         return true;
      }

      if (Match.PROPERTY_MODELOBJECT.equalsIgnoreCase(attrName))
      {
         ((Match) target).withModelObject((Object) value);
         return true;
      }

      if (Match.PROPERTY_TEMPLATE.equalsIgnoreCase(attrName))
      {
         ((Match) target).setTemplate((Template) value);
         return true;
      }

      if (Match.PROPERTY_PLACEHOLDER.equalsIgnoreCase(attrName))
      {
         ((Match) target).setPlaceholder((PlaceHolderDescription) value);
         return true;
      }

      if (Match.PROPERTY_SUBMATCHES.equalsIgnoreCase(attrName))
      {
         ((Match) target).withSubMatches((Match) value);
         return true;
      }
      
      if ((Match.PROPERTY_SUBMATCHES + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Match) target).withoutSubMatches((Match) value);
         return true;
      }

      if (Match.PROPERTY_PARENTMATCH.equalsIgnoreCase(attrName))
      {
         ((Match) target).setParentMatch((Match) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.models.transformations.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Match) entity).removeYou();
   }
}
