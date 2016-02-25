package org.sdmlib.test.examples.studyrightWithAssignments.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.studyrightWithAssignments.model.TeachingAssistant;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.UniversityPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.TeachingAssistantPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.RoomPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.StudentPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.StudentSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.AssignmentPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Assignment;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.AssignmentSet;

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
      newInstance(null);
   }

   public TeachingAssistantPO(TeachingAssistant... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public TeachingAssistantPO filterCertified(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_CERTIFIED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO createCertified(boolean value)
   {
      this.startCreate().filterCertified(value).endCreate();
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
   
   public TeachingAssistantPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO createName(String value)
   {
      this.startCreate().filterName(value).endCreate();
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
   
   public TeachingAssistantPO filterId(String value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO filterId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO createId(String value)
   {
      this.startCreate().filterId(value).endCreate();
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
   
   public TeachingAssistantPO filterAssignmentPoints(int value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ASSIGNMENTPOINTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO filterAssignmentPoints(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ASSIGNMENTPOINTS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO createAssignmentPoints(int value)
   {
      this.startCreate().filterAssignmentPoints(value).endCreate();
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
   
   public TeachingAssistantPO filterMotivation(int value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_MOTIVATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO filterMotivation(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_MOTIVATION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO createMotivation(int value)
   {
      this.startCreate().filterMotivation(value).endCreate();
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
   
   public TeachingAssistantPO filterCredits(int value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_CREDITS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO filterCredits(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_CREDITS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO createCredits(int value)
   {
      this.startCreate().filterCredits(value).endCreate();
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
   
   public UniversityPO filterUniversity()
   {
      UniversityPO result = new UniversityPO(new University[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Student.PROPERTY_UNIVERSITY, result);
      
      return result;
   }

   public UniversityPO createUniversity()
   {
      return this.startCreate().filterUniversity().endCreate();
   }

   public TeachingAssistantPO filterUniversity(UniversityPO tgt)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_UNIVERSITY);
   }

   public TeachingAssistantPO createUniversity(UniversityPO tgt)
   {
      return this.startCreate().filterUniversity(tgt).endCreate();
   }

   public University getUniversity()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getUniversity();
      }
      return null;
   }

   public RoomPO filterIn()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Student.PROPERTY_IN, result);
      
      return result;
   }

   public RoomPO createIn()
   {
      return this.startCreate().filterIn().endCreate();
   }

   public TeachingAssistantPO filterIn(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_IN);
   }

   public TeachingAssistantPO createIn(RoomPO tgt)
   {
      return this.startCreate().filterIn(tgt).endCreate();
   }

   public Room getIn()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getIn();
      }
      return null;
   }

   public StudentPO filterFriends()
   {
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Student.PROPERTY_FRIENDS, result);
      
      return result;
   }

   public StudentPO createFriends()
   {
      return this.startCreate().filterFriends().endCreate();
   }

   public TeachingAssistantPO filterFriends(StudentPO tgt)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_FRIENDS);
   }

   public TeachingAssistantPO createFriends(StudentPO tgt)
   {
      return this.startCreate().filterFriends(tgt).endCreate();
   }

   public StudentSet getFriends()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getFriends();
      }
      return null;
   }

   public AssignmentPO filterDone()
   {
      AssignmentPO result = new AssignmentPO(new Assignment[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Student.PROPERTY_DONE, result);
      
      return result;
   }

   public AssignmentPO createDone()
   {
      return this.startCreate().filterDone().endCreate();
   }

   public TeachingAssistantPO filterDone(AssignmentPO tgt)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_DONE);
   }

   public TeachingAssistantPO createDone(AssignmentPO tgt)
   {
      return this.startCreate().filterDone(tgt).endCreate();
   }

   public AssignmentSet getDone()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getDone();
      }
      return null;
   }

   public RoomPO filterRoom()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TeachingAssistant.PROPERTY_ROOM, result);
      
      return result;
   }

   public RoomPO createRoom()
   {
      return this.startCreate().filterRoom().endCreate();
   }

   public TeachingAssistantPO filterRoom(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, TeachingAssistant.PROPERTY_ROOM);
   }

   public TeachingAssistantPO createRoom(RoomPO tgt)
   {
      return this.startCreate().filterRoom(tgt).endCreate();
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
