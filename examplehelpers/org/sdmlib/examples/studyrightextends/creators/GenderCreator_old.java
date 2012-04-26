package org.sdmlib.examples.studyrightextends.creators;

//import org.sdmlib.serialization.interfaces.SendableEntityCreator;
//import org.sdmlib.serialization.json.JsonIdMap;
//import org.sdmlib.examples.studyrightextends.Gender;
//
//public class GenderCreator_old implements SendableEntityCreator
//{
//   private final String[] properties = new String[]
//   {
//      Gender.PROPERTY_GENDER,
//   };
//   
//   public String[] getProperties()
//   {
//      return properties;
//   }
//   
//   public Object getSendableInstance(boolean reference)
//   {
//      return new Gender();
//   }
//   
//   public Object getValue(Object target, String attrName)
//   {
//      return ((Gender) target).get(attrName);
//   }
//   
//   public boolean setValue(Object target, String attrName, Object value)
//   {
//      return ((Gender) target).set(attrName, value);
//   }
//   
//   public static JsonIdMap createIdMap(String sessionID)
//   {
//      return CreatorCreator.createIdMap(sessionID);
//   }
//}

