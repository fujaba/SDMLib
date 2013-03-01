package org.sdmlib.examples.studyright.creators;

import org.sdmlib.examples.studyright.StudyRightClassesCodeGen;
import org.sdmlib.models.pattern.PatternObject;

public class StudyRightClassesCodeGenPO extends PatternObject
{
   
   //==========================================================================
   
   public void testStudyRightOneToOneAssoc()
   {
      if (this.getPattern().getHasMatch())
      {
          ((StudyRightClassesCodeGen) getCurrentMatch()).testStudyRightOneToOneAssoc();
      }
   }

   
   //==========================================================================
   
   public void testStudyRightClassesCodeGen()
   {
      if (this.getPattern().getHasMatch())
      {
          ((StudyRightClassesCodeGen) getCurrentMatch()).testStudyRightClassesCodeGen();
      }
   }

   
   //==========================================================================
   
   public void testStudyRightObjectScenarios()
   {
      if (this.getPattern().getHasMatch())
      {
          ((StudyRightClassesCodeGen) getCurrentMatch()).testStudyRightObjectScenarios();
      }
   }

   
   //==========================================================================
   
   public void testStudyRightExtendsReverseClassModel()
   {
      if (this.getPattern().getHasMatch())
      {
          ((StudyRightClassesCodeGen) getCurrentMatch()).testStudyRightExtendsReverseClassModel();
      }
   }

   
   //==========================================================================
   
   public void testStudyRightReverseClassModel()
   {
      if (this.getPattern().getHasMatch())
      {
          ((StudyRightClassesCodeGen) getCurrentMatch()).testStudyRightReverseClassModel();
      }
   }

}


