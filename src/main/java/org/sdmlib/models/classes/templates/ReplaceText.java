package org.sdmlib.models.classes.templates;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Feature;

public class ReplaceText {
	private String value;
	private String otherValue = "";
	private String search;
	private Feature feature;
	private String importName;
	private boolean condition=true;
	private boolean checked;

	public ReplaceText(String value) {
		this.search = value;
	}

	public ReplaceText(String search, String value) {
		this.search = search;
		this.value = value;
	}
	
	public ReplaceText(String search, Feature feature, String value) {
		this.search = search;
		this.feature = feature;
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
	
	public ReplaceText withOtherValue(String value) {
		this.otherValue = value;
		return this;
	}
	
	public ReplaceText withCondition(boolean value) {
		this.condition = value;
		return this;
	}
	
	public ReplaceText withImport(String value) {
		this.importName = value;
		return this;
	}
	
	public String getSearch() {
		return search;
	}

	public String getText() {
		if(checked) {
			return value;
		}
		return otherValue;
	}
	
	public ReplaceText withChecked(boolean value) {
		this.checked = value;
		return this;
	}
	
	public ReplaceText checking(ClassModel model) {
		this.checked = condition && (feature == null || model.hasFeature(feature));
		return this;
	}
	
	public String getImport() {
		if(checked) {
			return importName;
		}
		return null;
	}
	public static ReplaceText create(String search, Feature feature, String value) {
		return new ReplaceText(search, feature, value);
	}
	public static ReplaceText create(String search, boolean condition, String value, String otherValue) {
		ReplaceText item = new ReplaceText(search, value);
		item.withOtherValue(otherValue);
		item.withCondition(condition);
		return item;
	}
	
	public static ReplaceText create(String search, boolean condition, String importName, String value, String otherValue) {
		ReplaceText item = new ReplaceText(search, value);
		item.withImport(importName);
		item.withOtherValue(otherValue);
		item.withCondition(condition);
		return item;
	}
}
