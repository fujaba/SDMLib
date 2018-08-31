package org.sdmlib.test.examples.studyrightWithAssignments.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.studyrightWithAssignments.model.TeachingAssistant;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.RoomPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.TeachingAssistantPO;

public class TeachingAssistantPO extends PatternObject<TeachingAssistantPO, TeachingAssistant>
{

    public TeachingAssistantSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TeachingAssistantSet matches = new TeachingAssistantSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((TeachingAssistant) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public TeachingAssistantPO(){
      newInstance(null);
   }

   public TeachingAssistantPO(TeachingAssistant... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public TeachingAssistantPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public TeachingAssistantPO createCertifiedCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_CERTIFIED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO createCertifiedAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_CERTIFIED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public boolean getCertified()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TeachingAssistant) getCurrentMatch()).isCertified();
      }
      return false;
   }
   
   public TeachingAssistantPO withCertified(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TeachingAssistant) getCurrentMatch()).setCertified(value);
      }
      return this;
   }
   
   public TeachingAssistantPO createAssignmentPointsCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ASSIGNMENTPOINTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO createAssignmentPointsCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ASSIGNMENTPOINTS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO createAssignmentPointsAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ASSIGNMENTPOINTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getAssignmentPoints()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TeachingAssistant) getCurrentMatch()).getAssignmentPoints();
      }
      return 0;
   }
   
   public TeachingAssistantPO withAssignmentPoints(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TeachingAssistant) getCurrentMatch()).setAssignmentPoints(value);
      }
      return this;
   }
   
   public TeachingAssistantPO createCreditsCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_CREDITS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO createCreditsCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_CREDITS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO createCreditsAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_CREDITS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getCredits()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TeachingAssistant) getCurrentMatch()).getCredits();
      }
      return 0;
   }
   
   public TeachingAssistantPO withCredits(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TeachingAssistant) getCurrentMatch()).setCredits(value);
      }
      return this;
   }
   
   public TeachingAssistantPO createIdCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO createIdCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO createIdAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_ID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TeachingAssistant) getCurrentMatch()).getId();
      }
      return null;
   }
   
   public TeachingAssistantPO withId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TeachingAssistant) getCurrentMatch()).setId(value);
      }
      return this;
   }
   
   public TeachingAssistantPO createMotivationCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_MOTIVATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO createMotivationCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_MOTIVATION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO createMotivationAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_MOTIVATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getMotivation()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TeachingAssistant) getCurrentMatch()).getMotivation();
      }
      return 0;
   }
   
   public TeachingAssistantPO withMotivation(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TeachingAssistant) getCurrentMatch()).setMotivation(value);
      }
      return this;
   }
   
   public TeachingAssistantPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TeachingAssistantPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(TeachingAssistant.PROPERTY_NAME)
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
         return ((TeachingAssistant) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public TeachingAssistantPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TeachingAssistant) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public RoomPO createRoomPO()
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TeachingAssistant.PROPERTY_ROOM, result);
      
      return result;
   }

   public RoomPO createRoomPO(String modifier)
   {
      RoomPO result = new RoomPO(new Room[]{});
      
      result.setModifier(modifier);
      super.hasLink(TeachingAssistant.PROPERTY_ROOM, result);
      
      return result;
   }

   public TeachingAssistantPO createRoomLink(RoomPO tgt)
   {
      return hasLinkConstraint(tgt, TeachingAssistant.PROPERTY_ROOM);
   }

   public TeachingAssistantPO createRoomLink(RoomPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, TeachingAssistant.PROPERTY_ROOM, modifier);
   }

   public Room getRoom()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TeachingAssistant) this.getCurrentMatch()).getRoom();
      }
      return null;
   }

}
