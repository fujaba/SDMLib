package org.sdmlib.models.classes.templates;

import org.sdmlib.StrUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.list.SimpleList;

public abstract class TemplateItem extends TemplateTask{
	public static final int DEBUG=1;
	protected int mode=DEBUG;
	protected int offset=-1;
	protected SimpleList<ReplaceText> variables=new SimpleList<ReplaceText>();
	
	public ReplaceText get(String value) {
		for(ReplaceText item : variables) {
			if(item .getSearch().equals(value)) {
				return item;
			}
		}
		return null;
	}
	
	@Override
	public TemplateResult execute(String searchString, Parser parser, ClassModel model, String... values) {
		if(!this.validate(parser, model, values)) {
			return new TemplateResult();
		}
		return run(searchString, parser, model, values);
	}
	
	public TemplateResult execute(String... values) {
		return run(template, null, null, values);
	}
	
	public TemplateResult run(String searchString, Parser parser, ClassModel model, String... values) {
		if(values==null || values.length%2==1) {
			if(mode==DEBUG) {
				throw new RuntimeException("values are: "+values);
			}
			return new TemplateResult();
		}
		// Read all Variables from Template
		boolean firstFound=false;
		for(int i=0;i<searchString.length();i++) {
			if(searchString.charAt(i) != '{') {
				firstFound = false;
				continue;
			}
			if(!firstFound) {
				firstFound = true;
				continue;
			}
			// cool its Variable
			i++;
			int end=i;
			for(;end<searchString.length();end++) {
				if(searchString.charAt(end) == '}') {
					break;
				}
			}
			String name=searchString.substring(i, end);
			String firstName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
			ReplaceText item = get(name);
			if(name.toLowerCase().equals(name) || name.toUpperCase().equals(name) || name.toLowerCase().equals(firstName)) {
				name = name.toLowerCase();
			}
			if(get(name)==null) {
				String value = null;
				if(item!=null) {
					value = item.getText();
				}
				variables.with(new ReplaceText(name).withValue(value));
			}
			firstFound = false;
		}
		
		for(int i=0;i<values.length;i+=2) {
			ReplaceText item = get(values[i]);
			if(item == null) {
				item = new ReplaceText(values[i], values[i+1]);
				variables.with(item);
			}
			if(item.getText()==null ){
				item.withValue(values[i+1]);
			}
			
			String name = values[i];
			String firstName = name.substring(0, 1).toUpperCase() + name.substring(1);
			boolean small=false;
			boolean firstUpper=false;
			boolean upper=false;
			if(name.toLowerCase().equals(name)) {
				firstUpper = true;
				upper = true;
			} else if(name.toUpperCase().equals(name)) {
				small=true;
				firstUpper = true;
			} else if(name.toLowerCase().equals(firstName)) {
				small=true;
				upper = true;
			}
			// Maybe autofill
			if(small) {
				item = get(name.toLowerCase());
				if(item == null ) {
					variables.with(new ReplaceText(name.toLowerCase(), values[i+1].toLowerCase()));
				}else if( item.getText() == null) {
					item.withValue(values[i+1].toLowerCase());				
				}
			}
			if(firstUpper) {
				item = get(firstName);
				if(item == null ) {
					variables.with(new ReplaceText(firstName, StrUtil.upFirstChar(values[i+1])));
				}else if( item.getText() == null) {
					item.withValue(StrUtil.upFirstChar(values[i+1]));				
				}
			}
			if(upper) {
				item = get(name.toUpperCase());
				if(item == null ) {
					variables.with(new ReplaceText(name.toUpperCase(), values[i+1].toUpperCase()));
				}else if( item.getText() == null) {
					item.withValue(values[i+1].toUpperCase());				
				}
			}
		}
		for(int i = 0; i < variables.size(); i++) {
			if(variables.get(i).getText()==null && mode==DEBUG) {
				throw new RuntimeException("Variable <"+variables.get(i).getSearch()+">cant be null!");
			}
		}
		TemplateResult text = new TemplateResult(searchString.toString());
		
		boolean foundVar = true;
		while(foundVar) {
			replacePlaceHolder(text);
			firstFound=false;
			foundVar = false;
			searchString = text.getTextValue();
			for(int i=0;i<text.getTextValue().length();i++) {
				if(searchString.charAt(i) != '{') {
					firstFound = false;
					continue;
				}
				if(!firstFound) {
					firstFound = true;
					continue;
				}
				// cool its Variable
				foundVar= true;
				break;
			}
		}
		return text;
	}
	
	private TemplateResult replacePlaceHolder(TemplateResult text) {
		// Read all Variables from Template
		// in the second run, replace <$<placeholders>$> by replacement
		for (int i = 0; i < variables.size(); i ++) {
	    	ReplaceText replaceText = variables.get(i);
	    	String placeholder = "{{" + replaceText.getSearch() + "}}";
	        int pos = -1 - placeholder.length();
	        pos = text.indexOf(placeholder, pos + placeholder.length());
	        while (pos >= 0)
	         {
	            String newString = replaceText.getText();
	            text.addImports(replaceText.getImport());
	            
	            variables.get(i).run(pos, newString);
	            text.replace(pos, pos + placeholder.length(), newString);
	            pos = text.indexOf(placeholder, pos + newString.length());
	         }
	      }
		return text;
	}
}
