package org.sdmlib.examples.ludoreverse.model.creators;

import org.sdmlib.examples.ludoreverse.model.Ludo;
import org.sdmlib.examples.ludoreverse.model.Player;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.ludoreverse.model.creators.PlayerSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.ludoreverse.model.creators.LudoPO;
import org.sdmlib.examples.ludoreverse.model.creators.PlayerPO;

public class PlayerPO extends PatternObject<PlayerPO, Player>
{
   public PlayerSet allMatches()
   {
      this.setDoAllMatches(true);

      PlayerSet matches = new PlayerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Player) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }

   public LudoPO hasGame()
   {
      LudoPO result = new LudoPO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(Player.PROPERTY_GAME, result);

      return result;
   }

   public PlayerPO hasGame(LudoPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Player.PROPERTY_GAME).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public Ludo getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getGame();
      }
      return null;
   }

   public PlayerPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Player.PROPERTY_NAME).withTgtValue(value).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getName();
      }
      return null;
   }

   public PlayerPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setName(value);
      }
      return this;
   }

   public PlayerPO hasColor(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Player.PROPERTY_COLOR).withTgtValue(value).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public String getColor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getColor();
      }
      return null;
   }

   public PlayerPO withColor(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setColor(value);
      }
      return this;
   }

   public PlayerPO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Player.PROPERTY_NAME).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public PlayerPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }

   public PlayerPO hasColor(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Player.PROPERTY_COLOR).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public PlayerPO createColor(String value)
   {
      this.startCreate().hasColor(value).endCreate();
      return this;
   }

   public LudoPO createGame()
   {
      return this.startCreate().hasGame().endCreate();
   }

   public PlayerPO createGame(LudoPO tgt)
   {
      return this.startCreate().hasGame(tgt).endCreate();
   }

}
