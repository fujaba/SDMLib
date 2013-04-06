package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import java.lang.Thread;
import org.sdmlib.replication.creators.ThreadSet;

public class ThreadPO extends PatternObject<ThreadPO, Thread>
{
   public ThreadSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ThreadSet matches = new ThreadSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Thread) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

