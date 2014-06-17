package org.sdmlib.examples.ludo.model.util;

import org.sdmlib.examples.ludo.LudoModel.LudoColor;
import org.sdmlib.models.pattern.PatternObject;

public class LudoColorPO extends PatternObject<LudoColorPO, LudoColor>
{

    public LudoColorSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LudoColorSet matches = new LudoColorSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((LudoColor) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public LudoColorPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public LudoColorPO(LudoColor... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
