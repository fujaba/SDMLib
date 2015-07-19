package org.sdmlib.models.classes.templates;

import org.sdmlib.StrUtil;

import de.uniks.networkparser.list.SimpleList;

public abstract class TemplateItem extends TemplateTask{
	public static final int DEBUG=1;
	protected int mode=DEBUG;
	protected SimpleList<ReplaceText> variables=new SimpleList<ReplaceText>();
	
	public ReplaceText get(String value) {
		for(ReplaceText item : variables) {
			if(item .getSearch().equals(value)) {
				return item;
			}
		}
		return null;
	}
	
	public TemplateResult execute(String... values) {
		if(values==null || values.length%2==1) {
			if(mode==DEBUG) {
				throw new RuntimeException("values are: "+values);
			}
			return null;
		}
		// Read all Variables from Template
		boolean firstFound=false;
		for(int i=0;i<template.length();i++) {
			if(template.charAt(i) != '{') {
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
			for(;end<template.length();end++) {
				if(template.charAt(end) == '}') {
					break;
				}
			}
			String name=template.substring(i, end);
			String first = name.substring(0, 1);
			if(first.toLowerCase() != first) {
				name = name.toLowerCase();
			}
			if(get(name)==null) {
				variables.with(new ReplaceText(name).withChecked(true));
			}
			firstFound = false;
		}
		
		for(int i=0;i<values.length;i+=2) {
			ReplaceText item = get(values[i]);
			if(item == null) {
				variables.with(new ReplaceText(values[i], values[i+1]));
				continue;
			}
			if(item.getText()!=null && item.getText()!=""){
				if(mode==DEBUG) {
					throw new RuntimeException("Variable not overide: "+values[i]);
				}
				item.withValue(values[i+1]);
			} else {
				item.withValue(values[i+1]);

				String first = values[i].substring(0, 1);
				if(first.toLowerCase() == first) {
					// Maybe autofill
					String temp = first.toUpperCase() + values[i].substring(1);
					item = get(temp); 
					if(item == null ) {
						variables.with(new ReplaceText(temp, StrUtil.upFirstChar(values[i+1])).withChecked(true));
					}else if( item.getText() == null) {
						item.withValue(StrUtil.upFirstChar(values[i+1]));				
					}
					temp = values[i].toUpperCase();
					item = get(temp); 
					if(item == null ) {
						variables.with(new ReplaceText(temp, values[i+1].toUpperCase()).withChecked(true));
					}else if( item.getText() == null) {
						item.withValue(values[i+1].toUpperCase());				
					}
				}
			}
		}
		for(int i = 0; i < variables.size(); i++) {
			if(variables.get(i).getText()==null && mode==DEBUG) {
				throw new RuntimeException("Variable <"+variables.get(i).getSearch()+">cant be null: "+values[i]);
			}
		}
		TemplateResult text = new TemplateResult(template.toString()); 
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
	            text.replace(pos, pos + placeholder.length(), newString);
	            pos = text.indexOf(placeholder, pos + newString.length());
	         }
	      }
		return text;
	}
}
