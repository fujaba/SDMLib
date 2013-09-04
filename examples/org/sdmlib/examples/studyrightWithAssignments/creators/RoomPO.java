package org.sdmlib.examples.studyrightWithAssignments.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyrightWithAssignments.Room;
import org.sdmlib.examples.studyrightWithAssignments.creators.RoomSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.studyrightWithAssignments.creators.UniversityPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.studyrightWithAssignments.creators.RoomPO;
import org.sdmlib.examples.studyrightWithAssignments.University;
import org.sdmlib.examples.studyrightWithAssignments.creators.StudentPO;
import org.sdmlib.examples.studyrightWithAssignments.Student;
import org.sdmlib.examples.studyrightWithAssignments.creators.StudentSet;
import org.sdmlib.examples.studyrightWithAssignments.creators.AssignmentPO;
import org.sdmlib.examples.studyrightWithAssignments.Assignment;
import org.sdmlib.examples.studyrightWithAssignments.creators.AssignmentSet;

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

   public RoomPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Room.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public RoomPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public RoomPO hasTopic(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Room.PROPERTY_TOPIC)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getTopic()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) getCurrentMatch()).getTopic();
      }
      return null;
   }
   
   public RoomPO withTopic(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) getCurrentMatch()).setTopic(value);
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
   
   public UniversityPO hasUniversity()
   {
      UniversityPO result = new UniversityPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Room.PROPERTY_UNIVERSITY, result);
      
      return result;
   }

   public RoomPO hasUniversity(UniversityPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Room.PROPERTY_UNIVERSITY)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public University getUniversity()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getUniversity();
      }
      return null;
   }

   public RoomPO hasDoor()
   {
      RoomPO result = new RoomPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Room.PROPERTY_DOOR, result);
      
      return result;
   }

   public RoomPO hasDoor(RoomPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Room.PROPERTY_DOOR)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public RoomSet getDoor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getDoor();
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

}

