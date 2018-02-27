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
   
package org.sdmlib.test.examples.groupaccount;
 

import java.beans.PropertyChangeSupport;

import org.junit.Test;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.Storyboard;
   
public class GroupAccountTests implements PropertyChangeInterface 
{
   /**
    * 
    * @see <a href='../../../../../../../../doc/GroupAccountStoryboard1.html'>GroupAccountStoryboard1.html</a>
    */
   @Test
   public void testGroupAccountStoryboard1()
   {
      Storyboard story = new Storyboard();
      
      story.add("This shall become a model distribution test, soon. ");
      
      story.dumpHTML();
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

