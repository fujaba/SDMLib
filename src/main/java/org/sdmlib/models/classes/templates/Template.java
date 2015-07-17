package org.sdmlib.models.classes.templates;

import org.sdmlib.codegen.Parser;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Feature;

public class Template extends TemplateItem {
	private String searchString;
	private Feature condition;
	private boolean active=true;
	
	public Template() {
		
	}

	public Template(String search) {
		this.withSearch(search);
	}
	
	public Template withTemplate(String value) {
		super.withTemplate(value);
		return this;
	}

	public boolean validate(Parser parser, ClassModel model) {
		if(!active) {
			return false;
		}
		if(condition != null && !model.hasFeature(condition)) {
			return false;
		}
		for(int i = 0; i < variables.size(); i++) {
			variables.get(i).checking(model);
		}
		return searching(parser);
	}
	
	public boolean searching(Parser parser) {
		return parser.indexOf(searchString) <= 0;
	}
	

	public Template withSearch(String value) {
		this.searchString = value;
		return this;
	}
	
	public Template withCondition(boolean condition) {
		if(!condition) {
			this.active = false;
		}
		return this;
	}
	
	public Template withVariable(ReplaceText... values) {
		if(values==null) {
			return this;
		}
		for(ReplaceText item : values) {
			variables.with(item);
		}
		return this;
	}
	
//	public Template withVariable(String... values) {
//		if(values==null) {
//			return this;
//		}
//		for(String item : values) {
//			variables.with(new ReplaceText(item));
//		}
//		return this;
//	}
	

	public Template addTemplate(String value) {
		if(this.template != null) {
			this.template += value;
		}else {
			this.template = value;
		}
		return this;
	}
}
