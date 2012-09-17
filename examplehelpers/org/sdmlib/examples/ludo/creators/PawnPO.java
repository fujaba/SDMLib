package org.sdmlib.examples.ludo.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.ludo.Pawn;
import org.sdmlib.examples.ludo.creators.PawnSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.ludo.creators.PlayerPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.ludo.creators.PawnPO;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.examples.ludo.creators.FieldPO;
import org.sdmlib.examples.ludo.Field;

public class PawnPO extends PatternObject<PawnPO, Pawn>
{
   public PawnSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PawnSet matches = new PawnSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Pawn) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public PawnPO hasColor(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_COLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getColor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pawn) getCurrentMatch()).getColor();
      }
      return null;
   }
   
   public PawnPO hasX(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getX()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pawn) getCurrentMatch()).getX();
      }
      return 0;
   }
   
   public PawnPO hasY(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getY()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pawn) getCurrentMatch()).getY();
      }
      return 0;
   }
   
   public PlayerPO hasPlayer()
   {
      PlayerPO result = new PlayerPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Pawn.PROPERTY_PLAYER, result);
      
      return result;
   }
   
   public PawnPO hasPlayer(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Pawn.PROPERTY_PLAYER)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Player getPlayer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pawn) this.getCurrentMatch()).getPlayer();
      }
      return null;
   }
   
   public FieldPO hasPos()
   {
      FieldPO result = new FieldPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Pawn.PROPERTY_POS, result);
      
      return result;
   }
   
   public PawnPO hasPos(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Pawn.PROPERTY_POS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Field getPos()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pawn) this.getCurrentMatch()).getPos();
      }
      return null;
   }
   
}

