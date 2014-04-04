package org.sdmlib.examples.studyright.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyright.Topic;
import org.sdmlib.examples.studyright.creators.TopicSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.studyright.creators.ProfessorPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.studyright.creators.TopicPO;
import org.sdmlib.examples.studyright.Professor;

public class TopicPO extends PatternObject<TopicPO, Topic>
{
   public TopicSet allMatches()
   {
      this.setDoAllMatches(true);

      TopicSet matches = new TopicSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Topic) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }

   public TopicPO hasTitle(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Topic.PROPERTY_TITLE).withTgtValue(value).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public String getTitle()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Topic) getCurrentMatch()).getTitle();
      }
      return null;
   }

   public TopicPO withTitle(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Topic) getCurrentMatch()).setTitle(value);
      }
      return this;
   }

   public ProfessorPO hasProf()
   {
      ProfessorPO result = new ProfessorPO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(Topic.PROPERTY_PROF, result);

      return result;
   }

   public TopicPO hasProf(ProfessorPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Topic.PROPERTY_PROF).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public Professor getProf()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Topic) this.getCurrentMatch()).getProf();
      }
      return null;
   }

   public TopicPO hasTitle(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Topic.PROPERTY_TITLE).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public ProfessorPO createProf()
   {
      return this.startCreate().hasProf().endCreate();
   }

   public TopicPO createTitle(String value)
   {
      this.startCreate().hasTitle(value).endCreate();
      return this;
   }

   public TopicPO createProf(ProfessorPO tgt)
   {
      return this.startCreate().hasProf(tgt).endCreate();
   }

}
