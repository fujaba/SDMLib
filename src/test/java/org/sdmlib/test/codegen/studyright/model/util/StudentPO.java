package org.sdmlib.test.codegen.studyright.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.codegen.studyright.model.Student;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.codegen.studyright.model.util.UniversityPO;
import org.sdmlib.test.codegen.studyright.model.University;
import org.sdmlib.test.codegen.studyright.model.util.StudentPO;

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


   public StudentPO(){
      newInstance(null);
   }

   public StudentPO(Student... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public StudentPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public StudentPO createAssignmentPointsCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_ASSIGNMENTPOINTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StudentPO createAssignmentPointsCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_ASSIGNMENTPOINTS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StudentPO createAssignmentPointsAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_ASSIGNMENTPOINTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public StudentPO createCreditsCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_CREDITS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StudentPO createCreditsCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_CREDITS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StudentPO createCreditsAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_CREDITS)
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
   
   public StudentPO createIdCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_ID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StudentPO createIdCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_ID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StudentPO createIdAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_ID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public StudentPO createMotivationCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_MOTIVATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StudentPO createMotivationCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_MOTIVATION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StudentPO createMotivationAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_MOTIVATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public StudentPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StudentPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StudentPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Student.PROPERTY_NAME)
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
   
   public UniversityPO createUniversityPO()
   {
      UniversityPO result = new UniversityPO(new University[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Student.PROPERTY_UNIVERSITY, result);
      
      return result;
   }

   public UniversityPO createUniversityPO(String modifier)
   {
      UniversityPO result = new UniversityPO(new University[]{});
      
      result.setModifier(modifier);
      super.hasLink(Student.PROPERTY_UNIVERSITY, result);
      
      return result;
   }

   public StudentPO createUniversityLink(UniversityPO tgt)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_UNIVERSITY);
   }

   public StudentPO createUniversityLink(UniversityPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_UNIVERSITY, modifier);
   }

   public University getUniversity()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getUniversity();
      }
      return null;
   }

}
