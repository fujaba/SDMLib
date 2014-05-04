package org.sdmlib.examples.helloworld.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.helloworld.GreetingMessage;
import org.sdmlib.examples.helloworld.util.GreetingMessageSet;
import org.sdmlib.models.pattern.AttributeConstraint;

public class GreetingMessagePO extends PatternObject<GreetingMessagePO, GreetingMessage>
{

    public GreetingMessageSet allMatches()
   {
      this.setDoAllMatches(true);
      
      GreetingMessageSet matches = new GreetingMessageSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((GreetingMessage) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
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
   
   public GreetingMessagePO createText(String value)
   {
      this.startCreate().hasText(value).endCreate();
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
   
   public GreetingMessagePO withText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((GreetingMessage) getCurrentMatch()).setText(value);
      }
      return this;
   }
   
}

