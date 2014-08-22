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
package org.sdmlib.templates;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.storyboards.Kanban;
import org.sdmlib.storyboards.Storyboard;

public class ModelToTextToModelClassModel
{
   @Test
   public void modelToTextToModelClassModel()
   {
      Storyboard story = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.models.transformations");
      
      Clazz template = model.createClazz("Template")
            .withAttribute("templateText", DataType.STRING) 
            .withAttribute("expandedText", DataType.STRING)
            .withAttribute("modelObject", DataType.OBJECT) 
            .withAttribute("modelClassName", DataType.STRING)
            .withAttribute("listStart", DataType.STRING)
            .withAttribute("listSeparator", DataType.STRING)
            .withAttribute("listEnd", DataType.STRING) 
            .withAttribute("referenceLookup", DataType.BOOLEAN)
            .withAttribute("name", DataType.STRING);
      
      Clazz placeholderDescription = model.createClazz("PlaceHolderDescription")
            .withAssoc(template, "owners", Card.MANY, "placeholders", Card.MANY)
            .withAttribute("textFragment", DataType.STRING)
            .withAttribute("value", DataType.STRING)
            .withAttribute("attrName", DataType.STRING)
            .withAttribute("isKeyAttribute", DataType.BOOLEAN)
            .withAttribute("prefix", DataType.STRING)
            .withAttribute("codeSnippet", DataType.STRING);
      
      
      
      model.createClazz("ChoiceTemplate")
            .withSuperClazz(template)
            .withAssoc(template, "choices", Card.MANY, "chooser", Card.ONE);
      
      Clazz matchClazz = model.createClazz("Match") 
            .withAttribute("startPos", DataType.INT) 
         .withAttribute("endPos", DataType.INT) 
         .withAttribute("fullText", DataType.STRING) 
         .withAttribute("matchText", DataType.STRING);
      
      matchClazz.withAssoc(template, "template", Card.ONE, "matches", Card.MANY);
      matchClazz.withAssoc(placeholderDescription, "placeholder", Card.ONE, "matches", Card.MANY);
      matchClazz.withAttribute("modelObject", DataType.OBJECT);
      matchClazz.withAssoc(matchClazz, "subMatches", Card.MANY, "parentMatch", Card.ONE);
      
      placeholderDescription.withAssoc(template, "subTemplate", Card.ONE, "parents", Card.MANY);
      
      story.addClassDiagram(model);
      
      model.generate("src/main/java");
      
      story.addLogEntry(Kanban.DONE, "zuendorf", "23.02.2014 17:50:42", 50, 0, "Solving tricky parsing issue. Enabling reference lookup on demand");
      
      story.dumpHTML();
   }
}
