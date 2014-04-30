package org.sdmlib.examples.ludo.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.ludo.LudoModel.LudoColor;
import org.sdmlib.examples.ludo.model.util.LudoColorSet;

public class LudoColorPO extends PatternObject<LudoColorPO, LudoColor>
{

    public LudoColorSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LudoColorSet matches = new LudoColorSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((LudoColor) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

