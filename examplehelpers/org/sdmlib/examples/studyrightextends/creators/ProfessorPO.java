package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyrightextends.Professor;
import org.sdmlib.examples.studyrightextends.creators.ProfessorSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.studyrightextends.creators.LecturePO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.studyrightextends.creators.ProfessorPO;
import org.sdmlib.examples.studyrightextends.Lecture;
import org.sdmlib.examples.studyrightextends.creators.LectureSet;

public class ProfessorPO extends PatternObject<ProfessorPO, Professor>
{
   public ProfessorSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ProfessorSet matches = new ProfessorSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Professor) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public ProfessorPO hasPersNr(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Professor.PROPERTY_PERSNR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
   
   public ProfessorPO withPersNr(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Professor) getCurrentMatch()).setPersNr(value);
      }
      return this;
   }
   
   public ProfessorPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Professor.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Professor) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public ProfessorPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Professor) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public LecturePO hasLecture()
   {
      LecturePO result = new LecturePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Professor.PROPERTY_LECTURE, result);
      
      return result;
   }

   public ProfessorPO hasLecture(LecturePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Professor.PROPERTY_LECTURE)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
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

