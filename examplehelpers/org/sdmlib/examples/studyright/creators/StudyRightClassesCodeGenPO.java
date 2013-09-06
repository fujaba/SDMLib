package org.sdmlib.examples.studyright.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyright.StudyRightClassesCodeGen;
import org.sdmlib.examples.studyright.creators.StudyRightClassesCodeGenSet;

public class StudyRightClassesCodeGenPO extends PatternObject<StudyRightClassesCodeGenPO, StudyRightClassesCodeGen>
{
   public StudyRightClassesCodeGenSet allMatches()
   {
      this.setDoAllMatches(true);
      
      StudyRightClassesCodeGenSet matches = new StudyRightClassesCodeGenSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((StudyRightClassesCodeGen) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   
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
   
   public void testStudyRightObjectStoryboards()
   {
      if (this.getPattern().getHasMatch())
      {
          ((StudyRightClassesCodeGen) getCurrentMatch()).testStudyRightObjectStoryboards();
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

