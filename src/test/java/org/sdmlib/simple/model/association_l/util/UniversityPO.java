package org.sdmlib.simple.model.association_l.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_l.University;
import org.sdmlib.simple.model.association_l.util.StudentPO;
import org.sdmlib.simple.model.association_l.Student;
import org.sdmlib.simple.model.association_l.util.UniversityPO;
import org.sdmlib.simple.model.association_l.util.StudentSet;

public class UniversityPO extends PatternObject<UniversityPO, University>
{

    public UniversitySet allMatches()
   {
      this.setDoAllMatches(true);
      
      UniversitySet matches = new UniversitySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((University) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public UniversityPO(){
      newInstance(null);
   }

   public UniversityPO(University... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public UniversityPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public StudentPO createStudentsPO()
   {
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(University.PROPERTY_STUDENTS, result);
      
      return result;
   }

   public StudentPO createStudentsPO(String modifier)
   {
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(modifier);
      super.hasLink(University.PROPERTY_STUDENTS, result);
      
      return result;
   }

   public UniversityPO createStudentsLink(StudentPO tgt)
   {
      return hasLinkConstraint(tgt, University.PROPERTY_STUDENTS);
   }

   public UniversityPO createStudentsLink(StudentPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, University.PROPERTY_STUDENTS, modifier);
   }

   public StudentSet getStudents()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((University) this.getCurrentMatch()).getStudents();
      }
      return null;
   }

}
