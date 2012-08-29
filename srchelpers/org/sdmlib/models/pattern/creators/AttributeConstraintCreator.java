package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternElement;

public class AttributeConstraintCreator implements EntityFactory
{
   private final String[] properties = new String[]
   {
      AttributeConstraint.PROPERTY_ATTRNAME,
      AttributeConstraint.PROPERTY_TGTVALUE,
      AttributeConstraint.PROPERTY_HOSTGRAPHSRCOBJECT,
      AttributeConstraint.PROPERTY_SRC,
      PatternElement.PROPERTY_MODIFIER,
      PatternElement.PROPERTY_PATTERNOBJECTNAME,
      PatternElement.PROPERTY_HASMATCH,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new AttributeConstraint();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((AttributeConstraint) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((AttributeConstraint) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((AttributeConstraint) entity).removeYou();
   }
}


