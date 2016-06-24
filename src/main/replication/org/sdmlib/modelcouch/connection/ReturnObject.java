package org.sdmlib.modelcouch.connection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ReturnObject {
	int responseCode = -1;
	private String responseMessage = null;
	byte[] content = null;
	LinkedList<String> error = null;
	private Map<String, List<String>> headerFields;

	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode
	 *            the responseCode to set
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the content
	 */
	public LinkedList<String> getContentAsString() {
		LinkedList<String> res = new LinkedList<>();
		String string = "";
		try {
			string = new String(getContentAsBytes(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// FIXME: make Platform independent
		String[] split = string.split("\n");

		for (int i = 0; i < split.length; i++) {
			res.add(split[i]);
		}

		return res;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}

	/**
	 * @return the error
	 */
	public LinkedList<String> getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(LinkedList<String> error) {
		this.error = error;
	}

	public LinkedList<String> read(InputStream content) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		LinkedList<String> lines = new LinkedList<>();
		try {
			if (reader.ready()) {
				while (reader.ready()) {
					lines.add(reader.readLine());
				}
			}
		} catch (Exception e) {
			//
		}
		return lines;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public String getResponseMessageSafe() {
		if (responseMessage == null)
			return "";
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	@Override
	public String toString() {
		StringBuffer res = new StringBuffer();

		if (error != null && error.size() > 0) {
			res.append(error);
		} else if (content != null && content.length > 0) {
			res.append(new String(content));
		} else {
			res.append(responseCode);
			res.append(":");
			res.append(responseMessage);
		}

		return res.toString();
	}

	public void setHeaderFields(Map<String, List<String>> headerFields) {
		this.headerFields = headerFields;
	}

	public Map<String, List<String>> getHeaderFields() {
		if (headerFields == null)
			return new LinkedHashMap<>();
		return headerFields;
	}

	public byte[] getContentAsBytes() {
		if (content == null) {
			return new byte[0];
		}
		return content;
	}
}
