package org.sdmlib.models.classes.templates;

import org.sdmlib.codegen.Parser;
import org.sdmlib.models.classes.ClassModel;

public abstract class TemplateTask {
	protected String template;
	protected int pos = -1;

	public TemplateTask withTemplate(String value) {
		this.template = value;
		return this;
	}

	public abstract boolean validate(Parser parser, ClassModel model);

	// public TemplateTask withVariable(String... values);
	// public TemplateTask withVariable(ReplaceText... values);
	public abstract TemplateResult execute(String... values);

	public boolean insert(Parser parser, String... values) {
		TemplateResult text = execute(values);
		 if(text == null || text.isEmpty()) {
			 return false;
		 }
		 int temp = parser.indexOf(Parser.CLASS_END);
		 if(pos>=0) {
			 temp = pos;
		 }
         parser.insert(temp, text.getTextValue());
         for(String item : text.getImports()) {
			 parser.insertImport(item);
		 }
         return true;
	}
}
