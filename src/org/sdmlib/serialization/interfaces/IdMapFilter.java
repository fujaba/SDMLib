package org.sdmlib.serialization.interfaces;

import org.sdmlib.serialization.IdMap;

public interface IdMapFilter {
	public final static int ALLDEEP = -1;
	public final static int DEEPER = -2;
	public boolean isConvertable(IdMap map, Object entity, String property, Object value);
	public boolean addObject(String id);
	public int setDeep(int value);
	public boolean existsObject(String id);
}
