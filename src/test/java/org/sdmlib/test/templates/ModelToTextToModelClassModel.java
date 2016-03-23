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
import org.sdmlib.storyboards.StoryPage;

import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class ModelToTextToModelClassModel
{
     /**
    * 
    * @see <a href='../../../../../../../doc/modelToTextToModelClassModel.html'>modelToTextToModelClassModel.html</a>
*/
   @Test
   public void modelToTextToModelClassModel()
   {
      StoryPage story = new StoryPage();

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
         .withBidirectional(template, "owners", Cardinality.MANY, "placeholders", Cardinality.MANY)
         .withAttribute("textFragment", DataType.STRING)
         .withAttribute("value", DataType.STRING)
         .withAttribute("attrName", DataType.STRING)
         .withAttribute("isKeyAttribute", DataType.BOOLEAN)
         .withAttribute("prefix", DataType.STRING);

      model.createClazz("ChoiceTemplate")
         .withSuperClazz(template)
         .withBidirectional(template, "choices", Cardinality.MANY, "chooser", Cardinality.ONE);

      Clazz matchClazz = model.createClazz("Match")
         .withAttribute("startPos", DataType.INT)
         .withAttribute("endPos", DataType.INT)
         .withAttribute("fullText", DataType.STRING)
         .withAttribute("matchText", DataType.STRING);

      matchClazz.withBidirectional(template, "template", Cardinality.ONE, "matches", Cardinality.MANY);
      matchClazz.withBidirectional(placeholderDescription, "placeholder", Cardinality.ONE, "matches", Cardinality.MANY);
      matchClazz.withAttribute("modelObject", DataType.OBJECT);
      matchClazz.withBidirectional(matchClazz, "subMatches", Cardinality.MANY, "parentMatch", Cardinality.ONE);

      placeholderDescription.withBidirectional(template, "subTemplate", Cardinality.ONE, "parents", Cardinality.MANY);

      story.addClassDiagram(model);

      model.generate("src/main/java");

      story.dumpHTML();
   }
}
