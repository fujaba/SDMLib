package org.sdmlib.test.model;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Parameter;

public class ModelRefactoring {

   /**
    * 
    * @see <a href='../../../../../../../doc/RemoveAttribute.html'>RemoveAttribute.html</a>/n */
   @Test
	public void testRemoveAttribute() {
		
		Storyboard story = new Storyboard();

		ClassModel model = new ClassModel("org.sdmlib.test.model.refactoring");
		
		Clazz ludo = model.createClazz("Ludo");
		
		Clazz player = model.createClazz("Player");
		
		ludo.withAttribute("location", DataType.STRING);
		
		ludo.withMethod("init", DataType.VOID, new Parameter(DataType.STRING).with("p"));
		
		ludo.withBidirectional(player, "players", Cardinality.MANY, "game", Cardinality.ONE);
		
		ludo.getMethods().first().withBody("     System.out.println(\"Hallo\");\n");
		
//		model.removeAllGeneratedCode("src/test/java");
		
//		model.generate("src/test/java");
		
//		ludo.getAttributes().hasName("location").removeFromModelAndCode("src/test/java");
		
//		ludo.getMethods().hasName("init").removeFromModelAndCode("src/test/java");
		
//		ludo.getRoles().getAssoc().removeFromModelAndCode("src/test/java");
		
//		model.getClasses().hasName("Ludo").first().removeFromModelAndCode("src/test/java");
		
		model.generate("src/test/java");
		
		story.addClassDiagram(model);
		
		story.dumpHTML();
		
	}
	
}
