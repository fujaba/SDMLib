package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.examples.adamandeve.model.Eve;
import org.sdmlib.models.pattern.PatternObject;

public class EvePO extends PatternObject<EvePO, Eve>
{

    public EveSet allMatches()
   {
      this.setDoAllMatches(true);
      
      EveSet matches = new EveSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Eve) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public EvePO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public EvePO(Eve... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
