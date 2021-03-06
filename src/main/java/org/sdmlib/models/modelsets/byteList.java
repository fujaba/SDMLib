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
   
package org.sdmlib.models.modelsets;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import org.sdmlib.serialization.PropertyChangeInterface;

public class byteList extends ArrayList<Byte> implements PropertyChangeInterface
{

	private static final long serialVersionUID = -2345886837862490672L;

	public byte sum()
   {
      byte result = 0;
      
      for (byte x : this)
      {
         result += x;
      }
      
      return result;
   }
	
	public byte max() {
		byte max = Byte.MIN_VALUE;

		for (byte x : this) {
			if (x > max) {
				max = x;
			}
		}
		
		return max;
	}
	
	public byte min() {
	   byte min = Byte.MAX_VALUE;

		for (byte x : this) {
			if (x < min) {
				min = x;
			}
		}
		
		return min;
	}
   

   
   //==========================================================================
   public Object get(String attrName)
   {
//      int pos = attrName.indexOf('.');
//      String attribute = attrName;
//      
//      if (pos > 0)
//      {
//         attribute = attrName.substring(0, pos);
//      }
      
      return null;
   }

   
   //==========================================================================
   public boolean set(String attrName, Object value)
   {
      return false;
   }

   
   //==========================================================================
   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   public void removeYou()
   {
	   getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }
}

