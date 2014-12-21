package org.sdmlib.examples.simpleModel.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.simpleModel.model.Person;

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
      newInstance(org.sdmlib.examples.simpleModel.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PersonPO(Person... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.examples.simpleModel.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
