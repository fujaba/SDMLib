package de.uniks.jism.gui.table;
/*
Copyright (c) 2012, Stefan Lindel
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

THIS SOFTWARE IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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
	private boolean isResizable=true;
	private int textalignment=-1;
	private boolean isVisible=true;
	private boolean isMovable=true;
	private String altAttribute;
	private PeerMessage item;
	private int browserId=TableComponent.BROWSER;
	
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
	public void setSelection(int x, int y){
		
	}
	public boolean isMovable() {
		return isMovable;
	}

	public void setMovable(boolean isMovable) {
		this.isMovable = isMovable;
	}

	public int getBrowserId() {
		return browserId;
	}

	public void setBrowserId(int browserId) {
		this.browserId = browserId;
	}
	public boolean isEditingSupport() {
		return false;
	}
	
}
