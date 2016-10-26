package org.sdmlib.simple.model.issue31.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.issue31.B;

public class BPO extends PatternObject<BPO, B>
{

    public BSet allMatches()
   {
      this.setDoAllMatches(true);
      
      BSet matches = new BSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((B) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public BPO(){
      newInstance(null);
   }

   public BPO(B... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public BPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
