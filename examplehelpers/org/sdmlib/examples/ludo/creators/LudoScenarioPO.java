package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.LudoScenario;
import org.sdmlib.models.pattern.PatternObject;

public class LudoScenarioPO extends PatternObject<LudoScenarioPO, LudoScenario>
{
   public LudoScenarioSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LudoScenarioSet matches = new LudoScenarioSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((LudoScenario) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   
   //==========================================================================
   
   public void testLudoScenario()
   {
      if (this.getPattern().getHasMatch())
      {
          ((LudoScenario) getCurrentMatch()).testLudoScenario();
      }
   }

   
   //==========================================================================
   
   public void testLudoScenarioManual()
   {
      if (this.getPattern().getHasMatch())
      {
          ((LudoScenario) getCurrentMatch()).testLudoScenarioManual();
      }
   }

}

