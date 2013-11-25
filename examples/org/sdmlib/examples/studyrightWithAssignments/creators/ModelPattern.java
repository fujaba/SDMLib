package org.sdmlib.examples.studyrightWithAssignments.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.examples.studyrightWithAssignments.creators.UniversityPO;
import org.sdmlib.examples.studyrightWithAssignments.University;
import org.sdmlib.examples.studyrightWithAssignments.creators.StudentPO;
import org.sdmlib.examples.studyrightWithAssignments.Student;
import org.sdmlib.examples.studyrightWithAssignments.creators.RoomPO;
import org.sdmlib.examples.studyrightWithAssignments.Room;
import org.sdmlib.examples.studyrightWithAssignments.creators.AssignmentPO;
import org.sdmlib.examples.studyrightWithAssignments.Assignment;

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

   public AssignmentPO hasElementAssignmentPO()
   {
      AssignmentPO value = new AssignmentPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public AssignmentPO hasElementAssignmentPO(Assignment hostGraphObject)
   {
      AssignmentPO value = new AssignmentPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


