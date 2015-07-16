package org.sdmlib.models.classes.templates;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Feature;

public class ReplaceText {
	private String value;
	private String search;
	private Feature feature;

	public ReplaceText(String value) {
		this.search = value;
	}

	public ReplaceText(String search, String value) {
		this.search = search;
		this.value = value;
	}

	public ReplaceText withFeature(Feature value) {
		this.feature = value;
		return this;
	}

	public ReplaceText withValue(String value) {
		this.value = value;
		return this;
	}
	
	public String getSearch() {
		return search;
	}

	public String getText(ClassModel model) {
		if(feature == null || model.hasFeature(feature)) {
			return value;
		}
		return "";
	}
}
