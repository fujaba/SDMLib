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

   public TeacherPO filterTeachers()
   {
      TeacherPO result = new TeacherPO(new Teacher[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_TEACHERS, result);
      
      return result;
   }

   public TeacherPO createTeachers()
   {
      return this.startCreate().filterTeachers().endCreate();
   }

   public RoomPO filterTeachers(TeacherPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_TEACHERS);
   }

   public RoomPO createTeachers(TeacherPO tgt)
   {
      return this.startCreate().filterTeachers(tgt).endCreate();
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
