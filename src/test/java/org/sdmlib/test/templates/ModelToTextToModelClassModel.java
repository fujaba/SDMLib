/*
   Copyright (c) 2014 zuendorf 
   
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
package org.sdmlib.test.templates;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class ModelToTextToModelClassModel
{
     /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/templates/ModelToTextToModelClassModel.java' type='text/x-java'>modelToTextToModelClassModel</a></p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"ChoiceTemplate"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Match",
    *          "attributes":[
    *             "endPos : int",
    *             "fullText : String",
    *             "matchText : String",
    *             "modelObject : Object",
    *             "startPos : int"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"PlaceHolderDescription",
    *          "attributes":[
    *             "attrName : String",
    *             "isKeyAttribute : boolean",
    *             "prefix : String",
    *             "textFragment : String",
    *             "value : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Template",
    *          "attributes":[
    *             "expandedText : String",
    *             "listEnd : String",
    *             "listSeparator : String",
    *             "listStart : String",
    *             "modelClassName : String",
    *             "modelObject : Object",
    *             "name : String",
    *             "referenceLookup : boolean",
    *             "templateText : String"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"ChoiceTemplate",
    *             "cardinality":"one",
    *             "property":"chooser"
    *          },
    *          "target":{
    *             "id":"Template",
    *             "cardinality":"many",
    *             "property":"choices"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"ChoiceTemplate",
    *             "cardinality":"one",
    *             "property":"choicetemplate"
    *          },
    *          "target":{
    *             "id":"Template",
    *             "cardinality":"one",
    *             "property":"template"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Match",
    *             "cardinality":"many",
    *             "property":"matches"
    *          },
    *          "target":{
    *             "id":"Template",
    *             "cardinality":"one",
    *             "property":"template"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Match",
    *             "cardinality":"many",
    *             "property":"matches"
    *          },
    *          "target":{
    *             "id":"PlaceHolderDescription",
    *             "cardinality":"one",
    *             "property":"placeholder"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Match",
    *             "cardinality":"many",
    *             "property":"subMatches"
    *          },
    *          "target":{
    *             "id":"Match",
    *             "cardinality":"one",
    *             "property":"parentMatch"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"PlaceHolderDescription",
    *             "cardinality":"many",
    *             "property":"parents"
    *          },
    *          "target":{
    *             "id":"Template",
    *             "cardinality":"one",
    *             "property":"subTemplate"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"PlaceHolderDescription",
    *             "cardinality":"one",
    *             "property":"placeholder"
    *          },
    *          "target":{
    *             "id":"Match",
    *             "cardinality":"many",
    *             "property":"matches"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"PlaceHolderDescription",
    *             "cardinality":"many",
    *             "property":"placeholders"
    *          },
    *          "target":{
    *             "id":"Template",
    *             "cardinality":"many",
    *             "property":"owners"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Template",
    *             "cardinality":"many",
    *             "property":"choices"
    *          },
    *          "target":{
    *             "id":"ChoiceTemplate",
    *             "cardinality":"one",
    *             "property":"chooser"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Template",
    *             "cardinality":"many",
    *             "property":"owners"
    *          },
    *          "target":{
    *             "id":"PlaceHolderDescription",
    *             "cardinality":"many",
    *             "property":"placeholders"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Template",
    *             "cardinality":"one",
    *             "property":"subTemplate"
    *          },
    *          "target":{
    *             "id":"PlaceHolderDescription",
    *             "cardinality":"many",
    *             "property":"parents"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Template",
    *             "cardinality":"one",
    *             "property":"template"
    *          },
    *          "target":{
    *             "id":"Match",
    *             "cardinality":"many",
    *             "property":"matches"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"ChoiceTemplate",
    *             "cardinality":"one",
    *             "property":"choicetemplate"
    *          },
    *          "target":{
    *             "id":"Template",
    *             "cardinality":"one",
    *             "property":"template"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasmodelToTextToModelClassModelClassDiagram0", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * @see <a href='../../../../../../../doc/modelToTextToModelClassModel.html'>modelToTextToModelClassModel.html</a>
*/
   @Test
   public void modelToTextToModelClassModel()
   {
      Storyboard story = new Storyboard();

      ClassModel model = new ClassModel("org.sdmlib.models.transformations");

      Clazz template = model.createClazz("Template")
         .withAttribute("modelObject", DataType.OBJECT)
         .withAttribute("templateText", DataType.STRING)
         .withAttribute("expandedText", DataType.STRING)
         .withAttribute("modelClassName", DataType.STRING)
         .withAttribute("listStart", DataType.STRING)
         .withAttribute("listSeparator", DataType.STRING)
         .withAttribute("listEnd", DataType.STRING)
         .withAttribute("referenceLookup", DataType.BOOLEAN)
         .withAttribute("name", DataType.STRING);

      Clazz placeholderDescription = model.createClazz("PlaceHolderDescription")
         .withBidirectional(template, "owners", Association.MANY, "placeholders", Association.MANY)
         .withAttribute("textFragment", DataType.STRING)
         .withAttribute("value", DataType.STRING)
         .withAttribute("attrName", DataType.STRING)
         .withAttribute("isKeyAttribute", DataType.BOOLEAN)
         .withAttribute("prefix", DataType.STRING);

      model.createClazz("ChoiceTemplate")
         .withSuperClazz(template)
         .withBidirectional(template, "choices", Association.MANY, "chooser", Association.ONE);

      Clazz matchClazz = model.createClazz("Match")
         .withAttribute("startPos", DataType.INT)
         .withAttribute("endPos", DataType.INT)
         .withAttribute("fullText", DataType.STRING)
         .withAttribute("matchText", DataType.STRING);

      matchClazz.withBidirectional(template, "template", Association.ONE, "matches", Association.MANY);
      matchClazz.withBidirectional(placeholderDescription, "placeholder", Association.ONE, "matches", Association.MANY);
      matchClazz.withAttribute("modelObject", DataType.OBJECT);
      matchClazz.withBidirectional(matchClazz, "subMatches", Association.MANY, "parentMatch", Association.ONE);

      placeholderDescription.withBidirectional(template, "subTemplate", Association.ONE, "parents", Association.MANY);

      story.addClassDiagram(model);

      model.generate("src/main/java");

      story.dumpHTML();
   }
}
