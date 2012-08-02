package org.sdmlib.serialization.bytes;

import org.sdmlib.serialization.IdMapFilter;

public class ByteFilter extends IdMapFilter{
	private ByteIdMap map;

	public ByteFilter(ByteIdMap map){
		this.map=map;
	}

	public ByteIdMap getMap() {
		return this.map;
	}
}
