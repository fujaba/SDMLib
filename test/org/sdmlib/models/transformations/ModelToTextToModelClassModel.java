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
      
      Clazz placeholderDescription = template.createClassAndAssoc("PlaceHolderDescription", "placeholders", R.MANY, "template", R.ONE)
            .withAttributes(
               "textFragment", R.STRING,
               "value", R.STRING,
               "attrName", R.STRING,
               "isKeyAttribute", R.BOOLEAN);
      
      placeholderDescription.withAssoc(template, "subTemplate", R.ONE, "placeholderDescription", R.ONE);
      
      storyboard.addClassDiagram(model);
      
      model.generate("src", "srchelpers");
      
      storyboard.dumpHTML();
   }
}
