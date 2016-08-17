package org.sdmlib.simple.model.association_f.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_f.Room;
import org.sdmlib.simple.model.association_f.util.PersonPO;
import org.sdmlib.simple.model.association_f.Person;
import org.sdmlib.simple.model.association_f.util.RoomPO;
import org.sdmlib.simple.model.association_f.util.PersonSet;

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

   public RoomPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PersonPO createPersonsPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_PERSONS, result);
      
      return result;
   }

   public PersonPO createPersonsPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(Room.PROPERTY_PERSONS, result);
      
      return result;
   }

   public RoomPO createPersonsLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_PERSONS);
   }

   public RoomPO createPersonsLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_PERSONS, modifier);
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
