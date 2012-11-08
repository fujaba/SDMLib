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

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Point;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class TableCellLabelProvider extends CellLabelProvider {
	private Column column;
	private IdMap map;

	public TableCellLabelProvider(Column column,IdMap map){
		this.column = column;
		this.map=map;
	}
	
	@Override
	public void update(ViewerCell cell) {
		SendableEntityCreator creatorClass = map.getCreatorClass(cell.getElement());
		if(Column.DATE.equalsIgnoreCase(column.getRegEx())){
			if(creatorClass!=null){
				Object value=creatorClass.getValue(cell.getElement(), column.getAttrName());
				if(value!=null){
					cell.setText(getDateFormat((Long) value));
				}
			}
		}else if(column.getRegEx()!=null){
			//FIND PROPERTY
			if(column instanceof ColumnNotification){
				((ColumnNotification)column).updateTableViewer(cell);
			}
		}else{
			if(creatorClass!=null){
				Object value=creatorClass.getValue(cell.getElement(), column.getAttrName());
				if(value==null){
					value="";
				}
				cell.setText(value.toString());
			}
		}
	}
	
	private String getDateFormat(long value){
		if(value==0){
			return "";
		}
		DateFormat formatter = new SimpleDateFormat("dd.MM.yy - HH:mm:ss");
		return formatter.format(new Date(value));
	}
	
	public String getToolTipText(Object element) {
		if(column.getAltAttribute()!=null){
			SendableEntityCreator creatorClass = map.getCreatorClass(element);
			if(creatorClass!=null){
				String text=""+creatorClass.getValue(element, column.getAltAttribute());
				if(text.equals("")){
					return null;
				}
				return text;
			}
			
		}
		return "";
	}
	
	public Point getToolTipShift(Object object) {
		return new Point(5, 5);
	}
	public int getToolTipDisplayDelayTime(Object object) {
		return 2000;
	}
	
	public int getToolTipTimeDisplayed(Object object) {
		return 8000;
	}
}

