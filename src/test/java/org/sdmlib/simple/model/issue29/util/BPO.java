package org.sdmlib.simple.model.issue29.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.issue29.B;
import org.sdmlib.simple.model.issue29.util.CPO;
import org.sdmlib.simple.model.issue29.C;
import org.sdmlib.simple.model.issue29.util.BPO;

public class BPO extends PatternObject<BPO, B>
{

    public BSet allMatches()
   {
      this.setDoAllMatches(true);
      
      BSet matches = new BSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((B) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public BPO(){
      newInstance(null);
   }

   public BPO(B... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public BPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public CPO createCPO()
   {
      CPO result = new CPO(new C[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(B.PROPERTY_C, result);
      
      return result;
   }

   public CPO createCPO(String modifier)
   {
      CPO result = new CPO(new C[]{});
      
      result.setModifier(modifier);
      super.hasLink(B.PROPERTY_C, result);
      
      return result;
   }

   public BPO createCLink(CPO tgt)
   {
      return hasLinkConstraint(tgt, B.PROPERTY_C);
   }

   public BPO createCLink(CPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, B.PROPERTY_C, modifier);
   }

   public C getC()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((B) this.getCurrentMatch()).getC();
      }
      return null;
   }

}
