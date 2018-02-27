package org.sdmlib.test.examples.groupaccount.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.groupaccount.model.Party;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.examples.groupaccount.model.util.PersonPO;
import org.sdmlib.test.examples.groupaccount.model.Person;
import org.sdmlib.test.examples.groupaccount.model.util.PartyPO;
import org.sdmlib.test.examples.groupaccount.model.util.PersonSet;

public class PartyPO extends PatternObject<PartyPO, Party>
{

    public PartySet allMatches()
   {
      this.setDoAllMatches(true);
      
      PartySet matches = new PartySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Party) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PartyPO(){
      newInstance(null);
   }

   public PartyPO(Party... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public PartyPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PartyPO createPartyNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Party.PROPERTY_PARTYNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PartyPO createPartyNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Party.PROPERTY_PARTYNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PartyPO createPartyNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Party.PROPERTY_PARTYNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getPartyName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Party) getCurrentMatch()).getPartyName();
      }
      return null;
   }
   
   public PartyPO withPartyName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Party) getCurrentMatch()).setPartyName(value);
      }
      return this;
   }
   
   public PartyPO createShareCondition(double value)
   {
      new AttributeConstraint()
      .withAttrName(Party.PROPERTY_SHARE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PartyPO createShareCondition(double lower, double upper)
   {
      new AttributeConstraint()
      .withAttrName(Party.PROPERTY_SHARE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PartyPO createShareAssignment(double value)
   {
      new AttributeConstraint()
      .withAttrName(Party.PROPERTY_SHARE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public double getShare()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Party) getCurrentMatch()).getShare();
      }
      return 0;
   }
   
   public PartyPO withShare(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Party) getCurrentMatch()).setShare(value);
      }
      return this;
   }
   
   public PartyPO createTotalCondition(double value)
   {
      new AttributeConstraint()
      .withAttrName(Party.PROPERTY_TOTAL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PartyPO createTotalCondition(double lower, double upper)
   {
      new AttributeConstraint()
      .withAttrName(Party.PROPERTY_TOTAL)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PartyPO createTotalAssignment(double value)
   {
      new AttributeConstraint()
      .withAttrName(Party.PROPERTY_TOTAL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public double getTotal()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Party) getCurrentMatch()).getTotal();
      }
      return 0;
   }
   
   public PartyPO withTotal(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Party) getCurrentMatch()).setTotal(value);
      }
      return this;
   }
   
   public PersonPO createGuestsPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Party.PROPERTY_GUESTS, result);
      
      return result;
   }

   public PersonPO createGuestsPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(Party.PROPERTY_GUESTS, result);
      
      return result;
   }

   public PartyPO createGuestsLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Party.PROPERTY_GUESTS);
   }

   public PartyPO createGuestsLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Party.PROPERTY_GUESTS, modifier);
   }

   public PersonSet getGuests()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Party) this.getCurrentMatch()).getGuests();
      }
      return null;
   }

}
