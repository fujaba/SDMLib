package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyright.model.Lecture;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.examples.studyright.model.util.RoomPO;
import org.sdmlib.examples.studyright.model.util.LecturePO;
import org.sdmlib.examples.studyright.model.Room;
import org.sdmlib.examples.studyright.model.util.ProfessorPO;
import org.sdmlib.examples.studyright.model.Professor;
import org.sdmlib.examples.studyright.model.util.StudentPO;
import org.sdmlib.examples.studyright.model.Student;

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


   public LecturePO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public LecturePO(Lecture... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public LecturePO hasTitle(String value)
   {
      new AttributeConstraint()
      .withAttrName(Lecture.PROPERTY_TITLE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LecturePO hasTitle(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Lecture.PROPERTY_TITLE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LecturePO createTitle(String value)
   {
      this.startCreate().hasTitle(value).endCreate();
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
      RoomPO result = new RoomPO(new org.sdmlib.examples.studyright.model.Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Lecture.PROPERTY_IN, result);
      
      return result;
   }

   public RoomPO createIn()
   {
      return this.startCreate().hasIn().endCreate();
   }

   public LecturePO hasIn(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Lecture.PROPERTY_IN);
   }

   public LecturePO createIn(RoomPO tgt)
   {
      return this.startCreate().hasIn(tgt).endCreate();
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
      ProfessorPO result = new ProfessorPO(new org.sdmlib.examples.studyright.model.Professor[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Lecture.PROPERTY_HAS, result);
      
      return result;
   }

   public ProfessorPO createHas()
   {
      return this.startCreate().hasHas().endCreate();
   }

   public LecturePO hasHas(ProfessorPO tgt)
   {
      return hasLinkConstraint(tgt, Lecture.PROPERTY_HAS);
   }

   public LecturePO createHas(ProfessorPO tgt)
   {
      return this.startCreate().hasHas(tgt).endCreate();
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
      StudentPO result = new StudentPO(new org.sdmlib.examples.studyright.model.Student[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Lecture.PROPERTY_LISTEN, result);
      
      return result;
   }

   public StudentPO createListen()
   {
      return this.startCreate().hasListen().endCreate();
   }

   public LecturePO hasListen(StudentPO tgt)
   {
      return hasLinkConstraint(tgt, Lecture.PROPERTY_LISTEN);
   }

   public LecturePO createListen(StudentPO tgt)
   {
      return this.startCreate().hasListen(tgt).endCreate();
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
