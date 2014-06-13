package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.adamandeve.model.Adam;

public class AdamPO extends PatternObject<AdamPO, Adam>
{

    public AdamSet allMatches()
   {
      this.setDoAllMatches(true);
      
      AdamSet matches = new AdamSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Adam) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public AdamPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public AdamPO(Adam... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
