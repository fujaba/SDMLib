package org.sdmlib.simple.model.modelling_a.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.modelling_a.Teacher;
import org.sdmlib.models.pattern.AttributeConstraint;
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
   
   //==========================================================================
   
   public String teach()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Teacher) getCurrentMatch()).teach();
      }
      return null;
   }

   public TeacherPO filterRank(String value)
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
   
   public TeacherPO filterRank(String lower, String upper)
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
   
   public TeacherPO createRank(String value)
   {
      this.startCreate().filterRank(value).endCreate();
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
   
   public TeacherPO filterName(String value)
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
   
   public TeacherPO filterName(String lower, String upper)
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
   
   public TeacherPO createName(String value)
   {
      this.startCreate().filterName(value).endCreate();
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
   
   public TeacherPO filterAge(int value)
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
   
   public TeacherPO filterAge(int lower, int upper)
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
   
   public TeacherPO createAge(int value)
   {
      this.startCreate().filterAge(value).endCreate();
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

   public TeacherPO filterRoom(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_ROOM);
   }

   public TeacherPO createRoom(RoomPO tgt)
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
      super.hasLink(Teacher.PROPERTY_CURRENTROOM, result);
      
      return result;
   }

   public RoomPO createCurrentRoom()
   {
      return this.startCreate().filterCurrentRoom().endCreate();
   }

   public TeacherPO filterCurrentRoom(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_CURRENTROOM);
   }

   public TeacherPO createCurrentRoom(RoomPO tgt)
   {
      return this.startCreate().filterCurrentRoom(tgt).endCreate();
   }

   public Room getCurrentRoom()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Teacher) this.getCurrentMatch()).getCurrentRoom();
      }
      return null;
   }

   public PupilPO filterPupils()
   {
      PupilPO result = new PupilPO(new Pupil[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Teacher.PROPERTY_PUPILS, result);
      
      return result;
   }

   public PupilPO createPupils()
   {
      return this.startCreate().filterPupils().endCreate();
   }

   public TeacherPO filterPupils(PupilPO tgt)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_PUPILS);
   }

   public TeacherPO createPupils(PupilPO tgt)
   {
      return this.startCreate().filterPupils(tgt).endCreate();
   }

   public PupilSet getPupils()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Teacher) this.getCurrentMatch()).getPupils();
      }
      return null;
   }

}
