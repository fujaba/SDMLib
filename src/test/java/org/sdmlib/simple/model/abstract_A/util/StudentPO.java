package org.sdmlib.simple.model.abstract_A.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.abstract_A.Student;
import org.sdmlib.simple.model.abstract_A.util.HumanPO;
import org.sdmlib.simple.model.abstract_A.Human;
import org.sdmlib.simple.model.abstract_A.util.StudentPO;

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
   public HumanPO createOwnerPO()
   {
      HumanPO result = new HumanPO(new Human[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Student.PROPERTY_OWNER, result);
      
      return result;
   }

   public HumanPO createOwnerPO(String modifier)
   {
      HumanPO result = new HumanPO(new Human[]{});
      
      result.setModifier(modifier);
      super.hasLink(Student.PROPERTY_OWNER, result);
      
      return result;
   }

   public StudentPO createOwnerLink(HumanPO tgt)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_OWNER);
   }

   public StudentPO createOwnerLink(HumanPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Student.PROPERTY_OWNER, modifier);
   }

   public Human getOwner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getOwner();
      }
      return null;
   }

}
