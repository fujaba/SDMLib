package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.examples.studyrightextends.Lecture;
import org.sdmlib.examples.studyrightextends.Room;
import org.sdmlib.examples.studyrightextends.University;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;

public class RoomPO extends PatternObject
{
   
   //==========================================================================
   
   public int studentCount()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) getCurrentMatch()).studentCount();
      }
      return 0;
   }

   public RoomPO hasRoomNo(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Room.PROPERTY_ROOMNO)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO withRoomNo(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) getCurrentMatch()).withRoomNo(value);
      }
      return this;
   }
   
   public RoomPO hasCredits(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Room.PROPERTY_CREDITS)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO withCredits(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) getCurrentMatch()).withCredits(value);
      }
      return this;
   }
   
   public RoomPO hasNeighbors()
   {
      RoomPO result = new RoomPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Room.PROPERTY_NEIGHBORS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public RoomPO hasNeighbors(RoomPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Room.PROPERTY_NEIGHBORS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO withNeighbors(RoomPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) this.getCurrentMatch()).withNeighbors((Room) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public RoomPO withoutNeighbors(RoomPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) this.getCurrentMatch()).withoutNeighbors((Room) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public LecturePO hasLecture()
   {
      LecturePO result = new LecturePO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Room.PROPERTY_LECTURE)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public RoomPO hasLecture(LecturePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Room.PROPERTY_LECTURE)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO withLecture(LecturePO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) this.getCurrentMatch()).withLecture((Lecture) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public RoomPO withoutLecture(LecturePO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) this.getCurrentMatch()).withoutLecture((Lecture) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public UniversityPO hasUni()
   {
      UniversityPO result = new UniversityPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Room.PROPERTY_UNI)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public RoomPO hasUni(UniversityPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Room.PROPERTY_UNI)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoomPO withUni(UniversityPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Room) this.getCurrentMatch()).withUni((University) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public String getRoomNo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) getCurrentMatch()).getRoomNo();
      }
      return null;
   }
   
   public int getCredits()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) getCurrentMatch()).getCredits();
      }
      return 0;
   }
   
   public RoomSet getNeighbors()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getNeighbors();
      }
      return null;
   }
   
   public LectureSet getLecture()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getLecture();
      }
      return null;
   }
   
   public University getUni()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getUni();
      }
      return null;
   }
   
}



