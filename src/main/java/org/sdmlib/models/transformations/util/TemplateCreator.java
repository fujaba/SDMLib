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

import org.sdmlib.models.transformations.ChoiceTemplate;
import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.Template;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

public class TemplateCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
         Template.PROPERTY_TEMPLATETEXT,
         Template.PROPERTY_EXPANDEDTEXT,
         Template.PROPERTY_MODELOBJECT,
         Template.PROPERTY_MODELCLASSNAME,
         Template.PROPERTY_LISTSTART,
         Template.PROPERTY_LISTSEPARATOR,
         Template.PROPERTY_LISTEND,
         Template.PROPERTY_REFERENCELOOKUP,
         Template.PROPERTY_NAME,
         Template.PROPERTY_PLACEHOLDERS,
         Template.PROPERTY_CHOOSER,
         Template.PROPERTY_MATCHES,
         Template.PROPERTY_PARENTS,
   };

   @Override
   public String[] getProperties()
   {
      return properties;
   }

   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Template();
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

      if (Template.PROPERTY_TEMPLATETEXT.equalsIgnoreCase(attribute))
      {
         return ((Template) target).getTemplateText();
      }

      if (Template.PROPERTY_EXPANDEDTEXT.equalsIgnoreCase(attribute))
      {
         return ((Template) target).getExpandedText();
      }

      if (Template.PROPERTY_MODELOBJECT.equalsIgnoreCase(attribute))
      {
         return ((Template) target).getModelObject();
      }

      if (Template.PROPERTY_MODELCLASSNAME.equalsIgnoreCase(attribute))
      {
         return ((Template) target).getModelClassName();
      }

      if (Template.PROPERTY_LISTSTART.equalsIgnoreCase(attribute))
      {
         return ((Template) target).getListStart();
      }

      if (Template.PROPERTY_LISTSEPARATOR.equalsIgnoreCase(attribute))
      {
         return ((Template) target).getListSeparator();
      }

      if (Template.PROPERTY_LISTEND.equalsIgnoreCase(attribute))
      {
         return ((Template) target).getListEnd();
      }

      if (Template.PROPERTY_REFERENCELOOKUP.equalsIgnoreCase(attribute))
      {
         return ((Template) target).getReferenceLookup();
      }

      if (Template.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Template) target).getName();
      }

      if (Template.PROPERTY_PLACEHOLDERS.equalsIgnoreCase(attribute))
      {
         return ((Template) target).getPlaceholders();
      }

      if (Template.PROPERTY_CHOOSER.equalsIgnoreCase(attribute))
      {
         return ((Template) target).getChooser();
      }

      if (Template.PROPERTY_MATCHES.equalsIgnoreCase(attribute))
      {
         return ((Template) target).getMatches();
      }

      if (Template.PROPERTY_PARENTS.equalsIgnoreCase(attribute))
      {
         return ((Template) target).getParents();
      }

      return null;
   }

   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Template.PROPERTY_TEMPLATETEXT.equalsIgnoreCase(attrName))
      {
         ((Template) target).withTemplateText((String) value);
         return true;
      }

      if (Template.PROPERTY_EXPANDEDTEXT.equalsIgnoreCase(attrName))
      {
         ((Template) target).withExpandedText((String) value);
         return true;
      }

      if (Template.PROPERTY_MODELOBJECT.equalsIgnoreCase(attrName))
      {
         ((Template) target).withModelObject((Object) value);
         return true;
      }

      if (Template.PROPERTY_MODELCLASSNAME.equalsIgnoreCase(attrName))
      {
         ((Template) target).withModelClassName((String) value);
         return true;
      }

      if (Template.PROPERTY_LISTSTART.equalsIgnoreCase(attrName))
      {
         ((Template) target).withListStart((String) value);
         return true;
      }

      if (Template.PROPERTY_LISTSEPARATOR.equalsIgnoreCase(attrName))
      {
         ((Template) target).withListSeparator((String) value);
         return true;
      }

      if (Template.PROPERTY_LISTEND.equalsIgnoreCase(attrName))
      {
         ((Template) target).withListEnd((String) value);
         return true;
      }

      if (Template.PROPERTY_REFERENCELOOKUP.equalsIgnoreCase(attrName))
      {
         ((Template) target).withReferenceLookup((Boolean) value);
         return true;
      }

      if (Template.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Template) target).withName((String) value);
         return true;
      }

      if (Template.PROPERTY_PLACEHOLDERS.equalsIgnoreCase(attrName))
      {
         ((Template) target).withPlaceholders((PlaceHolderDescription) value);
         return true;
      }

      if ((Template.PROPERTY_PLACEHOLDERS + REMOVE).equalsIgnoreCase(attrName))
      {
         ((Template) target).withoutPlaceholders((PlaceHolderDescription) value);
         return true;
      }

      if (Template.PROPERTY_CHOOSER.equalsIgnoreCase(attrName))
      {
         ((Template) target).setChooser((ChoiceTemplate) value);
         return true;
      }

      if (Template.PROPERTY_MATCHES.equalsIgnoreCase(attrName))
      {
         ((Template) target).withMatches((Match) value);
         return true;
      }

      if ((Template.PROPERTY_MATCHES + REMOVE).equalsIgnoreCase(attrName))
      {
         ((Template) target).withoutMatches((Match) value);
         return true;
      }

      if (Template.PROPERTY_PARENTS.equalsIgnoreCase(attrName))
      {
         ((Template) target).withParents((PlaceHolderDescription) value);
         return true;
      }

      if ((Template.PROPERTY_PARENTS + REMOVE).equalsIgnoreCase(attrName))
      {
         ((Template) target).withoutParents((PlaceHolderDescription) value);
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
      ((Template) entity).removeYou();
   }
}
