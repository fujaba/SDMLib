package org.sdmlib.logger.util;

import org.sdmlib.models.pattern.PatternObject;

import de.uniks.networkparser.json.JsonIdMap;

public class JsonIdMapPO extends PatternObject<JsonIdMapPO, JsonIdMap>
{
   public JsonIdMapPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public JsonIdMapPO(JsonIdMap... hostGraphObject) {
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
         matches.add((JsonIdMap) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

