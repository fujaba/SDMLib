package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.examples.helloworld.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.helloworld.GraphComponent;

public class GraphComponentCreator implements EntityFactory
{
   private final String[] properties = new String[]
   {
      GraphComponent.PROPERTY_TEXT,
      GraphComponent.PROPERTY_PARENT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new GraphComponent();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GraphComponent) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((GraphComponent) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((GraphComponent) entity).removeYou();
   }
}







