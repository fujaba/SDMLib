package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.maumau.model.DutyType;

public class DutyTypePO extends PatternObject<DutyTypePO, DutyType>
{

    public DutyTypeSet allMatches()
   {
      this.setDoAllMatches(true);
      
      DutyTypeSet matches = new DutyTypeSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((DutyType) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public DutyTypePO(){
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public DutyTypePO(DutyType... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
