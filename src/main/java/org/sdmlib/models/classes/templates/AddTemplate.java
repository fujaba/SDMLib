package org.sdmlib.models.classes.templates;

import org.sdmlib.codegen.Parser;
import org.sdmlib.models.classes.ClassModel;

public class AddTemplate extends TemplateItem {
	private int offset=-1;
	private String search;
	private int lastFound = -1;
	
	public AddTemplate(String value) {
		this.search = value;
	}
	
	public AddTemplate withOffset(int value) {
		this.offset = value;
		return this;
	}
	
	public AddTemplate withLast(int value) {
		this.lastFound = value;
		return this;
	}
	
	@Override
	public boolean validate(Parser parser, ClassModel model) {
		if (offset >= 0) {
			this.pos = parser.search(search, offset);
		} else {
			this.pos = parser.search(search);
		}
		int secondReturnPos = pos;
		while (secondReturnPos >= 0 && secondReturnPos < lastFound) {
			secondReturnPos = parser.search(search, secondReturnPos + 4);
			if (secondReturnPos >= 0 && secondReturnPos < pos) {
				pos = secondReturnPos;
			}
		}

		return this.pos>=0;
	}
}
