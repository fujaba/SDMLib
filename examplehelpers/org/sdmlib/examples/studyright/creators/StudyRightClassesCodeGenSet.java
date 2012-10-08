package org.sdmlib.examples.studyright.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyright.StudyRightClassesCodeGen;
import org.sdmlib.models.modelsets.StringList;

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



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (StudyRightClassesCodeGen elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public StudyRightClassesCodeGenSet with(StudyRightClassesCodeGen value)
   {
      this.add(value);
      return this;
   }
   
   public StudyRightClassesCodeGenSet without(StudyRightClassesCodeGen value)
   {
      this.remove(value);
      return this;
   }
}


