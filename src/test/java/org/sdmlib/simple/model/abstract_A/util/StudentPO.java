package org.sdmlib.simple.model.abstract_A.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.abstract_A.Human;
import org.sdmlib.simple.model.abstract_A.Person;
import org.sdmlib.simple.model.abstract_A.Student;

public class StudentPO extends PatternObject<StudentPO, Student>
{

    public StudentSet allMatches()
   {
      this.setDoAllMatches(true);
      
      StudentSet matches = new StudentSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Student) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public StudentPO(){
      newInstance(null);
   }

   public StudentPO(Student... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public StudentPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PersonPO createHasPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Human.PROPERTY_HAS, result);
      
      return result;
   }

   public PersonPO createHasPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(Human.PROPERTY_HAS, result);
      
      return result;
   }

   public StudentPO createHasLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Human.PROPERTY_HAS);
   }

   public StudentPO createHasLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Human.PROPERTY_HAS, modifier);
   }

   public Person getHas()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Human) this.getCurrentMatch()).getHas();
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

   public StudentPO createOwnerLink(HumanPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_OWNER);
   }

   public StudentPO createOwnerLink(HumanPO tgt, String modifier)
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
