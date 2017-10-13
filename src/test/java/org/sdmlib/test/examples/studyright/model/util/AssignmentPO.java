package org.sdmlib.test.examples.studyright.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.studyright.model.Assignment;
import org.sdmlib.test.examples.studyright.model.Room;
import org.sdmlib.test.examples.studyright.model.Student;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.examples.studyright.model.util.RoomPO;
import org.sdmlib.test.examples.studyright.model.util.AssignmentPO;
import org.sdmlib.test.examples.studyright.model.util.StudentPO;

public class AssignmentPO extends PatternObject<AssignmentPO, Assignment>
{

    public AssignmentSet allMatches()
   {
      this.setDoAllMatches(true);
      
      AssignmentSet matches = new AssignmentSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Assignment) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public AssignmentPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public AssignmentPO(Assignment... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public AssignmentPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AssignmentPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AssignmentPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Assignment) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public AssignmentPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Assignment) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public AssignmentPO hasPoints(int value)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_POINTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AssignmentPO hasPoints(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_POINTS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AssignmentPO createPoints(int value)
   {
      this.startCreate().hasPoints(value).endCreate();
      return this;
   }
   
   public int getPoints()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Assignment) getCurrentMatch()).getPoints();
      }
      return 0;
   }
   
   public AssignmentPO withPoints(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Assignment) getCurrentMatch()).setPoints(value);
      }
      return this;
   }
   
   public RoomPO hasAssignments()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Assignment.PROPERTY_ASSIGNMENTS, result);
      
      return result;
   }

   public RoomPO createAssignments()
   {
      return this.startCreate().hasAssignments().endCreate();
   }

   public AssignmentPO hasAssignments(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Assignment.PROPERTY_ASSIGNMENTS);
   }

   public AssignmentPO createAssignments(RoomPO tgt)
   {
      return this.startCreate().hasAssignments(tgt).endCreate();
   }

   public RoomSet getAssignments()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Assignment) this.getCurrentMatch()).getAssignments();
      }
      return null;
   }

   public StudentPO hasStudents()
   {
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Assignment.PROPERTY_STUDENTS, result);
      
      return result;
   }

   public StudentPO createStudents()
   {
      return this.startCreate().hasStudents().endCreate();
   }

   public AssignmentPO hasStudents(StudentPO tgt)
   {
      return hasLinkConstraint(tgt, Assignment.PROPERTY_STUDENTS);
   }

   public AssignmentPO createStudents(StudentPO tgt)
   {
      return this.startCreate().hasStudents(tgt).endCreate();
   }

   public Student getStudents()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Assignment) this.getCurrentMatch()).getStudents();
      }
      return null;
   }

   public AssignmentPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AssignmentPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AssignmentPO filterPoints(int value)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_POINTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AssignmentPO filterPoints(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_POINTS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RoomPO filterAssignments()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Assignment.PROPERTY_ASSIGNMENTS, result);
      
      return result;
   }

   public AssignmentPO filterAssignments(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Assignment.PROPERTY_ASSIGNMENTS);
   }

   public StudentPO filterStudents()
   {
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Assignment.PROPERTY_STUDENTS, result);
      
      return result;
   }

   public AssignmentPO filterStudents(StudentPO tgt)
   {
      return hasLinkConstraint(tgt, Assignment.PROPERTY_STUDENTS);
   }


   public AssignmentPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public AssignmentPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AssignmentPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AssignmentPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AssignmentPO createPointsCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_POINTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AssignmentPO createPointsCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_POINTS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AssignmentPO createPointsAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_POINTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RoomPO createAssignmentsPO()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Assignment.PROPERTY_ASSIGNMENTS, result);
      
      return result;
   }

   public RoomPO createAssignmentsPO(String modifier)
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(modifier);
      super.hasLink(Assignment.PROPERTY_ASSIGNMENTS, result);
      
      return result;
   }

   public AssignmentPO createAssignmentsLink(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Assignment.PROPERTY_ASSIGNMENTS);
   }

   public AssignmentPO createAssignmentsLink(RoomPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Assignment.PROPERTY_ASSIGNMENTS, modifier);
   }

   public StudentPO createStudentsPO()
   {
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Assignment.PROPERTY_STUDENTS, result);
      
      return result;
   }

   public StudentPO createStudentsPO(String modifier)
   {
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(modifier);
      super.hasLink(Assignment.PROPERTY_STUDENTS, result);
      
      return result;
   }

   public AssignmentPO createStudentsLink(StudentPO tgt)
   {
      return hasLinkConstraint(tgt, Assignment.PROPERTY_STUDENTS);
   }

   public AssignmentPO createStudentsLink(StudentPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Assignment.PROPERTY_STUDENTS, modifier);
   }

}
