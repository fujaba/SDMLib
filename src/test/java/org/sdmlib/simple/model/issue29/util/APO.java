package org.sdmlib.simple.model.issue29.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.issue29.A;

public class APO extends PatternObject<APO, A>
{

    public ASet allMatches()
   {
      this.setDoAllMatches(true);
      
      ASet matches = new ASet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((A) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public APO(){
      newInstance(null);
   }

   public APO(A... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public APO(String modifier)
   {
      this.setModifier(modifier);
   }
}
