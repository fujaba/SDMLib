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

public class ChoiceTemplateCreator extends EntityFactory
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
         ChoiceTemplate.PROPERTY_CHOICES,
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
      return new ChoiceTemplate();
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

      if (ChoiceTemplate.PROPERTY_PLACEHOLDERS.equalsIgnoreCase(attribute))
      {
         return ((ChoiceTemplate) target).getPlaceholders();
      }

      if (ChoiceTemplate.PROPERTY_CHOICES.equalsIgnoreCase(attribute))
      {
         return ((ChoiceTemplate) target).getChoices();
      }

      if (ChoiceTemplate.PROPERTY_CHOOSER.equalsIgnoreCase(attribute))
      {
         return ((ChoiceTemplate) target).getChooser();
      }

      if (ChoiceTemplate.PROPERTY_MATCHES.equalsIgnoreCase(attribute))
      {
         return ((ChoiceTemplate) target).getMatches();
      }

      if (ChoiceTemplate.PROPERTY_PARENTS.equalsIgnoreCase(attribute))
      {
         return ((ChoiceTemplate) target).getParents();
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

      if (ChoiceTemplate.PROPERTY_PLACEHOLDERS.equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate) target).withPlaceholders((PlaceHolderDescription) value);
         return true;
      }

      if ((ChoiceTemplate.PROPERTY_PLACEHOLDERS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate) target).withoutPlaceholders((PlaceHolderDescription) value);
         return true;
      }

      if (ChoiceTemplate.PROPERTY_CHOICES.equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate) target).withChoices((Template) value);
         return true;
      }

      if ((ChoiceTemplate.PROPERTY_CHOICES + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate) target).withoutChoices((Template) value);
         return true;
      }

      if (ChoiceTemplate.PROPERTY_CHOOSER.equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate) target).setChooser((ChoiceTemplate) value);
         return true;
      }

      if (ChoiceTemplate.PROPERTY_MATCHES.equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate) target).withMatches((Match) value);
         return true;
      }

      if ((ChoiceTemplate.PROPERTY_MATCHES + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate) target).withoutMatches((Match) value);
         return true;
      }

      if (ChoiceTemplate.PROPERTY_PARENTS.equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate) target).withParents((PlaceHolderDescription) value);
         return true;
      }

      if ((ChoiceTemplate.PROPERTY_PARENTS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate) target).withoutParents((PlaceHolderDescription) value);
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
      ((ChoiceTemplate) entity).removeYou();
   }
}
