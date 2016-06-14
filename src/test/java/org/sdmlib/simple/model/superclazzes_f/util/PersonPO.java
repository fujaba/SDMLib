package org.sdmlib.simple.model.superclazzes_f.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.superclazzes_f.Person;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.simple.model.superclazzes_f.util.TeacherPO;
import org.sdmlib.simple.model.superclazzes_f.Teacher;
import org.sdmlib.simple.model.superclazzes_f.util.PersonPO;

public class PersonPO extends PatternObject<PersonPO, Person>
{

    public PersonSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PersonSet matches = new PersonSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Person) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PersonPO(){
      newInstance(null);
   }

   public PersonPO(Person... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public PersonPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createName(String value)
   {
      this.startCreate().filterName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public PersonPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public TeacherPO filterTeacher()
   {
      TeacherPO result = new TeacherPO(new Teacher[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_TEACHER, result);
      
      return result;
   }

   public TeacherPO createTeacher()
   {
      return this.startCreate().filterTeacher().endCreate();
   }

   public PersonPO filterTeacher(TeacherPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_TEACHER);
   }

   public PersonPO createTeacher(TeacherPO tgt)
   {
      return this.startCreate().filterTeacher(tgt).endCreate();
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
