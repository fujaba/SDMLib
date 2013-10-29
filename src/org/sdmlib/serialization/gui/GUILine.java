package org.sdmlib.serialization.gui;

public class GUILine {
	/** The Font-Size-Family value. */
	private String color;
	
	private String width;

	public String getColor() {
		return color;
	}

	public GUILine withColor(String color) {
		this.color = color;
		return this;
	}

	public String getWidth() {
		return width;
	}

	public GUILine withWidth(String width) {
		this.width = width;
		return this;
	}
}
