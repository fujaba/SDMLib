package org.sdmlib.test.examples.m2m.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.m2m.model.Relation;

import de.uniks.networkparser.IdMap;

public class RelationPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new RelationPO(new Relation[]{});
      } else {
          return new RelationPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
