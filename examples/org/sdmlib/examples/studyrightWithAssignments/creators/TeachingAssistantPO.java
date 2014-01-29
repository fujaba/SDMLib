package org.sdmlib.examples.studyrightWithAssignments.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyrightWithAssignments.TeachingAssistant;
import org.sdmlib.examples.studyrightWithAssignments.creators.TeachingAssistantSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.studyrightWithAssignments.creators.UniversityPO;
import org.sdmlib.examples.studyrightWithAssignments.Student;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.studyrightWithAssignments.creators.TeachingAssistantPO;
import org.sdmlib.examples.studyrightWithAssignments.University;
import org.sdmlib.examples.studyrightWithAssignments.creators.RoomPO;
import org.sdmlib.examples.studyrightWithAssignments.Room;
import org.sdmlib.examples.studyrightWithAssignments.creators.AssignmentPO;
import org.sdmlib.examples.studyrightWithAssignments.Assignment;
import org.sdmlib.examples.studyrightWithAssignments.creators.AssignmentSet;
import org.sdmlib.examples.studyrightWithAssignments.creators.StudentPO;
import org.sdmlib.examples.studyrightWithAssignments.creators.StudentSet;

public class TeachingAssistantPO extends PatternObject<TeachingAssistantPO, TeachingAssistant>
{
   public TeachingAssistantSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TeachingAssistantSet matches = new TeachingAssistantSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((TeachingAssistant) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public TeachingAssistantPO hasCertified(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_CERTIFIED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public boolean getCertified()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TeachingAssistant) getCurrentMatch()).getCertified();
      }
      return false;
   }
   
   public TeachingAssistantPO withCertified(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TeachingAssistant) getCurrentMatch()).setCertified(value);
      }
      return this;
   }
   
   public TeachingAssistantPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public TeachingAssistantPO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
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
         return ((TeachingAssistant) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public TeachingAssistantPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TeachingAssistant) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public TeachingAssistantPO hasId(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public TeachingAssistantPO hasId(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
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
         return ((TeachingAssistant) getCurrentMatch()).getId();
      }
      return null;
   }
   
   public TeachingAssistantPO withId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TeachingAssistant) getCurrentMatch()).setId(value);
      }
      return this;
   }
   
   public TeachingAssistantPO hasAssignmentPoints(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ASSIGNMENTPOINTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public TeachingAssistantPO hasAssignmentPoints(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ASSIGNMENTPOINTS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
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
         return ((TeachingAssistant) getCurrentMatch()).getAssignmentPoints();
      }
      return 0;
   }
   
   public TeachingAssistantPO withAssignmentPoints(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TeachingAssistant) getCurrentMatch()).setAssignmentPoints(value);
      }
      return this;
   }
   
   public TeachingAssistantPO hasMotivation(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_MOTIVATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public TeachingAssistantPO hasMotivation(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_MOTIVATION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
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
         return ((TeachingAssistant) getCurrentMatch()).getMotivation();
      }
      return 0;
   }
   
   public TeachingAssistantPO withMotivation(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TeachingAssistant) getCurrentMatch()).setMotivation(value);
      }
      return this;
   }
   
   public TeachingAssistantPO hasCredits(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_CREDITS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public TeachingAssistantPO hasCredits(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_CREDITS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
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
         return ((TeachingAssistant) getCurrentMatch()).getCredits();
      }
      return 0;
   }
   
   public TeachingAssistantPO withCredits(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TeachingAssistant) getCurrentMatch()).setCredits(value);
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

   public TeachingAssistantPO hasUniversity(UniversityPO tgt)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_UNIVERSITY);
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

   public TeachingAssistantPO hasIn(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_IN);
   }

   public Room getIn()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getIn();
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

   public TeachingAssistantPO hasDone(AssignmentPO tgt)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_DONE);
   }

   public AssignmentSet getDone()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getDone();
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

   public TeachingAssistantPO hasFriends(StudentPO tgt)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_FRIENDS);
   }

   public StudentSet getFriends()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getFriends();
      }
      return null;
   }

   public RoomPO hasRoom()
   {
      RoomPO result = new RoomPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(TeachingAssistant.PROPERTY_ROOM, result);
      
      return result;
   }

   public TeachingAssistantPO hasRoom(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, TeachingAssistant.PROPERTY_ROOM);
   }

   public Room getRoom()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TeachingAssistant) this.getCurrentMatch()).getRoom();
      }
      return null;
   }

}

