package org.sdmlib.simple.model.association_c.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_c.Person;
import org.sdmlib.simple.model.association_c.util.RoomPO;
import org.sdmlib.simple.model.association_c.Room;
import org.sdmlib.simple.model.association_c.util.PersonPO;

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
   public RoomPO filterRoom()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_ROOM, result);
      
      return result;
   }

   public RoomPO createRoom()
   {
      return this.startCreate().filterRoom().endCreate();
   }

   public PersonPO filterRoom(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_ROOM);
   }

   public PersonPO createRoom(RoomPO tgt)
   {
      return this.startCreate().filterRoom(tgt).endCreate();
   }

   public Room getRoom()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getRoom();
      }
      return null;
   }

   public PersonPO filterPrevPerson()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_PREVPERSON, result);
      
      return result;
   }

   public PersonPO createPrevPerson()
   {
      return this.startCreate().filterPrevPerson().endCreate();
   }

   public PersonPO filterPrevPerson(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_PREVPERSON);
   }

   public PersonPO createPrevPerson(PersonPO tgt)
   {
      return this.startCreate().filterPrevPerson(tgt).endCreate();
   }

   public Person getPrevPerson()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getPrevPerson();
      }
      return null;
   }

   public PersonPO filterPerson()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_PERSON, result);
      
      return result;
   }

   public PersonPO createPerson()
   {
      return this.startCreate().filterPerson().endCreate();
   }

   public PersonPO filterPerson(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_PERSON);
   }

   public PersonPO createPerson(PersonPO tgt)
   {
      return this.startCreate().filterPerson(tgt).endCreate();
   }

   public Person getPerson()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getPerson();
      }
      return null;
   }

   public PersonPO filterNextPerson()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_NEXTPERSON, result);
      
      return result;
   }

   public PersonPO createNextPerson()
   {
      return this.startCreate().filterNextPerson().endCreate();
   }

   public PersonPO filterNextPerson(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_NEXTPERSON);
   }

   public PersonPO createNextPerson(PersonPO tgt)
   {
      return this.startCreate().filterNextPerson(tgt).endCreate();
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
