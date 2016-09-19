package org.sdmlib.simple.model.issue30.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.issue30.Ground;

public class GroundPO extends PatternObject<GroundPO, Ground>
{

    public GroundSet allMatches()
   {
      this.setDoAllMatches(true);
      
      GroundSet matches = new GroundSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Ground) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public GroundPO(){
      newInstance(null);
   }

   public GroundPO(Ground... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public GroundPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
