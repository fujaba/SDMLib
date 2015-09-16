package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.Lane;

public class LanePO extends PatternObject<LanePO, Lane>
{

    public LaneSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LaneSet matches = new LaneSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Lane) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public LanePO(){
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public LanePO(Lane... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
