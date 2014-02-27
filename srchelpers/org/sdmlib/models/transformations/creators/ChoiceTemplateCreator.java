package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.transformations.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.transformations.ChoiceTemplate;
import org.sdmlib.models.transformations.Template;

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
      Template.PROPERTY_PLACEHOLDERS,
      ChoiceTemplate.PROPERTY_CHOICES,
      Template.PROPERTY_CHOOSER,
      Template.PROPERTY_MATCHES,
      Template.PROPERTY_PARENTS,
      Template.PROPERTY_REFERENCELOOKUP,
      Template.PROPERTY_NAME,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new ChoiceTemplate();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ChoiceTemplate) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }
      return ((ChoiceTemplate) target).set(attrName, value);
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



