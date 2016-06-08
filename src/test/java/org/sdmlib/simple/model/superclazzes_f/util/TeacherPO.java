package org.sdmlib.simple.model.superclazzes_f.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.superclazzes_f.Teacher;
import org.sdmlib.simple.model.superclazzes_f.util.PersonPO;
import org.sdmlib.simple.model.superclazzes_f.Person;
import org.sdmlib.simple.model.superclazzes_f.util.TeacherPO;

public class TeacherPO extends PatternObject<TeacherPO, Teacher>
{

    public TeacherSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TeacherSet matches = new TeacherSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Teacher) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public TeacherPO(){
      newInstance(null);
   }

   public TeacherPO(Teacher... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public PersonPO filterPerson()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Teacher.PROPERTY_PERSON, result);
      
      return result;
   }

   public PersonPO createPerson()
   {
      return this.startCreate().filterPerson().endCreate();
   }

   public TeacherPO filterPerson(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Teacher.PROPERTY_PERSON);
   }

   public TeacherPO createPerson(PersonPO tgt)
   {
      return this.startCreate().filterPerson(tgt).endCreate();
   }

   public Person getPerson()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Teacher) this.getCurrentMatch()).getPerson();
      }
      return null;
   }

}
