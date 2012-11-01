package org.sdmlib.examples.ludo.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.ludo.Field;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.examples.ludo.Ludo;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.examples.ludo.Pawn;

public class FieldSet extends LinkedHashSet<Field>
{
   public StringList getColor()
   {
      StringList result = new StringList();
      
      for (Field obj : this)
      {
         result.add(obj.getColor());
      }
      
      return result;
   }

   public FieldSet withColor(String value)
   {
      for (Field obj : this)
      {
         obj.withColor(value);
      }
      
      return this;
   }

   public StringList getKind()
   {
      StringList result = new StringList();
      
      for (Field obj : this)
      {
         result.add(obj.getKind());
      }
      
      return result;
   }

   public FieldSet withKind(String value)
   {
      for (Field obj : this)
      {
         obj.withKind(value);
      }
      
      return this;
   }

   public intList getX()
   {
      intList result = new intList();
      
      for (Field obj : this)
      {
         result.add(obj.getX());
      }
      
      return result;
   }

   public FieldSet withX(int value)
   {
      for (Field obj : this)
      {
         obj.withX(value);
      }
      
      return this;
   }

   public intList getY()
   {
      intList result = new intList();
      
      for (Field obj : this)
      {
         result.add(obj.getY());
      }
      
      return result;
   }

   public FieldSet withY(int value)
   {
      for (Field obj : this)
      {
         obj.withY(value);
      }
      
      return this;
   }

   public LudoSet getGame()
   {
      LudoSet result = new LudoSet();
      
      for (Field obj : this)
      {
         result.add(obj.getGame());
      }
      
      return result;
   }
   public FieldSet withGame(Ludo value)
   {
      for (Field obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

   public FieldSet getNext()
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         result.add(obj.getNext());
      }
      
      return result;
   }
   public FieldSet withNext(Field value)
   {
      for (Field obj : this)
      {
         obj.withNext(value);
      }
      
      return this;
   }

   public FieldSet getPrev()
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         result.add(obj.getPrev());
      }
      
      return result;
   }
   public FieldSet withPrev(Field value)
   {
      for (Field obj : this)
      {
         obj.withPrev(value);
      }
      
      return this;
   }

   public FieldSet getLanding()
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         result.add(obj.getLanding());
      }
      
      return result;
   }
   public FieldSet withLanding(Field value)
   {
      for (Field obj : this)
      {
         obj.withLanding(value);
      }
      
      return this;
   }

   public FieldSet getEntry()
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         result.add(obj.getEntry());
      }
      
      return result;
   }
   public FieldSet withEntry(Field value)
   {
      for (Field obj : this)
      {
         obj.withEntry(value);
      }
      
      return this;
   }

   public PlayerSet getStarter()
   {
      PlayerSet result = new PlayerSet();
      
      for (Field obj : this)
      {
         result.add(obj.getStarter());
      }
      
      return result;
   }
   public FieldSet withStarter(Player value)
   {
      for (Field obj : this)
      {
         obj.withStarter(value);
      }
      
      return this;
   }

   public PlayerSet getBaseowner()
   {
      PlayerSet result = new PlayerSet();
      
      for (Field obj : this)
      {
         result.add(obj.getBaseowner());
      }
      
      return result;
   }
   public FieldSet withBaseowner(Player value)
   {
      for (Field obj : this)
      {
         obj.withBaseowner(value);
      }
      
      return this;
   }

   public PlayerSet getLander()
   {
      PlayerSet result = new PlayerSet();
      
      for (Field obj : this)
      {
         result.add(obj.getLander());
      }
      
      return result;
   }
   public FieldSet withLander(Player value)
   {
      for (Field obj : this)
      {
         obj.withLander(value);
      }
      
      return this;
   }

   public PawnSet getPawns()
   {
      PawnSet result = new PawnSet();
      
      for (Field obj : this)
      {
         result.addAll(obj.getPawns());
      }
      
      return result;
   }
   public FieldSet withPawns(Pawn value)
   {
      for (Field obj : this)
      {
         obj.withPawns(value);
      }
      
      return this;
   }

   public FieldSet withoutPawns(Pawn value)
   {
      for (Field obj : this)
      {
         obj.withoutPawns(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Field elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public FieldSet with(Field value)
   {
      this.add(value);
      return this;
   }
   
   public FieldSet without(Field value)
   {
      this.remove(value);
      return this;
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.ludo.Field";
   }
}



