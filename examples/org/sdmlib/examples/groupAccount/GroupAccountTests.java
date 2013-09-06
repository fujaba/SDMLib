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
   
package org.sdmlib.examples.groupAccount;
   
import static org.sdmlib.models.classes.Role.R.DOUBLE;
import static org.sdmlib.models.classes.Role.R.MANY;
import static org.sdmlib.models.classes.Role.R.ONE;
import static org.sdmlib.models.classes.Role.R.STRING;

import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.transformations.TransformOp;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardManager;
import org.sdmlib.utils.PropertyChangeInterface;

import java.beans.PropertyChangeSupport;
   
public class GroupAccountTests implements PropertyChangeInterface 
{
   @Test
   public void testGroupAccountStoryboard1()
   {
      Storyboard storyboard = new Storyboard("examples");
      
      storyboard.add("Start situation: classes have been generated. Now create some example object structure",
         MODELING, "zuendorf", "04.04.2012 00:26:59", 0, 0);
      
      GroupAccount g1 = new GroupAccount();
      
      Person albert = g1.createPersons()
      .withName("Albert");
      
      Person nina = g1.createPersons()
      .withName("Nina");
      
      Person artjom = new Person()
      .withName("Artjom")
      .withParent(g1);

      Item beer = new Item()
      .withBuyer(albert)
      .withDescription("Beer")
      .withValue(12)
      .withParent(g1);
      
      Item bread = new Item()
      .withBuyer(nina)
      .withDescription("Bread")
      .withValue(3)
      .withParent(g1);
      
      Item meat = new Item()
      .withBuyer(artjom)
      .withDescription("Meat")
      .withValue(6)
      .withParent(g1);

      // JsonIdMap createIdMap = org.sdmlib.examples.groupAccount.creators.GroupAccountCreator.createIdMap("az42");
      storyboard.addObjectDiagram(g1);
      
      storyboard.add("We call updateBalances() to compute the correct balances.\n",
         MODELING, "zuendorf", "15.04.2012 17:18:42", 8, 2);
      
      storyboard.markCodeStart();
      g1.updateBalances();
      storyboard.addCode("examples");
      
      storyboard.add("Now see updateBalances() as code: ");
      
      String methodText = storyboard.getMethodText("examples", "org.sdmlib.examples.groupAccount.GroupAccount", "updateBalances()");
      
      storyboard.add(methodText);
      
      storyboard.add("The effects of executing updateBalances() is shown in: ");
      
      storyboard.addObjectDiagram(g1);

      storyboard.assertEquals("Balance for Albert is now ", 5, albert.getBalance(), 0.0001);

      storyboard.assertEquals("The sum of all balances is ", 0, g1.getPersons().getBalance().sum(), 0.0001);
      
      storyboard.dumpHTML();
   }

   @Test
   public void testGroupAccountCodegen()
   {
      Storyboard storyboard = new Storyboard("examples");
      
      storyboard.add("Start situation: Nothing here yet. Generated classes",
         DONE, "zuendorf", "04.04.2012 00:11:32", 1, 0);
      
      ClassModel model = new ClassModel("org.sdmlib.examples.groupAccount");
      
      Clazz groupAccountClass = new Clazz("GroupAccount")
      .withMethods("initAccounts(double,String)", DOUBLE)
      .withMethods("updateBalances()", "void");
      
      //      groupAccountClass.withRunningConstants("RED", "YELLOW", "GREEN");
      //      groupAccountClass.withConstant("NORTH", "north");
      //      groupAccountClass.withConstant("CARD", 3);
      
      Clazz personClass = new Clazz(
         "Person",
         "name", STRING,
         "balance", DOUBLE);
      
      groupAccountClass.withAssoc(personClass, "persons", MANY, "parent", ONE);
      
      Clazz itemClass = new Clazz("Item", 
         "description", STRING, 
         "value", DOUBLE);
      
      groupAccountClass.withAssoc(itemClass, "items", MANY, "parent", ONE);
      
      new Association()
      .withSource("buyer", personClass, ONE)
      .withTarget("items", itemClass, MANY);

      // model.updateFromCode("examples", "examples", "org.sdmlib.examples.groupAccount");
      
      // model.insertModelCreationCodeHere("examples");
      
      // model.removeAllGeneratedCode("examples", "examples", "examples");
      
      model.generate("examples", "examples");
      
      storyboard.addImage(model.dumpClassDiagram("examples", "GroupAccountClassDiag01"));

      storyboard.add("Resolved Bug: creatorcreator class is no longer growing on each run. ",
         DONE, "zuendorf", "24.05.2012 00:16:18", 1, 0);
      
      StoryboardManager.get()
      .add(storyboard)
      .dumpHTML();
   }

   private static final String MODELING = "modeling";
   private static final String ACTIVE = "active";
   private static final String DONE = "done";
   private static final String IMPLEMENTATION = "implementation";
   private static final String BACKLOG = "backlog";

   
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

