package org.sdmlib.serialization;


public class IdMapFilter {
	public final static int ALLDEEP = -1;
	public final static int LASTDEEP = 0;
	public final static int DEEPER = -2;
	protected int deep = ALLDEEP;

	public boolean isConvertable(IdMap map, Object entity, String property, Object value){
		if (getDeep() == LASTDEEP){
			return false;
		}
		return true;
	}
	
	public int getDeep() {
		return deep;
	}
	
	public int setDeep(int value) {
		int oldValue = deep;
		if(value==DEEPER){
			if (deep != ALLDEEP) {
				deep = deep - 1;
			}
		}else{
			deep=value;
		}
		return oldValue;
	}
}

