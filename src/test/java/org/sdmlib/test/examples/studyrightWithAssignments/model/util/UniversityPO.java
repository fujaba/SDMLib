package org.sdmlib.test.examples.studyrightWithAssignments.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.PresidentPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.President;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.UniversityPO;
   /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsStoryboards.java'>StudyRightWithAssignmentsStoryboards.java</a>
 */
   public class UniversityPO extends PatternObject<UniversityPO, University>
{

    public UniversitySet allMatches()
   {
      this.setDoAllMatches(true);
      
      UniversitySet matches = new UniversitySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((University) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsStoryboards.java'>StudyRightWithAssignmentsStoryboards.java</a>
 */
   public UniversityPO(){
      newInstance(null);
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsStoryboards.java'>StudyRightWithAssignmentsStoryboards.java</a>
 */
   public UniversityPO(University... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsStoryboards.java'>StudyRightWithAssignmentsStoryboards.java</a>
 */
   public UniversityPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public UniversityPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(University.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UniversityPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(University.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UniversityPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(University.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((University) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public UniversityPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((University) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public StudentPO createStudentsPO()
   {
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(University.PROPERTY_STUDENTS, result);
      
      return result;
   }

   public StudentPO createStudentsPO(String modifier)
   {
      StudentPO result = new StudentPO(new Student[]{});
      
      result.setModifier(modifier);
      super.hasLink(University.PROPERTY_STUDENTS, result);
      
      return result;
   }

   public UniversityPO createStudentsLink(StudentPO tgt)
   {
      return hasLinkConstraint(tgt, University.PROPERTY_STUDENTS);
   }

   public UniversityPO createStudentsLink(StudentPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, University.PROPERTY_STUDENTS, modifier);
   }

   public StudentSet getStudents()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((University) this.getCurrentMatch()).getStudents();
      }
      return null;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsStoryboards.java'>StudyRightWithAssignmentsStoryboards.java</a>
 */
   public RoomPO createRoomsPO()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(University.PROPERTY_ROOMS, result);
      
      return result;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsStoryboards.java'>StudyRightWithAssignmentsStoryboards.java</a>
 */
   public RoomPO createRoomsPO(String modifier)
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(modifier);
      super.hasLink(University.PROPERTY_ROOMS, result);
      
      return result;
   }

   public UniversityPO createRoomsLink(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, University.PROPERTY_ROOMS);
   }

   public UniversityPO createRoomsLink(RoomPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, University.PROPERTY_ROOMS, modifier);
   }

   public RoomSet getRooms()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((University) this.getCurrentMatch()).getRooms();
      }
      return null;
   }

   public PresidentPO createPresidentPO()
   {
      PresidentPO result = new PresidentPO(new President[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(University.PROPERTY_PRESIDENT, result);
      
      return result;
   }

   public PresidentPO createPresidentPO(String modifier)
   {
      PresidentPO result = new PresidentPO(new President[]{});
      
      result.setModifier(modifier);
      super.hasLink(University.PROPERTY_PRESIDENT, result);
      
      return result;
   }

   public UniversityPO createPresidentLink(PresidentPO tgt)
   {
      return hasLinkConstraint(tgt, University.PROPERTY_PRESIDENT);
   }

   public UniversityPO createPresidentLink(PresidentPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, University.PROPERTY_PRESIDENT, modifier);
   }

   public President getPresident()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((University) this.getCurrentMatch()).getPresident();
      }
      return null;
   }

}
