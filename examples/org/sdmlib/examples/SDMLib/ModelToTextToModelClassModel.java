package org.sdmlib.models.transformations;

import org.junit.Test;
import org.sdmlib.CGUtil;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.storyboards.Storyboard;

public class ModelToTextToModelClassModel
{
   @Test
   public void modelToTextToModelClassModel()
   {
      Storyboard story = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.models.transformations");
      
      Clazz template = model.createClazz("Template")
            .withAttributes(
               "templateText", R.STRING, 
               "expandedText", R.STRING,
               "modelObject", CGUtil.shortClassName(Object.class.getName()), 
               "modelClassName", R.STRING,
               "listStart", R.STRING,
               "listSeparator", R.STRING,
               "listEnd", R.STRING, 
               "referenceLookup", R.BOOLEAN,
               "name", R.STRING 
                 );
      
      Clazz placeholderDescription = template.createClassAndAssoc("PlaceHolderDescription", "placeholders", R.MANY, "owners", R.MANY)
            .withAttributes(
               "textFragment", R.STRING,
               "value", R.STRING,
               "attrName", R.STRING,
               "isKeyAttribute", R.BOOLEAN,
               "prefix", R.STRING);
      
      Clazz choiceTemplate = model.createClazz("ChoiceTemplate")
            .withSuperClass(template)
            .withAssoc(template, "choices", R.MANY, "chooser", R.ONE);
      
      Clazz matchClazz = model.createClazz("Match", 
         "startPos", R.INT, 
         "endPos", R.INT, 
         "fullText", R.STRING, 
         "matchText", R.STRING);
      
      matchClazz.withAssoc(template, "template", R.ONE, "matches", R.MANY);
      matchClazz.withAssoc(placeholderDescription, "placeholder", R.ONE, "matches", R.MANY);
      matchClazz.withAttribute("modelObject", "Object");
      matchClazz.withAssoc(matchClazz, "subMatches", R.MANY, "parentMatch", R.ONE);
      
      placeholderDescription.withAssoc(template, "subTemplate", R.ONE, "parents", R.MANY);
      
      story.addClassDiagram(model);
      
      model.generate("src", "srchelpers");
      
      story.addLogEntry(R.DONE, "zuendorf", "23.02.2014 17:50:42", 50, 0, "Solving tricky parsing issue. Enabling reference lookup on demand");
      
      story.dumpHTML();
   }
}
