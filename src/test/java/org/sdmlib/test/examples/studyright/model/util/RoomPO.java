package org.sdmlib.test.examples.studyright.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.studyright.model.Assignment;
import org.sdmlib.test.examples.studyright.model.Room;
import org.sdmlib.test.examples.studyright.model.Student;
import org.sdmlib.test.examples.studyright.model.University;

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
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public RoomPO(Room... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================
   
   public int studentCount()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) getCurrentMatch()).studentCount();
      }
      return 0;
   }

   public RoomPO hasRoomNo(String value)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_ROOMNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO hasRoomNo(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_ROOMNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO createRoomNo(String value)
   {
      this.startCreate().hasRoomNo(value).endCreate();
      return this;
   }
   
   public String getRoomNo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) getCurrentMatch()).getRoomNo();
      }
      return null;
   }
   
   public RoomPO withRoomNo(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) getCurrentMatch()).setRoomNo(value);
      }
      return this;
   }
   
   public RoomPO hasCredits(int value)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_CREDITS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO hasCredits(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_CREDITS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO createCredits(int value)
   {
      this.startCreate().hasCredits(value).endCreate();
      return this;
   }
   
   public int getCredits()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) getCurrentMatch()).getCredits();
      }
      return 0;
   }
   
   public RoomPO withCredits(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) getCurrentMatch()).setCredits(value);
      }
      return this;
   }
   
   public RoomPO hasNeighbors()
   {
      RoomPO result = new RoomPO(new org.sdmlib.test.examples.studyright.model.Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_NEIGHBORS, result);
      
      return result;
   }

   public RoomPO createNeighbors()
   {
      return this.startCreate().hasNeighbors().endCreate();
   }

   public RoomPO hasNeighbors(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_NEIGHBORS);
   }

   public RoomPO createNeighbors(RoomPO tgt)
   {
      return this.startCreate().hasNeighbors(tgt).endCreate();
   }

   public RoomSet getNeighbors()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getNeighbors();
      }
      return null;
   }

   public LecturePO hasLecture()
   {
      LecturePO result = new LecturePO(new org.sdmlib.test.examples.studyright.model.Lecture[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_LECTURE, result);
      
      return result;
   }

   public LecturePO createLecture()
   {
      return this.startCreate().hasLecture().endCreate();
   }

   public RoomPO hasLecture(LecturePO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_LECTURE);
   }

   public RoomPO createLecture(LecturePO tgt)
   {
      return this.startCreate().hasLecture(tgt).endCreate();
   }

   public LectureSet getLecture()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getLecture();
      }
      return null;
   }

   public UniversityPO hasUni()
   {
      UniversityPO result = new UniversityPO(new org.sdmlib.test.examples.studyright.model.University[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_UNI, result);
      
      return result;
   }

   public UniversityPO createUni()
   {
      return this.startCreate().hasUni().endCreate();
   }

   public RoomPO hasUni(UniversityPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_UNI);
   }

   public RoomPO createUni(UniversityPO tgt)
   {
      return this.startCreate().hasUni(tgt).endCreate();
   }

   public University getUni()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getUni();
      }
      return null;
   }

   
   //==========================================================================
   
   public void findPath(String p0, int p1)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Room) getCurrentMatch()).findPath(p0, p1);
      }
   }

   public StudentPO hasStudents()
   {
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_STUDENTS, result);
      
      return result;
   }

   public StudentPO createStudents()
   {
      return this.startCreate().hasStudents().endCreate();
   }

   public RoomPO hasStudents(StudentPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_STUDENTS);
   }

   public RoomPO createStudents(StudentPO tgt)
   {
      return this.startCreate().hasStudents(tgt).endCreate();
   }

   public StudentSet getStudents()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getStudents();
      }
      return null;
   }

   public AssignmentPO hasRoom()
   {
      AssignmentPO result = new AssignmentPO(new Assignment[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_ROOM, result);
      
      return result;
   }

   public AssignmentPO createRoom()
   {
      return this.startCreate().hasRoom().endCreate();
   }

   public RoomPO hasRoom(AssignmentPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_ROOM);
   }

   public RoomPO createRoom(AssignmentPO tgt)
   {
      return this.startCreate().hasRoom(tgt).endCreate();
   }

   public Assignment getRoom()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getRoom();
      }
      return null;
   }

   public RoomPO filterRoomNo(String value)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_ROOMNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RoomPO filterRoomNo(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_ROOMNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RoomPO filterCredits(int value)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_CREDITS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RoomPO filterCredits(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_CREDITS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AssignmentPO filterRoom()
   {
      AssignmentPO result = new AssignmentPO(new Assignment[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_ROOM, result);
      
      return result;
   }

   public RoomPO filterRoom(AssignmentPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_ROOM);
   }

}
