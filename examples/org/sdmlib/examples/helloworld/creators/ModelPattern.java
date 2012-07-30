package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.examples.helloworld.creators.GreetingPO;
import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.examples.helloworld.creators.GreetingMessagePO;
import org.sdmlib.examples.helloworld.GreetingMessage;
import org.sdmlib.examples.helloworld.creators.PersonPO;
import org.sdmlib.examples.helloworld.Person;

public class ModelPattern extends Pattern
{
   public ModelPattern()
   {
      super(CreatorCreator.createIdMap("hg"));
   }
   
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public GreetingPO hasElementGreetingPO()
   {
      GreetingPO value = new GreetingPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public GreetingPO hasElementGreetingPO(Greeting hostGraphObject)
   {
      GreetingPO value = new GreetingPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public GreetingMessagePO hasElementGreetingMessagePO()
   {
      GreetingMessagePO value = new GreetingMessagePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public GreetingMessagePO hasElementGreetingMessagePO(GreetingMessage hostGraphObject)
   {
      GreetingMessagePO value = new GreetingMessagePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PersonPO hasElementPersonPO()
   {
      PersonPO value = new PersonPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PersonPO hasElementPersonPO(Person hostGraphObject)
   {
      PersonPO value = new PersonPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}



