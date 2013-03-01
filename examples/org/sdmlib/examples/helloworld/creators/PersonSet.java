package org.sdmlib.examples.helloworld.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.examples.helloworld.Person;
import org.sdmlib.models.modelsets.StringList;

public class PersonSet extends LinkedHashSet<Person>
{
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Person obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public PersonSet withName(String value)
   {
      for (Person obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public GreetingSet getGreeting()
   {
      GreetingSet result = new GreetingSet();
      
      for (Person obj : this)
      {
         result.add(obj.getGreeting());
      }
      
      return result;
   }
   public PersonSet withGreeting(Greeting value)
   {
      for (Person obj : this)
      {
         obj.withGreeting(value);
      }
      
      return this;
   }

}

