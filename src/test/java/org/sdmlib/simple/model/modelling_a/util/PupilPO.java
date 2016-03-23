package org.sdmlib.simple.model.modelling_a.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.modelling_a.Pupil;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.simple.model.modelling_a.util.RoomPO;
import org.sdmlib.simple.model.modelling_a.Person;
import org.sdmlib.simple.model.modelling_a.Room;
import org.sdmlib.simple.model.modelling_a.util.PupilPO;
import org.sdmlib.simple.model.modelling_a.util.TeacherPO;
import org.sdmlib.simple.model.modelling_a.Teacher;

public class PupilPO extends PatternObject<PupilPO, Pupil>
{

    public PupilSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PupilSet matches = new PupilSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Pupil) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PupilPO(){
      newInstance(null);
   }

   public PupilPO(Pupil... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   
   //==========================================================================
   
   public String read()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pupil) getCurrentMatch()).read();
      }
      return null;
   }

   public PupilPO filterCredits(int value)
   {
      new AttributeConstraint()
      .withAttrName(Pupil.PROPERTY_CREDITS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PupilPO filterCredits(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Pupil.PROPERTY_CREDITS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PupilPO createCredits(int value)
   {
      this.startCreate().filterCredits(value).endCreate();
      return this;
   }
   
   public int getCredits()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pupil) getCurrentMatch()).getCredits();
      }
      return 0;
   }
   
   public PupilPO withCredits(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pupil) getCurrentMatch()).setCredits(value);
      }
      return this;
   }
   
   public PupilPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Pupil.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PupilPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Pupil.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PupilPO createName(String value)
   {
      this.startCreate().filterName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pupil) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public PupilPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pupil) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public PupilPO filterAge(int value)
   {
      new AttributeConstraint()
      .withAttrName(Pupil.PROPERTY_AGE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PupilPO filterAge(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Pupil.PROPERTY_AGE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PupilPO createAge(int value)
   {
      this.startCreate().filterAge(value).endCreate();
      return this;
   }
   
   public int getAge()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pupil) getCurrentMatch()).getAge();
      }
      return 0;
   }
   
   public PupilPO withAge(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pupil) getCurrentMatch()).setAge(value);
      }
      return this;
   }
   
   public RoomPO filterRoom()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_ROOM, result);
      
      return result;
   }

   public RoomPO createRoom()
   {
      return this.startCreate().filterRoom().endCreate();
   }

   public PupilPO filterRoom(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_ROOM);
   }

   public PupilPO createRoom(RoomPO tgt)
   {
      return this.startCreate().filterRoom(tgt).endCreate();
   }

   public Room getRoom()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getRoom();
      }
      return null;
   }

   public RoomPO filterCurrentRoom()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pupil.PROPERTY_CURRENTROOM, result);
      
      return result;
   }

   public RoomPO createCurrentRoom()
   {
      return this.startCreate().filterCurrentRoom().endCreate();
   }

   public PupilPO filterCurrentRoom(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Pupil.PROPERTY_CURRENTROOM);
   }

   public PupilPO createCurrentRoom(RoomPO tgt)
   {
      return this.startCreate().filterCurrentRoom(tgt).endCreate();
   }

   public Room getCurrentRoom()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pupil) this.getCurrentMatch()).getCurrentRoom();
      }
      return null;
   }

   public TeacherPO filterTeacher()
   {
      TeacherPO result = new TeacherPO(new Teacher[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pupil.PROPERTY_TEACHER, result);
      
      return result;
   }

   public TeacherPO createTeacher()
   {
      return this.startCreate().filterTeacher().endCreate();
   }

   public PupilPO filterTeacher(TeacherPO tgt)
   {
      return hasLinkConstraint(tgt, Pupil.PROPERTY_TEACHER);
   }

   public PupilPO createTeacher(TeacherPO tgt)
   {
      return this.startCreate().filterTeacher(tgt).endCreate();
   }

   public Teacher getTeacher()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pupil) this.getCurrentMatch()).getTeacher();
      }
      return null;
   }

}
