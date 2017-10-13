package org.sdmlib.simple.model.association_i.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_i.Teacher;
import org.sdmlib.simple.model.association_i.util.PersonPO;
import org.sdmlib.simple.model.association_i.Person;
import org.sdmlib.simple.model.association_i.util.TeacherPO;
import org.sdmlib.simple.model.association_i.util.PersonSet;
import org.sdmlib.simple.model.association_i.util.RoomPO;
import org.sdmlib.simple.model.association_i.Room;
import org.sdmlib.simple.model.association_i.util.RoomSet;

public class TeacherPO extends PatternObject<TeacherPO, Teacher>
{

    public TeacherSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TeacherSet matches = new TeacherSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Teacher) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public TeacherPO(){
      newInstance(null);
   }

   public TeacherPO(Teacher... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public TeacherPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PersonPO createPersonsPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Teacher.PROPERTY_PERSONS, result);
      
      return result;
   }

   public PersonPO createPersonsPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(Teacher.PROPERTY_PERSONS, result);
      
      return result;
   }

   public TeacherPO createPersonsLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_PERSONS);
   }

   public TeacherPO createPersonsLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_PERSONS, modifier);
   }

   public PersonSet getPersons()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Teacher) this.getCurrentMatch()).getPersons();
      }
      return null;
   }

   public RoomPO createRoomsPO()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Teacher.PROPERTY_ROOMS, result);
      
      return result;
   }

   public RoomPO createRoomsPO(String modifier)
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(modifier);
      super.hasLink(Teacher.PROPERTY_ROOMS, result);
      
      return result;
   }

   public TeacherPO createRoomsLink(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_ROOMS);
   }

   public TeacherPO createRoomsLink(RoomPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_ROOMS, modifier);
   }

   public RoomSet getRooms()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Teacher) this.getCurrentMatch()).getRooms();
      }
      return null;
   }

}
