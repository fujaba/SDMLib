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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class TableColumnLabelProvider extends ColumnLabelProvider{
	private Color backgroundColor=null;
	private Color forgroundColor=null;
	private Color forgroundColorActiv=null;
	private Font font=null;
	private Color backgroundColorActiv=null;
	private Column column;
	private IdMap map;

	public TableColumnLabelProvider(Column column, IdMap map){
		this.map=map;
		if(column.getBackgroundColor()!=null){
			setBackgroundColor(column.getBackgroundColor());
		}
		if(column.getForgroundColor()!=null){
			setForgroundColor(column.getForgroundColor());
		}
		this.column=column;
	}

	@Override
    public Color getBackground(Object element) {
        return getBackgroundColor();
    }
    @Override
    public String getText(Object element) {
    	if(column.getCellValue()!=null){
    		return column.getCellValue();	
    	}
		SendableEntityCreator creatorClass = map.getCreatorClass(element);
		if(Column.DATE.equalsIgnoreCase(column.getRegEx())){
			if(creatorClass!=null){
				Object value=creatorClass.getValue(element, column.getAttrName());
				if(value!=null){
					return getDateFormat((Long) value);
				}
			}
		}else if(column.getRegEx()!=null){
			
		}else{
			if(creatorClass!=null){
				Object value=creatorClass.getValue(element, column.getAttrName());
				if(value!=null){
					return value.toString();
				}
			}
		}
    	return "";
    }
    @Override
    public void update(ViewerCell cell) {
    	super.update(cell);
    	//FIND PROPERTY
		if(column instanceof ColumnNotification){
			((ColumnNotification)column).updateTableViewer(cell);
		}
    }
    
    @Override
    public Color getForeground(Object element) {
        return forgroundColor;
    }
    @Override
    public Font getFont(Object element) {
    	if(font!=null){
    		return font;
    	}
    	if(this.column.getFont()!=null){
    		String[] fontParam = this.column.getFont().split(":");
    		font=new Font(Display.getDefault(), fontParam[0], Integer.valueOf(fontParam[1]), SWT.NONE);
    		return font;
    	}
    	return super.getFont(element);
    }

	public void setBackgroundColor(String backgroundColor) {
		Display display=Display.getDefault();
		this.backgroundColor = new Color(display, getRGB(backgroundColor));
		this.backgroundColorActiv = this.backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor, String backgroundColorActiv) {
		Display display=Display.getDefault();
		this.backgroundColor = new Color(display, getRGB(backgroundColor));
		this.backgroundColorActiv = new Color(display, getRGB(backgroundColorActiv));
		
	}
	
	public void setForgroundColor(String forgroundColor) {
		Display display=Display.getDefault();
		this.forgroundColor = new Color(display, getRGB(forgroundColor));
		this.forgroundColorActiv = this.forgroundColor;
	}
	public void setForegroundColor(String forgroundColor, String forgroundColorActiv) {
		Display display=Display.getDefault();
		this.forgroundColor = new Color(display, getRGB(forgroundColor));
		this.forgroundColorActiv = new Color(display, getRGB(forgroundColorActiv));
		
	}
	private RGB getRGB(String value){
		String hexVal = "0123456789ABCDEF";
		int data[] = new int[value.length()/2];
	    for(int i=0;i < value.length();i+=2) {
	        data[i/2] = hexVal.indexOf(value.charAt(i))*16+hexVal.indexOf(value.charAt(i+1));
	    }
		return new RGB(data[0], data[1], data[2]);
	}
	
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Color getBackgroundColorActiv() {
		return backgroundColorActiv;
	}

	public Color getForgroundColor() {
		return forgroundColor;
	}

	public Color getForgroundColorActiv() {
		return forgroundColorActiv;
	}
	private String getDateFormat(long value){
		if(value==0){
			return "";
		}
		DateFormat formatter = new SimpleDateFormat("dd.MM.yy - HH:mm:ss");
		return formatter.format(new Date(value));
	}
	
	public String getToolTipText(Object element) {
		if (column.getAltAttribute() != null) {
			SendableEntityCreator creatorClass = map.getCreatorClass(element);
			if (creatorClass != null) {
				String text = ""
						+ creatorClass.getValue(element,
								column.getAltAttribute());
				if (text.equals("")) {
					return null;
				}
				return text;
			}

		}
		return "";
	}
	
	
	@Override
	public Point getToolTipShift(Object object) {
		return new Point(5, 5);
	}
	
	@Override
	public int getToolTipDisplayDelayTime(Object object) {
		return 2000;
	}

	@Override
	public int getToolTipTimeDisplayed(Object object) {
		return 8000;
	}
}
