package org.sdmlib.modelcouch;

public enum ContentType {
	APPLICATION_JSON("application/json"),
	IMAGE_JPEG("image/jpeg"),
	APPLICATION_X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded"),
	TEXT_PLAIN("text/plain");

	private String value;

	ContentType(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
