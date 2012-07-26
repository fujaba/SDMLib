package org.sdmlib.examples.studyright.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyright.Topic;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.studyright.creators.ProfessorPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.studyright.creators.TopicPO;
import org.sdmlib.examples.studyright.Professor;

public class TopicPO extends PatternObject
{
   public TopicPO hasTitle(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Topic.PROPERTY_TITLE)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public TopicPO withTitle(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Topic) getCurrentMatch()).withTitle(value);
      }
      return this;
   }
   
   public ProfessorPO hasProf()
   {
      ProfessorPO result = new ProfessorPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Topic.PROPERTY_PROF)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public TopicPO hasProf(ProfessorPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Topic.PROPERTY_PROF)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public TopicPO withProf(ProfessorPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Topic) this.getCurrentMatch()).withProf((Professor) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
}


