package org.sdmlib.simple.model.methods_i.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.methods_i.Person;

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
   
   public void think(String value, int p0)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Person) getCurrentMatch()).think(value, p0);
      }
   }

}
