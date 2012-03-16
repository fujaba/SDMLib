package org.sdmlib.serialization;

import org.sdmlib.serialization.interfaces.IdCounter;


public class SimpleIdCounter implements IdCounter{
	protected String prefixId="J1";
	protected long number = 1;
	
	public void setPrefixId(String sessionId) {
		this.prefixId = sessionId;
	}

	public String getId(Object obj) {
		String key;

		// new object generate key and add to tables
		// <session id>.<first char><running number>
		if (obj == null) {
			try {
				throw new Exception("NullPointer: " + obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String className = obj.getClass().getName();
		char firstChar = className.charAt(className.lastIndexOf('.') + 1);
		if (prefixId != null) {
			key = prefixId + "." + firstChar + number;
		} else {
			key = "" + firstChar + number;
		}
		number++;
		return key;
	}

	public void readId(String jsonId) {
		// adjust number to be higher than read numbers
		String[] split = jsonId.split("\\.");

		if (split.length != 2) {
			throw new RuntimeException("jsonid " + jsonId
					+ " should have one . in its middle");
		}
		if (prefixId.equals(split[0])) {
			String oldNumber = split[1].substring(1);
			long oldInt = Long.parseLong(oldNumber);
			if (oldInt >= number) {
				number = oldInt + 1;
			}
		}
	}
}
