package org.sdmlib.simple.model.attribute_m.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.simple.model.attribute_m.Person;

import de.uniks.networkparser.IdMap;

public class PersonPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new PersonPO(new Person[]{});
      } else {
          return new PersonPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.attribute_m.util.CreatorCreator.createIdMap(sessionID);
   }
}
