package org.sdmlib.test.examples.mancala.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.mancala.model.Stone;

public class StonePO extends PatternObject<StonePO, Stone>
{

    public StoneSet allMatches()
   {
      this.setDoAllMatches(true);
      
      StoneSet matches = new StoneSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Stone) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public StonePO(){
      newInstance(org.sdmlib.test.examples.mancala.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public StonePO(Stone... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.mancala.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
