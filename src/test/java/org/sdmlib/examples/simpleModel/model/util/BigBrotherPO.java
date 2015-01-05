package org.sdmlib.examples.simpleModel.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.simpleModel.model.BigBrother;
import org.sdmlib.examples.simpleModel.model.util.PersonPO;
import org.sdmlib.examples.simpleModel.model.Person;
import org.sdmlib.examples.simpleModel.model.util.BigBrotherPO;
import org.sdmlib.examples.simpleModel.model.util.PersonSet;

public class BigBrotherPO extends PatternObject<BigBrotherPO, BigBrother>
{

    public BigBrotherSet allMatches()
   {
      this.setDoAllMatches(true);
      
      BigBrotherSet matches = new BigBrotherSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((BigBrother) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public BigBrotherPO(){
      newInstance(org.sdmlib.examples.simpleModel.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public BigBrotherPO(BigBrother... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.examples.simpleModel.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public PersonPO hasSuspects()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(BigBrother.PROPERTY_SUSPECTS, result);
      
      return result;
   }

   public PersonPO createSuspects()
   {
      return this.startCreate().hasSuspects().endCreate();
   }

   public BigBrotherPO hasSuspects(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, BigBrother.PROPERTY_SUSPECTS);
   }

   public BigBrotherPO createSuspects(PersonPO tgt)
   {
      return this.startCreate().hasSuspects(tgt).endCreate();
   }

   public PersonSet getSuspects()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BigBrother) this.getCurrentMatch()).getSuspects();
      }
      return null;
   }

   public PersonPO hasNoOne()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(BigBrother.PROPERTY_NOONE, result);
      
      return result;
   }

   public PersonPO createNoOne()
   {
      return this.startCreate().hasNoOne().endCreate();
   }

   public BigBrotherPO hasNoOne(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, BigBrother.PROPERTY_NOONE);
   }

   public BigBrotherPO createNoOne(PersonPO tgt)
   {
      return this.startCreate().hasNoOne(tgt).endCreate();
   }

   public Person getNoOne()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BigBrother) this.getCurrentMatch()).getNoOne();
      }
      return null;
   }

}
