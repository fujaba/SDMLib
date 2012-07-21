package org.sdmlib.examples.ludo.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.ludo.Dice;
import org.sdmlib.examples.ludo.Ludo;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.models.modelsets.intList;
import java.util.List;

public class DiceSet extends LinkedHashSet<Dice>
{
   public LudoSet getGame()
   {
      LudoSet result = new LudoSet();
      
      for (Dice obj : this)
      {
         result.add(obj.getGame());
      }
      
      return result;
   }
   public PlayerSet getPlayer()
   {
      PlayerSet result = new PlayerSet();
      
      for (Dice obj : this)
      {
         result.add(obj.getPlayer());
      }
      
      return result;
   }
   public intList getValue()
   {
      intList result = new intList();
      
      for (Dice obj : this)
      {
         result.add(obj.getValue());
      }
      
      return result;
   }

}


