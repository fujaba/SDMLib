package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.maumau.model.Suit;

public class SuitPO extends PatternObject<SuitPO, Suit>
{

    public SuitSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SuitSet matches = new SuitSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Suit) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SuitPO(){
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public SuitPO(Suit... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
