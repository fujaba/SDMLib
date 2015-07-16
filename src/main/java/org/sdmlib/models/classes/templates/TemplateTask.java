package org.sdmlib.models.classes.templates;

import org.sdmlib.codegen.Parser;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.list.SimpleList;

public abstract class TemplateTask {
	protected String template;
	protected SimpleList<String> imports;

	public abstract TemplateTask withTemplate(String value);

	public abstract boolean validate(Parser parser, ClassModel model);

	// public TemplateTask withVariable(String... values);
	// public TemplateTask withVariable(ReplaceText... values);
	public abstract StringBuilder execute(String... values);

	public SimpleList<String> getImports() {
		return imports;
	}

	public boolean insert(Parser parser, String... values) {
		this.imports = new SimpleList<String>();
		 StringBuilder text = execute(values);
		 if(text==null) {
			 return false;
		 }
		 int pos = parser.indexOf(Parser.CLASS_END);
		 for(String item : imports) {
			 parser.insertImport(item);
		 }
         parser.insert(pos, text.toString());
         return true;
	}
}
