package org.sdmlib.examples.groupAccount;

import static org.sdmlib.models.classes.Role.R.DOUBLE;
import static org.sdmlib.models.classes.Role.R.MANY;
import static org.sdmlib.models.classes.Role.R.ONE;
import static org.sdmlib.models.classes.Role.R.STRING;

import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardManager;

public class GroupAccountClassModel
{
   @Test
   public void testGroupAccountCodegen()
   {
      Storyboard storyboard = new Storyboard("examples");
      
      storyboard.setSprint("Sprint.002.Examples");
      
      
      storyboard.add("Start situation: Nothing here yet. Generate classes",
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
      
      storyboard.addClassDiagram(model);
      
      storyboard.dumpHTML();
   }

   private static final String MODELING = "modeling";
   private static final String ACTIVE = "active";
   private static final String DONE = "done";
   private static final String IMPLEMENTATION = "implementation";
   private static final String BACKLOG = "backlog";


}
