package org.sdmlib.test.examples.studyrightWithAssignments.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Assignment;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.RoomPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.AssignmentPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.StudentPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.StudentSet;

public class AssignmentPO extends PatternObject<AssignmentPO, Assignment>
{

    public AssignmentSet allMatches()
   {
      this.setDoAllMatches(true);
      
      AssignmentSet matches = new AssignmentSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Assignment) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public AssignmentPO(){
      newInstance(null);
   }

   public AssignmentPO(Assignment... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public AssignmentPO filterContent(String value)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_CONTENT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AssignmentPO filterContent(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_CONTENT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AssignmentPO createContent(String value)
   {
      this.startCreate().filterContent(value).endCreate();
      return this;
   }
   
   public String getContent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Assignment) getCurrentMatch()).getContent();
      }
      return null;
   }
   
   public AssignmentPO withContent(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Assignment) getCurrentMatch()).setContent(value);
      }
      return this;
   }
   
   public AssignmentPO filterPoints(int value)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_POINTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AssignmentPO filterPoints(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_POINTS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AssignmentPO createPoints(int value)
   {
      this.startCreate().filterPoints(value).endCreate();
      return this;
   }
   
   public int getPoints()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Assignment) getCurrentMatch()).getPoints();
      }
      return 0;
   }
   
   public AssignmentPO withPoints(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Assignment) getCurrentMatch()).setPoints(value);
      }
      return this;
   }
   
   public RoomPO filterRoom()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Assignment.PROPERTY_ROOM, result);
      
      return result;
   }

   public RoomPO createRoom()
   {
      return this.startCreate().filterRoom().endCreate();
   }

   public AssignmentPO filterRoom(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, Assignment.PROPERTY_ROOM);
   }

   public AssignmentPO createRoom(RoomPO tgt)
   {
      return this.startCreate().filterRoom(tgt).endCreate();
   }

   public Room getRoom()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Assignment) this.getCurrentMatch()).getRoom();
      }
      return null;
   }

   public StudentPO filterStudents()
   {
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Assignment.PROPERTY_STUDENTS, result);
      
      return result;
   }

   public StudentPO createStudents()
   {
      return this.startCreate().filterStudents().endCreate();
   }

   public AssignmentPO filterStudents(StudentPO tgt)
   {
      return hasLinkConstraint(tgt, Assignment.PROPERTY_STUDENTS);
   }

   public AssignmentPO createStudents(StudentPO tgt)
   {
      return this.startCreate().filterStudents(tgt).endCreate();
   }

   public StudentSet<Student> getStudents()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Assignment) this.getCurrentMatch()).getStudents();
      }
      return null;
   }

}
