package org.sdmlib.examples.studyright.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyright.StudyRightClassesCodeGen;

public class StudyRightClassesCodeGenSet extends LinkedHashSet<StudyRightClassesCodeGen>
{
   
   //==========================================================================
   
   public StudyRightClassesCodeGenSet testStudyRightOneToOneAssoc()
   {
      for (StudyRightClassesCodeGen obj : this)
      {
         obj.testStudyRightOneToOneAssoc();
      }
      return this;
   }

   
   //==========================================================================
   
   public StudyRightClassesCodeGenSet testStudyRightClassesCodeGen()
   {
      for (StudyRightClassesCodeGen obj : this)
      {
         obj.testStudyRightClassesCodeGen();
      }
      return this;
   }

   
   //==========================================================================
   
   public StudyRightClassesCodeGenSet testStudyRightObjectScenarios()
   {
      for (StudyRightClassesCodeGen obj : this)
      {
         obj.testStudyRightObjectScenarios();
      }
      return this;
   }

   
   //==========================================================================
   
   public StudyRightClassesCodeGenSet testStudyRightExtendsReverseClassModel()
   {
      for (StudyRightClassesCodeGen obj : this)
      {
         obj.testStudyRightExtendsReverseClassModel();
      }
      return this;
   }

   
   //==========================================================================
   
   public StudyRightClassesCodeGenSet testStudyRightReverseClassModel()
   {
      for (StudyRightClassesCodeGen obj : this)
      {
         obj.testStudyRightReverseClassModel();
      }
      return this;
   }

}

