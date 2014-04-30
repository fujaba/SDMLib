/*
   Copyright (c) 2012 zuendorf 
   
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

package org.sdmlib.examples.ludoreverse.model.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.examples.ludoreverse.model.Ludo;
import org.sdmlib.examples.ludoreverse.model.Player;
import org.sdmlib.models.modelsets.ModelSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.examples.ludoreverse.model.creators.PlayerSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;

public class LudoSet extends LinkedHashSet<Ludo> implements ModelSet
{

   public String toString()
   {
      StringList stringList = new StringList();

      for (Ludo elem : this)
      {
         stringList.add(elem.toString());
      }

      return "(" + stringList.concat(", ") + ")";
   }

   public String getEntryType()
   {
      return "org.sdmlib.examples.ludoreverse.model.Ludo";
   }

   public StringList getStyle()
   {
      StringList result = new StringList();

      for (Ludo obj : this)
      {
         result.add(obj.getStyle());
      }

      return result;
   }

   public LudoSet withStyle(String value)
   {
      for (Ludo obj : this)
      {
         obj.withStyle(value);
      }

      return this;
   }

   public intList getAge()
   {
      intList result = new intList();

      for (Ludo obj : this)
      {
         result.add(obj.getAge());
      }

      return result;
   }

   public LudoSet withAge(int value)
   {
      for (Ludo obj : this)
      {
         obj.withAge(value);
      }

      return this;
   }

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

   public LudoPO startModelPattern()
   {
      org.sdmlib.examples.ludoreverse.model.creators.ModelPattern pattern = new org.sdmlib.examples.ludoreverse.model.creators.ModelPattern();

      LudoPO patternObject = pattern.hasElementLudoPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }

   public LudoSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Ludo>) value);
      }
      else if (value != null)
      {
         this.add((Ludo) value);
      }

      return this;
   }

   public LudoSet without(Ludo value)
   {
      this.remove(value);
      return this;
   }

   public LudoPO hasLudoPO()
   {
      org.sdmlib.examples.ludoreverse.model.creators.ModelPattern pattern = new org.sdmlib.examples.ludoreverse.model.creators.ModelPattern();

      LudoPO patternObject = pattern.hasElementLudoPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }
}



