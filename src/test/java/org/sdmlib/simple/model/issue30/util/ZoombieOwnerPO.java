package org.sdmlib.simple.model.issue30.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.issue30.ZoombieOwner;

public class ZoombieOwnerPO extends PatternObject<ZoombieOwnerPO, ZoombieOwner>
{

    public ZoombieOwnerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ZoombieOwnerSet matches = new ZoombieOwnerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ZoombieOwner) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ZoombieOwnerPO(){
      newInstance(null);
   }

   public ZoombieOwnerPO(ZoombieOwner... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public ZoombieOwnerPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
