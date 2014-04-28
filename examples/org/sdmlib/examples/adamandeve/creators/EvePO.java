package org.sdmlib.examples.adamandeve.creators;

import org.sdmlib.examples.adamandeve.Eve;
import org.sdmlib.models.pattern.PatternObject;

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
