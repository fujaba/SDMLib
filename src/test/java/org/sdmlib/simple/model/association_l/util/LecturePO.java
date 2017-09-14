package org.sdmlib.simple.model.association_l.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_l.Lecture;
import org.sdmlib.simple.model.association_l.Student;

public class LecturePO extends PatternObject<LecturePO, Lecture>
{

    public LectureSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LectureSet matches = new LectureSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Lecture) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public LecturePO(){
      newInstance(null);
   }

   public LecturePO(Lecture... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public LecturePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public StudentPO createHasPO()
   {
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Lecture.PROPERTY_HAS, result);
      
      return result;
   }

   public StudentPO createHasPO(String modifier)
   {
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(modifier);
      super.hasLink(Lecture.PROPERTY_HAS, result);
      
      return result;
   }

   public LecturePO createHasLink(StudentPO tgt)
   {
      return hasLinkConstraint(tgt, Lecture.PROPERTY_HAS);
   }

   public LecturePO createHasLink(StudentPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Lecture.PROPERTY_HAS, modifier);
   }

   public StudentSet getHas()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Lecture) this.getCurrentMatch()).getHas();
      }
      return null;
   }

}
