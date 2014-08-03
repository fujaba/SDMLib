package org.sdmlib.models.classes.util;

import org.sdmlib.models.pattern.PatternObject;

public class ArrayListSetPO extends PatternObject<ArrayListSetPO, ArrayListSet>
{

    public ArrayListSetSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ArrayListSetSet matches = new ArrayListSetSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ArrayListSet) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ArrayListSetPO(){
      newInstance(org.sdmlib.models.classes.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ArrayListSetPO(ArrayListSet... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.models.classes.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
