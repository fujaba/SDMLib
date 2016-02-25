package org.sdmlib.simple.model.association_e.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_e.Room;
import org.sdmlib.simple.model.association_e.util.PersonPO;
import org.sdmlib.simple.model.association_e.Person;
import org.sdmlib.simple.model.association_e.util.RoomPO;
import org.sdmlib.simple.model.association_e.util.PersonSet;

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
   public PersonPO filterPersons()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_PERSONS, result);
      
      return result;
   }

   public PersonPO createPersons()
   {
      return this.startCreate().filterPersons().endCreate();
   }

   public RoomPO filterPersons(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_PERSONS);
   }

   public RoomPO createPersons(PersonPO tgt)
   {
      return this.startCreate().filterPersons(tgt).endCreate();
   }

   public PersonSet getPersons()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getPersons();
      }
      return null;
   }

}
