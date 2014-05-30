package org.sdmlib.models.transformations.util;

import org.sdmlib.models.transformations.ChoiceTemplate;
import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.Template;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

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
      Template.PROPERTY_PLACEHOLDERS,
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
      return new Template();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Template.PROPERTY_TEMPLATETEXT.equalsIgnoreCase(attrName))
      {
         return ((Template)target).getTemplateText();
      }

      if (Template.PROPERTY_MODELOBJECT.equalsIgnoreCase(attrName))
      {
         return ((Template)target).getModelObject();
      }

      if (Template.PROPERTY_MODELCLASSNAME.equalsIgnoreCase(attrName))
      {
         return ((Template)target).getModelClassName();
      }

      if (Template.PROPERTY_EXPANDEDTEXT.equalsIgnoreCase(attrName))
      {
         return ((Template)target).getExpandedText();
      }

      if (Template.PROPERTY_LISTSTART.equalsIgnoreCase(attrName))
      {
         return ((Template)target).getListStart();
      }

      if (Template.PROPERTY_LISTSEPARATOR.equalsIgnoreCase(attrName))
      {
         return ((Template)target).getListSeparator();
      }

      if (Template.PROPERTY_LISTEND.equalsIgnoreCase(attrName))
      {
         return ((Template)target).getListEnd();
      }

      if (Template.PROPERTY_PLACEHOLDERS.equalsIgnoreCase(attrName))
      {
         return ((Template)target).getPlaceholders();
      }

      if (Template.PROPERTY_CHOOSER.equalsIgnoreCase(attrName))
      {
         return ((Template)target).getChooser();
      }

      if (Template.PROPERTY_MATCHES.equalsIgnoreCase(attrName))
      {
         return ((Template)target).getMatches();
      }

      if (Template.PROPERTY_PARENTS.equalsIgnoreCase(attrName))
      {
         return ((Template)target).getParents();
      }
      if (Template.PROPERTY_REFERENCELOOKUP.equalsIgnoreCase(attrName))
      {
         return ((Template)target).getReferenceLookup();
      }

      if (Template.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return ((Template)target).getName();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Template.PROPERTY_TEMPLATETEXT.equalsIgnoreCase(attrName))
      {
         ((Template)target).setTemplateText((String) value);
         return true;
      }

      if (Template.PROPERTY_MODELOBJECT.equalsIgnoreCase(attrName))
      {
         ((Template)target).setModelObject((Object) value);
         return true;
      }

      if (Template.PROPERTY_MODELCLASSNAME.equalsIgnoreCase(attrName))
      {
         ((Template)target).setModelClassName((String) value);
         return true;
      }

      if (Template.PROPERTY_EXPANDEDTEXT.equalsIgnoreCase(attrName))
      {
         ((Template)target).setExpandedText((String) value);
         return true;
      }

      if (Template.PROPERTY_LISTSTART.equalsIgnoreCase(attrName))
      {
         ((Template)target).setListStart((String) value);
         return true;
      }

      if (Template.PROPERTY_LISTSEPARATOR.equalsIgnoreCase(attrName))
      {
         ((Template)target).setListSeparator((String) value);
         return true;
      }

      if (Template.PROPERTY_LISTEND.equalsIgnoreCase(attrName))
      {
         ((Template)target).setListEnd((String) value);
         return true;
      }

      if (Template.PROPERTY_PLACEHOLDERS.equalsIgnoreCase(attrName))
      {
         ((Template)target).addToPlaceholders((PlaceHolderDescription) value);
         return true;
      }
      
      if ((Template.PROPERTY_PLACEHOLDERS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Template)target).removeFromPlaceholders((PlaceHolderDescription) value);
         return true;
      }

      if (Template.PROPERTY_CHOOSER.equalsIgnoreCase(attrName))
      {
         ((Template)target).setChooser((ChoiceTemplate) value);
         return true;
      }

      if (Template.PROPERTY_MATCHES.equalsIgnoreCase(attrName))
      {
         ((Template)target).addToMatches((Match) value);
         return true;
      }
      
      if ((Template.PROPERTY_MATCHES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Template)target).removeFromMatches((Match) value);
         return true;
      }

      if (Template.PROPERTY_PARENTS.equalsIgnoreCase(attrName))
      {
         ((Template)target).addToParents((PlaceHolderDescription) value);
         return true;
      }
      
      if ((Template.PROPERTY_PARENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Template)target).removeFromParents((PlaceHolderDescription) value);
         return true;
      }

      if (Template.PROPERTY_REFERENCELOOKUP.equalsIgnoreCase(attrName))
      {
         ((Template)target).setReferenceLookup((Boolean) value);
         return true;
      }

      if (Template.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Template)target).setName((String) value);
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
      ((Template) entity).removeYou();
   }
}



