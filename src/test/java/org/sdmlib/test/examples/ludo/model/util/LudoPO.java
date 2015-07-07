package org.sdmlib.test.examples.ludo.model.util;

import java.util.Date;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.ludo.model.Dice;
import org.sdmlib.test.examples.ludo.model.Field;
import org.sdmlib.test.examples.ludo.model.Ludo;
import org.sdmlib.test.examples.ludo.model.Player;

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

}
