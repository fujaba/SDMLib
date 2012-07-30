package org.sdmlib.examples.ludo.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.ludo.Ludo;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.examples.ludo.Dice;
import org.sdmlib.examples.ludo.Field;

public class LudoSet extends LinkedHashSet<Ludo>
{
   public PlayerSet getPlayers()
   {
      PlayerSet result = new PlayerSet();
      
      for (Ludo obj : this)
      {
         result.addAll(obj.getPlayers());
      }
      
      return result;
   }
   public LudoSet withPlayers(Player value)
   {
      for (Ludo obj : this)
      {
         obj.withPlayers(value);
      }
      
      return this;
   }

   public LudoSet withoutPlayers(Player value)
   {
      for (Ludo obj : this)
      {
         obj.withoutPlayers(value);
      }
      
      return this;
   }

   public DiceSet getDice()
   {
      DiceSet result = new DiceSet();
      
      for (Ludo obj : this)
      {
         result.add(obj.getDice());
      }
      
      return result;
   }
   public LudoSet withDice(Dice value)
   {
      for (Ludo obj : this)
      {
         obj.withDice(value);
      }
      
      return this;
   }

   public FieldSet getFields()
   {
      FieldSet result = new FieldSet();
      
      for (Ludo obj : this)
      {
         result.addAll(obj.getFields());
      }
      
      return result;
   }
   public LudoSet withFields(Field value)
   {
      for (Ludo obj : this)
      {
         obj.withFields(value);
      }
      
      return this;
   }

   public LudoSet withoutFields(Field value)
   {
      for (Ludo obj : this)
      {
         obj.withoutFields(value);
      }
      
      return this;
   }

}

