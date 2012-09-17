package org.sdmlib.examples.ludo.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.ludo.Ludo;
import org.sdmlib.examples.ludo.creators.LudoSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.ludo.creators.PlayerPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.ludo.creators.LudoPO;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.examples.ludo.creators.PlayerSet;
import org.sdmlib.examples.ludo.creators.DicePO;
import org.sdmlib.examples.ludo.Dice;
import org.sdmlib.examples.ludo.creators.FieldPO;
import org.sdmlib.examples.ludo.Field;
import org.sdmlib.examples.ludo.creators.FieldSet;

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
   
   public PlayerPO hasPlayers()
   {
      PlayerPO result = new PlayerPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Ludo.PROPERTY_PLAYERS, result);
      
      return result;
   }
   
   public LudoPO hasPlayers(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Ludo.PROPERTY_PLAYERS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
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
      DicePO result = new DicePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Ludo.PROPERTY_DICE, result);
      
      return result;
   }
   
   public LudoPO hasDice(DicePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Ludo.PROPERTY_DICE)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
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
      FieldPO result = new FieldPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Ludo.PROPERTY_FIELDS, result);
      
      return result;
   }
   
   public LudoPO hasFields(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Ludo.PROPERTY_FIELDS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
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

