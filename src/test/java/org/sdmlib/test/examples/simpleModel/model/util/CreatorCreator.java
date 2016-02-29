package org.sdmlib.test.examples.simpleModel.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.with(new ItemCreator());
      jsonIdMap.with(new ItemPOCreator());
      jsonIdMap.with(new ArrayListCreator());
      jsonIdMap.with(new ArrayListPOCreator());
      jsonIdMap.with(new MacListCreator());
      jsonIdMap.with(new MacListPOCreator());
      jsonIdMap.with(new BigBrotherCreator());
      jsonIdMap.with(new BigBrotherPOCreator());
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      jsonIdMap.with(new BigBrotherCreator());
      jsonIdMap.with(new BigBrotherPOCreator());
      jsonIdMap.with(new ObjectCreator());
      jsonIdMap.with(new ObjectPOCreator());
      jsonIdMap.with(new ArrayListCreator());
      jsonIdMap.with(new ArrayListPOCreator());
      jsonIdMap.with(new MacListCreator());
      jsonIdMap.with(new MacListPOCreator());
      jsonIdMap.with(new BigBrotherCreator());
      jsonIdMap.with(new BigBrotherPOCreator());
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      jsonIdMap.with(new BigBrotherCreator());
      jsonIdMap.with(new BigBrotherPOCreator());
      jsonIdMap.with(new ObjectCreator());
      jsonIdMap.with(new ObjectPOCreator());
      return jsonIdMap;
   }
}
