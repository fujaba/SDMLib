package org.sdmlib.simple.model.association_h.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_h.Room;
import org.sdmlib.simple.model.association_h.util.PersonPO;
import org.sdmlib.simple.model.association_h.Person;
import org.sdmlib.simple.model.association_h.util.RoomPO;
import org.sdmlib.simple.model.association_h.util.TeacherPO;
import org.sdmlib.simple.model.association_h.Teacher;
import org.sdmlib.simple.model.association_h.util.TeacherSet;

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
   public PersonPO createPersonPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_PERSON, result);
      
      return result;
   }

   public PersonPO createPersonPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(Room.PROPERTY_PERSON, result);
      
      return result;
   }

   public RoomPO createPersonLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_PERSON);
   }

   public RoomPO createPersonLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_PERSON, modifier);
   }

   public Person getPerson()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getPerson();
      }
      return null;
   }

   public TeacherPO createTeachersPO()
   {
      TeacherPO result = new TeacherPO(new Teacher[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_TEACHERS, result);
      
      return result;
   }

   public TeacherPO createTeachersPO(String modifier)
   {
      TeacherPO result = new TeacherPO(new Teacher[]{});
      
      result.setModifier(modifier);
      super.hasLink(Room.PROPERTY_TEACHERS, result);
      
      return result;
   }

   public RoomPO createTeachersLink(TeacherPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_TEACHERS);
   }

   public RoomPO createTeachersLink(TeacherPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_TEACHERS, modifier);
   }

   public TeacherSet getTeachers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getTeachers();
      }
      return null;
   }

}
