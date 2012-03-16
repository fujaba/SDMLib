package org.sdmlib.serialization.interfaces;

public interface IdCounter {
	public void setPrefixId(String sessionId);
	public String getId(Object obj);
	public void readId(String jsonId);
}
