package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.examples.helloworld.Person;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.helloworld.creators.PersonSet;
import org.sdmlib.examples.helloworld.creators.GreetingPO;
import org.sdmlib.examples.helloworld.creators.PersonPO;

public class PersonPO extends PatternObject<PersonPO, Person>
{
   public PatternObject hasGreeting()
   {
      PatternObject result = new GreetingPO();

      PatternLink patternLink = new PatternLink().withTgt(result)
         .withTgtRoleName(Person.PROPERTY_GREETING).withSrc(this);

      this.getPattern().addToElements(patternLink);

      this.getPattern().addToElements(result);

      this.getPattern().findMatch();

      return result;
   }

   public PersonPO hasGreeting(PatternObject tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Person.PROPERTY_GREETING).withSrc(this);

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public PersonPO withGreeting(PatternObject tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Person.PROPERTY_GREETING).withSrc(this)
         .withModifier(Pattern.CREATE);

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public PersonPO hasGreeting(GreetingPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Person.PROPERTY_GREETING).withSrc(this);

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public PersonPO withGreeting(GreetingPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) this.getCurrentMatch()).withGreeting((Greeting) tgtPO
            .getCurrentMatch());
      }
      return this;
   }

   public PersonPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         getCurrentMatch().withName(value);
      }
      return this;
   }

   public PersonPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Person.PROPERTY_NAME).withTgtValue(value).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

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

   public Greeting getGreeting()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getGreeting();
      }
      return null;
   }

   public PersonPO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Person.PROPERTY_NAME).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
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

   public GreetingPO createGreeting()
   {
      return (GreetingPO) this.startCreate().hasGreeting().endCreate();
   }

   public PersonPO createGreeting(GreetingPO tgt)
   {
      return this.startCreate().hasGreeting(tgt).endCreate();
   }

}
