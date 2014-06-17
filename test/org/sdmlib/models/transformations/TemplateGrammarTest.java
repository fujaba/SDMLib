package org.sdmlib.models.transformations;

import org.junit.Test;
import org.sdmlib.examples.studyright.Student;
import org.sdmlib.examples.studyright.University;
import org.sdmlib.storyboards.Storyboard;

public class TemplateGrammarTest
{
   @Test
   public void testTemplateGrammarTest()
   {
      Storyboard story = new Storyboard();

      story.addStep("Simple example templates parsing students:");

      Template uniTemplate = new Template()
         .withName("UniTemplate")
         .withModelClassName(University.class.getName())
         .with("Uni name has some students:\nStudentList", "name",
            University.PROPERTY_NAME);

      uniTemplate.getPlaceholders().first().withPrefix("Uni ");

      Template studentTemplate = uniTemplate
         .createPlaceHolderAndSubTemplate("StudentList",
            University.PROPERTY_STUDENTS, "name matrikelnummer", "", "\n", "\n")
         .withName("StudentTemplate")
         .withReplacements("name", Student.PROPERTY_NAME, "matrikelnummer",
            Student.PROPERTY_MATRNO);

      studentTemplate.getParents().first().withPrefix(" has some students:\n");

      story.addObjectDiagram(uniTemplate);

      story.addStep("Define templates to generate a template description");

      Template templateListTemplate = new Template()
         .withName("templateListTemplate")
         .withModelObject(uniTemplate)
         .with("class className \n" + "placeholderList", "className",
            Template.PROPERTY_MODELCLASSNAME);

      templateListTemplate.createPlaceHolderAndSubTemplate("placeholderList",
         Template.PROPERTY_PLACEHOLDERS, "const<<name", "", ">>", ">>")
         .withReplacements("const", PlaceHolderDescription.PROPERTY_PREFIX,
            "name", PlaceHolderDescription.PROPERTY_ATTRNAME);

      templateListTemplate.generate();

      story.addPreformatted(templateListTemplate.getExpandedText());

      story.dumpHTML();
   }
}
