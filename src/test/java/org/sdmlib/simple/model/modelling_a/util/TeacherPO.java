package org.sdmlib.simple.model.modelling_a.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.modelling_a.Teacher;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.simple.model.modelling_a.util.RoomPO;
import org.sdmlib.simple.model.modelling_a.Person;
import org.sdmlib.simple.model.modelling_a.Room;
import org.sdmlib.simple.model.modelling_a.util.TeacherPO;
import org.sdmlib.simple.model.modelling_a.util.PupilPO;
import org.sdmlib.simple.model.modelling_a.Pupil;
import org.sdmlib.simple.model.modelling_a.util.PupilSet;

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
   
   //==========================================================================
   
   public String teach()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Teacher) getCurrentMatch()).teach();
      }
      return null;
   }

   public TeacherPO createRankCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Teacher.PROPERTY_RANK)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeacherPO createRankCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Teacher.PROPERTY_RANK)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeacherPO createRankAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Teacher.PROPERTY_RANK)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getRank()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Teacher) getCurrentMatch()).getRank();
      }
      return null;
   }
   
   public TeacherPO withRank(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Teacher) getCurrentMatch()).setRank(value);
      }
      return this;
   }
   
   public TeacherPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Teacher.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeacherPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Teacher.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeacherPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Teacher.PROPERTY_NAME)
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
         return ((Teacher) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public TeacherPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Teacher) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public TeacherPO createAgeCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Teacher.PROPERTY_AGE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeacherPO createAgeCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Teacher.PROPERTY_AGE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeacherPO createAgeAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Teacher.PROPERTY_AGE)
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
         return ((Teacher) getCurrentMatch()).getAge();
      }
      return 0;
   }
   
   public TeacherPO withAge(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Teacher) getCurrentMatch()).setAge(value);
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

   public TeacherPO createRoomLink(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_ROOM);
   }

   public TeacherPO createRoomLink(RoomPO tgt, String modifier)
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

   public PupilPO createPupilsPO()
   {
      PupilPO result = new PupilPO(new Pupil[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Teacher.PROPERTY_PUPILS, result);
      
      return result;
   }

   public PupilPO createPupilsPO(String modifier)
   {
      PupilPO result = new PupilPO(new Pupil[]{});
      
      result.setModifier(modifier);
      super.hasLink(Teacher.PROPERTY_PUPILS, result);
      
      return result;
   }

   public TeacherPO createPupilsLink(PupilPO tgt)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_PUPILS);
   }

   public TeacherPO createPupilsLink(PupilPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_PUPILS, modifier);
   }

   public PupilSet getPupils()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Teacher) this.getCurrentMatch()).getPupils();
      }
      return null;
   }

   public RoomPO createCurrentRoomPO()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Teacher.PROPERTY_CURRENTROOM, result);
      
      return result;
   }

   public RoomPO createCurrentRoomPO(String modifier)
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(modifier);
      super.hasLink(Teacher.PROPERTY_CURRENTROOM, result);
      
      return result;
   }

   public TeacherPO createCurrentRoomLink(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_CURRENTROOM);
   }

   public TeacherPO createCurrentRoomLink(RoomPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_CURRENTROOM, modifier);
   }

   public Room getCurrentRoom()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Teacher) this.getCurrentMatch()).getCurrentRoom();
      }
      return null;
   }

}
