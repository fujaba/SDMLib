package org.sdmlib.simple.model.association_h.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_h.Person;
import org.sdmlib.simple.model.association_h.util.RoomPO;
import org.sdmlib.simple.model.association_h.Room;
import org.sdmlib.simple.model.association_h.util.PersonPO;
import org.sdmlib.simple.model.association_h.util.RoomSet;
import org.sdmlib.simple.model.association_h.util.TeacherPO;
import org.sdmlib.simple.model.association_h.Teacher;
import org.sdmlib.simple.model.association_h.util.TeacherSet;

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
   public RoomPO filterRooms()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_ROOMS, result);
      
      return result;
   }

   public RoomPO createRooms()
   {
      return this.startCreate().filterRooms().endCreate();
   }

   public PersonPO filterRooms(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_ROOMS);
   }

   public PersonPO createRooms(RoomPO tgt)
   {
      return this.startCreate().filterRooms(tgt).endCreate();
   }

   public RoomSet getRooms()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getRooms();
      }
      return null;
   }

   public TeacherPO filterTeachers()
   {
      TeacherPO result = new TeacherPO(new Teacher[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_TEACHERS, result);
      
      return result;
   }

   public TeacherPO createTeachers()
   {
      return this.startCreate().filterTeachers().endCreate();
   }

   public PersonPO filterTeachers(TeacherPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_TEACHERS);
   }

   public PersonPO createTeachers(TeacherPO tgt)
   {
      return this.startCreate().filterTeachers(tgt).endCreate();
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
