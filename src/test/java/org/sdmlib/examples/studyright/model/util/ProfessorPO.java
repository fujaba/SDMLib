package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.examples.studyright.model.Professor;
import org.sdmlib.examples.studyright.model.Topic;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

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


   public ProfessorPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ProfessorPO(Professor... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ProfessorPO hasPersNr(int value)
   {
      new AttributeConstraint()
      .withAttrName(Professor.PROPERTY_PERSNR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ProfessorPO hasPersNr(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Professor.PROPERTY_PERSNR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ProfessorPO createPersNr(int value)
   {
      this.startCreate().hasPersNr(value).endCreate();
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
      new AttributeConstraint()
      .withAttrName(Professor.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ProfessorPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Professor.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ProfessorPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
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
      LecturePO result = new LecturePO(new org.sdmlib.examples.studyright.model.Lecture[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Professor.PROPERTY_LECTURE, result);
      
      return result;
   }

   public LecturePO createLecture()
   {
      return this.startCreate().hasLecture().endCreate();
   }

   public ProfessorPO hasLecture(LecturePO tgt)
   {
      return hasLinkConstraint(tgt, Professor.PROPERTY_LECTURE);
   }

   public ProfessorPO createLecture(LecturePO tgt)
   {
      return this.startCreate().hasLecture(tgt).endCreate();
   }

   public LectureSet getLecture()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Professor) this.getCurrentMatch()).getLecture();
      }
      return null;
   }

   public TopicPO hasTopic()
   {
      TopicPO result = new TopicPO(new Topic[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Professor.PROPERTY_TOPIC, result);
      
      return result;
   }

   public TopicPO createTopic()
   {
      return this.startCreate().hasTopic().endCreate();
   }

   public ProfessorPO hasTopic(TopicPO tgt)
   {
      return hasLinkConstraint(tgt, Professor.PROPERTY_TOPIC);
   }

   public ProfessorPO createTopic(TopicPO tgt)
   {
      return this.startCreate().hasTopic(tgt).endCreate();
   }

   public Topic getTopic()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Professor) this.getCurrentMatch()).getTopic();
      }
      return null;
   }

}
