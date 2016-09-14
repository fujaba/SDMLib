package org.sdmlib.simple.model.interface_c.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.interface_c.Pupil;

public class PupilPO extends PatternObject<PupilPO, Pupil>
{

    public PupilSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PupilSet matches = new PupilSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Pupil) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PupilPO(){
      newInstance(null);
   }

   public PupilPO(Pupil... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public PupilPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
