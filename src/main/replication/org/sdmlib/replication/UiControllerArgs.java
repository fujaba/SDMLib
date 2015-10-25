package org.sdmlib.replication;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Vector;

public class UiControllerArgs extends ResourceBundle {

	private HashMap<String, Object> args = new HashMap<>();

	public Object put(String key, Object value) {
		return args.put(key, value);
	}

	@Override
	public Enumeration<String> getKeys() {
		return new Vector<String>(args.keySet()).elements();
	}

	@Override
	protected Object handleGetObject(String key) {
		return args.get(key);
	}
}