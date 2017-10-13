package org.sdmlib.models.taskflows.util;

import java.util.Timer;

import org.sdmlib.models.pattern.PatternObject;

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

   public TimerPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
