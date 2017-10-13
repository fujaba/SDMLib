/*
   Copyright (c) 2014 NeTH 
   
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
   
package org.sdmlib.test.examples.mancala.referencemodel.util;

import java.util.Collection;

import org.sdmlib.test.examples.mancala.referencemodel.Color;

import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.interfaces.Condition;

public class ColorSet extends SimpleSet<Color>
{


   public ColorPO hasColorPO()
   {
      return new ColorPO(this.toArray(new Color[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public ColorSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Color>)value);
      }
      else if (value != null)
      {
         this.add((Color) value);
      }
      
      return this;
   }
   
   public ColorSet without(Color value)
   {
      this.remove(value);
      return this;
   }


   public static final ColorSet EMPTY_SET = new ColorSet().withFlag(ColorSet.READONLY);


   public ColorPO filterColorPO()
   {
      return new ColorPO(this.toArray(new Color[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.mancala.referencemodel.Color";
   }

   public ColorSet()
   {
      // empty
   }

   public ColorSet(Color... objects)
   {
      for (Color obj : objects)
      {
         this.add(obj);
      }
   }

   public ColorSet(Collection<Color> objects)
   {
      this.addAll(objects);
   }


   public ColorPO createColorPO()
   {
      return new ColorPO(this.toArray(new Color[this.size()]));
   }


   @Override
   public ColorSet getNewList(boolean keyValue)
   {
      return new ColorSet();
   }


   public ColorSet filter(Condition<Color> condition) {
      ColorSet filterList = new ColorSet();
      filterItems(filterList, condition);
      return filterList;
   }}
