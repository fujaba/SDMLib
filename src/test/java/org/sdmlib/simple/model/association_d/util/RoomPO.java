package org.sdmlib.simple.model.association_d.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_d.Room;
import org.sdmlib.simple.model.association_d.util.PersonPO;
import org.sdmlib.simple.model.association_d.Person;
import org.sdmlib.simple.model.association_d.util.RoomPO;

public class RoomPO extends PatternObject<RoomPO, Room>
{

    public RoomSet allMatches()
   {
      this.setDoAllMatches(true);
      
      RoomSet matches = new RoomSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Room) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public RoomPO(){
      newInstance(null);
   }

   public RoomPO(Room... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public PersonPO filterPerson()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_PERSON, result);
      
      return result;
   }

   public PersonPO createPerson()
   {
      return this.startCreate().filterPerson().endCreate();
   }

   public RoomPO filterPerson(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_PERSON);
   }

   public RoomPO createPerson(PersonPO tgt)
   {
      return this.startCreate().filterPerson(tgt).endCreate();
   }

   public Person getPerson()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getPerson();
      }
      return null;
   }

}
