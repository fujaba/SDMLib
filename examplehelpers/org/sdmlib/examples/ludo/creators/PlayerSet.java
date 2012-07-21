package org.sdmlib.examples.ludo.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.examples.ludo.Ludo;
import org.sdmlib.examples.ludo.Dice;
import org.sdmlib.examples.ludo.Field;
import org.sdmlib.examples.ludo.Pawn;

public class PlayerSet extends LinkedHashSet<Player>
{
   public StringList getColor()
   {
      StringList result = new StringList();
      
      for (Player obj : this)
      {
         result.add(obj.getColor());
      }
      
      return result;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Player obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public intList getX()
   {
      intList result = new intList();
      
      for (Player obj : this)
      {
         result.add(obj.getX());
      }
      
      return result;
   }

   public intList getY()
   {
      intList result = new intList();
      
      for (Player obj : this)
      {
         result.add(obj.getY());
      }
      
      return result;
   }

   public LudoSet getGame()
   {
      LudoSet result = new LudoSet();
      
      for (Player obj : this)
      {
         result.add(obj.getGame());
      }
      
      return result;
   }
   public PlayerSet getNext()
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         result.add(obj.getNext());
      }
      
      return result;
   }
   public PlayerSet getPrev()
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         result.add(obj.getPrev());
      }
      
      return result;
   }
   public DiceSet getDice()
   {
      DiceSet result = new DiceSet();
      
      for (Player obj : this)
      {
         result.add(obj.getDice());
      }
      
      return result;
   }
   public FieldSet getStart()
   {
      FieldSet result = new FieldSet();
      
      for (Player obj : this)
      {
         result.add(obj.getStart());
      }
      
      return result;
   }
   public PawnSet getPawns()
   {
      PawnSet result = new PawnSet();
      
      for (Player obj : this)
      {
         result.addAll(obj.getPawns());
      }
      
      return result;
   }
   public FieldSet getBase()
   {
      FieldSet result = new FieldSet();
      
      for (Player obj : this)
      {
         result.add(obj.getBase());
      }
      
      return result;
   }
   public FieldSet getLanding()
   {
      FieldSet result = new FieldSet();
      
      for (Player obj : this)
      {
         result.add(obj.getLanding());
      }
      
      return result;
   }
}


