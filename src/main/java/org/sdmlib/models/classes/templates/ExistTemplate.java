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
	
	
	public StringBuilder execute(String... values) {
		StringBuilder sb=new StringBuilder();
		sb.append(template);
		boolean added=false;
		for(TemplateTask template : templates) {
			StringBuilder sub = template.execute(values);
			if(sub!=null) {
				sb.append(sub.toString());
				added = true;
				imports.withList(template.getImports());
			}
		}
		if(!added) {
			return null;
		}
		return sb;
	}

	public ExistTemplate withTemplate(String value) {
		this.template = value;
		return this;
	}

	public boolean validate(Parser parser, ClassModel model) {
		for(int i=0; i < templates.size(); i++) {
			if(!templates.get(i).validate(parser, model)) {
				templates.remove(i);
				continue;
			}
		}
		return true;
	}
}