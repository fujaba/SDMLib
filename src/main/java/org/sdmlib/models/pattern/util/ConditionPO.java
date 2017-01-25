package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.PatternObject;

public class ConditionPO extends PatternObject<ConditionPO, Object>
{

    public ConditionSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ConditionSet matches = new ConditionSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Object) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ConditionPO(){
      newInstance(null);
   }

   public ConditionPO(Object... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public ConditionPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
