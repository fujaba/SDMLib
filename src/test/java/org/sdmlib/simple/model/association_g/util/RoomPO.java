package org.sdmlib.simple.model.association_g.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_g.Person;
import org.sdmlib.simple.model.association_g.Room;
import org.sdmlib.simple.model.association_g.Teacher;

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

   public TeacherPO createTeacherPO()
   {
      TeacherPO result = new TeacherPO(new Teacher[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_TEACHER, result);
      
      return result;
   }

   public TeacherPO createTeacherPO(String modifier)
   {
      TeacherPO result = new TeacherPO(new Teacher[]{});
      
      result.setModifier(modifier);
      super.hasLink(Room.PROPERTY_TEACHER, result);
      
      return result;
   }

   public RoomPO createTeacherLink(TeacherPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_TEACHER);
   }

   public RoomPO createTeacherLink(TeacherPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_TEACHER, modifier);
   }

   public Teacher getTeacher()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getTeacher();
      }
      return null;
   }

}
