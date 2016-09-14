package org.sdmlib.test.examples.studyrightWithAssignments.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.UniversityPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.RoomPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.StudentPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.StudentSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.RoomSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.AssignmentPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Assignment;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.AssignmentSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.TeachingAssistantPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.TeachingAssistant;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.TeachingAssistantSet;

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
      newInstance(null);
   }

   public RoomPO(Room... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public RoomPO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public String findPath(int motivation)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) getCurrentMatch()).findPath(motivation);
      }
      return null;
   }

   public RoomPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RoomPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RoomPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public RoomPO createTopicCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_TOPIC)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RoomPO createTopicCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_TOPIC)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RoomPO createTopicAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_TOPIC)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public RoomPO createCreditsCondition(int value)
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
   
   public RoomPO createCreditsCondition(int lower, int upper)
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
   
   public RoomPO createCreditsAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_CREDITS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public UniversityPO createUniversityPO()
   {
      UniversityPO result = new UniversityPO(new University[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_UNIVERSITY, result);
      
      return result;
   }

   public UniversityPO createUniversityPO(String modifier)
   {
      UniversityPO result = new UniversityPO(new University[]{});
      
      result.setModifier(modifier);
      super.hasLink(Room.PROPERTY_UNIVERSITY, result);
      
      return result;
   }

   public RoomPO createUniversityLink(UniversityPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_UNIVERSITY);
   }

   public RoomPO createUniversityLink(UniversityPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_UNIVERSITY, modifier);
   }

   public University getUniversity()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getUniversity();
      }
      return null;
   }

   public StudentPO createStudentsPO()
   {
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_STUDENTS, result);
      
      return result;
   }

   public StudentPO createStudentsPO(String modifier)
   {
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(modifier);
      super.hasLink(Room.PROPERTY_STUDENTS, result);
      
      return result;
   }

   public RoomPO createStudentsLink(StudentPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_STUDENTS);
   }

   public RoomPO createStudentsLink(StudentPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_STUDENTS, modifier);
   }

   public StudentSet getStudents()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getStudents();
      }
      return null;
   }

   public RoomPO createDoorsPO()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_DOORS, result);
      
      return result;
   }

   public RoomPO createDoorsPO(String modifier)
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(modifier);
      super.hasLink(Room.PROPERTY_DOORS, result);
      
      return result;
   }

   public RoomPO createDoorsLink(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_DOORS);
   }

   public RoomPO createDoorsLink(RoomPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_DOORS, modifier);
   }

   public RoomSet getDoors()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getDoors();
      }
      return null;
   }

   public AssignmentPO createAssignmentsPO()
   {
      AssignmentPO result = new AssignmentPO(new Assignment[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_ASSIGNMENTS, result);
      
      return result;
   }

   public AssignmentPO createAssignmentsPO(String modifier)
   {
      AssignmentPO result = new AssignmentPO(new Assignment[]{});
      
      result.setModifier(modifier);
      super.hasLink(Room.PROPERTY_ASSIGNMENTS, result);
      
      return result;
   }

   public RoomPO createAssignmentsLink(AssignmentPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_ASSIGNMENTS);
   }

   public RoomPO createAssignmentsLink(AssignmentPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_ASSIGNMENTS, modifier);
   }

   public AssignmentSet getAssignments()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getAssignments();
      }
      return null;
   }

   public TeachingAssistantPO createTasPO()
   {
      TeachingAssistantPO result = new TeachingAssistantPO(new TeachingAssistant[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_TAS, result);
      
      return result;
   }

   public TeachingAssistantPO createTasPO(String modifier)
   {
      TeachingAssistantPO result = new TeachingAssistantPO(new TeachingAssistant[]{});
      
      result.setModifier(modifier);
      super.hasLink(Room.PROPERTY_TAS, result);
      
      return result;
   }

   public RoomPO createTasLink(TeachingAssistantPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_TAS);
   }

   public RoomPO createTasLink(TeachingAssistantPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_TAS, modifier);
   }

   public TeachingAssistantSet getTas()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getTas();
      }
      return null;
   }

}
