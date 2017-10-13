package org.sdmlib.simple.model.issue31.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.issue31.C;

public class CPO extends PatternObject<CPO, C>
{

    public CSet allMatches()
   {
      this.setDoAllMatches(true);
      
      CSet matches = new CSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((C) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public CPO(){
      newInstance(null);
   }

   public CPO(C... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public CPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
