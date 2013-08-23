package org.sdmlib.examples.ludo.creators;

import org.sdmlib.models.pattern.PatternObject;
import java.util.Date;
import org.sdmlib.examples.ludo.creators.DateSet;

public class DatePO extends PatternObject<DatePO, Date>
{
   public DateSet allMatches()
   {
      this.setDoAllMatches(true);
      
      DateSet matches = new DateSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Date) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

