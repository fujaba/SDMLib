package org.sdmlib.serialization.gui.table;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or – as soon they
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
import java.util.Comparator;
import org.sdmlib.serialization.EntityValueFactory;
import org.sdmlib.serialization.gui.Style;
import org.sdmlib.serialization.interfaces.GUIPosition;
import org.sdmlib.serialization.interfaces.PeerMessage;

public class Column implements PeerMessage{
	public static final int AUTOWIDTH=-1;
	public static final String PROPERTY_STYLE="style";
	public static final String PROPERTY_ACTIVESTYLE="activeStyle";
	public static final String PROPERTY_ATTRNAME="attrName";
	public static final String PROPERTY_NUMBERFORMAT="numberformat";
	public static final String PROPERTY_EDITCOLUMN="editColumn";
	public static final String PROPERTY_LABEL="label";
	public static final String PROPERTY_DEFAULTTEXT="defaulttext";
	public static final String PROPERTY_RESIZE="resize";
	public static final String PROPERTY_VISIBLE="visible";
	public static final String PROPERTY_MOVABLE="movable";
	public static final String PROPERTY_ALTTEXT="altText";
	public static final String PROPERTY_BROWSERID="browserid";
	public static final String PROPERTY_FIELDTYP="fieldTyp";
	
	public static final String DATE="%DATE%";
	private Style style;
	private Style activestyle;
	
	private String attrName;
	private String numberFormat;
	private boolean isEditable=false;
	private String label;
	private String defaultText;
	private boolean isResizable=true;
	private boolean isVisible=true;
	private boolean isMovable=true;
	private String altAttribute;
	private FieldTyp fieldTyp;
	private GUIPosition browserId=GUIPosition.CENTER;
	protected ColumnListener handler;
	private Comparator<TableCellValue> comparator;
	
	
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	
	public String getLabelOrAttrName() {
		if(label==null){
			return attrName;
		}
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
		this.isEditable = edit;
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
	public boolean isEditable() {
		return isEditable;
	}

	/**
	 * @param editColumn the editColumn to set
	 */
	public Column withEditable(boolean value) {
		this.isEditable = value;
		return this;
	}

	public boolean isResizable() {
		return isResizable;
	}

	public Column withResizable(boolean isResizable) {
		this.isResizable = isResizable;
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
	
	public FieldTyp getFieldTyp() {
		return fieldTyp;
	}
	
	public Column withFieldTyp(FieldTyp fieldTyp) {
		this.fieldTyp = fieldTyp;
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
		if (attrName.equalsIgnoreCase(PROPERTY_ATTRNAME))
			return this.getAttrName();
		if (attrName.equalsIgnoreCase(PROPERTY_NUMBERFORMAT))
			return this.getNumberFormat();
		if (attrName.equalsIgnoreCase(PROPERTY_EDITCOLUMN))
			return this.isEditable();
		if (attrName.equalsIgnoreCase(PROPERTY_LABEL))
			return this.getLabel();
		if (attrName.equalsIgnoreCase(PROPERTY_DEFAULTTEXT))
			return this.getDefaultText();
		if (attrName.equalsIgnoreCase(PROPERTY_STYLE))
			return this.getStyle();
		if (attrName.equalsIgnoreCase(PROPERTY_ACTIVESTYLE))
			return this.getActiveStyle();
		if (attrName.equalsIgnoreCase(PROPERTY_RESIZE))
			return this.isResizable();
		if (attrName.equalsIgnoreCase(PROPERTY_VISIBLE))
			return this.isVisible();
		if (attrName.equalsIgnoreCase(PROPERTY_MOVABLE))
			return this.isMovable();
		if (attrName.equalsIgnoreCase(PROPERTY_ALTTEXT))
			return this.getAltAttribute();
		if (attrName.equalsIgnoreCase(PROPERTY_BROWSERID))
			return this.getBrowserId();
		if (attrName.equalsIgnoreCase(PROPERTY_FIELDTYP))
			return this.getFieldTyp();
		return null;
	}
	
	@Override
	public boolean set(String attribute, Object value) {
		if (attribute.equalsIgnoreCase(PROPERTY_ATTRNAME)) {
			withAttrName((String) value);
			return true;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_NUMBERFORMAT)) {
			withNumberFormat((String) value);
			return true;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_EDITCOLUMN)) {
			withEditable((Boolean) value);
			return true;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_LABEL)) {
			withLabel((String) value);
			return true;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_DEFAULTTEXT)) {
			withDefaultText((String) value);
			return true;
		}
		if (attrName.equalsIgnoreCase(PROPERTY_STYLE)){
			withStyle((Style) value);
			return true;
		}
		if (attrName.equalsIgnoreCase(PROPERTY_ACTIVESTYLE)){
			withActiveStyle((Style) value);
			return true;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_RESIZE)) {
			withResizable((Boolean) value);
			return true;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_VISIBLE)) {
			withVisible((Boolean) value);
			return true;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_MOVABLE)) {
			withMovable((Boolean) value);
			return true;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_ALTTEXT)) {
			withAltAttribute((String) value);
			return true;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_BROWSERID)) {
			withBrowserId(GUIPosition.valueOf((String)value));
			return true;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_FIELDTYP)) {
			withFieldTyp(FieldTyp.valueOf(""+value));
			return true;
		}
		return false;
	}

	public Style getStyle() {
		return style;
	}

	public Column withStyle(Style style) {
		this.style = style;
		return this;
	}
	
	public Style getActiveStyle() {
		return activestyle;
	}

	public Column withActiveStyle(Style activestyle) {
		this.activestyle = activestyle;
		return this;
	}
	
	public Column withListener(ColumnListener handler){
		this.handler = handler;
		this.handler.withColumn(this);
		return this;
	}
	
	public ColumnListener getListener(){
		if(handler==null){
			withListener(getDefaultListener());
		}
		return handler;
	}
	
	public ColumnListener getDefaultListener(){
		return new ColumnListener();
	}

	public Comparator<TableCellValue> getComparator() {
		return comparator;
	}

	public Column withComparator(Comparator<TableCellValue> comparator) {
		this.comparator = comparator;
		return this;
	}
}
