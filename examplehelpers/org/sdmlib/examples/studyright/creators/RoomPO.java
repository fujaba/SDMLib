package org.sdmlib.examples.studyright.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyright.Room;
import org.sdmlib.examples.studyright.creators.RoomSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.studyright.creators.UniversityPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.studyright.creators.RoomPO;
import org.sdmlib.examples.studyright.University;
import org.sdmlib.examples.studyright.creators.StudentPO;
import org.sdmlib.examples.studyright.Student;
import org.sdmlib.examples.studyright.creators.StudentSet;
import org.sdmlib.examples.studyright.creators.AssignmentPO;
import org.sdmlib.examples.studyright.Assignment;
import org.sdmlib.examples.studyright.creators.AssignmentSet;

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
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Room.PROPERTY_CREDITS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
   
   public UniversityPO hasUni()
   {
      UniversityPO result = new UniversityPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Room.PROPERTY_UNI, result);
      
      return result;
   }

   public RoomPO hasUni(UniversityPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Room.PROPERTY_UNI)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public University getUni()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getUni();
      }
      return null;
   }

   public RoomPO hasNeighbors()
   {
      RoomPO result = new RoomPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Room.PROPERTY_NEIGHBORS, result);
      
      return result;
   }

   public RoomPO hasNeighbors(RoomPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Room.PROPERTY_NEIGHBORS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public RoomSet getNeighbors()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getNeighbors();
      }
      return null;
   }

   public StudentPO hasStudents()
   {
      StudentPO result = new StudentPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Room.PROPERTY_STUDENTS, result);
      
      return result;
   }

   public RoomPO hasStudents(StudentPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Room.PROPERTY_STUDENTS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public StudentSet getStudents()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getStudents();
      }
      return null;
   }

   public AssignmentPO hasAssignments()
   {
      AssignmentPO result = new AssignmentPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Room.PROPERTY_ASSIGNMENTS, result);
      
      return result;
   }

   public RoomPO hasAssignments(AssignmentPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Room.PROPERTY_ASSIGNMENTS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public AssignmentSet getAssignments()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getAssignments();
      }
      return null;
   }

   public RoomPO hasRoomNo(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Room.PROPERTY_ROOMNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO hasCredits(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Room.PROPERTY_CREDITS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public UniversityPO createUni()
   {
      return this.startCreate().hasUni().endCreate();
   }

   public RoomPO createNeighbors()
   {
      return this.startCreate().hasNeighbors().endCreate();
   }

   public StudentPO createStudents()
   {
      return this.startCreate().hasStudents().endCreate();
   }

   public AssignmentPO createAssignments()
   {
      return this.startCreate().hasAssignments().endCreate();
   }

   public RoomPO createRoomNo(String value)
   {
      this.startCreate().hasRoomNo(value).endCreate();
      return this;
   }
   
   public RoomPO createCredits(int value)
   {
      this.startCreate().hasCredits(value).endCreate();
      return this;
   }
   
   public RoomPO createUni(UniversityPO tgt)
   {
      return this.startCreate().hasUni(tgt).endCreate();
   }

   public RoomPO createNeighbors(RoomPO tgt)
   {
      return this.startCreate().hasNeighbors(tgt).endCreate();
   }

   public RoomPO createStudents(StudentPO tgt)
   {
      return this.startCreate().hasStudents(tgt).endCreate();
   }

   public RoomPO createAssignments(AssignmentPO tgt)
   {
      return this.startCreate().hasAssignments(tgt).endCreate();
   }

}




