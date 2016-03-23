package org.sdmlib.simple.model.interface_c.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.interface_c.Secretary;

public class SecretaryPO extends PatternObject<SecretaryPO, Secretary>
{

    public SecretarySet allMatches()
   {
      this.setDoAllMatches(true);
      
      SecretarySet matches = new SecretarySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Secretary) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SecretaryPO(){
      newInstance(null);
   }

   public SecretaryPO(Secretary... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
}
