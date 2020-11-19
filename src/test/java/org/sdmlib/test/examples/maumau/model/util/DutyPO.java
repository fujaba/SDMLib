package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.maumau.model.Duty;
import org.sdmlib.test.examples.maumau.model.DutyType;
import org.sdmlib.test.examples.maumau.model.Player;

public class DutyPO extends PatternObject<DutyPO, Duty>
{

    public DutySet allMatches()
   {
      this.setDoAllMatches(true);
      
      DutySet matches = new DutySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Duty) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public DutyPO(){
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public DutyPO(Duty... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public DutyPO hasType(DutyType value)
   {
      new AttributeConstraint()
      .withAttrName(Duty.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public DutyPO createType(DutyType value)
   {
      this.startCreate().hasType(value).endCreate();
      return this;
   }
   
   public DutyType getType()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Duty) getCurrentMatch()).getType();
      }
      return null;
   }
   
   public DutyPO withType(DutyType value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Duty) getCurrentMatch()).setType(value);
      }
      return this;
   }
   
   public DutyPO hasNumber(int value)
   {
      new AttributeConstraint()
      .withAttrName(Duty.PROPERTY_NUMBER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public DutyPO hasNumber(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Duty.PROPERTY_NUMBER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public DutyPO createNumber(int value)
   {
      this.startCreate().hasNumber(value).endCreate();
      return this;
   }
   
   public int getNumber()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Duty) getCurrentMatch()).getNumber();
      }
      return 0;
   }
   
   public DutyPO withNumber(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Duty) getCurrentMatch()).setNumber(value);
      }
      return this;
   }
   
   public PlayerPO hasPlayer()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Duty.PROPERTY_PLAYER, result);
      
      return result;
   }

   public PlayerPO createPlayer()
   {
      return this.startCreate().hasPlayer().endCreate();
   }

   public DutyPO hasPlayer(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Duty.PROPERTY_PLAYER);
   }

   public DutyPO createPlayer(PlayerPO tgt)
   {
      return this.startCreate().hasPlayer(tgt).endCreate();
   }

   public Player getPlayer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Duty) this.getCurrentMatch()).getPlayer();
      }
      return null;
   }

   public DutyPO filterType(DutyType value)
   {
      new AttributeConstraint()
      .withAttrName(Duty.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public DutyPO filterNumber(int value)
   {
      new AttributeConstraint()
      .withAttrName(Duty.PROPERTY_NUMBER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public DutyPO filterNumber(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Duty.PROPERTY_NUMBER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO filterPlayer()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Duty.PROPERTY_PLAYER, result);
      
      return result;
   }

   public DutyPO filterPlayer(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Duty.PROPERTY_PLAYER);
   }


   public DutyPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public DutyPO createNumberCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Duty.PROPERTY_NUMBER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public DutyPO createNumberCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Duty.PROPERTY_NUMBER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public DutyPO createNumberAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Duty.PROPERTY_NUMBER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public DutyPO createTypeCondition(DutyType value)
   {
      new AttributeConstraint()
      .withAttrName(Duty.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public DutyPO createTypeAssignment(DutyType value)
   {
      new AttributeConstraint()
      .withAttrName(Duty.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createPlayerPO()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Duty.PROPERTY_PLAYER, result);
      
      return result;
   }

   public PlayerPO createPlayerPO(String modifier)
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(modifier);
      super.hasLink(Duty.PROPERTY_PLAYER, result);
      
      return result;
   }

   public DutyPO createPlayerLink(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Duty.PROPERTY_PLAYER);
   }

   public DutyPO createPlayerLink(PlayerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Duty.PROPERTY_PLAYER, modifier);
   }

}
