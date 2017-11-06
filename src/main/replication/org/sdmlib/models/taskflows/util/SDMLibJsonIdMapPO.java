package org.sdmlib.models.taskflows.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.SDMLibJsonIdMap;

public class SDMLibJsonIdMapPO extends PatternObject<SDMLibJsonIdMapPO, SDMLibJsonIdMap>
{

    public SDMLibJsonIdMapSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SDMLibJsonIdMapSet matches = new SDMLibJsonIdMapSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SDMLibJsonIdMap) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SDMLibJsonIdMapPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public SDMLibJsonIdMapPO(SDMLibJsonIdMap... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }

   public SDMLibJsonIdMapPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
