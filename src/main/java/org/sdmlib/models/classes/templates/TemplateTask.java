package org.sdmlib.models.classes.templates;

import org.sdmlib.codegen.Parser;
import org.sdmlib.models.classes.ClassModel;

public abstract class TemplateTask {
	protected String template;

	public abstract TemplateTask withTemplate(String value);

	public abstract boolean validate(Parser parser, ClassModel model);

	// public TemplateTask withVariable(String... values);
	// public TemplateTask withVariable(ReplaceText... values);
	public abstract TemplateResult execute(String... values);

	public boolean insert(Parser parser, String... values) {
		TemplateResult text = execute(values);
		 if(text == null || text.isEmpty()) {
			 return false;
		 }
		 int pos = parser.indexOf(Parser.CLASS_END);
         parser.insert(pos, text.getTextValue());
         for(String item : text.getImports()) {
			 parser.insertImport(item);
		 }
         return true;
	}
}
