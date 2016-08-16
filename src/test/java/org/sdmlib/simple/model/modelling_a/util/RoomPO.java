package org.sdmlib.simple.model.modelling_a.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.modelling_a.Room;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.simple.model.modelling_a.util.PersonPO;
import org.sdmlib.simple.model.modelling_a.Person;
import org.sdmlib.simple.model.modelling_a.util.RoomPO;
import org.sdmlib.simple.model.modelling_a.util.PersonSet;
import org.sdmlib.simple.model.modelling_a.util.PupilPO;
import org.sdmlib.simple.model.modelling_a.Pupil;
import org.sdmlib.simple.model.modelling_a.util.PupilSet;
import org.sdmlib.simple.model.modelling_a.util.TeacherPO;
import org.sdmlib.simple.model.modelling_a.Teacher;

public class RoomPO extends PatternObject<RoomPO, Room>
{

    public RoomSet allMatches()
   {
      this.setDoAllMatches(true);
      
      RoomSet matches = new RoomSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Room) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public RoomPO(){
      newInstance(null);
   }

   public RoomPO(Room... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public RoomPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public RoomPO createNumberCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_NUMBER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RoomPO createNumberCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_NUMBER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RoomPO createNumberAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Room.PROPERTY_NUMBER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getNumber()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) getCurrentMatch()).getNumber();
      }
      return 0;
   }
   
   public RoomPO withNumber(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) getCurrentMatch()).setNumber(value);
      }
      return this;
   }
   
   public PersonPO createPersonsPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_PERSONS, result);
      
      return result;
   }

   public PersonPO createPersonsPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(Room.PROPERTY_PERSONS, result);
      
      return result;
   }

   public RoomPO createPersonsLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_PERSONS);
   }

   public RoomPO createPersonsLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_PERSONS, modifier);
   }

   public PersonSet getPersons()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getPersons();
      }
      return null;
   }

   public PupilPO createCurrentPupilsPO()
   {
      PupilPO result = new PupilPO(new Pupil[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_CURRENTPUPILS, result);
      
      return result;
   }

   public PupilPO createCurrentPupilsPO(String modifier)
   {
      PupilPO result = new PupilPO(new Pupil[]{});
      
      result.setModifier(modifier);
      super.hasLink(Room.PROPERTY_CURRENTPUPILS, result);
      
      return result;
   }

   public RoomPO createCurrentPupilsLink(PupilPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_CURRENTPUPILS);
   }

   public RoomPO createCurrentPupilsLink(PupilPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_CURRENTPUPILS, modifier);
   }

   public PupilSet getCurrentPupils()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getCurrentPupils();
      }
      return null;
   }

   public TeacherPO createCurrentTeacherPO()
   {
      TeacherPO result = new TeacherPO(new Teacher[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Room.PROPERTY_CURRENTTEACHER, result);
      
      return result;
   }

   public TeacherPO createCurrentTeacherPO(String modifier)
   {
      TeacherPO result = new TeacherPO(new Teacher[]{});
      
      result.setModifier(modifier);
      super.hasLink(Room.PROPERTY_CURRENTTEACHER, result);
      
      return result;
   }

   public RoomPO createCurrentTeacherLink(TeacherPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_CURRENTTEACHER);
   }

   public RoomPO createCurrentTeacherLink(TeacherPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_CURRENTTEACHER, modifier);
   }

   public Teacher getCurrentTeacher()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getCurrentTeacher();
      }
      return null;
   }

}
