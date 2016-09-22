package org.sdmlib.simple.model.issue30.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.issue30.A;

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
   
   //==========================================================================
   
   public boolean checkEnd()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((A) getCurrentMatch()).checkEnd();
      }
      return false;
   }

}
