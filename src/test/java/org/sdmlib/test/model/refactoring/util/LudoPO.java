package org.sdmlib.test.model.refactoring.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.model.refactoring.Ludo;
import org.sdmlib.models.pattern.AttributeConstraint;

public class LudoPO extends PatternObject<LudoPO, Ludo>
{

    public LudoSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LudoSet matches = new LudoSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Ludo) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public LudoPO(){
      newInstance(org.sdmlib.test.model.refactoring.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public LudoPO(Ludo... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.model.refactoring.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   
   
   
   
   
   
   
   
   
}
