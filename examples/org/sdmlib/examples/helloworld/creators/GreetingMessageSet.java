package org.sdmlib.examples.helloworld.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.examples.helloworld.GreetingMessage;
import org.sdmlib.models.modelsets.StringList;

public class GreetingMessageSet extends LinkedHashSet<GreetingMessage>
{
   public StringList getText()
   {
      StringList result = new StringList();
      
      for (GreetingMessage obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public GreetingMessageSet withText(String value)
   {
      for (GreetingMessage obj : this)
      {
         obj.withText(value);
      }
      
      return this;
   }

   public GreetingSet getGreeting()
   {
      GreetingSet result = new GreetingSet();
      
      for (GreetingMessage obj : this)
      {
         result.add(obj.getGreeting());
      }
      
      return result;
   }
   public GreetingMessageSet withGreeting(Greeting value)
   {
      for (GreetingMessage obj : this)
      {
         obj.withGreeting(value);
      }
      
      return this;
   }

}

