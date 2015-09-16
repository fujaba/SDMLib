package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.maumau.model.Value;

public class ValuePO extends PatternObject<ValuePO, Value>
{

    public ValueSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ValueSet matches = new ValueSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Value) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ValuePO(){
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ValuePO(Value... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
