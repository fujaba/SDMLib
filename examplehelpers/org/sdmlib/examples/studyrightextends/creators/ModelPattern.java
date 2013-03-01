package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.examples.studyright.StudyRightClassesCodeGen;
import org.sdmlib.examples.studyright.creators.StudyRightClassesCodeGenPO;
import org.sdmlib.examples.studyrightextends.Female;
import org.sdmlib.examples.studyrightextends.Lecture;
import org.sdmlib.examples.studyrightextends.Professor;
import org.sdmlib.examples.studyrightextends.Room;
import org.sdmlib.examples.studyrightextends.Student;
import org.sdmlib.examples.studyrightextends.University;
import org.sdmlib.models.pattern.Pattern;

public class ModelPattern extends Pattern
{
   public ModelPattern()
   {
      super(CreatorCreator.createIdMap("hg"));
   }
   
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public LecturePO hasElementLecturePO()
   {
      LecturePO value = new LecturePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public LecturePO hasElementLecturePO(Lecture hostGraphObject)
   {
      LecturePO value = new LecturePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public RoomPO hasElementRoomPO()
   {
      RoomPO value = new RoomPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public RoomPO hasElementRoomPO(Room hostGraphObject)
   {
      RoomPO value = new RoomPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public UniversityPO hasElementUniversityPO()
   {
      UniversityPO value = new UniversityPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public UniversityPO hasElementUniversityPO(University hostGraphObject)
   {
      UniversityPO value = new UniversityPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public StudyRightClassesCodeGenPO hasElementStudyRightClassesCodeGenPO()
   {
      StudyRightClassesCodeGenPO value = new StudyRightClassesCodeGenPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public StudyRightClassesCodeGenPO hasElementStudyRightClassesCodeGenPO(StudyRightClassesCodeGen hostGraphObject)
   {
      StudyRightClassesCodeGenPO value = new StudyRightClassesCodeGenPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public FemalePO hasElementFemalePO()
   {
      FemalePO value = new FemalePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public FemalePO hasElementFemalePO(Female hostGraphObject)
   {
      FemalePO value = new FemalePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ProfessorPO hasElementProfessorPO()
   {
      ProfessorPO value = new ProfessorPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ProfessorPO hasElementProfessorPO(Professor hostGraphObject)
   {
      ProfessorPO value = new ProfessorPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public StudentPO hasElementStudentPO()
   {
      StudentPO value = new StudentPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public StudentPO hasElementStudentPO(Student hostGraphObject)
   {
      StudentPO value = new StudentPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


