package org.sdmlib.simple.model.association_i.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_i.Room;
import org.sdmlib.simple.model.association_i.util.PersonPO;
import org.sdmlib.simple.model.association_i.Person;
import org.sdmlib.simple.model.association_i.util.RoomPO;
import org.sdmlib.simple.model.association_i.util.PersonSet;
import org.sdmlib.simple.model.association_i.util.TeacherPO;
import org.sdmlib.simple.model.association_i.Teacher;

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

   public TeacherPO filterTeacher()
   {
      TeacherPO result = new TeacherPO(new Teacher[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_TEACHER, result);
      
      return result;
   }

   public TeacherPO createTeacher()
   {
      return this.startCreate().filterTeacher().endCreate();
   }

   public RoomPO filterTeacher(TeacherPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_TEACHER);
   }

   public RoomPO createTeacher(TeacherPO tgt)
   {
      return this.startCreate().filterTeacher(tgt).endCreate();
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
