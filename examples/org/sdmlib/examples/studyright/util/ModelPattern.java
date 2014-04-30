package org.sdmlib.examples.studyright.util;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.examples.studyright.util.AssignmentPO;
import org.sdmlib.examples.studyright.Assignment;

public class ModelPattern extends Pattern
{
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public AssignmentPO hasElementAssignmentPO()
   {
      AssignmentPO value = new AssignmentPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public AssignmentPO hasElementAssignmentPO(Assignment hostGraphObject)
   {
      AssignmentPO value = new AssignmentPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


