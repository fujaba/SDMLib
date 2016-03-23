package org.sdmlib.serialization.util;

import org.sdmlib.models.pattern.PatternObject;

import de.uniks.networkparser.IdMap;

public class JsonIdMapPO extends PatternObject<JsonIdMapPO, IdMap>
{
   public JsonIdMapPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public JsonIdMapPO(IdMap... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   public JsonIdMapSet allMatches()
   {
      this.setDoAllMatches(true);

      JsonIdMapSet matches = new JsonIdMapSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((IdMap) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }

}
