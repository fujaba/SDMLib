package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.Pawn;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;

public class PlayerPO extends PatternObject
{
   public DicePO hasDice()
   {
      DicePO result = new DicePO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Player.PROPERTY_DICE)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }

   public FieldPO hasStart()
   {
      FieldPO result = new FieldPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Player.PROPERTY_START)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }

   public PlayerPO hasBase(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_BASE)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

}
