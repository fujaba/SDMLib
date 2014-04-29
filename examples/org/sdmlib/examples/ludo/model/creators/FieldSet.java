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
   
package org.sdmlib.examples.ludo.model.creators;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.ludo.model.Field;
import org.sdmlib.models.modelsets.StringList;
import java.util.Collection;
import java.util.List;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.examples.ludo.model.creators.PointSet;
import java.awt.Point;

public class FieldSet extends SDMSet<Field>
{


   public FieldPO hasFieldPO()
   {
      org.sdmlib.examples.ludo.model.creators.ModelPattern pattern = new org.sdmlib.examples.ludo.model.creators.ModelPattern();
      
      FieldPO patternObject = pattern.hasElementFieldPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.ludo.model.Field";
   }


   public FieldSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Field>)value);
      }
      else if (value != null)
      {
         this.add((Field) value);
      }
      
      return this;
   }
   
   public FieldSet without(Field value)
   {
      this.remove(value);
      return this;
   }

   public StringList getColor()
   {
      StringList result = new StringList();
      
      for (Field obj : this)
      {
         result.add(obj.getColor());
      }
      
      return result;
   }

   public FieldSet hasColor(String value)
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         if (value.equals(obj.getColor()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FieldSet withColor(String value)
   {
      for (Field obj : this)
      {
         obj.setColor(value);
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

   public FieldSet hasKind(String value)
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         if (value.equals(obj.getKind()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FieldSet withKind(String value)
   {
      for (Field obj : this)
      {
         obj.setKind(value);
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

   public FieldSet hasX(int value)
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         if (value == obj.getX())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FieldSet withX(int value)
   {
      for (Field obj : this)
      {
         obj.setX(value);
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

   public FieldSet hasY(int value)
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         if (value == obj.getY())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FieldSet withY(int value)
   {
      for (Field obj : this)
      {
         obj.setY(value);
      }
      
      return this;
   }

   public PointSet getPoint()
   {
      PointSet result = new PointSet();
      
      for (Field obj : this)
      {
         result.add(obj.getPoint());
      }
      
      return result;
   }

   public FieldSet hasPoint(java.awt.Point value)
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         if (value == obj.getPoint())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FieldSet withPoint(Point value)
   {
      for (Field obj : this)
      {
         obj.setPoint(value);
      }
      
      return this;
   }

}

