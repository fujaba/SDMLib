/*
   Copyright (c) 2016 Stefan
   
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
   
package org.sdmlib.simple.model.association_a.util;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.simple.model.association_a.Room;
import java.util.Collection;

public class RoomSet extends SimpleSet<Room>
{
	protected Class<?> getTypClass() {
		return Room.class;
	}

   public RoomSet()
   {
      // empty
   }

   public RoomSet(Room... objects)
   {
      for (Room obj : objects)
      {
         this.add(obj);
      }
   }

   public RoomSet(Collection<Room> objects)
   {
      this.addAll(objects);
   }

   public static final RoomSet EMPTY_SET = new RoomSet().withFlag(RoomSet.READONLY);


   public RoomPO createRoomPO()
   {
      return new RoomPO(this.toArray(new Room[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.association_a.Room";
   }


   @SuppressWarnings("unchecked")
   public RoomSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
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

}
