package org.sdmlib.model.classes.creators;

import org.sdmlib.codegen.creators.StatementEntryPO;
import org.sdmlib.codegen.creators.SymTabEntryPO;
import org.sdmlib.model.classes.ReverseClassModelTest;
import org.sdmlib.model.test.interfaces.Student;
import org.sdmlib.model.test.interfaces.creators.StudentPO;
import org.sdmlib.models.pattern.Pattern;

public class ModelPattern extends Pattern
{
   public ModelPattern()
   {
      super(CreatorCreator.createIdMap("hg"));
   }
   
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public ReverseClassModelTestPO hasElementReverseClassModelTestPO()
   {
      ReverseClassModelTestPO value = new ReverseClassModelTestPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ReverseClassModelTestPO hasElementReverseClassModelTestPO(ReverseClassModelTest hostGraphObject)
   {
      ReverseClassModelTestPO value = new ReverseClassModelTestPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public StudentPO hasElementStudentPO()
   {
      StudentPO value = new StudentPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public StudentPO hasElementStudentPO(Student hostGraphObject)
   {
      StudentPO value = new StudentPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   }

   public SymTabEntryPO hasElementSymTabEntryPO()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public StatementEntryPO hasElementStatementEntryPO()
   {
      // TODO Auto-generated method stub
      return null;
   } 

}


