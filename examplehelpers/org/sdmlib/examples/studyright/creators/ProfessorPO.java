package org.sdmlib.examples.studyright.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyright.Professor;
import org.sdmlib.examples.studyright.creators.ProfessorSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.studyright.creators.TopicPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.studyright.creators.ProfessorPO;
import org.sdmlib.examples.studyright.Topic;

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
   
   public TopicPO hasTopic()
   {
      TopicPO result = new TopicPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Professor.PROPERTY_TOPIC, result);
      
      return result;
   }

   public ProfessorPO hasTopic(TopicPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Professor.PROPERTY_TOPIC)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Topic getTopic()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Professor) this.getCurrentMatch()).getTopic();
      }
      return null;
   }

   public ProfessorPO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Professor.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public TopicPO createTopic()
   {
      return this.startCreate().hasTopic().endCreate();
   }

}



