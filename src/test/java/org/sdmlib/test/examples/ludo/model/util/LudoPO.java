package org.sdmlib.test.examples.ludo.model.util;

import java.util.Date;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.ludo.model.Dice;
import org.sdmlib.test.examples.ludo.model.Field;
import org.sdmlib.test.examples.ludo.model.Ludo;
import org.sdmlib.test.examples.ludo.model.Player;
import org.sdmlib.test.examples.ludo.model.util.PlayerPO;
import org.sdmlib.test.examples.ludo.model.util.LudoPO;
import org.sdmlib.test.examples.ludo.model.util.DicePO;
import org.sdmlib.test.examples.ludo.model.util.FieldPO;
import org.sdmlib.models.pattern.Pattern;

public class LudoPO extends PatternObject<LudoPO, Ludo>
{

    public LudoSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LudoSet matches = new LudoSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Ludo) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public LudoPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public LudoPO(Ludo... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public LudoPO hasDate(Date value)
   {
      new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_DATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LudoPO createDate(Date value)
   {
      this.startCreate().hasDate(value).endCreate();
      return this;
   }
   
   public Date getDate()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Ludo) getCurrentMatch()).getDate();
      }
      return null;
   }
   
   public LudoPO withDate(Date value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Ludo) getCurrentMatch()).setDate(value);
      }
      return this;
   }
   
   public PlayerPO hasPlayers()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Ludo.PROPERTY_PLAYERS, result);
      
      return result;
   }

   public PlayerPO createPlayers()
   {
      return this.startCreate().hasPlayers().endCreate();
   }

   public LudoPO hasPlayers(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Ludo.PROPERTY_PLAYERS);
   }

   public LudoPO createPlayers(PlayerPO tgt)
   {
      return this.startCreate().hasPlayers(tgt).endCreate();
   }

   public PlayerSet getPlayers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Ludo) this.getCurrentMatch()).getPlayers();
      }
      return null;
   }

   public DicePO hasDice()
   {
      DicePO result = new DicePO(new Dice[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Ludo.PROPERTY_DICE, result);
      
      return result;
   }

   public DicePO createDice()
   {
      return this.startCreate().hasDice().endCreate();
   }

   public LudoPO hasDice(DicePO tgt)
   {
      return hasLinkConstraint(tgt, Ludo.PROPERTY_DICE);
   }

   public LudoPO createDice(DicePO tgt)
   {
      return this.startCreate().hasDice(tgt).endCreate();
   }

   public Dice getDice()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Ludo) this.getCurrentMatch()).getDice();
      }
      return null;
   }

   public FieldPO hasFields()
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Ludo.PROPERTY_FIELDS, result);
      
      return result;
   }

   public FieldPO createFields()
   {
      return this.startCreate().hasFields().endCreate();
   }

   public LudoPO hasFields(FieldPO tgt)
   {
      return hasLinkConstraint(tgt, Ludo.PROPERTY_FIELDS);
   }

   public LudoPO createFields(FieldPO tgt)
   {
      return this.startCreate().hasFields(tgt).endCreate();
   }

   public FieldSet getFields()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Ludo) this.getCurrentMatch()).getFields();
      }
      return null;
   }

   public LudoPO filterDate(Date value)
   {
      new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_DATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO filterPlayers()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Ludo.PROPERTY_PLAYERS, result);
      
      return result;
   }

   public LudoPO filterPlayers(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Ludo.PROPERTY_PLAYERS);
   }

   public DicePO filterDice()
   {
      DicePO result = new DicePO(new Dice[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Ludo.PROPERTY_DICE, result);
      
      return result;
   }

   public LudoPO filterDice(DicePO tgt)
   {
      return hasLinkConstraint(tgt, Ludo.PROPERTY_DICE);
   }

   public FieldPO filterFields()
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Ludo.PROPERTY_FIELDS, result);
      
      return result;
   }

   public LudoPO filterFields(FieldPO tgt)
   {
      return hasLinkConstraint(tgt, Ludo.PROPERTY_FIELDS);
   }


   public LudoPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public LudoPO createDateCondition(Date value)
   {
      new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_DATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LudoPO createDateAssignment(Date value)
   {
      new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_DATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createPlayersPO()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Ludo.PROPERTY_PLAYERS, result);
      
      return result;
   }

   public PlayerPO createPlayersPO(String modifier)
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(modifier);
      super.hasLink(Ludo.PROPERTY_PLAYERS, result);
      
      return result;
   }

   public LudoPO createPlayersLink(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Ludo.PROPERTY_PLAYERS);
   }

   public LudoPO createPlayersLink(PlayerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Ludo.PROPERTY_PLAYERS, modifier);
   }

   public DicePO createDicePO()
   {
      DicePO result = new DicePO(new Dice[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Ludo.PROPERTY_DICE, result);
      
      return result;
   }

   public DicePO createDicePO(String modifier)
   {
      DicePO result = new DicePO(new Dice[]{});
      
      result.setModifier(modifier);
      super.hasLink(Ludo.PROPERTY_DICE, result);
      
      return result;
   }

   public LudoPO createDiceLink(DicePO tgt)
   {
      return hasLinkConstraint(tgt, Ludo.PROPERTY_DICE);
   }

   public LudoPO createDiceLink(DicePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Ludo.PROPERTY_DICE, modifier);
   }

   public FieldPO createFieldsPO()
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Ludo.PROPERTY_FIELDS, result);
      
      return result;
   }

   public FieldPO createFieldsPO(String modifier)
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(modifier);
      super.hasLink(Ludo.PROPERTY_FIELDS, result);
      
      return result;
   }

   public LudoPO createFieldsLink(FieldPO tgt)
   {
      return hasLinkConstraint(tgt, Ludo.PROPERTY_FIELDS);
   }

   public LudoPO createFieldsLink(FieldPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Ludo.PROPERTY_FIELDS, modifier);
   }

}
