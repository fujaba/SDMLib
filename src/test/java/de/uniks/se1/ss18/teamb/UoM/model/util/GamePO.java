package de.uniks.se1.ss18.teamb.UoM.model.util;

import org.sdmlib.models.pattern.PatternObject;
import de.uniks.se1.ss18.teamb.UoM.model.Game;
import de.uniks.se1.ss18.teamb.UoM.model.GameState;

public class GamePO extends PatternObject<GamePO, Game>
{

    public GameSet allMatches()
   {
      this.setDoAllMatches(true);
      
      GameSet matches = new GameSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Game) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public GamePO(){
      newInstance(null);
   }

   public GamePO(Game... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public GamePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public GameState getGameState()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) this.getCurrentMatch()).getGameState();
      }
      return null;
   }

}
