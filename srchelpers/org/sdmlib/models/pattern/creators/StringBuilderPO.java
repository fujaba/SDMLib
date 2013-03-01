package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.PatternObject;
import java.lang.StringBuilder;
import org.sdmlib.models.pattern.creators.StringBuilderSet;

public class StringBuilderPO extends PatternObject<StringBuilderPO, StringBuilder>
{
   public StringBuilderSet allMatches()
   {
      this.setDoAllMatches(true);
      
      StringBuilderSet matches = new StringBuilderSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((StringBuilder) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

