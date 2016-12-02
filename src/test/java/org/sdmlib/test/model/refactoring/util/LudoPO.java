package org.sdmlib.test.model.refactoring.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.model.refactoring.Ludo;
import org.sdmlib.test.model.refactoring.Player;

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
      newInstance(org.sdmlib.test.model.refactoring.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public LudoPO(Ludo... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.model.refactoring.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
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

   
   //==========================================================================
   
   public void init(String p)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Ludo) getCurrentMatch()).init(p);
      }
   }

   public LudoPO hasLocation(String value)
   {
      new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_LOCATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LudoPO hasLocation(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_LOCATION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LudoPO createLocation(String value)
   {
      this.startCreate().hasLocation(value).endCreate();
      return this;
   }
   
   public String getLocation()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Ludo) getCurrentMatch()).getLocation();
      }
      return null;
   }
   
   public LudoPO withLocation(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Ludo) getCurrentMatch()).setLocation(value);
      }
      return this;
   }
   
   public LudoPO filterLocation(String value)
   {
      new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_LOCATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LudoPO filterLocation(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_LOCATION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO filterPlayers()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Ludo.PROPERTY_PLAYERS, result);
      
      return result;
   }

   public LudoPO filterPlayers(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Ludo.PROPERTY_PLAYERS);
   }

}
