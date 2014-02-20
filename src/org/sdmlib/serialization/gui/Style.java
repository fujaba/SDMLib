package org.sdmlib.serialization.gui;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or - as soon they
 will be approved by the European Commission - subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
*/
import java.util.HashMap;
import org.sdmlib.serialization.interfaces.GUIPosition;
import org.sdmlib.serialization.interfaces.PeerMessage;

public class Style implements PeerMessage, Cloneable{
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
	
	/** The Constant PROPERTY_BACKGROUND for Color of Background Attribute */
	public static final String PROPERTY_UNDERLINE = "underline";
	/** The Underline value. */
	private boolean underline;
	
	/** The Constant PROPERTY_BACKGROUND for Color of Background Attribute */
	public static final String PROPERTY_ALIGNMENT = "alignment";
	/** The Underline value. */
	private String alignment;
	
	/** The Constant PROPERTY_WIDTH for Width of Width */
	public static final String PROPERTY_WIDTH = "width";
	/** The Width value. */
	private double width;

	/** The Constant PROPERTY_HEIGHT for Height of Height */
	public static final String PROPERTY_HEIGHT = "height";
	/** The Height value. */
	private double height;
	
	public static final String PROPERTY_BORDER = "borders";

	protected HashMap<GUIPosition, GUILine> borders=new HashMap<GUIPosition, GUILine>(); 
	
	public boolean isBold() {
		return bold;
	}

	public Style withBold(boolean value) {
		Boolean oldValue = this.bold;
		this.bold = value;
		propertyChange(PROPERTY_BOLD, oldValue, value);
		return this;
	}
	
	public boolean isItalic() {
		return italic;
	}

	public Style withItalic(boolean value) {
		Boolean oldValue = this.italic;
		this.italic = value;
		propertyChange(PROPERTY_ITALIC, oldValue, value);
		return this;
	}

	public String getFontFamily() {
		return fontfamily;
	}

	public Style withFontFamily(String value) {
		String oldValue = this.fontfamily;
		this.fontfamily = value;
		propertyChange(PROPERTY_FONTFAMILY, oldValue, value);
		return this;
	}

	public String getFontSize() {
		return fontsize;
	}

	public Style withFontSize(String value) {
		String oldValue = this.fontsize;
		this.fontsize = value;
		propertyChange(PROPERTY_FONTSIZE, oldValue, value);
		return this;
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
		} else if (attribute.equalsIgnoreCase(PROPERTY_UNDERLINE)) {
			return isUnderline();
		} else if (attribute.equalsIgnoreCase(PROPERTY_ALIGNMENT)) {
			return getAlignment();
		} else if (attribute.equalsIgnoreCase(PROPERTY_WIDTH)) {
			return getWidth();
		} else if (attribute.equalsIgnoreCase(PROPERTY_HEIGHT)) {
			return getHeight();
		}
		return null;
	}

	/*
	 * Generic Setter for Attributes
	 */
	@Override
	public boolean set(String attribute, Object value) {
		if (attribute.equalsIgnoreCase(PROPERTY_BOLD)) {
			withBold((Boolean) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_ITALIC)) {
			withItalic((Boolean) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_FONTFAMILY)) {
			withFontFamily((String) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_FONTSIZE)) {
			withFontSize(value.toString());
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_FORGROUND)) {
			withForground((String) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_BACKGROUND)) {
			withBackground((String) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_UNDERLINE)) {
			withUnderline((Boolean) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_ALIGNMENT)) {
			withAlignment((String)value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_WIDTH)) {
			withWidth(Double.valueOf(""+value));
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_HEIGHT)) {
			withHeight(Double.valueOf(""+value));
			return true;
		}
		return false;
	}

	public String getForground() {
		return forground;
	}

	public Style withForground(String value) {
		String oldValue = this.forground;
		this.forground = value;
		propertyChange(PROPERTY_FORGROUND, oldValue, value);
		return this;
	}

	public String getBackground() {
		return background;
	}

	public Style withBackground(String value) {
		String oldValue = this.background;
		this.background = value;
		propertyChange(PROPERTY_BACKGROUND, oldValue, value);
		return this;
	}
	
	public Style clone(){
		try {
			super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return new Style()
				.withFontFamily(fontfamily)
				.withFontSize(fontsize)
				.withForground(forground)
				.withBackground(background)
				.withBold(bold)
				.withItalic(italic)
				.withAlignment(alignment)
				.withUnderline(underline)
				.withWidth(width)
				.withHeight(height);
	}

	public boolean isUnderline() {
		return underline;
	}

	public Style withUnderline(Boolean value) {
		Boolean oldValue = this.underline;
		this.underline = value;
		propertyChange(PROPERTY_UNDERLINE, oldValue, value);
		return this;
	}

	public String getAlignment() {
		return alignment;
	}

	public Style withAlignment(GUIPosition value) {
		String oldValue = this.alignment;
		this.alignment = ""+value;
		propertyChange(PROPERTY_ALIGNMENT, oldValue, value);
		return this;
	}
	public Style withAlignment(String value) {
		String oldValue = this.alignment;
		this.alignment = value;
		propertyChange(PROPERTY_ALIGNMENT, oldValue, value);
		return this;
	}

	public double getHeight() {
		return height;
	}

	public Style withHeight(double value) {
		Double oldValue = this.height;
		this.height = value;
		propertyChange(PROPERTY_HEIGHT, oldValue, value);
		return this;
	}

	public double getWidth() {
		return width;
	}

	public Style withWidth(double value) {
		Double oldValue = this.width;
		this.width = value;
		propertyChange(PROPERTY_WIDTH, oldValue, value);
		return this;
	}
	
	
	public Style withBorder(GUIPosition position, GUILine line){
		getBorders().put(position, line);
		propertyChange(PROPERTY_BORDER, null, position);
		return this;
	}
	
	public void setBorder(GUIPosition position, String width, String color){
		GUILine border = this.borders.get(position);
		if(width!=null){
			if(border==null){
				this.borders.put(position, new GUILine().withColor(color).withWidth(width));
				this.propertyChange(PROPERTY_BORDER, null, this.borders);
			}else{
				if(!border.isCustomLine()){
					border.withColor(color);
					border.withWidth(width);
					this.propertyChange(PROPERTY_BORDER, null, this.borders);
				}
			}
		}else if(border!=null){
			if(!border.isCustomLine()){
				this.borders.remove(position);
				this.propertyChange(PROPERTY_BORDER, null, this.borders);
			}
		}
	}
	
	public HashMap<GUIPosition, GUILine> getBorders(){
		return borders;
	}
	
	public Style withOutBorder(GUIPosition position) {
		GUILine removedItem = getBorders().remove(position);
		if(removedItem!=null){
			propertyChange(PROPERTY_BORDER, position, null);
		}
		return this;
	}
	
	
	public void propertyChange(String property, Object oldValue, Object newValue){
	}
}
