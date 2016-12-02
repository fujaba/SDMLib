package org.sdmlib.simple.model.association_h.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_h.Person;
import org.sdmlib.simple.model.association_h.Room;
import org.sdmlib.simple.model.association_h.Teacher;

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
   public RoomPO createRoomsPO()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_ROOMS, result);
      
      return result;
   }

   public RoomPO createRoomsPO(String modifier)
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_ROOMS, result);
      
      return result;
   }

   public PersonPO createRoomsLink(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_ROOMS);
   }

   public PersonPO createRoomsLink(RoomPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_ROOMS, modifier);
   }

   public RoomSet getRooms()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getRooms();
      }
      return null;
   }

   public TeacherPO createTeachersPO()
   {
      TeacherPO result = new TeacherPO(new Teacher[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_TEACHERS, result);
      
      return result;
   }

   public TeacherPO createTeachersPO(String modifier)
   {
      TeacherPO result = new TeacherPO(new Teacher[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_TEACHERS, result);
      
      return result;
   }

   public PersonPO createTeachersLink(TeacherPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_TEACHERS);
   }

   public PersonPO createTeachersLink(TeacherPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_TEACHERS, modifier);
   }

   public TeacherSet getTeachers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getTeachers();
      }
      return null;
   }

}
