package org.sdmlib.test.model.refactoring.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.model.refactoring.Player;
import org.sdmlib.test.model.refactoring.util.LudoPO;
import org.sdmlib.test.model.refactoring.Ludo;
import org.sdmlib.test.model.refactoring.util.PlayerPO;

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


   public PlayerPO(){
      newInstance(org.sdmlib.test.model.refactoring.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PlayerPO(Player... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.model.refactoring.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   

   

   

   

   

}
