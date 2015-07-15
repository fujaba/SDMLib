package org.sdmlib.models.classes.templates;

import de.uniks.networkparser.list.SimpleList;

public class ExistTemplate {
	public SimpleList<Template> templates=new SimpleList<Template>();
	public String template;
	public ExistTemplate withTemplate(String value) {
		this.template = value;
		return this;
	}
	public ExistTemplate withTemplates(Template... values) {
		templates.with(values);
		return this;
	}
	
	public StringBuilder execute(String... values) {
		StringBuilder sb=new StringBuilder();
		sb.append(template);
		for(Template template : templates) {
			StringBuilder sub = template.execute(values);
			if(sub==null) {
				return null;
			}
			sb.append(sub.toString());
		}
		return sb;
	}
}
