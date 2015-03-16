/*
   Copyright (c) 2015 alex 
   
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
   
package org.sdmlib.examples.features.model.simple;

import java.util.LinkedHashSet;

public class House
{

   
   //==========================================================================
   
   
   public void removeYou()
   {
      
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * House ----------------------------------- Door
    *              house                   doors
    * </pre>
    */
   
   public static final String PROPERTY_DOORS = "doors";

   private LinkedHashSet<Door> doors = null;
   
   public LinkedHashSet<Door> getDoors()
   {
      if (this.doors == null)
      {
         return Door.EMPTY_SET;
      }
   
      return this.doors;
   }

   public House withDoors(Door... value)
   {
      if(value==null){
         return this;
      }
      for (Door item : value)
      {
         if (item != null)
         {
            if (this.doors == null)
            {
               this.doors = new LinkedHashSet<Door>();
            }
            
            boolean changed = this.doors.add (item);

            if (changed)
            {
               item.withHouse(this);
               
            }
         }
      }
      return this;
   } 

   public House withoutDoors(Door... value)
   {
      for (Door item : value)
      {
         if ((this.doors != null) && (item != null))
         {
            if (this.doors.remove(item))
            {
               item.setHouse(null);
               
            }
         }
      }
      return this;
   }

   public Door createDoors()
   {
      Door value = new Door();
      withDoors(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * House ----------------------------------- Window
    *              house                   windows
    * </pre>
    */
   
   public static final String PROPERTY_WINDOWS = "windows";

   private LinkedHashSet<Window> windows = null;
   
   public LinkedHashSet<Window> getWindows()
   {
      if (this.windows == null)
      {
         return Window.EMPTY_SET;
      }
   
      return this.windows;
   }

   public House withWindows(Window... value)
   {
      if(value==null){
         return this;
      }
      for (Window item : value)
      {
         if (item != null)
         {
            if (this.windows == null)
            {
               this.windows = new LinkedHashSet<Window>();
            }
            
            boolean changed = this.windows.add (item);

            if (changed)
            {
               item.withHouse(this);
               
            }
         }
      }
      return this;
   } 

   public House withoutWindows(Window... value)
   {
      for (Window item : value)
      {
         if ((this.windows != null) && (item != null))
         {
            if (this.windows.remove(item))
            {
               item.setHouse(null);
               
            }
         }
      }
      return this;
   }

   public Window createWindows()
   {
      Window value = new Window();
      withWindows(value);
      return value;
   } 
}
