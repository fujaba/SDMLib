package org.sdmlib.examples.helloworld.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.examples.helloworld.util.GreetingSet;
import org.sdmlib.models.pattern.AttributeConstraint;

public class GreetingPO extends PatternObject<GreetingPO, Greeting>
{

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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
   
}

