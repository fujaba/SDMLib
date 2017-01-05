package org.sdmlib.simple.model.abstract_B.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.abstract_B.Flower;
import org.sdmlib.simple.model.abstract_B.Human;

public class FlowerPO extends PatternObject<FlowerPO, Flower>
{

    public FlowerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      FlowerSet matches = new FlowerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Flower) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public FlowerPO(){
      newInstance(null);
   }

   public FlowerPO(Flower... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public FlowerPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public HumanPO createOwnerPO()
   {
      HumanPO result = new HumanPO(new Human[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Flower.PROPERTY_OWNER, result);
      
      return result;
   }

   public HumanPO createOwnerPO(String modifier)
   {
      HumanPO result = new HumanPO(new Human[]{});
      
      result.setModifier(modifier);
      super.hasLink(Flower.PROPERTY_OWNER, result);
      
      return result;
   }

   public FlowerPO createOwnerLink(HumanPO tgt)
   {
      return hasLinkConstraint(tgt, Flower.PROPERTY_OWNER);
   }

   public FlowerPO createOwnerLink(HumanPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Flower.PROPERTY_OWNER, modifier);
   }

   public Human getOwner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Flower) this.getCurrentMatch()).getOwner();
      }
      return null;
   }

}
