package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.examples.helloworld.GreetingMessage;
import org.sdmlib.examples.helloworld.Person;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.helloworld.creators.GreetingSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.helloworld.creators.GreetingMessagePO;
import org.sdmlib.examples.helloworld.creators.GreetingPO;
import org.sdmlib.examples.helloworld.creators.PersonPO;

public class GreetingPO extends PatternObject<GreetingPO, Greeting>
{
   public GreetingPO hasText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Greeting.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GreetingPO withText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Greeting.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }

   public GreetingMessagePO hasGreetingMessage()
   {
      GreetingMessagePO result = new GreetingMessagePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Greeting.PROPERTY_GREETINGMESSAGE, result);
      
      return result;
   }

   public GreetingPO hasGreetingMessage(GreetingMessagePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Greeting.PROPERTY_GREETINGMESSAGE)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PersonPO hasPerson()
   {
      PersonPO result = new PersonPO();
      result.setModifier(this.getModifier());

      super.hasLink(Greeting.PROPERTY_PERSON, result);
      
      return result;
   }
   
   public GreetingPO hasPerson(PersonPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Greeting.PROPERTY_PERSON)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GreetingPO withPerson(PersonPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Greeting) this.getCurrentMatch()).withPerson((Person) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public String getText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Greeting) getCurrentMatch()).getText();
      }
      return null;
   }
   
   public GreetingMessage getGreetingMessage()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Greeting) this.getCurrentMatch()).getGreetingMessage();
      }
      return null;
   }
   
   public Person getPerson()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Greeting) this.getCurrentMatch()).getPerson();
      }
      return null;
   }
   
   public GreetingPO hasText(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Greeting.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GreetingPO createText(String value)
   {
      this.startCreate().hasText(value).endCreate();
      return this;
   }
   
   public GreetingMessagePO createGreetingMessage()
   {
      return this.startCreate().hasGreetingMessage().endCreate();
   }

   public GreetingPO createGreetingMessage(GreetingMessagePO tgt)
   {
      return this.startCreate().hasGreetingMessage(tgt).endCreate();
   }

   public PersonPO createPerson()
   {
      return this.startCreate().hasPerson().endCreate();
   }

   public GreetingPO createPerson(PersonPO tgt)
   {
      return this.startCreate().hasPerson(tgt).endCreate();
   }

}







