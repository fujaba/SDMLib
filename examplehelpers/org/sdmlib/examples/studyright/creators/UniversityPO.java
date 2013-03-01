package org.sdmlib.examples.studyright.creators;

import org.sdmlib.examples.studyright.Room;
import org.sdmlib.examples.studyright.Student;
import org.sdmlib.examples.studyright.University;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;

public class UniversityPO extends PatternObject
{
   public UniversityPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(University.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public UniversityPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((University) getCurrentMatch()).withName(value);
      }
      return this;
   }
   
   public StudentPO hasStudents()
   {
      StudentPO result = new StudentPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(University.PROPERTY_STUDENTS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public UniversityPO hasStudents(StudentPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(University.PROPERTY_STUDENTS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public UniversityPO withStudents(StudentPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((University) this.getCurrentMatch()).withStudents((Student) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public UniversityPO withoutStudents(StudentPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((University) this.getCurrentMatch()).withoutStudents((Student) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public RoomPO hasRooms()
   {
      RoomPO result = new RoomPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(University.PROPERTY_ROOMS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public UniversityPO hasRooms(RoomPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(University.PROPERTY_ROOMS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public UniversityPO withRooms(RoomPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((University) this.getCurrentMatch()).withRooms((Room) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public UniversityPO withoutRooms(RoomPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((University) this.getCurrentMatch()).withoutRooms((Room) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((University) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public StudentSet getStudents()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((University) this.getCurrentMatch()).getStudents();
      }
      return null;
   }
   
   public RoomSet getRooms()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((University) this.getCurrentMatch()).getRooms();
      }
      return null;
   }
   
}



