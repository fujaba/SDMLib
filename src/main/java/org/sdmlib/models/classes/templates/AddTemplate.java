package org.sdmlib.models.classes.templates;

import org.sdmlib.codegen.Parser;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.list.SimpleList;

public class AddTemplate extends TemplateItem {

	private SimpleList<String> search = new SimpleList<String>();
	private String lastFound;
	
	
	public AddTemplate(int methodStart, String... search) {
		this.withPos(methodStart);
		this.withOffset(methodStart+1);
		if(search != null) {
			for(String string : search) {
				this.search.with(string);
			}
		}
	}
	
	public AddTemplate withLast(String last) {
		this.lastFound = last;
		return this;
	}
	
	public int getLast(Parser parser, String item, int start, int end) {
		int result = parser.search(item, start);
		if(result > end) {
			result = -1; 
		}
		while (result >= 0 && result < end) {
			int temp = parser.search(item, result + 4);
			if (temp >= 0 && temp < end) {
				result = temp;
			}else {
				break;
			}
		}
		return result;
	}
	
	@Override
	public boolean validate(Parser parser, ClassModel model, String... values) {
		int methodEnd = parser.methodBodyIndexOf(Parser.METHOD_END, pos);
		if(lastFound!=null) {
			methodEnd = getLast(parser, lastFound, offset, methodEnd);
		}
		int max=-1;
		if (offset >= 0) {
			for(String item : search) {
				int temp = parser.search(item, offset);
				if(temp>max && temp < methodEnd) {
					max = temp;
				}
			}
		} else {
			for(String item : search) {
				int temp = parser.search(item);
				if(temp>max && temp < methodEnd) {
					max = temp;
				}
			}
		}
		if(max<0) {
			this.offset = methodEnd;
			return true;
		}
		return false;
	}
}
