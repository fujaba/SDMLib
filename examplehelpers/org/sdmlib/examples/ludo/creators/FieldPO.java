package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.Dice;
import org.sdmlib.examples.ludo.Field;
import org.sdmlib.examples.ludo.Pawn;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.ludo.creators.LudoPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.ludo.creators.FieldPO;
import org.sdmlib.examples.ludo.Ludo;
import org.sdmlib.examples.ludo.creators.PlayerPO;
import org.sdmlib.examples.ludo.creators.PawnPO;

public class FieldPO extends PatternObject
{

   public FieldPO hasKind(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Field.PROPERTY_KIND)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO withKind(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) getCurrentMatch()).withKind(value);
      }
      return this;
   }

   public FieldPO startNAC()
   {
      NegativeApplicationCondition nac = new NegativeApplicationCondition();
      
      this.getPattern().addToElements(nac);

      return this;
   }

   public PawnPO hasPawns()
   {
      PawnPO result = new PawnPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Field.PROPERTY_PAWNS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }     
   
   public FieldPO hasColor(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Field.PROPERTY_COLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO withColor(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) getCurrentMatch()).withColor(value);
      }
      return this;
   }
   
   public FieldPO hasX(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Field.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO withX(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) getCurrentMatch()).withX(value);
      }
      return this;
   }
   
   public FieldPO hasY(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Field.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO withY(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) getCurrentMatch()).withY(value);
      }
      return this;
   }
   
   public LudoPO hasGame()
   {
      LudoPO result = new LudoPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Field.PROPERTY_GAME)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public FieldPO hasGame(LudoPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_GAME)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO withGame(LudoPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) this.getCurrentMatch()).withGame((Ludo) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public FieldPO hasNext()
   {
      FieldPO result = new FieldPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Field.PROPERTY_NEXT)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public FieldPO hasNext(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_NEXT)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO withNext(FieldPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) this.getCurrentMatch()).withNext((Field) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public FieldPO hasPrev()
   {
      FieldPO result = new FieldPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Field.PROPERTY_PREV)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public FieldPO hasPrev(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_PREV)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO withPrev(FieldPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) this.getCurrentMatch()).withPrev((Field) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public FieldPO hasLanding()
   {
      FieldPO result = new FieldPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Field.PROPERTY_LANDING)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public FieldPO hasLanding(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_LANDING)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO withLanding(FieldPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) this.getCurrentMatch()).withLanding((Field) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public FieldPO hasEntry()
   {
      FieldPO result = new FieldPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Field.PROPERTY_ENTRY)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public FieldPO hasEntry(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_ENTRY)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO withEntry(FieldPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) this.getCurrentMatch()).withEntry((Field) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PlayerPO hasStarter()
   {
      PlayerPO result = new PlayerPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Field.PROPERTY_STARTER)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public FieldPO hasStarter(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_STARTER)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO withStarter(PlayerPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) this.getCurrentMatch()).withStarter((Player) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PlayerPO hasBaseowner()
   {
      PlayerPO result = new PlayerPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Field.PROPERTY_BASEOWNER)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public FieldPO hasBaseowner(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_BASEOWNER)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO withBaseowner(PlayerPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) this.getCurrentMatch()).withBaseowner((Player) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PlayerPO hasLander()
   {
      PlayerPO result = new PlayerPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Field.PROPERTY_LANDER)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public FieldPO hasLander(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_LANDER)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO withLander(PlayerPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) this.getCurrentMatch()).withLander((Player) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public FieldPO hasPawns(PawnPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_PAWNS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO withPawns(PawnPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) this.getCurrentMatch()).withPawns((Pawn) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public FieldPO withoutPawns(PawnPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) this.getCurrentMatch()).withoutPawns((Pawn) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
}

