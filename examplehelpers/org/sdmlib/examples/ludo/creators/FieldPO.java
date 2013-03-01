package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.Field;
import org.sdmlib.examples.ludo.Ludo;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class FieldPO extends PatternObject<FieldPO, Field>
{
   public FieldSet allMatches()
   {
      this.setDoAllMatches(true);
      
      FieldSet matches = new FieldSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Field) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public FieldPO hasColor(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Field.PROPERTY_COLOR)
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
         return ((Field) getCurrentMatch()).getColor();
      }
      return null;
   }
   
   public FieldPO hasKind(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Field.PROPERTY_KIND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getKind()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) getCurrentMatch()).getKind();
      }
      return null;
   }
   
   public FieldPO hasX(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Field.PROPERTY_X)
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
         return ((Field) getCurrentMatch()).getX();
      }
      return 0;
   }
   
   public FieldPO hasY(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Field.PROPERTY_Y)
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
         return ((Field) getCurrentMatch()).getY();
      }
      return 0;
   }
   
   public LudoPO hasGame()
   {
      LudoPO result = new LudoPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Field.PROPERTY_GAME, result);
      
      return result;
   }
   
   public FieldPO hasGame(LudoPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_GAME)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Ludo getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getGame();
      }
      return null;
   }
   
   public FieldPO hasNext()
   {
      FieldPO result = new FieldPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Field.PROPERTY_NEXT, result);
      
      return result;
   }
   
   public FieldPO hasNext(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_NEXT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Field getNext()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getNext();
      }
      return null;
   }
   
   public FieldPO hasPrev()
   {
      FieldPO result = new FieldPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Field.PROPERTY_PREV, result);
      
      return result;
   }
   
   public FieldPO hasPrev(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_PREV)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Field getPrev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getPrev();
      }
      return null;
   }
   
   public FieldPO hasLanding()
   {
      FieldPO result = new FieldPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Field.PROPERTY_LANDING, result);
      
      return result;
   }
   
   public FieldPO hasLanding(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_LANDING)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Field getLanding()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getLanding();
      }
      return null;
   }
   
   public FieldPO hasEntry()
   {
      FieldPO result = new FieldPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Field.PROPERTY_ENTRY, result);
      
      return result;
   }
   
   public FieldPO hasEntry(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_ENTRY)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Field getEntry()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getEntry();
      }
      return null;
   }
   
   public PlayerPO hasStarter()
   {
      PlayerPO result = new PlayerPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Field.PROPERTY_STARTER, result);
      
      return result;
   }
   
   public FieldPO hasStarter(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_STARTER)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Player getStarter()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getStarter();
      }
      return null;
   }
   
   public PlayerPO hasBaseowner()
   {
      PlayerPO result = new PlayerPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Field.PROPERTY_BASEOWNER, result);
      
      return result;
   }
   
   public FieldPO hasBaseowner(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_BASEOWNER)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Player getBaseowner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getBaseowner();
      }
      return null;
   }
   
   public PlayerPO hasLander()
   {
      PlayerPO result = new PlayerPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Field.PROPERTY_LANDER, result);
      
      return result;
   }
   
   public FieldPO hasLander(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_LANDER)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Player getLander()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getLander();
      }
      return null;
   }
   
   public PawnPO hasPawns()
   {
      PawnPO result = new PawnPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Field.PROPERTY_PAWNS, result);
      
      return result;
   }
   
   public FieldPO hasPawns(PawnPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_PAWNS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnSet getPawns()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getPawns();
      }
      return null;
   }
   
}

