package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyrightextends.Lecture;
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

public class LecturePO extends PatternObject
{
   public LecturePO hasTitle(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Lecture.PROPERTY_TITLE)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LecturePO withTitle(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Lecture) getCurrentMatch()).withTitle(value);
      }
      return this;
   }
   
   public RoomPO hasIn()
   {
      RoomPO result = new RoomPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Lecture.PROPERTY_IN)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public LecturePO hasIn(RoomPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Lecture.PROPERTY_IN)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LecturePO withIn(RoomPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Lecture) this.getCurrentMatch()).withIn((Room) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public ProfessorPO hasHas()
   {
      ProfessorPO result = new ProfessorPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Lecture.PROPERTY_HAS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public LecturePO hasHas(ProfessorPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Lecture.PROPERTY_HAS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LecturePO withHas(ProfessorPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Lecture) this.getCurrentMatch()).withHas((Professor) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public StudentPO hasListen()
   {
      StudentPO result = new StudentPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Lecture.PROPERTY_LISTEN)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public LecturePO hasListen(StudentPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Lecture.PROPERTY_LISTEN)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LecturePO withListen(StudentPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Lecture) this.getCurrentMatch()).withListen((Student) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
}


