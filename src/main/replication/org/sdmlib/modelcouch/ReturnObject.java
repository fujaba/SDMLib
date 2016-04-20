package org.sdmlib.modelcouch;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class ReturnObject {
	int responseCode = -1;
	LinkedList<String> content = null;
	LinkedList<String> error = null;

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
	public LinkedList<String> getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(LinkedList<String> content) {
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

	public LinkedList<String> read(InputStream content){
		BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		LinkedList<String> lines = new LinkedList<>();
		try {
			if(reader.ready()){
				while (reader.ready()) {
					lines.add(reader.readLine());
				}
			}
		}catch(Exception e){
			//
		}
		return lines;
	}

}
