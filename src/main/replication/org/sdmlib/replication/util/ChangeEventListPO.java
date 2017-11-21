package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.ChangeEventList;

public class ChangeEventListPO extends PatternObject<ChangeEventListPO, ChangeEventList>
{

    public ChangeEventListSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ChangeEventListSet matches = new ChangeEventListSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ChangeEventList) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ChangeEventListPO(){
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ChangeEventListPO(ChangeEventList... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }

   public ChangeEventListPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
