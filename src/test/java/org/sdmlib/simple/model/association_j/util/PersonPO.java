package org.sdmlib.simple.model.association_j.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_j.Person;
import org.sdmlib.simple.model.association_j.util.RoomPO;
import org.sdmlib.simple.model.association_j.Room;
import org.sdmlib.simple.model.association_j.util.PersonPO;
import org.sdmlib.simple.model.association_j.util.RoomSet;

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
   public RoomPO filterRooms()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_ROOMS, result);
      
      return result;
   }

   public RoomPO createRooms()
   {
      return this.startCreate().filterRooms().endCreate();
   }

   public PersonPO filterRooms(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_ROOMS);
   }

   public PersonPO createRooms(RoomPO tgt)
   {
      return this.startCreate().filterRooms(tgt).endCreate();
   }

   public RoomSet getRooms()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getRooms();
      }
      return null;
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

}
