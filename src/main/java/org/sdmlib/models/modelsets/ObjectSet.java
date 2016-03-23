package org.sdmlib.models.modelsets;

import de.uniks.networkparser.list.AbstractArray;

public class ObjectSet extends SDMSet<Object>
{
	@Override
	public ObjectSet with(Object... values) {
		super.with(values);
		return this;
	}
}
