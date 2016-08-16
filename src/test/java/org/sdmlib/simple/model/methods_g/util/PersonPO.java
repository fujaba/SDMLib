package org.sdmlib.simple.model.methods_g.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.methods_g.Person;
import de.uniks.networkparser.list.SimpleSet;

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

   public PersonPO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public void think(SimpleSet<String> values)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Person) getCurrentMatch()).think(values);
      }
   }

}
