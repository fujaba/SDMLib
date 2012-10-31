package de.uniks.jism.gui.table;

import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.TableItem;
import org.sdmlib.serialization.interfaces.PeerMessage;

public class Column {
	public static final String DATE="%DATE%";
	private int width=100;
	private String attrName;
	private String regEx;
	private String editColumn;
	private String label;
	private String backgroundColor=null;
	private String forgroundColor=null;
	private String forgroundColorActiv=null;
	private String backgroundColorActiv=null;
	private String cellValue=null;
	private EditingSupport editingsupport;
	private boolean isResizable=true;
	private int textalignment=-1;
	private boolean isVisible=true;
	private String altAttribute;
	private PeerMessage item;
	
	public Column(){
		
	}
	
	public Column(String label, int width){
		this.label=label;
		this.width=width;
	}
	public Column(String label, int width, String attrName){
		this.label=label;
		this.width=width;
		this.attrName=attrName;
	}
	public Column(String label, int width, String attrName, boolean edit){
		this.label=label;
		this.width=width;
		this.attrName=attrName;
		if(edit){
			this.editColumn=attrName;
		}
	}
	public Column(String label, int width, String attrName, String regEx, String editColumn){
		this.label=label;
		this.width=width;
		this.attrName=attrName;
		this.regEx=regEx;
		this.editColumn=editColumn;
	}
	public Column(String label, int width, String attrName, String regEx){
		this.label=label;
		this.width=width;
		this.attrName=attrName;
		this.regEx=regEx;
	}
	
	public void updateTableViewer(ViewerCell cell){
		
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
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
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the sort
	 */
	public String getAttrName() {
		return attrName;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	/**
	 * @return the regEx
	 */
	public String getRegEx() {
		return regEx;
	}

	/**
	 * @param regEx the regEx to set
	 */
	public void setRegEx(String regEx) {
		this.regEx = regEx;
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
	public void setEditColumn(String editColumn) {
		this.editColumn = editColumn;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public void setColor(String forgroundColor, String backgroundColor) {
		this.forgroundColor = forgroundColor;
		this.backgroundColor = backgroundColor;
	}

	public String getForgroundColor() {
		return forgroundColor;
	}

	public void setForgroundColor(String value) {
		this.forgroundColor = value;
	}

	public String getForgroundColorActiv() {
		return forgroundColorActiv;
	}

	public void setForgroundColorActiv(String forgroundColorActiv) {
		this.forgroundColorActiv = forgroundColorActiv;
	}

	public String getBackgroundColorActiv() {
		return backgroundColorActiv;
	}

	public void setBackgroundColorActiv(String backgroundColorActiv) {
		this.backgroundColorActiv = backgroundColorActiv;
	}

	public String getCellValue() {
		return cellValue;
	}

	public void setCellValue(String cellValue) {
		this.cellValue = cellValue;
	}

	public void setCellValue(String cellValue, int alignment) {
		this.cellValue = cellValue;
		this.textalignment=alignment;
	}

	public void setEditingSupport(EditingSupport value) {
		this.editingsupport=value;
	}
	public EditingSupport getEditingSupport() {
		return this.editingsupport;
	}
	
	public boolean isEditingSupport() {
		return editingsupport!=null;
	}

	public boolean isResizable() {
		return isResizable;
	}

	public void setResizable(boolean isResizable) {
		this.isResizable = isResizable;
	}

	public int getTextalignment() {
		return textalignment;
	}

	public void setTextalignment(int textalignment) {
		this.textalignment = textalignment;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public void setAltAttribute(String altAttribute){
		this.altAttribute=altAttribute;
	}
	public String getAltAttribute() {
		return altAttribute;
	}

	public void setItem(PeerMessage item) {
		this.item=item;
	}
	public PeerMessage getItem() {
		return item;
	}
	
	public void setSelection(TableComponent tableComponent, TableItem item, int x, int y){
		
	}
	
}
