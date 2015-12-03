package org.sdmlib.test.examples.simpleEnumModel.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.simpleEnumModel.model.TEnum;

public class TEnumPO extends PatternObject<TEnumPO, TEnum>
{

    public TEnumSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TEnumSet matches = new TEnumSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((TEnum) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public TEnumPO(){
      newInstance(org.sdmlib.test.examples.simpleEnumModel.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public TEnumPO(TEnum... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.simpleEnumModel.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
