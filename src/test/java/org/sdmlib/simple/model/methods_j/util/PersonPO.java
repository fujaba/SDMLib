package org.sdmlib.simple.model.methods_j.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.methods_j.Person;

public class PersonPO extends PatternObject<PersonPO, Person>
{

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


   public PersonPO(){
      newInstance(null);
   }

   public PersonPO(Person... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   
   //==========================================================================
   
   public String think()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).think();
      }
      return null;
   }

}
