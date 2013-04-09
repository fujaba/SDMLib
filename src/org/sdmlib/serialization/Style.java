package org.sdmlib.serialization;

import org.sdmlib.serialization.interfaces.PeerMessage;

public class Style implements PeerMessage{
	/** The Constant PROPERTY_BOLD for Bold Attribute */
	public static final String PROPERTY_BOLD = "bold";
	/** The Bold value. */
	private boolean bold;

	/** The Constant PROPERTY_ITALIC for Italic Attribute */
	public static final String PROPERTY_ITALIC = "italic";
	/** The Italic value. */
	private boolean italic;

	/** The Constant PROPERTY_FONT for Font-Family Attribute */
	public static final String PROPERTY_FONTFAMILY = "fontfamily";
	/** The Font-Family value. */
	private String fontfamily;

	/** The Constant PROPERTY_FONTSIZE for Font-Size Attribute */
	public static final String PROPERTY_FONTSIZE = "size";
	/** The Font-Size-Family value. */
	private String fontsize;

	/** The Constant PROPERTY_FORGROUND for Color of Font Attribute */
	public static final String PROPERTY_FORGROUND = "foreground";
	/** The Foreground-Color. */
	private String forground;

	/** The Constant PROPERTY_BACKGROUND for Color of Background Attribute */
	public static final String PROPERTY_BACKGROUND = "background";
	/** The Font-Size-Family value. */
	private String background;

	/**
	 * Empty Constructor
	 */
	public Style(){ 
	}

	/**
	 * Empty Constructor
	 */
	public Style(Object... fields){
		if (fields.length % 2 == 0) {
			for (int z = 0; z < fields.length; z += 2) {
				set((String)fields[z], fields[z + 1]);
			}
		}
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	public boolean isItalic() {
		return italic;
	}

	public void setItalic(boolean italic) {
		this.italic = italic;
	}

	public String getFontFamily() {
		return fontfamily;
	}

	public void setFontFamily(String fontFamily) {
		this.fontfamily = fontFamily;
	}

	public String getFontSize() {
		return fontsize;
	}

	public void setFontSize(String size) {
		this.fontsize = size;
	}

	/*
	 * Generic Getter for Attributes
	 */
	@Override
	public Object get(String attrName) {
		String attribute;
		int pos = attrName.indexOf(".");
		if (pos > 0) {
			attribute = attrName.substring(0, pos);
		} else {
			attribute = attrName;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_BOLD)) {
			return isBold();
		} else if (attribute.equalsIgnoreCase(PROPERTY_ITALIC)) {
			return isItalic();
		} else if (attribute.equalsIgnoreCase(PROPERTY_FONTFAMILY)) {
			return getFontFamily();
		} else if (attribute.equalsIgnoreCase(PROPERTY_FONTSIZE)) {
			return getFontSize();
		} else if (attribute.equalsIgnoreCase(PROPERTY_FORGROUND)) {
			return getForground();
		} else if (attribute.equalsIgnoreCase(PROPERTY_BACKGROUND)) {
			return getBackground();
		}
		return null;
	}

	/*
	 * Generic Setter for Attributes
	 */
	@Override
	public boolean set(String attribute, Object value) {
		if (attribute.equalsIgnoreCase(PROPERTY_BOLD)) {
			setBold((Boolean) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_ITALIC)) {
			setItalic((Boolean) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_FONTFAMILY)) {
			setFontFamily((String) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_FONTSIZE)) {
			setFontSize(value.toString());
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_FORGROUND)) {
			setForground((String) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_BACKGROUND)) {
			setBackground((String) value);
			return true;
		}
		return false;
	}

	public String getForground() {
		return forground;
	}

	public void setForground(String forground) {
		this.forground = forground;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}
}
