package org.sdmlib.model.taskflows.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.model.taskflows.creators.SDMLibJsonIdMapSet;

public class SDMLibJsonIdMapPO extends PatternObject<SDMLibJsonIdMapPO, SDMLibJsonIdMap>
{
   public SDMLibJsonIdMapSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SDMLibJsonIdMapSet matches = new SDMLibJsonIdMapSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SDMLibJsonIdMap) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

