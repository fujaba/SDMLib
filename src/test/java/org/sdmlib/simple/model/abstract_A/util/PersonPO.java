package org.sdmlib.simple.model.abstract_A.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.abstract_A.Person;
import org.sdmlib.simple.model.abstract_A.util.PersonPO;
import org.sdmlib.simple.model.abstract_A.util.HumanPO;
import org.sdmlib.simple.model.abstract_A.Human;

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
   public PersonPO createHasPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_HAS, result);
      
      return result;
   }

   public PersonPO createHasPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_HAS, result);
      
      return result;
   }

   public PersonPO createHasLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_HAS);
   }

   public PersonPO createHasLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_HAS, modifier);
   }

   public Person getHas()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getHas();
      }
      return null;
   }

   public HumanPO createOwnerPO()
   {
      HumanPO result = new HumanPO(new Human[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_OWNER, result);
      
      return result;
   }

   public HumanPO createOwnerPO(String modifier)
   {
      HumanPO result = new HumanPO(new Human[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_OWNER, result);
      
      return result;
   }

   public PersonPO createOwnerLink(HumanPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_OWNER);
   }

   public PersonPO createOwnerLink(HumanPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_OWNER, modifier);
   }

   public Human getOwner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getOwner();
      }
      return null;
   }

}
