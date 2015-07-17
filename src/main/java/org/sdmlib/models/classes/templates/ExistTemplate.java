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
	
	
	public TemplateResult execute(String... values) {
		TemplateResult text=new TemplateResult(template);
		boolean added=false;
		for(TemplateTask template : templates) {
			TemplateResult sub = template.execute(values);
			if(text.append(sub)) {
				added = true;
			}
		}
		if(!added) {
			return null;
		}
		return text;
	}

	public boolean validate(Parser parser, ClassModel model) {
		for(int i=0; i < templates.size(); i++) {
			if(!templates.get(i).validate(parser, model)) {
				templates.remove(i);
				i--;
				continue;
			}
		}
		return true;
	}
}