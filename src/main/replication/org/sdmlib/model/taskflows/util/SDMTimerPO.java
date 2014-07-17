package org.sdmlib.model.taskflows.util;

import org.sdmlib.model.taskflows.SDMTimer;
import org.sdmlib.models.pattern.PatternObject;

public class SDMTimerPO extends PatternObject<SDMTimerPO, SDMTimer>
{

    public SDMTimerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SDMTimerSet matches = new SDMTimerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SDMTimer) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SDMTimerPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public SDMTimerPO(SDMTimer... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================
   
   public void schedule(java.util.TimerTask p0)
   {
      if (this.getPattern().getHasMatch())
      {
          ((SDMTimer) getCurrentMatch()).schedule(p0);
      }
   }

}
