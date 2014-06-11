package org.sdmlib.models.transformations.util;

import org.sdmlib.models.transformations.ChoiceTemplate;
import org.sdmlib.models.transformations.Template;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.Match;

public class ChoiceTemplateCreator extends TemplateCreator
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
      Template.PROPERTY_PLACEHOLDERS,
      ChoiceTemplate.PROPERTY_CHOICES,
      Template.PROPERTY_CHOOSER,
      Template.PROPERTY_MATCHES,
      Template.PROPERTY_PARENTS,
      Template.PROPERTY_REFERENCELOOKUP,
      Template.PROPERTY_NAME,
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
      if (ChoiceTemplate.PROPERTY_CHOICES.equalsIgnoreCase(attrName))
      {
         return ((ChoiceTemplate)target).getChoices();
      }

      if (ChoiceTemplate.PROPERTY_PLACEHOLDERS.equalsIgnoreCase(attribute))
      {
         return ((ChoiceTemplate) target).getPlaceholders();
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
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (ChoiceTemplate.PROPERTY_CHOICES.equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate)target).addToChoices((Template) value);
         return true;
      }
      
      if ((ChoiceTemplate.PROPERTY_CHOICES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate)target).removeFromChoices((Template) value);
         return true;
      }

      if (ChoiceTemplate.PROPERTY_PLACEHOLDERS.equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate) target).addToPlaceholders((PlaceHolderDescription) value);
         return true;
      }
      
      if ((ChoiceTemplate.PROPERTY_PLACEHOLDERS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate) target).removeFromPlaceholders((PlaceHolderDescription) value);
         return true;
      }

      if (ChoiceTemplate.PROPERTY_CHOOSER.equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate) target).setChooser((ChoiceTemplate) value);
         return true;
      }

      if (ChoiceTemplate.PROPERTY_MATCHES.equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate) target).addToMatches((Match) value);
         return true;
      }
      
      if ((ChoiceTemplate.PROPERTY_MATCHES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate) target).removeFromMatches((Match) value);
         return true;
      }

      if (ChoiceTemplate.PROPERTY_PARENTS.equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate) target).addToParents((PlaceHolderDescription) value);
         return true;
      }
      
      if ((ChoiceTemplate.PROPERTY_PARENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ChoiceTemplate) target).removeFromParents((PlaceHolderDescription) value);
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
      ((ChoiceTemplate) entity).removeYou();
   }
}



