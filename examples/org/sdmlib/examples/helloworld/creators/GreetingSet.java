package org.sdmlib.examples.helloworld.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.examples.helloworld.GreetingMessage;
import org.sdmlib.examples.helloworld.Person;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;

public class GreetingSet extends LinkedHashSet<Greeting>
{
   public GreetingMessageSet getGreetingMessage()
   {
      GreetingMessageSet result = new GreetingMessageSet();
      
      for (Greeting obj : this)
      {
         result.add(obj.getGreetingMessage());
      }
      
      return result;
   }
   public GreetingSet withGreetingMessage(GreetingMessage value)
   {
      for (Greeting obj : this)
      {
         obj.withGreetingMessage(value);
      }
      
      return this;
   }

   public PersonSet getPerson()
   {
      PersonSet result = new PersonSet();
      
      for (Greeting obj : this)
      {
         result.add(obj.getPerson());
      }
      
      return result;
   }
   public GreetingSet withPerson(Person value)
   {
      for (Greeting obj : this)
      {
         obj.withPerson(value);
      }
      
      return this;
   }

   public StringList getText()
   {
      StringList result = new StringList();
      
      for (Greeting obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public GreetingSet withText(String value)
   {
      for (Greeting obj : this)
      {
         obj.withText(value);
      }
      
      return this;
   }

}


