package org.sdmlib.serialization.interfaces;

import org.sdmlib.serialization.json.JsonObject;

public interface MapUpdateListener {
	public boolean sendUpdateMsg(JsonObject jsonObject);
}
