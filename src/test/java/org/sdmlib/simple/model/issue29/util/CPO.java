package org.sdmlib.simple.model.issue29.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.issue29.C;
import org.sdmlib.simple.model.issue29.util.BPO;
import org.sdmlib.simple.model.issue29.B;
import org.sdmlib.simple.model.issue29.util.CPO;

public class CPO extends PatternObject<CPO, C>
{

    public CSet allMatches()
   {
      this.setDoAllMatches(true);
      
      CSet matches = new CSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((C) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public CPO(){
      newInstance(null);
   }

   public CPO(C... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public CPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public BPO createBPO()
   {
      BPO result = new BPO(new B[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(C.PROPERTY_B, result);
      
      return result;
   }

   public BPO createBPO(String modifier)
   {
      BPO result = new BPO(new B[]{});
      
      result.setModifier(modifier);
      super.hasLink(C.PROPERTY_B, result);
      
      return result;
   }

   public CPO createBLink(BPO tgt)
   {
      return hasLinkConstraint(tgt, C.PROPERTY_B);
   }

   public CPO createBLink(BPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, C.PROPERTY_B, modifier);
   }

   public B getB()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((C) this.getCurrentMatch()).getB();
      }
      return null;
   }

}
