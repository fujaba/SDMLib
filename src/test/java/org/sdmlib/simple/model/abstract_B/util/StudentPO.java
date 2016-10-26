package org.sdmlib.simple.model.abstract_B.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.abstract_B.Student;
import org.sdmlib.simple.model.abstract_B.util.FlowerPO;
import org.sdmlib.simple.model.abstract_B.Human;
import org.sdmlib.simple.model.abstract_B.Flower;
import org.sdmlib.simple.model.abstract_B.util.StudentPO;

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
   public FlowerPO createHasPO()
   {
      FlowerPO result = new FlowerPO(new Flower[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Human.PROPERTY_HAS, result);
      
      return result;
   }

   public FlowerPO createHasPO(String modifier)
   {
      FlowerPO result = new FlowerPO(new Flower[]{});
      
      result.setModifier(modifier);
      super.hasLink(Human.PROPERTY_HAS, result);
      
      return result;
   }

   public StudentPO createHasLink(FlowerPO tgt)
   {
      return hasLinkConstraint(tgt, Human.PROPERTY_HAS);
   }

   public StudentPO createHasLink(FlowerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Human.PROPERTY_HAS, modifier);
   }

   public Flower getHas()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Human) this.getCurrentMatch()).getHas();
      }
      return null;
   }

}
