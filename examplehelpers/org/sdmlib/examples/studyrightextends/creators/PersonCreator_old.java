package org.sdmlib.examples.studyrightextends.creators;

import java.util.HashSet;
//
//import org.sdmlib.serialization.interfaces.SendableEntityCreator;
//import org.sdmlib.serialization.json.JsonIdMap;
//import org.sdmlib.examples.studyrightextends.Person;
//
//public class PersonCreator extends GenderCreator_old implements SendableEntityCreator
//{
//   private final String[] properties = new String[]
//   {
//      Person.PROPERTY_NAME,
//   };
//   
//   public String[] getProperties()
//   {
//  	 HashSet<String> hashSet = new HashSet<String>();
//  	 hashSet.addAll(super.getProperties());
//  	 hashSet.add(Person.PROPERTY_NAME);
//     return properties;
//   }
//   
//   public Object getSendableInstance(boolean reference)
//   {
//      return new Person();
//   }
//   
//   public Object getValue(Object target, String attrName)
//   {
//      return ((Person) target).get(attrName);
//   }
//   
//   public boolean setValue(Object target, String attrName, Object value)
//   {
//      return ((Person) target).set(attrName, value);
//   }
//   
//   public static JsonIdMap createIdMap(String sessionID)
//   {
//      return CreatorCreator.createIdMap(sessionID);
//   }
//}

