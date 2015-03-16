package org.sdmlib.examples.studyrightWithAssignments.model.util;

import org.sdmlib.examples.studyrightWithAssignments.model.Assignment;
import org.sdmlib.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.examples.studyrightWithAssignments.model.TeachingAssistant;
import org.sdmlib.examples.studyrightWithAssignments.model.University;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

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


   public TeachingAssistantPO(){
      newInstance(org.sdmlib.examples.studyrightWithAssignments.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public TeachingAssistantPO(TeachingAssistant... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.examples.studyrightWithAssignments.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public TeachingAssistantPO hasCertified(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_CERTIFIED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public TeachingAssistantPO createCertified(boolean value)
   {
      this.startCreate().hasCertified(value).endCreate();
      return this;
   }
   
   public boolean getCertified()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TeachingAssistant) getCurrentMatch()).isCertified();
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
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public TeachingAssistantPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public TeachingAssistantPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
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
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public TeachingAssistantPO hasId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public TeachingAssistantPO createId(String value)
   {
      this.startCreate().hasId(value).endCreate();
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
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ASSIGNMENTPOINTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public TeachingAssistantPO hasAssignmentPoints(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ASSIGNMENTPOINTS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public TeachingAssistantPO createAssignmentPoints(int value)
   {
      this.startCreate().hasAssignmentPoints(value).endCreate();
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
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_MOTIVATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public TeachingAssistantPO hasMotivation(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_MOTIVATION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public TeachingAssistantPO createMotivation(int value)
   {
      this.startCreate().hasMotivation(value).endCreate();
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
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_CREDITS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public TeachingAssistantPO hasCredits(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_CREDITS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public TeachingAssistantPO createCredits(int value)
   {
      this.startCreate().hasCredits(value).endCreate();
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
      UniversityPO result = new UniversityPO(new University[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Student.PROPERTY_UNIVERSITY, result);
      
      return result;
   }

   public UniversityPO createUniversity()
   {
      return this.startCreate().hasUniversity().endCreate();
   }

   public TeachingAssistantPO hasUniversity(UniversityPO tgt)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_UNIVERSITY);
   }

   public TeachingAssistantPO createUniversity(UniversityPO tgt)
   {
      return this.startCreate().hasUniversity(tgt).endCreate();
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
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Student.PROPERTY_IN, result);
      
      return result;
   }

   public RoomPO createIn()
   {
      return this.startCreate().hasIn().endCreate();
   }

   public TeachingAssistantPO hasIn(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_IN);
   }

   public TeachingAssistantPO createIn(RoomPO tgt)
   {
      return this.startCreate().hasIn(tgt).endCreate();
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
      AssignmentPO result = new AssignmentPO(new Assignment[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Student.PROPERTY_DONE, result);
      
      return result;
   }

   public AssignmentPO createDone()
   {
      return this.startCreate().hasDone().endCreate();
   }

   public TeachingAssistantPO hasDone(AssignmentPO tgt)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_DONE);
   }

   public TeachingAssistantPO createDone(AssignmentPO tgt)
   {
      return this.startCreate().hasDone(tgt).endCreate();
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
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Student.PROPERTY_FRIENDS, result);
      
      return result;
   }

   public StudentPO createFriends()
   {
      return this.startCreate().hasFriends().endCreate();
   }

   public TeachingAssistantPO hasFriends(StudentPO tgt)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_FRIENDS);
   }

   public TeachingAssistantPO createFriends(StudentPO tgt)
   {
      return this.startCreate().hasFriends(tgt).endCreate();
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
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TeachingAssistant.PROPERTY_ROOM, result);
      
      return result;
   }

   public RoomPO createRoom()
   {
      return this.startCreate().hasRoom().endCreate();
   }

   public TeachingAssistantPO hasRoom(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, TeachingAssistant.PROPERTY_ROOM);
   }

   public TeachingAssistantPO createRoom(RoomPO tgt)
   {
      return this.startCreate().hasRoom(tgt).endCreate();
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
