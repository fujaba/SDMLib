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
import org.sdmlib.examples.ludo.model.Pawn;
import org.sdmlib.models.modelsets.StringList;
import java.util.Collection;
import java.util.List;
import org.sdmlib.models.modelsets.intList;

public class PawnSet extends SDMSet<Pawn>
{


   public PawnPO hasPawnPO()
   {
      org.sdmlib.examples.ludo.model.util.ModelPattern pattern = new org.sdmlib.examples.ludo.model.util.ModelPattern();
      
      PawnPO patternObject = pattern.hasElementPawnPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.ludo.model.Pawn";
   }


   public PawnSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Pawn>)value);
      }
      else if (value != null)
      {
         this.add((Pawn) value);
      }
      
      return this;
   }
   
   public PawnSet without(Pawn value)
   {
      this.remove(value);
      return this;
   }

   public StringList getColor()
   {
      StringList result = new StringList();
      
      for (Pawn obj : this)
      {
         result.add(obj.getColor());
      }
      
      return result;
   }

   public PawnSet hasColor(String value)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (value.equals(obj.getColor()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PawnSet withColor(String value)
   {
      for (Pawn obj : this)
      {
         obj.setColor(value);
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

   public PawnSet hasX(int value)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (value == obj.getX())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PawnSet withX(int value)
   {
      for (Pawn obj : this)
      {
         obj.setX(value);
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

   public PawnSet hasY(int value)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (value == obj.getY())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PawnSet withY(int value)
   {
      for (Pawn obj : this)
      {
         obj.setY(value);
      }
      
      return this;
   }

}

