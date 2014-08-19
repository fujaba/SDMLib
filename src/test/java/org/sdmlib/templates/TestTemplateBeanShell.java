package org.sdmlib.templates;

import static org.junit.Assert.*;

import org.junit.Test;
import org.sdmlib.examples.groupAccount.model.Item;
import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.Template;

public class TestTemplateBeanShell {
	
	@Test
	public void testTemplateBeanShellParse() throws Exception {
		Template template = new Template();
		
		template.setTemplateText("Item value cons");
		template.setModelClassName(Item.class.getName());

		PlaceHolderDescription phd = new PlaceHolderDescription();
		phd.with("value", "value");
		phd.withCodeSnippet("value = value *100"); // <<== new voodoo
		
		template.addToPlaceholders(phd);
		template.setExpandedText("Item 1234 cons");
		template.parseOnce();
		assertEquals(1234d *100d, ((Item)template.getModelObject()).getValue(),0.1);
	
	}
	
	@Test
   public void testTemplateBeanShellGenerate() throws Exception {
      Template template = new Template();
      
      template.setTemplateText("Item value cons");
      template.setModelClassName(Item.class.getName());

      PlaceHolderDescription phd = new PlaceHolderDescription();
      phd.with("value", "value");
      phd.withCodeSnippet("value = value *100"); // <<== new voodoo
      
      template.addToPlaceholders(phd);

      Item item =  new Item();
      item.setValue(55.7);
      
      template.setModelObject(item);
      template.generate();
      assertEquals("Item 5570.0 cons", template.getExpandedText());
   }
	
	  @Test
	   public void testTemplateBeanShellGenerateWithIf() throws Exception {
	      Template template = new Template();
	      
	      template.setTemplateText("Item value cons");
	      template.setModelClassName(Item.class.getName());

	      PlaceHolderDescription phd = new PlaceHolderDescription();
	      phd.with("value", "value");
	      phd.withCodeSnippet("if (value > 50){value = value *100;}else{value = value *42;}"); // <<== new voodoo
	      
	      template.addToPlaceholders(phd);

	      Item item =  new Item();
	      item.setValue(55.7);
	      
	      template.setModelObject(item);
	      template.generate();
	      assertEquals("Item 5570.0 cons", template.getExpandedText());
	      
	       item =  new Item();
         item.setValue(10);
         
         template.setModelObject(item);
         template.generate();
         assertEquals("Item 420.0 cons", template.getExpandedText());
	   }
}
