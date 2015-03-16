package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.PatternObject;

public class RunnablePO extends PatternObject<RunnablePO, Runnable>
{

    public RunnableSet allMatches()
   {
      this.setDoAllMatches(true);
      
      RunnableSet matches = new RunnableSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Runnable) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public RunnablePO(){
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public RunnablePO(Runnable... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
