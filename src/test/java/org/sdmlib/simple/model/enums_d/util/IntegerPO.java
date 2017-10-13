package org.sdmlib.simple.model.enums_d.util;

import org.sdmlib.models.pattern.PatternObject;
import java.lang.Integer;

public class IntegerPO extends PatternObject<IntegerPO, Integer>
{

    public IntegerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      IntegerSet matches = new IntegerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Integer) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public IntegerPO(){
      newInstance(null);
   }

   public IntegerPO(Integer... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public IntegerPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
