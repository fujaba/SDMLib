package org.sdmlib.examples.studyright.creators;

import org.sdmlib.examples.studyright.Professor;
import org.sdmlib.examples.studyright.Room;
import org.sdmlib.examples.studyright.Student;
import org.sdmlib.examples.studyright.Topic;
import org.sdmlib.examples.studyright.University;
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

   public TopicPO hasElementTopicPO()
   {
      TopicPO value = new TopicPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public TopicPO hasElementTopicPO(Topic hostGraphObject)
   {
      TopicPO value = new TopicPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}



