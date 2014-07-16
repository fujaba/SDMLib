package org.sdmlib.model.taskflows.util;

import org.sdmlib.models.pattern.PatternObject;
import java.util.Timer;

public class TimerPO extends PatternObject<TimerPO, Timer>
{

    public TimerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TimerSet matches = new TimerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Timer) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public TimerPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public TimerPO(Timer... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
