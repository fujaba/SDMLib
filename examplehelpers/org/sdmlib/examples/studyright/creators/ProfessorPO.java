package org.sdmlib.examples.studyright.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyright.Professor;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.studyright.creators.TopicPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.studyright.creators.ProfessorPO;
import org.sdmlib.examples.studyright.Topic;

public class ProfessorPO extends PatternObject
{
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
   
   public TopicPO hasTopic()
   {
      TopicPO result = new TopicPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Professor.PROPERTY_TOPIC)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public ProfessorPO hasTopic(TopicPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Professor.PROPERTY_TOPIC)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ProfessorPO withTopic(TopicPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Professor) this.getCurrentMatch()).withTopic((Topic) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
}


