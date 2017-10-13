package org.sdmlib.test.examples.simpleModel.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.simpleModel.model.BigBrother;
import org.sdmlib.test.examples.simpleModel.model.Person;
import org.sdmlib.test.examples.simpleModel.model.util.PersonPO;
import org.sdmlib.test.examples.simpleModel.model.util.BigBrotherPO;

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
      newInstance(org.sdmlib.test.examples.simpleModel.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public BigBrotherPO(BigBrother... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.simpleModel.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ObjectPO hasKids()
   {
      ObjectPO result = new ObjectPO(new java.lang.Object[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(BigBrother.PROPERTY_KIDS, result);
      
      return result;
   }

   public ObjectPO createKids()
   {
      return this.startCreate().hasKids().endCreate();
   }

   public BigBrotherPO hasKids(ObjectPO tgt)
   {
      return hasLinkConstraint(tgt, BigBrother.PROPERTY_KIDS);
   }

   public BigBrotherPO createKids(ObjectPO tgt)
   {
      return this.startCreate().hasKids(tgt).endCreate();
   }

   public ObjectSet getKids()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BigBrother) this.getCurrentMatch()).getKids();
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

   public PersonPO filterNoOne()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(BigBrother.PROPERTY_NOONE, result);
      
      return result;
   }

   public BigBrotherPO filterNoOne(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, BigBrother.PROPERTY_NOONE);
   }

   public PersonPO filterSuspects()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(BigBrother.PROPERTY_SUSPECTS, result);
      
      return result;
   }

   public BigBrotherPO filterSuspects(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, BigBrother.PROPERTY_SUSPECTS);
   }

   public ObjectPO filterKids()
   {
      ObjectPO result = new ObjectPO(new java.lang.Object[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(BigBrother.PROPERTY_KIDS, result);
      
      return result;
   }

   public BigBrotherPO filterKids(ObjectPO tgt)
   {
      return hasLinkConstraint(tgt, BigBrother.PROPERTY_KIDS);
   }


   public BigBrotherPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PersonPO createNoOnePO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(BigBrother.PROPERTY_NOONE, result);
      
      return result;
   }

   public PersonPO createNoOnePO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(BigBrother.PROPERTY_NOONE, result);
      
      return result;
   }

   public BigBrotherPO createNoOneLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, BigBrother.PROPERTY_NOONE);
   }

   public BigBrotherPO createNoOneLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, BigBrother.PROPERTY_NOONE, modifier);
   }

   public PersonPO createSuspectsPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(BigBrother.PROPERTY_SUSPECTS, result);
      
      return result;
   }

   public PersonPO createSuspectsPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(BigBrother.PROPERTY_SUSPECTS, result);
      
      return result;
   }

   public BigBrotherPO createSuspectsLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, BigBrother.PROPERTY_SUSPECTS);
   }

   public BigBrotherPO createSuspectsLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, BigBrother.PROPERTY_SUSPECTS, modifier);
   }

   public ObjectPO createKidsPO()
   {
      ObjectPO result = new ObjectPO(new java.lang.Object[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(BigBrother.PROPERTY_KIDS, result);
      
      return result;
   }

   public ObjectPO createKidsPO(String modifier)
   {
      ObjectPO result = new ObjectPO(new java.lang.Object[]{});
      
      result.setModifier(modifier);
      super.hasLink(BigBrother.PROPERTY_KIDS, result);
      
      return result;
   }

   public BigBrotherPO createKidsLink(ObjectPO tgt)
   {
      return hasLinkConstraint(tgt, BigBrother.PROPERTY_KIDS);
   }

   public BigBrotherPO createKidsLink(ObjectPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, BigBrother.PROPERTY_KIDS, modifier);
   }

}
