package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.PatternObject;
import java.util.LinkedHashSet;

public class LinkedHashSetPO extends PatternObject<LinkedHashSetPO, LinkedHashSet>
{

    public LinkedHashSetSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LinkedHashSetSet matches = new LinkedHashSetSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((LinkedHashSet) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public LinkedHashSetPO(){
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public LinkedHashSetPO(LinkedHashSet... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
