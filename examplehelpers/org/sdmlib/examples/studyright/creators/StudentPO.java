package org.sdmlib.examples.studyright.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyright.Student;
import org.sdmlib.examples.studyright.creators.StudentSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.studyright.creators.UniversityPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.studyright.creators.StudentPO;
import org.sdmlib.examples.studyright.University;
import org.sdmlib.examples.studyright.creators.RoomPO;
import org.sdmlib.examples.studyright.Room;
import org.sdmlib.examples.studyright.creators.AssignmentPO;
import org.sdmlib.examples.studyright.Assignment;
import org.sdmlib.examples.studyright.creators.AssignmentSet;

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
         .withAttrName(Student.PROPERTY_NAME).withTgtValue(value).withSrc(this)
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

   public StudentPO hasMatrNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Student.PROPERTY_MATRNO).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public int getMatrNo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) getCurrentMatch()).getMatrNo();
      }
      return 0;
   }

   public StudentPO withMatrNo(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Student) getCurrentMatch()).setMatrNo(value);
      }
      return this;
   }

   public StudentPO hasCredits(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Student.PROPERTY_CREDITS).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
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

   public StudentPO hasMotivation(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Student.PROPERTY_MOTIVATION).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
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

   public UniversityPO hasUni()
   {
      UniversityPO result = new UniversityPO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(Student.PROPERTY_UNI, result);

      return result;
   }

   public StudentPO hasUni(UniversityPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Student.PROPERTY_UNI).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public University getUni()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getUni();
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
         .withTgt(tgt).withTgtRoleName(Student.PROPERTY_IN).withSrc(this)
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
         .withTgt(tgt).withTgtRoleName(Student.PROPERTY_DONE).withSrc(this)
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

   public StudentPO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Student.PROPERTY_NAME).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public StudentPO hasMatrNo(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Student.PROPERTY_MATRNO).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public StudentPO hasCredits(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Student.PROPERTY_CREDITS).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public StudentPO hasMotivation(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Student.PROPERTY_MOTIVATION).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public UniversityPO createUni()
   {
      return this.startCreate().hasUni().endCreate();
   }

   public RoomPO createIn()
   {
      return this.startCreate().hasIn().endCreate();
   }

   public AssignmentPO createDone()
   {
      return this.startCreate().hasDone().endCreate();
   }

   public StudentPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }

   public StudentPO createMatrNo(int value)
   {
      this.startCreate().hasMatrNo(value).endCreate();
      return this;
   }

   public StudentPO createCredits(int value)
   {
      this.startCreate().hasCredits(value).endCreate();
      return this;
   }

   public StudentPO createMotivation(int value)
   {
      this.startCreate().hasMotivation(value).endCreate();
      return this;
   }

   public StudentPO createUni(UniversityPO tgt)
   {
      return this.startCreate().hasUni(tgt).endCreate();
   }

   public StudentPO createIn(RoomPO tgt)
   {
      return this.startCreate().hasIn(tgt).endCreate();
   }

   public StudentPO createDone(AssignmentPO tgt)
   {
      return this.startCreate().hasDone(tgt).endCreate();
   }

}
