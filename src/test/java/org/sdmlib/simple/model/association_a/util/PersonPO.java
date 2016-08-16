package org.sdmlib.simple.model.association_a.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_a.Person;
import org.sdmlib.simple.model.association_a.util.RoomPO;
import org.sdmlib.simple.model.association_a.Room;
import org.sdmlib.simple.model.association_a.util.PersonPO;

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

}
