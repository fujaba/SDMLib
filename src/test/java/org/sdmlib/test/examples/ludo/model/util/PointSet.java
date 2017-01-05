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
   
package org.sdmlib.test.examples.ludo.model.util;

import java.awt.Point;
import java.util.Collection;

import org.sdmlib.models.modelsets.intList;

import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.SimpleSet;

public class PointSet extends SimpleSet<Point>
{


   public PointPO hasPointPO()
   {
      return new PointPO(this.toArray(new Point[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public PointSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Point>)value);
      }
      else if (value != null)
      {
         this.add((Point) value);
      }
      
      return this;
   }
   
   public PointSet without(Point value)
   {
      this.remove(value);
      return this;
   }

   public intList getX()
   {
      intList result = new intList();
      
      for (Point obj : this)
      {
         result.add(obj.x);
      }
      
      return result;
   }

   public PointSet hasX(int value)
   {
      PointSet result = new PointSet();
      
      for (Point obj : this)
      {
         if (value == obj.getX())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PointSet withX(int value)
   {
      for (Point obj : this)
      {
         obj.x = value;
      }
      
      return this;
   }

   public intList getY()
   {
      intList result = new intList();
      
      for (Point obj : this)
      {
         result.add(obj.y);
      }
      
      return result;
   }

   public PointSet hasY(int value)
   {
      PointSet result = new PointSet();
      
      for (Point obj : this)
      {
         if (value == obj.getY())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PointSet withY(int value)
   {
      for (Point obj : this)
      {
         obj.y = value;
      }
      
      return this;
   }


   public static final PointSet EMPTY_SET = new PointSet().withFlag(PointSet.READONLY);


   public PointPO filterPointPO()
   {
      return new PointPO(this.toArray(new Point[this.size()]));
   }


   public String getEntryType()
   {
      return "java.awt.Point";
   }

   public PointSet()
   {
      // empty
   }

   public PointSet(Point... objects)
   {
      for (Point obj : objects)
      {
         this.add(obj);
      }
   }

   public PointSet(Collection<Point> objects)
   {
      this.addAll(objects);
   }


   public PointPO createPointPO()
   {
      return new PointPO(this.toArray(new Point[this.size()]));
   }


   @Override
   public PointSet getNewList(boolean keyValue)
   {
      return new PointSet();
   }


   public PointSet filter(Condition<Point> condition) {
      PointSet filterList = new PointSet();
      filterItems(filterList, condition);
      return filterList;
   }}
