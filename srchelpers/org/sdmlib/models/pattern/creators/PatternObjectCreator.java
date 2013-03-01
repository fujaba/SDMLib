package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class PatternObjectCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      PatternObject.PROPERTY_CURRENTMATCH,
      PatternObject.PROPERTY_INCOMMING,
      PatternObject.PROPERTY_OUTGOING,
      PatternObject.PROPERTY_CANDIDATES,
      PatternObject.PROPERTY_ATTRCONSTRAINTS,
      PatternElement.PROPERTY_PATTERN, 
      PatternElement.PROPERTY_MODIFIER,
      PatternObject.PROPERTY_HASMATCH,
      PatternObject.PROPERTY_PATTERNOBJECTNAME,
      PatternObject.PROPERTY_DESTROYELEM,
      PatternElement.PROPERTY_DOALLMATCHES,
      PatternObject.PROPERTY_PONAME,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new PatternObject();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PatternObject) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((PatternObject) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((PatternObject) entity).removeYou();
   }
}









