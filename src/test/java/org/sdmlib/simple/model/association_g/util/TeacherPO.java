package org.sdmlib.simple.model.association_g.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_g.Teacher;
import org.sdmlib.simple.model.association_g.util.PersonPO;
import org.sdmlib.simple.model.association_g.Person;
import org.sdmlib.simple.model.association_g.util.TeacherPO;
import org.sdmlib.simple.model.association_g.util.RoomPO;
import org.sdmlib.simple.model.association_g.Room;

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
   public PersonPO filterPerson()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Teacher.PROPERTY_PERSON, result);
      
      return result;
   }

   public PersonPO createPerson()
   {
      return this.startCreate().filterPerson().endCreate();
   }

   public TeacherPO filterPerson(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_PERSON);
   }

   public TeacherPO createPerson(PersonPO tgt)
   {
      return this.startCreate().filterPerson(tgt).endCreate();
   }

   public Person getPerson()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Teacher) this.getCurrentMatch()).getPerson();
      }
      return null;
   }

   public RoomPO filterRoom()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Teacher.PROPERTY_ROOM, result);
      
      return result;
   }

   public RoomPO createRoom()
   {
      return this.startCreate().filterRoom().endCreate();
   }

   public TeacherPO filterRoom(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_ROOM);
   }

   public TeacherPO createRoom(RoomPO tgt)
   {
      return this.startCreate().filterRoom(tgt).endCreate();
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
