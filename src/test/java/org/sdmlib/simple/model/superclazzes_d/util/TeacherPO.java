package org.sdmlib.simple.model.superclazzes_d.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.superclazzes_d.Teacher;

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
}
