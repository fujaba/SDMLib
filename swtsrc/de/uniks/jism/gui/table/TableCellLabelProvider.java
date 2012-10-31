package de.uniks.jism.gui.table;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Point;
import org.sdmlib.serialization.interfaces.PeerMessage;

public class TableCellLabelProvider extends CellLabelProvider {
	private Column column;

	public TableCellLabelProvider(Column column){
		this.column = column;
	}
	
	@Override
	public void update(ViewerCell cell) {
		if(Column.DATE.equalsIgnoreCase(column.getRegEx())){
			Object value=((PeerMessage)cell.getElement()).get(column.getAttrName());
			if(value!=null){
				cell.setText(getDateFormat((Long) value));
			}
		}else if(column.getRegEx()!=null){
			//FIND PROPERTY
			column.updateTableViewer(cell);
		}
		
		
//		System.out.println(arg0.getColumnIndex());
	}
	private String getDateFormat(long value){
		if(value==0){
			return "";
		}
		DateFormat formatter = new SimpleDateFormat("dd.MM.yy - HH:mm:ss");
		return formatter.format(new Date(value));
	}
	
	public String getToolTipText(Object element) {
	
		if(element instanceof PeerMessage&&column.getAltAttribute()!=null){
			PeerMessage item=(PeerMessage) element;
			String text=""+item.get(column.getAltAttribute());
			if(text.equals("")){
				return null;
			}
			return text;
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

