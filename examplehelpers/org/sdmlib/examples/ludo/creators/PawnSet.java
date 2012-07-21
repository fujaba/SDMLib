package org.sdmlib.examples.ludo.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.ludo.Pawn;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.examples.ludo.Field;

public class PawnSet extends LinkedHashSet<Pawn>
{
   public StringList getColor()
   {
      StringList result = new StringList();
      
      for (Pawn obj : this)
      {
         result.add(obj.getColor());
      }
      
      return result;
   }

   public intList getX()
   {
      intList result = new intList();
      
      for (Pawn obj : this)
      {
         result.add(obj.getX());
      }
      
      return result;
   }

   public intList getY()
   {
      intList result = new intList();
      
      for (Pawn obj : this)
      {
         result.add(obj.getY());
      }
      
      return result;
   }

   public PlayerSet getPlayer()
   {
      PlayerSet result = new PlayerSet();
      
      for (Pawn obj : this)
      {
         result.add(obj.getPlayer());
      }
      
      return result;
   }
   public FieldSet getPos()
   {
      FieldSet result = new FieldSet();
      
      for (Pawn obj : this)
      {
         result.add(obj.getPos());
      }
      
      return result;
   }
}

