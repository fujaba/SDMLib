/*
   Copyright (c) 2014 zuendorf 
   
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
   
package de.kassel.roombook.creators;

import org.sdmlib.models.modelsets.SDMSet;
import de.kassel.roombook.Room;
import org.sdmlib.models.modelsets.StringList;
import java.util.Collection;
import de.kassel.roombook.creators.FloorSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;
import de.kassel.roombook.Floor;

public class RoomSet extends SDMSet<Room>
{


   public RoomPO hasRoomPO()
   {
      de.kassel.roombook.creators.ModelPattern pattern = new de.kassel.roombook.creators.ModelPattern();
      
      RoomPO patternObject = pattern.hasElementRoomPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   @Override
   public String getEntryType()
   {
      return "de.kassel.roombook.Room";
   }


   public RoomSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Room>)value);
      }
      else if (value != null)
      {
         this.add((Room) value);
      }
      
      return this;
   }
   
   public RoomSet without(Room value)
   {
      this.remove(value);
      return this;
   }

   public FloorSet getFloor()
   {
      FloorSet result = new FloorSet();
      
      for (Room obj : this)
      {
         result.with(obj.getFloor());
      }
      
      return result;
   }

   public RoomSet hasFloor(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      RoomSet answer = new RoomSet();
      
      for (Room obj : this)
      {
         if (neighbors.contains(obj.getFloor()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public RoomSet withFloor(Floor value)
   {
      for (Room obj : this)
      {
         obj.withFloor(value);
      }
      
      return this;
   }

}















