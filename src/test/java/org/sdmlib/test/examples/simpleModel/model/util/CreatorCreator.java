package org.sdmlib.test.examples.simpleModel.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

class CreatorCreator{

   public static SDMLibJsonIdMap createIdMap(String sessionID)
   {
	   SDMLibJsonIdMap map = new SDMLibJsonIdMap();
	   map.withSessionId(sessionID);
      
	   map.with(new ItemCreator());
	   map.with(new ItemPOCreator());
	   map.with(new ArrayListCreator());
	   map.with(new ArrayListPOCreator());
	   map.with(new MacListCreator());
	   map.with(new MacListPOCreator());
       map.with(new BigBrotherCreator());
       map.with(new BigBrotherPOCreator());
       map.with(new PersonCreator());
       map.with(new PersonPOCreator());
       map.with(new ObjectCreator());
       map.with(new ObjectPOCreator());
       return map;
   }
}
