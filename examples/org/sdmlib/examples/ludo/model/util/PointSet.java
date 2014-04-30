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
import java.awt.Point;
import org.sdmlib.models.modelsets.StringList;
import java.util.Collection;
import org.sdmlib.models.modelsets.intList;
import java.util.List;

public class PointSet extends SDMSet<Point>
{


   public PointPO hasPointPO()
   {
      org.sdmlib.examples.ludo.model.util.ModelPattern pattern = new org.sdmlib.examples.ludo.model.util.ModelPattern();
      
      PointPO patternObject = pattern.hasElementPointPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   @Override
   public String getEntryType()
   {
      return "java.awt.Point";
   }


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
         obj.x = (value);
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
         obj.y = (value);
      }
      
      return this;
   }

}

