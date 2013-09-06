package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyrightextends.Male;
import org.sdmlib.examples.studyrightextends.creators.MaleSet;

public class MalePO extends PatternObject<MalePO, Male>
{
   public MaleSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MaleSet matches = new MaleSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Male) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

