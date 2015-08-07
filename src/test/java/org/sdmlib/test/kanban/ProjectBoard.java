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

package org.sdmlib.test.kanban;

import java.util.LinkedHashMap;

import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Role;
import org.sdmlib.storyboards.Kanban;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardWall;

public class ProjectBoard
{
   @Test
   public void testStoryboardInfrastructure()
   {
      Storyboard storyboard = new Storyboard();

      storyboard.add("This storyboard tests the storyboard infrastructure. ");
      storyboard.addStep("At first creating the html file just with text should work. ");
      storyboard.addStep("Next we need to create some class model. This will be done in a parallel activity.");
      storyboard.addStep("With the class model we create an object model and try to dump it here.");
      storyboard.addStep("Well, dumping the class model would be great, either.");

      storyboard.add("need to restructure design: logentries shall be direct kids of kanbanentries. \n" +
            "(has been below phase entries before.)\n" +
            "phase entries will be used for planning, in future");

      ClassModel model = new ClassModel("org.sdmlib.storyboards"); 

      Clazz kanbanEntryClass = model.createClazz("KanbanEntry")
            .withAttribute("oldNoOfLogEntries", DataType.INT)  
            .withAttribute("phases", DataType.STRING);

      Clazz logEntryClass = model.createClazz("LogEntryStoryBoard");

      new Association()
      .withSource(new Role(kanbanEntryClass, "kanbanEntry", Card.ONE).withKind(Role.AGGREGATION))
      .withTarget(logEntryClass, "logEntries", Card.MANY);

      Clazz storyboardWallClass = model.createClazz("StoryboardWall");

      Clazz storyboardClass = model.createClazz(Storyboard.class.getName()) 
            .withAttribute("rootDir", DataType.STRING)
            .withAttribute("stepCounter", DataType.INT) 
            .withAttribute("stepDoneCounter", DataType.INT);

      storyboardWallClass.withAssoc(storyboardClass, "storyboard", Card.ONE, "wall", Card.ONE);

      Clazz storyboardStepClass = model.createClazz("StoryboardStep")
            .withAttribute("text", DataType.STRING);

      storyboardClass.withAssoc(storyboardStepClass, "storyboardSteps", Card.MANY, "storyboard", Card.ONE);

      storyboard.addClassDiagram(model);

      // model.getGenerator().withShowDiff(true);
      
      model.generate();

      storyboard.addStep("Show some internals");

      storyboard.add("Internally, the class model looks like:");

      storyboard.addObjectDiagram(model);

      storyboard.dumpHTML();
   }

}



