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

import org.junit.Test;
import org.sdmlib.scenarios.KanbanEntry;
import org.sdmlib.scenarios.LogEntry;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;

public class ProjectBoard
{
   public static final String MODELING = "modeling";
   public static final String ACTIVE = "active";
   public static final String DONE = "done";
   public static final String IMPLEMENTATION = "implementation";
   public static final String BACKLOG = "backlog";

   @Test
   public void testAdmin()
   {
      ScenarioManager man = ScenarioManager.get();
      
      
      KanbanEntry kanbanBoard = man.loadOldKanbanEntries();

      KanbanEntry sprint1 = kanbanBoard.findOrCreate("Sprint.001.Booting")
            .withLastDeveloper("zuendorf")
            .withPhase(ACTIVE)
            .withParent(kanbanBoard);
      
      KanbanEntry entry = kanbanBoard.findOrCreate("StudyRightClassesCodeGen")
            .withParent(sprint1);

      LogEntry logEntry = entry.findOrCreateLogEntry("12.03.2012 00:12:00", BACKLOG)
            .withDeveloper("zuendorf")
            .withHoursSpend(1)
            .withHoursRemainingInTotal(20);

      entry.linkToTest("examples.org.sdmlib.examples.studyright.StudyRightClassesCodeGen", entry.getName());

      man.dumpKanban();
   }

   @Test
   public void testScenarioInfrastructure()
   {
      Scenario scenario = new Scenario("ScenarioInfrastructure");
      
      scenario.add("This scenario tests the scenario infrastructure. ");
      scenario.add("At first creating the html file just with text should work. ");
      scenario.add("Next we need to create some class model. This will be done in a parallel activity.");
      scenario.add("With the class model we create an object model and try to dump it here.");
      scenario.add("Well, dumping the class model would be great, either.");
      
      scenario.addLogEntry(new LogEntry()
      .withDate("14.03.2012 14:03:42")
      .withPhase(DONE)
      .withDeveloper("zuendorf")
      .withHoursSpend(1)
      .withHoursRemainingInTotal(0)
      .withComment("Fixed some bugs. Works reasonable now."));
      
      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();
   }
}

