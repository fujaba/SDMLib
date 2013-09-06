package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyrightextends.Lecture;
import org.sdmlib.examples.studyrightextends.creators.LectureSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.studyrightextends.creators.RoomPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.studyrightextends.creators.LecturePO;
import org.sdmlib.examples.studyrightextends.Room;
import org.sdmlib.examples.studyrightextends.creators.ProfessorPO;
import org.sdmlib.examples.studyrightextends.Professor;
import org.sdmlib.examples.studyrightextends.creators.StudentPO;
import org.sdmlib.examples.studyrightextends.Student;

public class LecturePO extends PatternObject<LecturePO, Lecture>
{
   public LectureSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LectureSet matches = new LectureSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Lecture) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public LecturePO hasTitle(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Lecture.PROPERTY_TITLE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getTitle()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Lecture) getCurrentMatch()).getTitle();
      }
      return null;
   }
   
   public LecturePO withTitle(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Lecture) getCurrentMatch()).setTitle(value);
      }
      return this;
   }
   
   public RoomPO hasIn()
   {
      RoomPO result = new RoomPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Lecture.PROPERTY_IN, result);
      
      return result;
   }

   public LecturePO hasIn(RoomPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Lecture.PROPERTY_IN)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Room getIn()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Lecture) this.getCurrentMatch()).getIn();
      }
      return null;
   }

   public ProfessorPO hasHas()
   {
      ProfessorPO result = new ProfessorPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Lecture.PROPERTY_HAS, result);
      
      return result;
   }

   public LecturePO hasHas(ProfessorPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Lecture.PROPERTY_HAS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Professor getHas()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Lecture) this.getCurrentMatch()).getHas();
      }
      return null;
   }

   public StudentPO hasListen()
   {
      StudentPO result = new StudentPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Lecture.PROPERTY_LISTEN, result);
      
      return result;
   }

   public LecturePO hasListen(StudentPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Lecture.PROPERTY_LISTEN)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Student getListen()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Lecture) this.getCurrentMatch()).getListen();
      }
      return null;
   }

}

