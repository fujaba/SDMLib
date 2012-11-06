package de.uniks.jism.gui.table;

import java.util.Comparator;

import org.sdmlib.serialization.interfaces.PeerMessage;

public class TableListComparator implements Comparator<Object>{
	public static final int ASC = 1;
	public static final int DESC = -1;

	private int direction = 1;
	private String column;
	public TableListComparator()
	{
	}
	
	@Override
	public int compare(Object o1, Object o2) {
		return direction*compareValue(o1, o2);
	}
	
	public int compareValue(Object o1, Object o2) {
		PeerMessage p1 = (PeerMessage) o1;
		PeerMessage p2 = (PeerMessage) o2;
		if(column==null){
			if(p1.equals(p2)){
				return 0;
			}
			return 1;
		}
		
		if(p1.get(column) instanceof String){
			String valueA=(String) p1.get(column);
			String valueB=(String) p2.get(column);
			if(valueA!=null){
				return valueA.compareToIgnoreCase(valueB);
			}else{
				return -1;
			}
		}else if(p1.get(column) instanceof Integer){
			Integer valueA=(Integer) p1.get(column);
			Integer valueB=(Integer) p2.get(column);
			if(valueA!=null){
				return valueA.compareTo(valueB);
			}else{
				return -1;
			}
		}else if(p1.get(column) instanceof Long){
			Long valueA=(Long) p1.get(column);
			Long valueB=(Long) p2.get(column);
			if(valueA!=null){
				return valueA.compareTo(valueB);
			}else{
				return -1;
			}		}else if(p1.get(column) instanceof Boolean){
			Boolean valueA=(Boolean) p1.get(column);
			Boolean valueB=(Boolean) p2.get(column);
			if(valueA!=null){
				return valueA.compareTo(valueB);
			}else{
				return -1;
			}
		}
		return 0;
	}

	public int getDirection() {
		return direction;
	}

	public int setDirection(int direction) {
		this.direction = direction;
		return direction;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}
}
