package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.examples.studyrightextends.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.studyrightextends.Female;

public class FemaleCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Female.PROPERTY_NAME,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Female();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Female) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((Female) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

