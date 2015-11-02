package org.sdmlib.test.model;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.storyboards.StoryPage;

public class ModelRefactoring {

   /**
    * 
    * @see <a href='../../../../../../../doc/RemoveAttribute.html'>RemoveAttribute.html</a>/n */
   @Test
	public void testRemoveAttribute() {
		
		StoryPage story = new StoryPage();

		ClassModel model = new ClassModel("org.sdmlib.test.model.refactoring");
		
		Clazz ludo = model.createClazz("Ludo");
		
		ludo.withAttribute("location", DataType.STRING);
		
		model.removeAllGeneratedCode("src/test/java");
		
		model.generate("src/test/java");
		
		ludo.getAttributes().hasName("location").removeFromModelAndCode("src/test/java");
		
		model.generate("src/test/java");
		
		story.addClassDiagram(model);
		
		story.dumpHTML();
		
	}
	
}
