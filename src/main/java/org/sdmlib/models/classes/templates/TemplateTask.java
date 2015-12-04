package org.sdmlib.models.classes.templates;

import org.sdmlib.codegen.Parser;
import org.sdmlib.models.classes.ClassModel;

public abstract class TemplateTask {
	protected String template = "";
	// Position of Method
	protected int pos = -1;
	// Inser Position
	protected int offset=-1;

	public TemplateTask withTemplate(String value) {
		this.template = value;
		return this;
	}

	public boolean validate(Parser parser, ClassModel model, String... values) {
		return true;
	}

	public abstract TemplateResult execute(String searchString, Parser parser, ClassModel model, String... values);

	public boolean insert(Parser parser, String... values) {
		return insert(parser, null, values);
	}

	public boolean insert(Parser parser, ClassModel model, String... values) {
		TemplateResult text = execute(template, parser, model, values);
		 if(text == null || text.isEmpty()) {
			 return false;
		 }
		 int temp = parser.indexOf(Parser.CLASS_END);
		 if (this.offset >0) {
			 temp = this.offset;
		 }else if(pos>=0) {
			 temp = pos;
		 }
         parser.insert(temp, text.getTextValue());
         parser.indexOf(Parser.CLASS_END);
         
         for(String item : text.getImports()) {
			 parser.insertImport(item);
		 }
         parser.indexOf(Parser.CLASS_END);
         
         return true;
	}

	public String getTemplate() {
		return template;
	}
	public TemplateTask withOffset(int value) {
		this.offset = value;
		return this;
	}
	public TemplateTask withPos(int value) {
		this.pos = value;
		return this;
	}
}
