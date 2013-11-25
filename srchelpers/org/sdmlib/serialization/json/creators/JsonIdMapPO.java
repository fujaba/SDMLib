package org.sdmlib.serialization.json.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.json.JsonIdMap;

public class JsonIdMapPO extends PatternObject<JsonIdMapPO, JsonIdMap>
{
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

