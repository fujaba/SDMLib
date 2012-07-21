package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.Field;
import org.sdmlib.examples.ludo.Pawn;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;

public class PawnPO extends PatternObject
{
   public PlayerPO hasPlayer()
   {
      PlayerPO result = new PlayerPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Pawn.PROPERTY_PLAYER)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }

   public PawnPO hasPlayer(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Pawn.PROPERTY_PLAYER)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
           
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO hasPos()
   {
      FieldPO result = new FieldPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Pawn.PROPERTY_POS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }

   public PawnPO endNAC()
   {
      Pattern directPattern = this.getPattern();
      if (directPattern instanceof NegativeApplicationCondition)
      {
         directPattern = directPattern.getPattern();
      }
      
      directPattern.setCurrentNAC(null);
      
      directPattern.findMatch();
      
      return this;
   }

   public void setPos(FieldPO startFieldPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pawn) this.getCurrentMatch()).setPos((Field) startFieldPO.getCurrentMatch());
      }      
   }

   
   
}
