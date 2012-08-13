package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyrightextends.Professor;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.studyrightextends.creators.LecturePO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.studyrightextends.creators.ProfessorPO;
import org.sdmlib.examples.studyrightextends.Lecture;
import org.sdmlib.examples.studyrightextends.creators.ProfessorSet;
import org.sdmlib.examples.studyrightextends.creators.LectureSet;

public class ProfessorPO extends PatternObject
{
   public ProfessorPO hasPersNr(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Professor.PROPERTY_PERSNR)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ProfessorPO withPersNr(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Professor) getCurrentMatch()).withPersNr(value);
      }
      return this;
   }
   
   public ProfessorPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Professor.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ProfessorPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Professor) getCurrentMatch()).withName(value);
      }
      return this;
   }
   
   public LecturePO hasLecture()
   {
      LecturePO result = new LecturePO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Professor.PROPERTY_LECTURE)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public ProfessorPO hasLecture(LecturePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Professor.PROPERTY_LECTURE)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ProfessorPO withLecture(LecturePO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Professor) this.getCurrentMatch()).withLecture((Lecture) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public ProfessorPO withoutLecture(LecturePO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Professor) this.getCurrentMatch()).withoutLecture((Lecture) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public int getPersNr()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Professor) getCurrentMatch()).getPersNr();
      }
      return 0;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Professor) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public LectureSet getLecture()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Professor) this.getCurrentMatch()).getLecture();
      }
      return null;
   }
   
}



