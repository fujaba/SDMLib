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
   
package org.sdmlib.serialization;

import java.beans.PropertyChangeSupport;

import org.sdmlib.models.debug.FlipBook;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.json.JsonIdMap;

public class SDMLibJsonIdMap extends JsonIdMap implements PropertyChangeInterface{
	/** The Constant JSON_PROPS. */
	public static final String JSON_HYPERREF = "hyperref";

	@Override
	public boolean addListener(Object object) 
	{
		if(super.addListener(object)){
			return true;
		}
		if (object instanceof PropertyChangeSupport) {
			((PropertyChangeSupport) object).addPropertyChangeListener(
					IdMap.UPDATE, getUpdateListener());
			return true;
		} else if (object instanceof PropertyChangeInterface)
		{
		   ((PropertyChangeInterface) object).getPropertyChangeSupport().addPropertyChangeListener(getUpdateListener());
		   return true;
		}
		return false;
		
	}
	
   public Object get(String attrName)
   {
//      int pos = attrName.indexOf('.');
//      String attribute = attrName;
//      
//      if (pos > 0)
//      {
//         attribute = attrName.substring(0, pos);
//      }
//      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
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
   
   public FlipBook createFlipBook()
   {
      FlipBook flipBook = new FlipBook().init(this);
      
      this.withUpdateMsgListener(flipBook);

      return flipBook;
   }

}

