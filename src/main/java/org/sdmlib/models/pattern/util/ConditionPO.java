package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.PatternObject;
import de.uniks.networkparser.interfaces.Condition;

public class ConditionPO extends PatternObject<ConditionPO, Condition>
{

    public ConditionSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ConditionSet matches = new ConditionSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Condition) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ConditionPO(){
      newInstance(null);
   }

   public ConditionPO(Condition... hostGraphObject) {
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
