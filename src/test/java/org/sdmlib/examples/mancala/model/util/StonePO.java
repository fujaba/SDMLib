package org.sdmlib.examples.mancala.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.mancala.model.Stone;
import org.sdmlib.examples.mancala.model.util.PlayerPO;
import org.sdmlib.examples.mancala.model.Player;
import org.sdmlib.examples.mancala.model.util.StonePO;

public class StonePO extends PatternObject<StonePO, Stone>
{

    public StoneSet allMatches()
   {
      this.setDoAllMatches(true);
      
      StoneSet matches = new StoneSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Stone) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public StonePO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public StonePO(Stone... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public PlayerPO hasPlayer()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Stone.PROPERTY_PLAYER, result);
      
      return result;
   }

   public PlayerPO createPlayer()
   {
      return this.startCreate().hasPlayer().endCreate();
   }

   public StonePO hasPlayer(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Stone.PROPERTY_PLAYER);
   }

   public StonePO createPlayer(PlayerPO tgt)
   {
      return this.startCreate().hasPlayer(tgt).endCreate();
   }

   public Player getPlayer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Stone) this.getCurrentMatch()).getPlayer();
      }
      return null;
   }

}
