package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.LudoStoryboard;
import org.sdmlib.models.pattern.PatternObject;

public class LudoStoryboardPO extends PatternObject<LudoStoryboardPO, LudoStoryboard>
{
   public LudoStoryboardSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LudoStoryboardSet matches = new LudoStoryboardSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((LudoStoryboard) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   
   //==========================================================================
   
   public void testLudoStoryboard()
   {
      if (this.getPattern().getHasMatch())
      {
          ((LudoStoryboard) getCurrentMatch()).testLudoStoryboard();
      }
   }

   
   //==========================================================================
   
   public void testLudoStoryboardManual()
   {
      if (this.getPattern().getHasMatch())
      {
          ((LudoStoryboard) getCurrentMatch()).testLudoStoryboardManual();
      }
   }

}

