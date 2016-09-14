/*
   Copyright (c) 2016 zuendorf
   
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
   
package org.sdmlib.test.examples.annotations.model.simple;

import org.sdmlib.test.examples.annotations.model.simple.Cube;
import org.sdmlib.test.examples.annotations.model.simple.util.DoorSet;
import org.sdmlib.test.examples.annotations.model.simple.Door;
import org.sdmlib.test.examples.annotations.model.simple.util.WindowSet;
import org.sdmlib.test.examples.annotations.model.simple.Window;
   /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/annotations/AnnotationTest.java'>AnnotationTest.java</a>
 */
   public  class House extends Cube
{

   
   //==========================================================================
   public void init(  )
   {
      
   }

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      withoutDoors(this.getDoors().toArray(new Door[this.getDoors().size()]));
      withoutWindows(this.getWindows().toArray(new Window[this.getWindows().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * House ----------------------------------- Door
    *              house                   doors
    * </pre>
    */
   
   public static final String PROPERTY_DOORS = "doors";

   private DoorSet doors = null;
   
   public DoorSet getDoors()
   {
      if (this.doors == null)
      {
         return DoorSet.EMPTY_SET;
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
               this.doors = new DoorSet();
            }
            
            boolean changed = this.doors.add (item);

            if (changed)
            {
               item.withHouse(this);
               firePropertyChange(PROPERTY_DOORS, null, item);
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
               firePropertyChange(PROPERTY_DOORS, item, null);
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

   private WindowSet windows = null;
   
   public WindowSet getWindows()
   {
      if (this.windows == null)
      {
         return WindowSet.EMPTY_SET;
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
               this.windows = new WindowSet();
            }
            
            boolean changed = this.windows.add (item);

            if (changed)
            {
               item.withHouse(this);
               firePropertyChange(PROPERTY_WINDOWS, null, item);
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
               firePropertyChange(PROPERTY_WINDOWS, item, null);
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
