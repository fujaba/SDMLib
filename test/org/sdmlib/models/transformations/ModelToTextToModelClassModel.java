package org.sdmlib.models.transformations;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.storyboards.Storyboard;

public class ModelToTextToModelClassModel
{
   @Test
   public void modelToTextToModelClassModel()
   {
      Storyboard storyboard = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.models.transformations");
      
      Clazz template = model.createClazz("Template")
            .withAttributes(
               "templateText", R.STRING, 
               "expandedText", R.STRING,
               "modelObject", Object.class.getName(), 
               "modelClassName", R.STRING,
               "listStart", R.STRING,
               "listSeparator", R.STRING,
               "listEnd", R.STRING
                 );
      
      Clazz placeholderDescription = template.createClassAndAssoc("PlaceHolderDescription", "placeholders", R.MANY, "owners", R.MANY)
            .withAttributes(
               "textFragment", R.STRING,
               "value", R.STRING,
               "attrName", R.STRING,
               "isKeyAttribute", R.BOOLEAN);
      
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
      
      placeholderDescription.withAssoc(template, "subTemplate", R.ONE, "parent", R.ONE);
      
      storyboard.addClassDiagram(model);
      
      model.generate("src", "srchelpers");
      
      storyboard.dumpHTML();
   }
}
