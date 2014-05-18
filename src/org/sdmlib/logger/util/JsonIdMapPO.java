package org.sdmlib.logger.util;

import org.sdmlib.models.pattern.PatternObject;

import de.uniks.networkparser.json.JsonIdMap;

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

