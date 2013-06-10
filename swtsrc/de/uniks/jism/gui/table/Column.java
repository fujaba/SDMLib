package de.uniks.jism.gui.table;

/*
 Json Id Serialisierung Map
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:
 1. Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.
 3. All advertising materials mentioning features or use of this software
 must display the following acknowledgement:
 This product includes software developed by Stefan Lindel.
 4. Neither the name of contributors may be used to endorse or promote products
 derived from this software without specific prior written permission.

 THE SOFTWARE 'AS IS' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL STEFAN LINDEL BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

import org.sdmlib.serialization.EntityValueFactory;
import org.sdmlib.serialization.interfaces.PeerMessage;
import de.uniks.jism.gui.GUIPosition;

public class Column implements PeerMessage{
	public static final int AUTOWIDTH=-1;
	public static final String PROPERTY_ATTRNAME="attrName";
	public static final String PROPERTY_NUMBERFORMAT="numberformat";
	public static final String PROPERTY_EDITCOLUMN="editColumn";
	public static final String PROPERTY_LABEL="label";
	public static final String PROPERTY_DEFAULTTEXT="defaulttext";
	public static final String PROPERTY_BACKGROUNDCOLOR="bgcolor";
	public static final String PROPERTY_FORGROUNDCOLOR="fgcolor";
	public static final String PROPERTY_FORGROUNDCOLORACTIV="fgcoloractiv";
	public static final String PROPERTY_BACKGROUNDCOLORACTIV="bgcoloractiv";
	public static final String PROPERTY_CELLVALUE="cellvalue";
	public static final String PROPERTY_TEXTALIGNMENT="textalignment";
	public static final String PROPERTY_FONT="font";
	public static final String PROPERTY_SIZE="size";
	public static final String PROPERTY_RESIZE="resize";
	public static final String PROPERTY_VISIBLE="visible";
	public static final String PROPERTY_MOVABLE="movable";
	public static final String PROPERTY_ALTTEXT="altText";
	public static final String PROPERTY_BROWSERID="browserid";
	public static final String PROPERTY_VALUEFROMDROPDOWNLIST="valuefromdropdown";
	
	public static final String DATE="%DATE%";
	private int width=100;
	private String attrName;
	private String numberFormat;
	private String editColumn;
	private String label;
	private String defaultText;
	private String backgroundColor=null;
	private String forgroundColor=null;
	private String forgroundColorActiv=null;
	private String backgroundColorActiv=null;
	private String cellValue=null;
	private boolean isResizable=true;
	private int textalignment=-1;
	private boolean isVisible=true;
	private boolean isMovable=true;
	private String altAttribute;
	private Object item;
	private GUIPosition browserId=GUIPosition.CENTER;
	private String font;
	private boolean getDropDownListFromMap=false;
	
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public Column withLabel(String label) {
		this.label = label;
		return this;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public Column withWidth(int width) {
		this.width = width;
		return this;
	}

	/**
	 * @return the Attribute Name for display
	 */
	public String getAttrName() {
		return attrName;
	}

	/**
	 * @param attrName Attribute Name for display
	 */
	public Column withAttrName(String attrName) {
		this.attrName = attrName;
		return this;
	}
	/**
	 * @param attrName Attribute Name for display
	 * @param edit is the Column is editable 
	 * @return this
	 */
	public Column withAttrName(String attrName, boolean edit) {
		this.attrName = attrName;
		if(label==null){
			label = attrName;
		}
		if(edit){
			this.editColumn = attrName;
		}
		return this;
	}

	/**
	 * @return the NumberFormat
	 */
	public String getNumberFormat() {
		return numberFormat;
	}

	/**
	 * @param NumberFormat the NumberFormat to set
	 */
	public Column withNumberFormat(String value) {
		this.numberFormat = value;
		return this;
	}

	/**
	 * @return the editColumn
	 */
	public String getEditColumn() {
		return editColumn;
	}

	/**
	 * @param editColumn the editColumn to set
	 */
	public Column withEditColumn(String editColumn) {
		this.editColumn = editColumn;
		return this;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public Column withBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
		return this;
	}

	public String getForgroundColor() {
		return forgroundColor;
	}

	public Column withForgroundColor(String value) {
		this.forgroundColor = value;
		return this;
	}

	public String getForgroundColorActiv() {
		return forgroundColorActiv;
	}

	public Column withForgroundColorActiv(String forgroundColorActiv) {
		this.forgroundColorActiv = forgroundColorActiv;
		return this;
	}

	public String getBackgroundColorActiv() {
		return backgroundColorActiv;
	}

	public Column withBackgroundColorActiv(String backgroundColorActiv) {
		this.backgroundColorActiv = backgroundColorActiv;
		return this;
	}

	public String getCellValue() {
		return cellValue;
	}

	public Column withCellValue(String cellValue) {
		this.cellValue = cellValue;
		return this;
	}

	public boolean isResizable() {
		return isResizable;
	}

	public Column withResizable(boolean isResizable) {
		this.isResizable = isResizable;
		return this;
	}

	public int getTextalignment() {
		return textalignment;
	}

	public Column withTextalignment(int textalignment) {
		this.textalignment = textalignment;
		return this;
	}
	
	public EntityValueFactory getCellValueCreator(){
		return new EntityValueFactory();
	}

	public boolean isVisible() {
		return isVisible;
	}

	public Column withVisible(boolean isVisible) {
		this.isVisible = isVisible;
		return this;
	}

	public Column withAltAttribute(String altAttribute){
		this.altAttribute=altAttribute;
		return this;
	}
	
	public String getAltAttribute() {
		return altAttribute;
	}

	public Column withItem(Object item) {
		this.item=item;
		return this;
	}
	public Object getItem() {
		return item;
	}
	public void setSelection(int x, int y){}

	public boolean isMovable() {
		return isMovable;
	}

	public Column withMovable(boolean isMovable) {
		this.isMovable = isMovable;
		return this;
	}

	public GUIPosition getBrowserId() {
		return browserId;
	}

	public Column withBrowserId(GUIPosition browserId) {
		this.browserId = browserId;
		return this;
	}
	
	public boolean isEditingSupport() {
		return false;
	}

	public String getFont() {
		return font;
	}

	public Column withFont(String font) {
		this.font = font;
		return this;
	}
	
	public boolean isGetDropDownListFromMap() {
		return getDropDownListFromMap;
	}
	
	public Column withGetDropDownListFromMap(boolean getDropDownListFromMap) {
		this.getDropDownListFromMap = getDropDownListFromMap;
		return this;
	}
	
	public String getDefaultText() {
		return defaultText;
	}
	public Column withDefaultText(String defaultText) {
		this.defaultText = defaultText;
		return this;
	}
	@Override
	public Object get(String attribute) {
		String attrName;
		int pos = attribute.indexOf(".");
		if (pos > 0) {
			attrName = attribute.substring(0, pos);
		} else {
			attrName = attribute;
		}
		if (attrName.equalsIgnoreCase(PROPERTY_ATTRNAME)) {
			return this.getAttrName();
		}else if (attrName.equalsIgnoreCase(PROPERTY_NUMBERFORMAT)) {
			return this.getNumberFormat();
		}else if (attrName.equalsIgnoreCase(PROPERTY_EDITCOLUMN)) {
			return this.getEditColumn();
		}else if (attrName.equalsIgnoreCase(PROPERTY_LABEL)) {
			return this.getLabel();
		}else if (attrName.equalsIgnoreCase(PROPERTY_DEFAULTTEXT)) {
			return this.getDefaultText();
		}else if (attrName.equalsIgnoreCase(PROPERTY_BACKGROUNDCOLOR)) {
			return this.getBackgroundColor();
		}else if (attrName.equalsIgnoreCase(PROPERTY_FORGROUNDCOLOR)) {
			return this.getForgroundColor();
		}else if (attrName.equalsIgnoreCase(PROPERTY_FORGROUNDCOLORACTIV)) {
			return this.getForgroundColorActiv();
		}else if (attrName.equalsIgnoreCase(PROPERTY_BACKGROUNDCOLORACTIV)) {
			return this.getBackgroundColorActiv();
		}else if (attrName.equalsIgnoreCase(PROPERTY_CELLVALUE)) {
			return this.getCellValue();
		}else if (attrName.equalsIgnoreCase(PROPERTY_TEXTALIGNMENT)) {
			return this.getTextalignment();
		}else if (attrName.equalsIgnoreCase(PROPERTY_FONT)) {
			return this.getFont();
		}else if (attrName.equalsIgnoreCase(PROPERTY_SIZE)) {
			return this.getWidth();
		}else if (attrName.equalsIgnoreCase(PROPERTY_RESIZE)) {
			return this.isResizable();
		}else if (attrName.equalsIgnoreCase(PROPERTY_VISIBLE)) {
			return this.isVisible();
		}else if (attrName.equalsIgnoreCase(PROPERTY_MOVABLE)) {
			return this.isMovable();
		}else if (attrName.equalsIgnoreCase(PROPERTY_ALTTEXT)) {
			return this.getAltAttribute();
		}else if (attrName.equalsIgnoreCase(PROPERTY_BROWSERID)) {
			return this.getBrowserId();
		}else if (attrName.equalsIgnoreCase(PROPERTY_VALUEFROMDROPDOWNLIST)) {
			return this.isGetDropDownListFromMap();
		}
		return null;
	}
	@Override
	public boolean set(String attribute, Object value) {
		if (attribute.equalsIgnoreCase(PROPERTY_ATTRNAME)) {
			withAttrName((String) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_NUMBERFORMAT)) {
			withNumberFormat((String) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_EDITCOLUMN)) {
			withEditColumn((String) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_LABEL)) {
			withLabel((String) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_DEFAULTTEXT)) {
			withDefaultText((String) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_BACKGROUNDCOLOR)) {
			withBackgroundColor((String) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_FORGROUNDCOLOR)) {
			withForgroundColor((String) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_FORGROUNDCOLORACTIV)) {
			withForgroundColorActiv((String) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_BACKGROUNDCOLORACTIV)) {
			withBackgroundColorActiv((String) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_CELLVALUE)) {
			withCellValue((String) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_TEXTALIGNMENT)) {
			withTextalignment((Integer) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_FONT)) {
			withFont((String) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_SIZE)) {
			withWidth((Integer) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_RESIZE)) {
			withResizable((Boolean) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_VISIBLE)) {
			withVisible((Boolean) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_MOVABLE)) {
			withMovable((Boolean) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_ALTTEXT)) {
			withAltAttribute((String) value);
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_BROWSERID)) {
			withBrowserId(GUIPosition.valueOf((String)value));
			return true;
		} else if (attribute.equalsIgnoreCase(PROPERTY_VALUEFROMDROPDOWNLIST)) {
			withGetDropDownListFromMap((Boolean) value);
			return true;
		}
		return false;
	}
}
