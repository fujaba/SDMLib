package org.sdmlib.examples.studyrightWithAssignments.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyrightWithAssignments.Student;
import org.sdmlib.examples.studyrightWithAssignments.creators.StudentSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.studyrightWithAssignments.creators.UniversityPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.studyrightWithAssignments.creators.StudentPO;
import org.sdmlib.examples.studyrightWithAssignments.University;
import org.sdmlib.examples.studyrightWithAssignments.creators.RoomPO;
import org.sdmlib.examples.studyrightWithAssignments.Room;
import org.sdmlib.examples.studyrightWithAssignments.creators.AssignmentPO;
import org.sdmlib.examples.studyrightWithAssignments.Assignment;
import org.sdmlib.examples.studyrightWithAssignments.creators.AssignmentSet;

public class StudentPO extends PatternObject<StudentPO, Student>
{
   public StudentSet allMatches()
   {
      this.setDoAllMatches(true);
      
      StudentSet matches = new StudentSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Student) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public StudentPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Student.PROPERTY_NAME)
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
         return ((Student) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public StudentPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Student) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public StudentPO hasId(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Student.PROPERTY_ID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) getCurrentMatch()).getId();
      }
      return null;
   }
   
   public StudentPO withId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Student) getCurrentMatch()).setId(value);
      }
      return this;
   }
   
   public StudentPO hasAssignmentPoints(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Student.PROPERTY_ASSIGNMENTPOINTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getAssignmentPoints()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) getCurrentMatch()).getAssignmentPoints();
      }
      return 0;
   }
   
   public StudentPO withAssignmentPoints(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Student) getCurrentMatch()).setAssignmentPoints(value);
      }
      return this;
   }
   
   public StudentPO hasMotivation(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Student.PROPERTY_MOTIVATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getMotivation()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) getCurrentMatch()).getMotivation();
      }
      return 0;
   }
   
   public StudentPO withMotivation(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Student) getCurrentMatch()).setMotivation(value);
      }
      return this;
   }
   
   public StudentPO hasCredits(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Student.PROPERTY_CREDITS)
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
         return ((Student) getCurrentMatch()).getCredits();
      }
      return 0;
   }
   
   public StudentPO withCredits(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Student) getCurrentMatch()).setCredits(value);
      }
      return this;
   }
   
   public UniversityPO hasUniversity()
   {
      UniversityPO result = new UniversityPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Student.PROPERTY_UNIVERSITY, result);
      
      return result;
   }

   public StudentPO hasUniversity(UniversityPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Student.PROPERTY_UNIVERSITY)
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
         return ((Student) this.getCurrentMatch()).getUniversity();
      }
      return null;
   }

   public RoomPO hasIn()
   {
      RoomPO result = new RoomPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Student.PROPERTY_IN, result);
      
      return result;
   }

   public StudentPO hasIn(RoomPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Student.PROPERTY_IN)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Room getIn()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getIn();
      }
      return null;
   }

   public StudentPO hasFriends()
   {
      StudentPO result = new StudentPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Student.PROPERTY_FRIENDS, result);
      
      return result;
   }

   public StudentPO hasFriends(StudentPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Student.PROPERTY_FRIENDS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public StudentSet getFriends()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getFriends();
      }
      return null;
   }

   public AssignmentPO hasDone()
   {
      AssignmentPO result = new AssignmentPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Student.PROPERTY_DONE, result);
      
      return result;
   }

   public StudentPO hasDone(AssignmentPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Student.PROPERTY_DONE)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public AssignmentSet getDone()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getDone();
      }
      return null;
   }

}

