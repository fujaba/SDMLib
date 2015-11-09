package org.sdmlib.test.model;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Parameter;
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
		
		Clazz player = model.createClazz("Player");
		
		ludo.withAttribute("location", DataType.STRING);
		
		ludo.withMethod("init", DataType.VOID, new Parameter("p", DataType.STRING));
		
		ludo.withAssoc(player, "players", Card.MANY, "game", Card.ONE);
		
		ludo.getMethods().first().withBody("     System.out.println(\"Hallo\");\n");
		
		model.removeAllGeneratedCode("src/test/java");
		
		model.generate("src/test/java");
		
		ludo.getAttributes().hasName("location").removeFromModelAndCode("src/test/java");
		
		ludo.getMethods().hasName("init").removeFromModelAndCode("src/test/java");
		
//		ludo.getRoles().getAssoc().removeFromModelAndCode("src/test/java");
		
//		model.getClasses().hasName("Ludo").first().removeFromModelAndCode("src/test/java");
		
		model.generate("src/test/java");
		
		story.addClassDiagram(model);
		
		story.dumpHTML();
		
	}
	
}
