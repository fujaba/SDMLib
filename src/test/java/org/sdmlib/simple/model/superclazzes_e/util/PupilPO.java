package org.sdmlib.simple.model.superclazzes_e.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.superclazzes_e.Pupil;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.simple.model.superclazzes_e.util.TeacherPO;
import org.sdmlib.simple.model.superclazzes_e.Person;
import org.sdmlib.simple.model.superclazzes_e.Teacher;
import org.sdmlib.simple.model.superclazzes_e.util.PupilPO;

public class PupilPO extends PatternObject<PupilPO, Pupil>
{

    public PupilSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PupilSet matches = new PupilSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Pupil) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PupilPO(){
      newInstance(null);
   }

   public PupilPO(Pupil... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public PupilPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PupilPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Pupil.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PupilPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Pupil.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PupilPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Pupil.PROPERTY_NAME)
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
         return ((Pupil) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public PupilPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pupil) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public TeacherPO createTeacherPO()
   {
      TeacherPO result = new TeacherPO(new Teacher[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_TEACHER, result);
      
      return result;
   }

   public TeacherPO createTeacherPO(String modifier)
   {
      TeacherPO result = new TeacherPO(new Teacher[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_TEACHER, result);
      
      return result;
   }

   public PupilPO createTeacherLink(TeacherPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_TEACHER);
   }

   public PupilPO createTeacherLink(TeacherPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_TEACHER, modifier);
   }

   public Teacher getTeacher()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getTeacher();
      }
      return null;
   }

}
