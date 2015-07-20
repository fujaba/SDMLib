package org.sdmlib.models.classes.templates;

import org.sdmlib.codegen.Parser;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.list.SimpleList;

public class ExistTemplate extends TemplateTask{
	public SimpleList<TemplateTask> templates=new SimpleList<TemplateTask>();

	public ExistTemplate() {
		
	}
	
	public ExistTemplate(String template) {
		withTemplate(template);
	}
	
	public ExistTemplate withTemplates(Template... values) {
		templates.with(values);
		return this;
	}
	

	@Override
	public TemplateResult execute(String searchString, Parser parser, ClassModel model, String... values) {
		TemplateResult text=new TemplateResult(template);
		boolean added=false;
		for(TemplateTask template : templates) {
			TemplateResult sub = template.execute(template.getTemplate(), parser, model, values);
			if(text.append(sub)) {
				added = true;
			}
		}
		if(!added) {
			return null;
		}
		return text;
	}
}