package org.sdmlib.test.examples.helloworld.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.helloworld.Greeting;
import org.sdmlib.test.examples.helloworld.GreetingMessage;

public class GreetingMessagePO extends PatternObject<GreetingMessagePO, GreetingMessage>
{
   public GreetingMessagePO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public GreetingMessagePO(GreetingMessage... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   

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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
   
     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/helloworld/HelloWorldTTC2011.java'>HelloWorldTTC2011.java</a>
*/
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
   
   public GreetingPO hasGreeting()
   {
      GreetingPO result = new GreetingPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GreetingMessage.PROPERTY_GREETING, result);
      
      return result;
   }

   public GreetingPO createGreeting()
   {
      return this.startCreate().hasGreeting().endCreate();
   }

   public GreetingMessagePO hasGreeting(GreetingPO tgt)
   {
      return hasLinkConstraint(tgt, GreetingMessage.PROPERTY_GREETING);
   }

   public GreetingMessagePO createGreeting(GreetingPO tgt)
   {
      return this.startCreate().hasGreeting(tgt).endCreate();
   }

   public Greeting getGreeting()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GreetingMessage) this.getCurrentMatch()).getGreeting();
      }
      return null;
   }

   public GreetingMessagePO filterText(String value)
   {
      new AttributeConstraint()
      .withAttrName(GreetingMessage.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GreetingMessagePO filterText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(GreetingMessage.PROPERTY_TEXT)
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
      super.hasLink(GreetingMessage.PROPERTY_GREETING, result);
      
      return result;
   }

   public GreetingMessagePO filterGreeting(GreetingPO tgt)
   {
      return hasLinkConstraint(tgt, GreetingMessage.PROPERTY_GREETING);
   }


   public GreetingMessagePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public GreetingMessagePO createTextCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(GreetingMessage.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GreetingMessagePO createTextCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(GreetingMessage.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GreetingMessagePO createTextAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(GreetingMessage.PROPERTY_TEXT)
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
      super.hasLink(GreetingMessage.PROPERTY_GREETING, result);
      
      return result;
   }

   public GreetingPO createGreetingPO(String modifier)
   {
      GreetingPO result = new GreetingPO(new org.sdmlib.test.examples.helloworld.Greeting[]{});
      
      result.setModifier(modifier);
      super.hasLink(GreetingMessage.PROPERTY_GREETING, result);
      
      return result;
   }

   public GreetingMessagePO createGreetingLink(GreetingPO tgt)
   {
      return hasLinkConstraint(tgt, GreetingMessage.PROPERTY_GREETING);
   }

   public GreetingMessagePO createGreetingLink(GreetingPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, GreetingMessage.PROPERTY_GREETING, modifier);
   }

}



