/*
   Copyright (c) 2013 NeTH 
   
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
   
package org.sdmlib.model.classes;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;

public class ConsistenceTest implements PropertyChangeInterface {

	@Test
	public void testEmptyClass() {

		ClassModel model = new ClassModel();
		
		Clazz field = new Clazz("org.sdmlib.model.test.consistence.Field");
		
		Clazz bord = new Clazz("org.sdmlib.model.test.consistence.Border")
				.withAssoc(field, "field", R.ONE, "border", R.ONE)
				.withAttribute("borderLocation", "String")
				.withAttribute("connectedTo", "String")
				.withAttribute("connectedToRev", "String")
				.withAttribute("serverId", "String")
				.withAttribute("road", "String");
		
		bord.withAssoc(bord, "connectedTo", R.ONE, "connectedToRev", R.ONE);
		
		model.generate("test", "testhelpers");
	}


   
   //==========================================================================
   
   public Object get(String attrName)
   {
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
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

