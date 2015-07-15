package org.sdmlib.models.classes.templates;

import org.sdmlib.codegen.Parser;

import de.uniks.networkparser.list.SimpleKeyValueList;

public class Template {
	public static final int DEBUG=1;
	private String searchString;
	private StringBuilder template;
	private int mode=DEBUG;
	private SimpleKeyValueList<String, String> variables=new SimpleKeyValueList<>();
	public boolean validate(Parser parser) {
		return parser.indexOf(searchString) >= 0;
	}

	public Template withSearch(String value) {
		this.searchString = value;
		return this;
	}
	public Template withVariable(String... values) {
		if(values==null) {
			return this;
		}
		for(String item : values) {
			variables.withKeyValue(item, null);
		}
		return this;
	}
	public StringBuilder execute(String... values) {
		if(values==null || values.length%2==1) {
			if(mode==DEBUG) {
				throw new RuntimeException("values are: "+values);
			}
			return null;
		}
		for(int i=0;i<values.length;i+=2) {
			int pos = variables.indexOf(values[i]);
			if(pos<0) {
				if(mode==DEBUG) {
					throw new RuntimeException("Variable not exist: "+values[i]);
				}
			}else {
				variables.setValue(pos, values[i+1]);
			}
		}
		StringBuilder text=new StringBuilder(template.toString());
		// in the second run, replace <$<placeholders>$> by replacement
	    for (int i = 0; i < variables.size(); i ++) {
	    	String placeholder = "{{" + variables.getKeyByIndex(i) + "}}";
	        int pos = -1 - placeholder.length();
	        pos = text.indexOf(variables.get(i), pos + placeholder.length());
	        while (pos >= 0)
	         {
	            String newString = variables.getValueByIndex(i);
	            text.replace(pos, pos + placeholder.length(), newString);
	            pos = text.indexOf(placeholder, pos + newString.length());
	         }
	      }
		return text;
	}

	public Template withTemplate(String value) {
		this.template = new StringBuilder(value);
		return this;
	}
	public Template addTemplate(String value) {
		this.template.append(value);
		return this;
	}
	
}
