package org.sdmlib.simple.model.abstract_B.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.abstract_B.Human;
import org.sdmlib.simple.model.abstract_B.util.FlowerPO;
import org.sdmlib.simple.model.abstract_B.Flower;
import org.sdmlib.simple.model.abstract_B.util.HumanPO;

public class HumanPO extends PatternObject<HumanPO, Human>
{

    public HumanSet allMatches()
   {
      this.setDoAllMatches(true);
      
      HumanSet matches = new HumanSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Human) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public HumanPO(){
      newInstance(null);
   }

   public HumanPO(Human... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public HumanPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public FlowerPO createHasPO()
   {
      FlowerPO result = new FlowerPO(new Flower[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Human.PROPERTY_HAS, result);
      
      return result;
   }

   public FlowerPO createHasPO(String modifier)
   {
      FlowerPO result = new FlowerPO(new Flower[]{});
      
      result.setModifier(modifier);
      super.hasLink(Human.PROPERTY_HAS, result);
      
      return result;
   }

   public HumanPO createHasLink(FlowerPO tgt)
   {
      return hasLinkConstraint(tgt, Human.PROPERTY_HAS);
   }

   public HumanPO createHasLink(FlowerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Human.PROPERTY_HAS, modifier);
   }

   public Flower getHas()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Human) this.getCurrentMatch()).getHas();
      }
      return null;
   }

}
