package org.sdmlib.test.model.refactoring.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.model.refactoring.Ludo;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.test.model.refactoring.util.PlayerPO;
import org.sdmlib.test.model.refactoring.Player;
import org.sdmlib.test.model.refactoring.util.LudoPO;
import org.sdmlib.test.model.refactoring.util.PlayerSet;

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

}
