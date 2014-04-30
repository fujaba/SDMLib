package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.adamandeve.model.Eve;
import org.sdmlib.examples.adamandeve.model.util.EveSet;

public class EvePO extends PatternObject<EvePO, Eve>
{

    public EveSet allMatches()
   {
      this.setDoAllMatches(true);
      
      EveSet matches = new EveSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Eve) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

