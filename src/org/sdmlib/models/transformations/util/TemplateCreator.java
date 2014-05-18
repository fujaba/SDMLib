package org.sdmlib.models.transformations.util;

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
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Template();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Template) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }
      return ((Template) target).set(attrName, value);
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



