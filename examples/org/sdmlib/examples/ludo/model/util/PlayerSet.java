/*
   Copyright (c) 2014 Stefan 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.examples.ludo.model.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.ludo.model.Player;
import org.sdmlib.models.modelsets.StringList;
import java.util.Collection;
import java.util.List;
import org.sdmlib.examples.ludo.model.util.LudoColorSet;
import org.sdmlib.examples.ludo.LudoModel.LudoColor;
import org.sdmlib.models.modelsets.intList;

public class PlayerSet extends SDMSet<Player>
{


   public PlayerPO hasPlayerPO()
   {
      org.sdmlib.examples.ludo.model.util.ModelPattern pattern = new org.sdmlib.examples.ludo.model.util.ModelPattern();
      
      PlayerPO patternObject = pattern.hasElementPlayerPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.ludo.model.Player";
   }


   public PlayerSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Player>)value);
      }
      else if (value != null)
      {
         this.add((Player) value);
      }
      
      return this;
   }
   
   public PlayerSet without(Player value)
   {
      this.remove(value);
      return this;
   }

   public StringList getColor()
   {
      StringList result = new StringList();
      
      for (Player obj : this)
      {
         result.add(obj.getColor());
      }
      
      return result;
   }

   public PlayerSet hasColor(String value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value.equals(obj.getColor()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PlayerSet withColor(String value)
   {
      for (Player obj : this)
      {
         obj.setColor(value);
      }
      
      return this;
   }

   public LudoColorSet getEnumColor()
   {
      LudoColorSet result = new LudoColorSet();
      
      for (Player obj : this)
      {
         result.add(obj.getEnumColor());
      }
      
      return result;
   }

   public PlayerSet hasEnumColor(org.sdmlib.examples.ludo.LudoModel.LudoColor value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value == obj.getEnumColor())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PlayerSet withEnumColor(LudoColor value)
   {
      for (Player obj : this)
      {
         obj.setEnumColor(value);
      }
      
      return this;
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

   public PlayerSet hasName(String value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PlayerSet withName(String value)
   {
      for (Player obj : this)
      {
         obj.setName(value);
      }
      
      return this;
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

   public PlayerSet hasX(int value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value == obj.getX())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PlayerSet withX(int value)
   {
      for (Player obj : this)
      {
         obj.setX(value);
      }
      
      return this;
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

   public PlayerSet hasY(int value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value == obj.getY())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PlayerSet withY(int value)
   {
      for (Player obj : this)
      {
         obj.setY(value);
      }
      
      return this;
   }

}
