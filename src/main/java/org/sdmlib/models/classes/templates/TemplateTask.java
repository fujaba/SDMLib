package org.sdmlib.models.classes.templates;

import org.sdmlib.codegen.Parser;
import org.sdmlib.models.classes.ClassModel;

public interface TemplateTask {
	public TemplateTask withTemplate(String value);
	public boolean validate(Parser parser, ClassModel model);
	public TemplateTask withVariable(String... values);
	public TemplateTask withVariable(ReplaceText... values);
	public StringBuilder execute(String... values);
	public boolean insert(Parser parser, String... values);
}
