package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.examples.helloworld.GreetingMessage;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.helloworld.creators.GreetingMessageSet;
import org.sdmlib.examples.helloworld.creators.GreetingPO;

public class GreetingMessagePO extends PatternObject
{
   public GreetingMessagePO hasText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(GreetingMessage.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GreetingMessage) getCurrentMatch()).getText();
      }
      return null;
   }
   
   public PatternObject hasGreeting()
   {
      PatternObject result = new GreetingPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(GreetingMessage.PROPERTY_GREETING)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public GreetingMessagePO hasGreeting(PatternObject tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GreetingMessage.PROPERTY_GREETING)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GreetingMessagePO withGreeting(PatternObject tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GreetingMessage.PROPERTY_GREETING)
      .withSrc(this)
      .withModifier(Pattern.CREATE);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GreetingMessagePO hasGreeting(GreetingPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GreetingMessage.PROPERTY_GREETING)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GreetingMessagePO withGreeting(GreetingPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((GreetingMessage) this.getCurrentMatch()).withGreeting((Greeting) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public GreetingMessagePO withText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((GreetingMessage) getCurrentMatch()).withText(value);
      }
      return this;
   }
   
   public Greeting getGreeting()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GreetingMessage) this.getCurrentMatch()).getGreeting();
      }
      return null;
   }
   
   public GreetingMessagePO hasText(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(GreetingMessage.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
}





