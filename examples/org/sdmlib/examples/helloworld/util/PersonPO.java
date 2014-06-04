package org.sdmlib.examples.helloworld.util;

import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.examples.helloworld.Person;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.helloworld.util.PersonSet;
import org.sdmlib.examples.helloworld.util.GreetingPO;

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

}



