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
   public PersonPO filterPersons()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Teacher.PROPERTY_PERSONS, result);
      
      return result;
   }

   public PersonPO createPersons()
   {
      return this.startCreate().filterPersons().endCreate();
   }

   public TeacherPO filterPersons(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_PERSONS);
   }

   public TeacherPO createPersons(PersonPO tgt)
   {
      return this.startCreate().filterPersons(tgt).endCreate();
   }

   public PersonSet getPersons()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Teacher) this.getCurrentMatch()).getPersons();
      }
      return null;
   }

   public RoomPO filterRooms()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Teacher.PROPERTY_ROOMS, result);
      
      return result;
   }

   public RoomPO createRooms()
   {
      return this.startCreate().filterRooms().endCreate();
   }

   public TeacherPO filterRooms(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_ROOMS);
   }

   public TeacherPO createRooms(RoomPO tgt)
   {
      return this.startCreate().filterRooms(tgt).endCreate();
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
