package org.sdmlib.simple.model.association_c.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_c.Person;
import org.sdmlib.simple.model.association_c.Room;

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
   public RoomPO createRoomPO()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_ROOM, result);
      
      return result;
   }

   public RoomPO createRoomPO(String modifier)
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_ROOM, result);
      
      return result;
   }

   public PersonPO createRoomLink(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_ROOM);
   }

   public PersonPO createRoomLink(RoomPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_ROOM, modifier);
   }

   public Room getRoom()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getRoom();
      }
      return null;
   }

   public PersonPO createPrevPersonPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_PREVPERSON, result);
      
      return result;
   }

   public PersonPO createPrevPersonPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_PREVPERSON, result);
      
      return result;
   }

   public PersonPO createPrevPersonLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_PREVPERSON);
   }

   public PersonPO createPrevPersonLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_PREVPERSON, modifier);
   }

   public Person getPrevPerson()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getPrevPerson();
      }
      return null;
   }

   public PersonPO createNextPersonPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_NEXTPERSON, result);
      
      return result;
   }

   public PersonPO createNextPersonPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_NEXTPERSON, result);
      
      return result;
   }

   public PersonPO createNextPersonLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_NEXTPERSON);
   }

   public PersonPO createNextPersonLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_NEXTPERSON, modifier);
   }

   public Person getNextPerson()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getNextPerson();
      }
      return null;
   }

}
