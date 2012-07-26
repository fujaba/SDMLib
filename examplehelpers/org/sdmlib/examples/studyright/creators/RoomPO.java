package org.sdmlib.examples.studyright.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyright.Room;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.studyright.creators.UniversityPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.studyright.creators.RoomPO;
import org.sdmlib.examples.studyright.University;
import org.sdmlib.examples.studyright.creators.StudentPO;
import org.sdmlib.examples.studyright.Student;

public class RoomPO extends PatternObject
{
   
   //==========================================================================
   
   public void findPath(String p0, int p1)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Room) getCurrentMatch()).findPath( p0,  p1);
      }
   }

   public RoomPO hasRoomNo(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Room.PROPERTY_ROOMNO)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO withRoomNo(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) getCurrentMatch()).withRoomNo(value);
      }
      return this;
   }
   
   public RoomPO hasCredits(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Room.PROPERTY_CREDITS)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO withCredits(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) getCurrentMatch()).withCredits(value);
      }
      return this;
   }
   
   public UniversityPO hasUni()
   {
      UniversityPO result = new UniversityPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Room.PROPERTY_UNI)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public RoomPO hasUni(UniversityPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Room.PROPERTY_UNI)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO withUni(UniversityPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) this.getCurrentMatch()).withUni((University) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public RoomPO hasNeighbors()
   {
      RoomPO result = new RoomPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Room.PROPERTY_NEIGHBORS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public RoomPO hasNeighbors(RoomPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Room.PROPERTY_NEIGHBORS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO withNeighbors(RoomPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) this.getCurrentMatch()).withNeighbors((Room) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public RoomPO withoutNeighbors(RoomPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) this.getCurrentMatch()).withoutNeighbors((Room) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public StudentPO hasStudents()
   {
      StudentPO result = new StudentPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Room.PROPERTY_STUDENTS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public RoomPO hasStudents(StudentPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Room.PROPERTY_STUDENTS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO withStudents(StudentPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) this.getCurrentMatch()).withStudents((Student) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public RoomPO withoutStudents(StudentPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) this.getCurrentMatch()).withoutStudents((Student) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
}


