package org.sdmlib.examples.ludoreverse.model.creators;

import org.sdmlib.examples.ludoreverse.model.Ludo;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

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
   
   public LudoPO hasStyle(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_STYLE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getStyle()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Ludo) getCurrentMatch()).getStyle();
      }
      return null;
   }
   
   public LudoPO withStyle(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Ludo) getCurrentMatch()).setStyle(value);
      }
      return this;
   }
   
   public LudoPO hasAge(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_AGE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getAge()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Ludo) getCurrentMatch()).getAge();
      }
      return 0;
   }
   
   public LudoPO withAge(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Ludo) getCurrentMatch()).setAge(value);
      }
      return this;
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
   
}




