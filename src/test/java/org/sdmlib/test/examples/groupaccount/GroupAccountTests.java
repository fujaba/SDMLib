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
import org.sdmlib.storyboards.*;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.groupaccount.model.GroupAccount;
import org.sdmlib.test.examples.groupaccount.model.Item;
import org.sdmlib.test.examples.groupaccount.model.Person;
import org.sdmlib.test.examples.groupaccount.model.util.PersonSet;
   
public class GroupAccountTests implements PropertyChangeInterface 
{
   @Test
   public void testGroupAccountStoryboard1()
   {
      Storyboard story = new Storyboard();
      
      GroupAccount g1 = new GroupAccount();
      
      Person albert = g1.createPersons()
      .withName("Albert");
      
      Person nina = g1.createPersons()
      .withName("Nina");
      
      Person artjom = new Person()
      .withName("Artjom")
      .withParent(g1);

      Item beer = albert.createItem()
      .withDescription("Beer")
      .withValue(12);
      
      Item bread = new Item()
      .withBuyer(nina)
      .withDescription("Bread")
      .withValue(3);
      
      Item meat = new Item()
      .withBuyer(artjom)
      .withDescription("Meat")
      .withValue(6);

      // JsonIdMap createIdMap = org.sdmlib.test.examples.groupAccount.creators.GroupAccountCreator.createIdMap("az42");
      story.addObjectDiagram(g1);
      
      story.add("We call updateBalances() to compute the correct balances.\n");
      
      story.markCodeStart();
      g1.updateBalances();
      story.addCode("examples");
      
      story.add("Now see updateBalances() as code: ");
      
      String methodText = story.getMethodText("src/test/java", "org.sdmlib.test.examples.groupAccount.model.GroupAccount", "updateBalances()");
      
      story.add(methodText);
      
      
      story.add("The effects of executing updateBalances() is shown in: ");
      
      story.addObjectDiagram(g1);

      story.assertEquals("Balance for Albert is now ", 5, albert.getBalance(), 0.0001);

      story.assertEquals("The sum of all balances is ", 0, g1.getPersons().getBalance().sum(), 0.0001);
      
      story.assertEquals("Found one person with name Albert ", 1,
         g1.getPersons().hasName("Albert").size(), 0.0001);
      
      story.assertTrue("Albert has name Albert", g1.getPersons().hasName("Albert").first() == albert);
      
      PersonSet contributors = g1.getPersons().getItem().getBuyer();
      
      story.add("Persons that have bought at least one item: " + contributors.toString());
      
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

