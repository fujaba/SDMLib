package org.sdmlib.models.classes.templates;

import java.util.Collection;

import de.uniks.networkparser.list.SimpleList;

public class TemplateResult {
	private StringBuilder text;
	private SimpleList<String> imports = new SimpleList<String>();
	
	public TemplateResult() {
	}
	
	public TemplateResult(String value) {
		this.text = new StringBuilder(value);
	}
	
	public StringBuilder getText() {
		return text;
	}
	public void addText(StringBuilder value) {
		if(this.text==null) {
			this.text = new StringBuilder();
		}
		this.text.append(value);
	}
	public SimpleList<String> getImports() {
		return imports;
	}
	public void addImports(String... values) {
		if(values != null) {
			for(String string : values) {
				this.imports.with(string);
			}
		}
	}
	public void addImports(Collection<String> values) {
		this.imports.withList(values);
	}
	
	public boolean isEmpty() {
		return text == null;
	}

	public int indexOf(String search, int len) {
		if(isEmpty()) {
			return -1;
		}
		return text.indexOf(search, len);
	}

	public void replace(int pos, int i, String newString) {
		if(isEmpty()) {
			return;
		}
		text.replace(pos, i, newString);
	}

	public boolean append(TemplateResult sub) {
		if(sub.isEmpty()) {
			return false;
		}
		this.text.append(sub.getText().toString());
		this.imports.withList(sub.getImports());
		return true;
	}

	public String getTextValue() {
		return this.text.toString();
	}
}
