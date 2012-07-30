package org.sdmlib.examples.ludo.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.ludo.Dice;
import org.sdmlib.models.modelsets.intList;
import java.util.List;
import org.sdmlib.examples.ludo.Ludo;
import org.sdmlib.examples.ludo.Player;

public class DiceSet extends LinkedHashSet<Dice>
{
   public intList getValue()
   {
      intList result = new intList();
      
      for (Dice obj : this)
      {
         result.add(obj.getValue());
      }
      
      return result;
   }

   public DiceSet withValue(int value)
   {
      for (Dice obj : this)
      {
         obj.withValue(value);
      }
      
      return this;
   }

   public LudoSet getGame()
   {
      LudoSet result = new LudoSet();
      
      for (Dice obj : this)
      {
         result.add(obj.getGame());
      }
      
      return result;
   }
   public DiceSet withGame(Ludo value)
   {
      for (Dice obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
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
   public DiceSet withPlayer(Player value)
   {
      for (Dice obj : this)
      {
         obj.withPlayer(value);
      }
      
      return this;
   }

}

