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

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.AssociationTypes;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class ProjectBoard
{
     /**
    * 
    * @see <a href='../../../../../../../doc/StoryboardInfrastructure.html'>StoryboardInfrastructure.html</a>
*/
   @Test
   public void testStoryboardInfrastructure()
   {
      Storyboard story = new Storyboard();

      story.add("This storyboard tests the storyboard infrastructure. ");
      story.addStep("At first creating the html file just with text should work. ");
      story.addStep("Next we need to create some class model. This will be done in a parallel activity.");
      story.addStep("With the class model we create an object model and try to dump it here.");
      story.addStep("Well, dumping the class model would be great, either.");

      ClassModel model = new ClassModel("org.sdmlib.storyboards"); 

      Clazz kanbanEntryClass = model.createClazz("KanbanEntry")
            .withAttribute("oldNoOfLogEntries", DataType.INT)  
            .withAttribute("phases", DataType.STRING);

      Clazz logEntryClass = model.createClazz("LogEntryStoryBoard");

      new Association(kanbanEntryClass).with("kanbanEntry").with(Cardinality.ONE)
      	.with(AssociationTypes.AGGREGATION)
      	.with(new Association(logEntryClass).with("logEntries").with(Cardinality.MANY));
      
      Clazz storyboardWallClass = model.createClazz("StoryboardWall");

      Clazz storyboardClass = model.createClazz(Storyboard.class.getName()) 
            .withAttribute("rootDir", DataType.STRING)
            .withAttribute("stepCounter", DataType.INT) 
            .withAttribute("stepDoneCounter", DataType.INT);

      storyboardWallClass.withBidirectional(storyboardClass, "storyboard", Cardinality.ONE, "wall", Cardinality.ONE);

      Clazz storyboardStepClass = model.createClazz("StoryboardStep")
            .withAttribute("text", DataType.STRING);

      storyboardClass.withBidirectional(storyboardStepClass, "storyboardSteps", Cardinality.MANY, "storyboard", Cardinality.ONE);

      story.addClassDiagram(model);

      // model.getGenerator().withShowDiff(true);
      
      model.generate();

      story.addStep("Show some internals");

      story.add("Internally, the class model looks like:");

      story.addObjectDiagram(model);

      story.dumpHTML();
   }

}



