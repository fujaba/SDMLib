package org.sdmlib.modelcouch.connection;

public enum ContentType {
	APPLICATION_JSON("application/json"),
	IMAGE_JPEG("image/jpeg"),
	IMAGE("image"),
	APPLICATION_X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded"),
	TEXT_PLAIN("text/plain"), 
	APPLICATION_PDF("application/pdf");

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
