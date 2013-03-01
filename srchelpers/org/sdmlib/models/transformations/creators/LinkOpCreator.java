package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.transformations.LinkOp;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class LinkOpCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      LinkOp.PROPERTY_SRCTEXT,
      LinkOp.PROPERTY_TGTTEXT,
      LinkOp.PROPERTY_TRANSFORMOP,
      LinkOp.PROPERTY_SRC,
      LinkOp.PROPERTY_TGT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new LinkOp();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((LinkOp) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((LinkOp) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((LinkOp) entity).removeYou();
   }
}






