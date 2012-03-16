package org.sdmlib.serialization.interfaces;

import org.sdmlib.serialization.json.JsonObject;

public interface UpdateListener {
	public void sendUpdateMsg(JsonObject jsonObject);
}
