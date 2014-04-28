package org.sdmlib.examples.studyrightWithAssignments.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyrightWithAssignments.University;
import org.sdmlib.examples.studyrightWithAssignments.creators.UniversitySet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.studyrightWithAssignments.creators.StudentPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.studyrightWithAssignments.creators.UniversityPO;
import org.sdmlib.examples.studyrightWithAssignments.Student;
import org.sdmlib.examples.studyrightWithAssignments.creators.StudentSet;
import org.sdmlib.examples.studyrightWithAssignments.creators.RoomPO;
import org.sdmlib.examples.studyrightWithAssignments.Room;
import org.sdmlib.examples.studyrightWithAssignments.creators.RoomSet;

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

   public UniversityPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(University.PROPERTY_NAME).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public UniversityPO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(University.PROPERTY_NAME).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public UniversityPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
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

   public StudentPO hasStudents()
   {
      StudentPO result = new StudentPO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(University.PROPERTY_STUDENTS, result);

      return result;
   }

   public StudentPO createStudents()
   {
      return this.startCreate().hasStudents().endCreate();
   }

   public UniversityPO hasStudents(StudentPO tgt)
   {
      return hasLinkConstraint(tgt, University.PROPERTY_STUDENTS);
   }

   public UniversityPO createStudents(StudentPO tgt)
   {
      return this.startCreate().hasStudents(tgt).endCreate();
   }

   public StudentSet getStudents()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((University) this.getCurrentMatch()).getStudents();
      }
      return null;
   }

   public RoomPO hasRooms()
   {
      RoomPO result = new RoomPO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(University.PROPERTY_ROOMS, result);

      return result;
   }

   public RoomPO createRooms()
   {
      return this.startCreate().hasRooms().endCreate();
   }

   public UniversityPO hasRooms(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, University.PROPERTY_ROOMS);
   }

   public UniversityPO createRooms(RoomPO tgt)
   {
      return this.startCreate().hasRooms(tgt).endCreate();
   }

   public RoomSet getRooms()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((University) this.getCurrentMatch()).getRooms();
      }
      return null;
   }

}
