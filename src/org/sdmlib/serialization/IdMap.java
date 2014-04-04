package org.sdmlib.serialization;

import org.sdmlib.serialization.interfaces.BaseEntity;

public abstract class IdMap extends IdMapEncoder{
	public abstract Object decode(BaseEntity value);
	public abstract Object decode(String value);
}
