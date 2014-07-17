package org.sdmlib.examples.simpleModel.model.util;

import org.sdmlib.models.pattern.PatternObject;
import java.util.ArrayList;

public class ArrayListPO extends PatternObject<ArrayListPO, ArrayList>
{

    public ArrayListSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ArrayListSet matches = new ArrayListSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ArrayList) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ArrayListPO(){
      newInstance(org.sdmlib.examples.simpleModel.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ArrayListPO(ArrayList... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.examples.simpleModel.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
