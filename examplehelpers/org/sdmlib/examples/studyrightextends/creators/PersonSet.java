package org.sdmlib.examples.studyrightextends.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyrightextends.Person;

import org.sdmlib.models.modelsets.StringList;

import org.sdmlib.examples.studyrightextends.creators.PersonSet;

public class PersonSet extends LinkedHashSet<Person>
{
   
   //==========================================================================
   
   public PersonSet findMyPosition()
   {
      for (Person obj : this)
      {
         obj.findMyPosition();
      }
      return this;
   }

   
   //==========================================================================
   
   public PersonSet findMyPosition(String p0)
   {
      for (Person obj : this)
      {
         obj.findMyPosition( p0);
      }
      return this;
   }

   
   //==========================================================================
   
   public PersonSet findMyPosition(String p0, int p1)
   {
      for (Person obj : this)
      {
         obj.findMyPosition( p0,  p1);
      }
      return this;
   }

   
 


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Person elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public PersonSet with(Person value)
   {
      this.add(value);
      return this;
   }
   
   public PersonSet without(Person value)
   {
      this.remove(value);
      return this;
   }
}



