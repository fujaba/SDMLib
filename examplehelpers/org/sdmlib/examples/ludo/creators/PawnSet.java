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

   public PawnSet withColor(String value)
   {
      for (Pawn obj : this)
      {
         obj.withColor(value);
      }
      
      return this;
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

   public PawnSet withX(int value)
   {
      for (Pawn obj : this)
      {
         obj.withX(value);
      }
      
      return this;
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

   public PawnSet withY(int value)
   {
      for (Pawn obj : this)
      {
         obj.withY(value);
      }
      
      return this;
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
   public PawnSet withPlayer(Player value)
   {
      for (Pawn obj : this)
      {
         obj.withPlayer(value);
      }
      
      return this;
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
   public PawnSet withPos(Field value)
   {
      for (Pawn obj : this)
      {
         obj.withPos(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Pawn elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public PawnSet with(Pawn value)
   {
      this.add(value);
      return this;
   }
   
   public PawnSet without(Pawn value)
   {
      this.remove(value);
      return this;
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.ludo.Pawn";
   }
}



