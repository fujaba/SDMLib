/*
   Copyright (c) 2012 Albert Zündorf

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

package org.sdmlib.kanban;

import javax.print.DocFlavor.STRING;

import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.storyboards.KanbanEntry;
import org.sdmlib.storyboards.LogEntry;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardManager;

public class ProjectBoard
{
   @Test
   public void testExtendStoryboardByAddToDoMethod()
   {
      Storyboard storyboard = new Storyboard("test", "ExtendStoryboardByAddToDoMethod");
      
      storyboard.add("Start situation: ",
         DONE, "zuendorf", "01.11.2012 12:33:42", 1, 0);
      
      storyboard.dumpHTML();
   }

   public static final String MODELING = "modeling";
   public static final String ACTIVE = "active";
   public static final String DONE = "done";
   public static final String IMPLEMENTATION = "implementation";
   public static final String BACKLOG = "backlog";

   @Test
   public void testAdmin()
   {
      StoryboardManager man = StoryboardManager.get();
      
      
      KanbanEntry kanbanBoard = man.loadOldKanbanEntries()
         .withName("SDMLibProject")
         .withPhase(ACTIVE);

      KanbanEntry sprint1 = kanbanBoard.findOrCreate("Sprint.001.Booting")
            .withLastDeveloper("zuendorf")
            .withPhase(ACTIVE)
            .withParent(kanbanBoard);
      
      KanbanEntry entry = kanbanBoard.findOrCreate("StoryboardInfrastructure")
            .withParent(sprint1);

      LogEntry logEntry;
      
      entry = kanbanBoard.findOrCreate("ProjectManagement")
            .withParent(kanbanBoard);

      logEntry = entry.findOrCreateLogEntry("07.05.2012 23:38:42", ACTIVE)
            .withDeveloper("zuendorf")
            .withHoursSpend(0)
            .withHoursRemainingInTotal(0);
      
      entry = kanbanBoard.findOrCreate("StudyRightClassesCodeGen")
            .withParent(sprint1);
      
      entry = kanbanBoard.findOrCreate("StudyRightObjectStoryboards")
            .withParent(sprint1);
      // entry.linkToTest("examples", "org.sdmlib.examples.studyright.StudyRightClassesCodeGen", entry.getName());
      
      entry = kanbanBoard.findOrCreate("StudyRightReverseClassModel")
            .withParent(sprint1);
      // entry.linkToTest("examples", "org.sdmlib.examples.studyright.StudyRightClassesCodeGen", entry.getName());
      
      
      
      entry = kanbanBoard.findOrCreate("ClassModelCodeGen")
            .withParent(sprint1);
      
      
      entry = kanbanBoard.findOrCreate("GroupAccountCodegen")
            .withParent(sprint1);
      // entry.linkToTest("examples", "org.sdmlib.examples.groupAccount.GroupAccountTests", entry.getName());
      
      entry = kanbanBoard.findOrCreate("GroupAccountRuleRecognition")
            .withParent(sprint1);
      // entry.linkToTest("examples", "org.sdmlib.examples.groupAccount.GroupAccountTests", entry.getName());
      
      entry = kanbanBoard.findOrCreate("TransformationsCodegen")
            .withParent(sprint1);
      // entry.linkToTest("test", "org.sdmlib.models.transformations.TransformationsCodeGen", entry.getName());
      
      KanbanEntry sprint2 = kanbanBoard.findOrCreate("Sprint.002.Transformations")
            .withLastDeveloper("zuendorf")
            .withPhase(ACTIVE)
            .withParent(kanbanBoard);
      
      entry = kanbanBoard.findOrCreate("PatternModelCodeGen")
            .withParent(sprint2)
      //      .linkToTest("test", "org.sdmlib.models.patterns.PatternModelCodeGen", entry.getName())
            ;
      
      entry = kanbanBoard.findOrCreate("GenericObjectDiagram")
            .withParent(sprint1);
     
      // entry.linkToTest("test", "org.sdmlib.models.objects.GenericObjectsTest", entry.getName());
      
      entry = kanbanBoard.findOrCreate("LudoModel")
            .withParent(sprint2)
      //      .linkToTest("examples", "org.sdmlib.examples.ludo.LudoModel", entry.getName())
            ;

      entry = kanbanBoard.findOrCreate("LudoStoryboard")
            .withParent(sprint2)
      //      .linkToTest("examples", "org.sdmlib.examples.ludo.LudoStoryboard", entry.getName())
            ;

      man.dumpKanban();
   }

   @Test
   public void testTodoEntries()
   {
      Storyboard storyboard = new Storyboard("test");
      
      storyboard.add("It should be possible to add todo entries to the kanban board without adding a storyboard for them. "
         , MODELING, "zuendorf", "21.08.2012 15:57:42", 0, 4);
      
      storyboard.addToDo("ExtendStoryboardByAddToDoMethod", DONE, "zuendorf", "21.08.2012 17:53:42", 2, 0)
      .linkToTest("test", this.getClass().getName());
      
      storyboard.dumpHTML();
   }
   
   
   @Test
   public void testStoryboardInfrastructure()
   {
      // file:///C:/Users/zuendorf/eclipseworkspaces/indigo/SDMLib/doc/StoryboardInfrastructure.html
      Storyboard storyboard = new Storyboard();
      
      storyboard.add("This storyboard tests the storyboard infrastructure. ");
      storyboard.add("At first creating the html file just with text should work. ");
      storyboard.add("Next we need to create some class model. This will be done in a parallel activity.");
      storyboard.add("With the class model we create an object model and try to dump it here.");
      storyboard.add("Well, dumping the class model would be great, either.");

      storyboard.add("need to restructure design: logentries shall be direct kids of kanbanentries. \n" +
            "(has been below phase entries before.)\n" +
            "phase entries will be used for planning, in future");
      
      ClassModel model = new ClassModel("org.sdmlib.storyboards"); 
      
      Clazz kanbanEntryClass = new Clazz("org.sdmlib.storyboards.KanbanEntry", "oldNoOfLogEntries", R.INT);

      Clazz logEntryClass = new Clazz("org.sdmlib.storyboards.LogEntry");
      
      new Association()
      .withSource("kanbanEntry", kanbanEntryClass, R.ONE, Role.AGGREGATION)
      .withTarget("logEntries", logEntryClass, R.MANY);
      
      Clazz storyboardWallClass = model.createClazz("StoryboardWall");
      
      Clazz storyboardClass = model.createClazz(Storyboard.class.getName(), 
               "rootDir", R.STRING,
               "stepCounter", R.INT, 
               "stepDoneCounter", R.INT
                  );
      
      storyboardWallClass.withAssoc(storyboardClass, "storyboard", R.ONE, "wall", R.ONE);
      
      Clazz storyboardStepClass = storyboardClass.createClassAndAssoc("StoryboardStep", "storyboardSteps", R.MANY, "storyboard", R.ONE)
            .withAttributes("text", R.STRING);
      
      storyboard.addSVGImage(model.dumpClassDiagram("src", "StoryboardClasses.001"));
      
      model.generate("src", "srchelpers");

      storyboard.add(" Editing the log entries works now fine as part of the add method. " , 
            DONE, "zuendorf", "07.05.2012 23:36:42", 0, 0);
      
      storyboard.add("Seems that we have solved the problem with the sorting of log entries after loading. " , 
         DONE, "zuendorf", "19.05.2012 19:22:42", 1, 0);
   
      storyboard.dumpHTML();
   }
}



