package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.PatternObject;

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


   public ThreadPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ThreadPO(Thread... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }

   public ThreadPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
