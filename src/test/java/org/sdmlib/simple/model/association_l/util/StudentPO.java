package org.sdmlib.simple.model.association_l.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_l.Lecture;
import org.sdmlib.simple.model.association_l.Student;
import org.sdmlib.simple.model.association_l.University;

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
   public LecturePO createAttendedPO()
   {
      LecturePO result = new LecturePO(new Lecture[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Student.PROPERTY_ATTENDED, result);
      
      return result;
   }

   public LecturePO createAttendedPO(String modifier)
   {
      LecturePO result = new LecturePO(new Lecture[]{});
      
      result.setModifier(modifier);
      super.hasLink(Student.PROPERTY_ATTENDED, result);
      
      return result;
   }

   public StudentPO createAttendedLink(LecturePO tgt)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_ATTENDED);
   }

   public StudentPO createAttendedLink(LecturePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_ATTENDED, modifier);
   }

   public LectureSet getAttended()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getAttended();
      }
      return null;
   }

   public UniversityPO createStudsPO()
   {
      UniversityPO result = new UniversityPO(new University[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Student.PROPERTY_STUDS, result);
      
      return result;
   }

   public UniversityPO createStudsPO(String modifier)
   {
      UniversityPO result = new UniversityPO(new University[]{});
      
      result.setModifier(modifier);
      super.hasLink(Student.PROPERTY_STUDS, result);
      
      return result;
   }

   public StudentPO createStudsLink(UniversityPO tgt)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_STUDS);
   }

   public StudentPO createStudsLink(UniversityPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_STUDS, modifier);
   }

   public University getStuds()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getStuds();
      }
      return null;
   }

}
