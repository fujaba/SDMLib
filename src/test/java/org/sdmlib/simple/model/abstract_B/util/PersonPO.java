package org.sdmlib.simple.model.abstract_B.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.abstract_B.Person;
import org.sdmlib.simple.model.abstract_B.util.FlowerPO;
import org.sdmlib.simple.model.abstract_B.Flower;
import org.sdmlib.simple.model.abstract_B.util.PersonPO;

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
   public FlowerPO createHasPO()
   {
      FlowerPO result = new FlowerPO(new Flower[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_HAS, result);
      
      return result;
   }

   public FlowerPO createHasPO(String modifier)
   {
      FlowerPO result = new FlowerPO(new Flower[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_HAS, result);
      
      return result;
   }

   public PersonPO createHasLink(FlowerPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_HAS);
   }

   public PersonPO createHasLink(FlowerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_HAS, modifier);
   }

   public Flower getHas()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getHas();
      }
      return null;
   }

}
