package org.sdmlib.simple.model.association_h.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_h.Person;
import org.sdmlib.simple.model.association_h.Room;
import org.sdmlib.simple.model.association_h.Teacher;

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
   public PersonPO createPersonPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Teacher.PROPERTY_PERSON, result);
      
      return result;
   }

   public PersonPO createPersonPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(Teacher.PROPERTY_PERSON, result);
      
      return result;
   }

   public TeacherPO createPersonLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_PERSON);
   }

   public TeacherPO createPersonLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_PERSON, modifier);
   }

   public Person getPerson()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Teacher) this.getCurrentMatch()).getPerson();
      }
      return null;
   }

   public RoomPO createRoomPO()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Teacher.PROPERTY_ROOM, result);
      
      return result;
   }

   public RoomPO createRoomPO(String modifier)
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(modifier);
      super.hasLink(Teacher.PROPERTY_ROOM, result);
      
      return result;
   }

   public TeacherPO createRoomLink(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_ROOM);
   }

   public TeacherPO createRoomLink(RoomPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_ROOM, modifier);
   }

   public Room getRoom()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Teacher) this.getCurrentMatch()).getRoom();
      }
      return null;
   }

}
