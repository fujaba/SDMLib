package org.sdmlib.simple.model.modelling_a.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.modelling_a.Pupil;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
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

   public PupilPO(String modifier)
   {
      this.setModifier(modifier);
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

   public PupilPO createCreditsCondition(int value)
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
   
   public PupilPO createCreditsCondition(int lower, int upper)
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
   
   public PupilPO createCreditsAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Pupil.PROPERTY_CREDITS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public PupilPO createNameCondition(String value)
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
   
   public PupilPO createNameCondition(String lower, String upper)
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
   
   public PupilPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Pupil.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public PupilPO createAgeCondition(int value)
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
   
   public PupilPO createAgeCondition(int lower, int upper)
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
   
   public PupilPO createAgeAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Pupil.PROPERTY_AGE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public RoomPO createRoomPO()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_ROOM, result);
      
      return result;
   }

   public RoomPO createRoomPO(String modifier)
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_ROOM, result);
      
      return result;
   }

   public PupilPO createRoomLink(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_ROOM);
   }

   public PupilPO createRoomLink(RoomPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_ROOM, modifier);
   }

   public Room getRoom()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getRoom();
      }
      return null;
   }

   public RoomPO createCurrentRoomPO()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pupil.PROPERTY_CURRENTROOM, result);
      
      return result;
   }

   public RoomPO createCurrentRoomPO(String modifier)
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(modifier);
      super.hasLink(Pupil.PROPERTY_CURRENTROOM, result);
      
      return result;
   }

   public PupilPO createCurrentRoomLink(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Pupil.PROPERTY_CURRENTROOM);
   }

   public PupilPO createCurrentRoomLink(RoomPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Pupil.PROPERTY_CURRENTROOM, modifier);
   }

   public Room getCurrentRoom()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pupil) this.getCurrentMatch()).getCurrentRoom();
      }
      return null;
   }

   public TeacherPO createTeacherPO()
   {
      TeacherPO result = new TeacherPO(new Teacher[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pupil.PROPERTY_TEACHER, result);
      
      return result;
   }

   public TeacherPO createTeacherPO(String modifier)
   {
      TeacherPO result = new TeacherPO(new Teacher[]{});
      
      result.setModifier(modifier);
      super.hasLink(Pupil.PROPERTY_TEACHER, result);
      
      return result;
   }

   public PupilPO createTeacherLink(TeacherPO tgt)
   {
      return hasLinkConstraint(tgt, Pupil.PROPERTY_TEACHER);
   }

   public PupilPO createTeacherLink(TeacherPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Pupil.PROPERTY_TEACHER, modifier);
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
