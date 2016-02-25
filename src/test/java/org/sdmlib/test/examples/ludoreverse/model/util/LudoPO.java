package org.sdmlib.test.examples.ludoreverse.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.ludoreverse.model.Ludo;
import org.sdmlib.test.examples.ludoreverse.model.Player;
import org.sdmlib.test.examples.ludoreverse.model.util.PlayerPO;
import org.sdmlib.test.examples.ludoreverse.model.util.LudoPO;

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
   public LudoPO hasStyle(String value)
   {
      new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_STYLE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LudoPO hasStyle(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_STYLE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LudoPO createStyle(String value)
   {
      this.startCreate().hasStyle(value).endCreate();
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
      new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_AGE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LudoPO hasAge(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_AGE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LudoPO createAge(int value)
   {
      this.startCreate().hasAge(value).endCreate();
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
   
   public PlayerPO hasGame()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Ludo.PROPERTY_GAME, result);
      
      return result;
   }

   public PlayerPO createGame()
   {
      return this.startCreate().hasGame().endCreate();
   }

   public LudoPO hasGame(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Ludo.PROPERTY_GAME);
   }

   public LudoPO createGame(PlayerPO tgt)
   {
      return this.startCreate().hasGame(tgt).endCreate();
   }

   public Player getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Ludo) this.getCurrentMatch()).getGame();
      }
      return null;
   }

   public LudoPO filterStyle(String value)
   {
      new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_STYLE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LudoPO filterStyle(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_STYLE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LudoPO filterAge(int value)
   {
      new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_AGE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LudoPO filterAge(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_AGE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO filterGame()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Ludo.PROPERTY_GAME, result);
      
      return result;
   }

   public LudoPO filterGame(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Ludo.PROPERTY_GAME);
   }

}
