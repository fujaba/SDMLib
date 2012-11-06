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

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class TableColumnLabelProvider extends ColumnLabelProvider{
	private Color backgroundColor=null;
	private Color forgroundColor=null;
	private Color forgroundColorActiv=null;
	private Font font=new Font(Display.getDefault(), "Comic Sans MS", 12, SWT.NONE);
	private Color backgroundColorActiv=null;
	private Column column;

	public TableColumnLabelProvider(Column column){
		if(column.getBackgroundColor()!=null){
			setBackgroundColor(column.getBackgroundColor());
		}
		if(column.getForgroundColor()!=null){
			setForgroundColor(column.getForgroundColor());
		}
		this.column=column;
		
//		tableLabelProvider.setBackgroundColor("D4D4D4");
//		tableLabelProvider.setForgoundColor("00A18F");
//		explorerColumnViewer.setLabelProvider(this.cellListener.addCellListener(tableLabelProvider));
//
	}
    @Override
    public Color getBackground(Object element) {
        return getBackgroundColor();
    }
    @Override
    public String getText(Object element) {
    	return column.getCellValue();
    }
    @Override
    public Color getForeground(Object element) {
        return forgroundColor;
    }
    @Override
    public Font getFont(Object element) {
    	return font;
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
}
