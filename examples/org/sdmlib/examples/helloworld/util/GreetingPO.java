package org.sdmlib.examples.helloworld.util;

import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.examples.helloworld.GreetingMessage;
import org.sdmlib.examples.helloworld.Person;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class GreetingPO extends PatternObject<GreetingPO, Greeting>
{
   public GreetingPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public GreetingPO(Greeting... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   
   
    public GreetingSet allMatches()
   {
      this.setDoAllMatches(true);
      
      GreetingSet matches = new GreetingSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Greeting) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public GreetingPO hasText(String value)
   {
      new AttributeConstraint()
      .withAttrName(Greeting.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GreetingPO hasText(String lower, String upper)
   {
      new AttributeConstraint()
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
   
   public String getText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Greeting) getCurrentMatch()).getText();
      }
      return null;
   }
   
   public GreetingPO withText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Greeting) getCurrentMatch()).setText(value);
      }
      return this;
   }
   
   public GreetingMessagePO hasGreetingMessage()
   {
      GreetingMessagePO result = new GreetingMessagePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Greeting.PROPERTY_GREETINGMESSAGE, result);
      
      return result;
   }

   public GreetingMessagePO createGreetingMessage()
   {
      return this.startCreate().hasGreetingMessage().endCreate();
   }

   public GreetingPO hasGreetingMessage(GreetingMessagePO tgt)
   {
      return hasLinkConstraint(tgt, Greeting.PROPERTY_GREETINGMESSAGE);
   }

   public GreetingPO createGreetingMessage(GreetingMessagePO tgt)
   {
      return this.startCreate().hasGreetingMessage(tgt).endCreate();
   }

   public GreetingMessage getGreetingMessage()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Greeting) this.getCurrentMatch()).getGreetingMessage();
      }
      return null;
   }

   public PersonPO hasPerson()
   {
      PersonPO result = new PersonPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Greeting.PROPERTY_PERSON, result);
      
      return result;
   }

   public PersonPO createPerson()
   {
      return this.startCreate().hasPerson().endCreate();
   }

   public GreetingPO hasPerson(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Greeting.PROPERTY_PERSON);
   }

   public GreetingPO createPerson(PersonPO tgt)
   {
      return this.startCreate().hasPerson(tgt).endCreate();
   }

   public Person getPerson()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Greeting) this.getCurrentMatch()).getPerson();
      }
      return null;
   }

}



