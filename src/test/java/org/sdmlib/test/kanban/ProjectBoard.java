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
import org.sdmlib.storyboards.StoryboardImpl;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.AssociationTypes;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class ProjectBoard
{
     /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/kanban/ProjectBoard.java' type='text/x-java'>StoryboardInfrastructure</a></p>
    * <p>This storyboard tests the storyboard infrastructure. </p>
    * <p>Start: At first creating the html file just with text should work. </p>
    * <p><a name = 'step_1'>Step 1: Next we need to create some class model. This will be done in a parallel activity.</a></p>
    * <p><a name = 'step_2'>Step 2: With the class model we create an object model and try to dump it here.</a></p>
    * <p><a name = 'step_3'>Step 3: Well, dumping the class model would be great, either.</a></p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"KanbanEntry",
    *          "attributes":[
    *             "oldNoOfLogEntries : int",
    *             "phases : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"LogEntryStoryBoard"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"StoryboardStep",
    *          "attributes":[
    *             "text : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"StoryboardWall"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"StoryboardImpl",
    *          "attributes":[
    *             "rootDir : String",
    *             "stepCounter : int",
    *             "stepDoneCounter : int"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"StoryboardStep",
    *             "cardinality":"many",
    *             "property":"storyboardSteps"
    *          },
    *          "target":{
    *             "id":"StoryboardImpl",
    *             "cardinality":"one",
    *             "property":"storyboard"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"StoryboardWall",
    *             "cardinality":"one",
    *             "property":"wall"
    *          },
    *          "target":{
    *             "id":"StoryboardImpl",
    *             "cardinality":"one",
    *             "property":"storyboard"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"StoryboardImpl",
    *             "cardinality":"one",
    *             "property":"storyboard"
    *          },
    *          "target":{
    *             "id":"StoryboardWall",
    *             "cardinality":"one",
    *             "property":"wall"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"StoryboardImpl",
    *             "cardinality":"one",
    *             "property":"storyboard"
    *          },
    *          "target":{
    *             "id":"StoryboardStep",
    *             "cardinality":"many",
    *             "property":"storyboardSteps"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasStoryboardInfrastructureClassDiagram5", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * <p><a name = 'step_4'>Step 4: Show some internals</a></p>
    * <p>Internally, the class model looks like:</p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"C1 : ClassModel",
    *          "attributes":[
    *             "name=org.sdmlib.storyboards"
    *          ]
    *       }
    *    ],
    *    "edges":null
    * }   ;
    *    json["options"]={"canvasid":"canvasStoryboardInfrastructure9", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * @see <a href='../../../../../../../doc/StoryboardInfrastructure.html'>StoryboardInfrastructure.html</a>
*/
   @Test
   public void testStoryboardInfrastructure()
   {
      StoryboardImpl story = new StoryboardImpl();

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

      new Association(kanbanEntryClass).with("kanbanEntry").with(Association.ONE)
      	.with(AssociationTypes.AGGREGATION)
      	.with(new Association(logEntryClass).with("logEntries").with(Association.MANY));
      
      Clazz storyboardWallClass = model.createClazz("StoryboardWall");

      Clazz storyboardClass = model.createClazz(StoryboardImpl.class.getName()) 
            .withAttribute("rootDir", DataType.STRING)
            .withAttribute("stepCounter", DataType.INT) 
            .withAttribute("stepDoneCounter", DataType.INT);

      storyboardWallClass.withBidirectional(storyboardClass, "storyboard", Association.ONE, "wall", Association.ONE);

      Clazz storyboardStepClass = model.createClazz("StoryboardStep")
            .withAttribute("text", DataType.STRING);

      storyboardClass.withBidirectional(storyboardStepClass, "storyboardSteps", Association.MANY, "storyboard", Association.ONE);

      story.addClassDiagram(model);

      // model.getGenerator().withShowDiff(true);
      
      // model.generate();

      story.addStep("Show some internals");

      story.add("Internally, the class model looks like:");

      story.addObjectDiagram(model);

      story.dumpHTML();
   }

}



