package org.sdmlib.examples.studyright.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyright.Student;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.studyright.creators.UniversityPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.studyright.creators.StudentPO;
import org.sdmlib.examples.studyright.University;
import org.sdmlib.examples.studyright.creators.RoomPO;
import org.sdmlib.examples.studyright.Room;
import org.sdmlib.examples.studyright.creators.StudentSet;

public class StudentPO extends PatternObject
{
   public StudentPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Student.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StudentPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Student) getCurrentMatch()).withName(value);
      }
      return this;
   }
   
   public StudentPO hasMatrNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Student.PROPERTY_MATRNO)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StudentPO withMatrNo(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Student) getCurrentMatch()).withMatrNo(value);
      }
      return this;
   }
   
   public UniversityPO hasUni()
   {
      UniversityPO result = new UniversityPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Student.PROPERTY_UNI)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public StudentPO hasUni(UniversityPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Student.PROPERTY_UNI)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StudentPO withUni(UniversityPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Student) this.getCurrentMatch()).withUni((University) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public RoomPO hasIn()
   {
      RoomPO result = new RoomPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Student.PROPERTY_IN)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public StudentPO hasIn(RoomPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Student.PROPERTY_IN)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StudentPO withIn(RoomPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Student) this.getCurrentMatch()).withIn((Room) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public int getMatrNo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) getCurrentMatch()).getMatrNo();
      }
      return 0;
   }
   
   public University getUni()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getUni();
      }
      return null;
   }
   
   public Room getIn()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Student) this.getCurrentMatch()).getIn();
      }
      return null;
   }
   
}



