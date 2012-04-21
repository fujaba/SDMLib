package org.sdmlib.serialization.interfaces;

import org.sdmlib.serialization.IdMap;

public interface IdMapFilter {
	public boolean isConvertable(IdMap map, Object entity, String property, Object value);
}
