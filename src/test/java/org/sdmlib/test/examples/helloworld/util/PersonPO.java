package org.sdmlib.test.examples.helloworld.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.helloworld.Greeting;
import org.sdmlib.test.examples.helloworld.Person;

public class PersonPO extends PatternObject<PersonPO, Person>
{

   public PersonPO(){
   }

   public PersonPO(Person... hostGraphObject)
   {
      Pattern<Object> pattern = new Pattern<Object>();
      PersonPO value = new PersonPO();
      pattern.addToElements(value);
      value.setModifier(this.getModifier());
      
      if(hostGraphObject!=null){
            if(hostGraphObject.length>1){
                  value.withCandidates(hostGraphObject);
            } else {
                  value.setCurrentMatch(hostGraphObject);
            }
      }
      pattern.findMatch();
   }
  
    public PersonSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PersonSet matches = new PersonSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Person) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public PersonPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PersonPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PersonPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public PersonPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public GreetingPO hasGreeting()
   {
      GreetingPO result = new GreetingPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Person.PROPERTY_GREETING, result);
      
      return result;
   }

   public GreetingPO createGreeting()
   {
      return this.startCreate().hasGreeting().endCreate();
   }

   public PersonPO hasGreeting(GreetingPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_GREETING);
   }

   public PersonPO createGreeting(GreetingPO tgt)
   {
      return this.startCreate().hasGreeting(tgt).endCreate();
   }

   public Greeting getGreeting()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getGreeting();
      }
      return null;
   }

   public PersonPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GreetingPO filterGreeting()
   {
      GreetingPO result = new GreetingPO(new org.sdmlib.test.examples.helloworld.Greeting[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_GREETING, result);
      
      return result;
   }

   public PersonPO filterGreeting(GreetingPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_GREETING);
   }


   public PersonPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PersonPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GreetingPO createGreetingPO()
   {
      GreetingPO result = new GreetingPO(new org.sdmlib.test.examples.helloworld.Greeting[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_GREETING, result);
      
      return result;
   }

   public GreetingPO createGreetingPO(String modifier)
   {
      GreetingPO result = new GreetingPO(new org.sdmlib.test.examples.helloworld.Greeting[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_GREETING, result);
      
      return result;
   }

   public PersonPO createGreetingLink(GreetingPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_GREETING);
   }

   public PersonPO createGreetingLink(GreetingPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_GREETING, modifier);
   }

}



