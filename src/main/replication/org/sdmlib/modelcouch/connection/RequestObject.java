package org.sdmlib.modelcouch.connection;

import java.io.UnsupportedEncodingException;
import java.net.ContentHandler;
import java.util.LinkedHashMap;

public class RequestObject {
	private RequestType requestType = RequestType.GET;
	private String path;
	private String server;
	private byte[] output = null;
	private ContentHandler contentHandler = null;
	// TODO - set to false for Speed Improvement!
	private boolean shouldHandleInput = false;
	private LinkedHashMap<String, String> requestProperties = null;
	private ContentType contentType = ContentType.APPLICATION_JSON;
	private HTTPConnectionHandler connnectionHandler = null;

	public RequestObject(HTTPConnectionHandler conHandler, String server, String path) {
		this.connnectionHandler = conHandler;
		this.server = server;
		this.path = path;
	}

	public RequestObject(String server, String path) {
		this.server = server;
		this.path = path;
	}

	public RequestObject(String server) {
		this(server, "");
	}

	/**
	 * @return the requestType
	 */
	public RequestType getRequestType() {
		return requestType;
	}

	/**
	 * @param requestType
	 *            the requestType to set
	 */
	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * @param server
	 *            the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}

	/**
	 * @return the output
	 */
	public byte[] getOutput() {
		return output;
	}

	/**
	 * @param output
	 *            the output to set
	 */
	public void setOutput(byte[] output) {
		this.output = output;
	}

	public void setOutput(String output) {
		this.output = output.getBytes();
	}
	
	public void setOutput(String output, String encoding) throws UnsupportedEncodingException {
		this.output = output.getBytes(encoding);
	}

	/**
	 * @return the contentHandler
	 */
	public ContentHandler getContentHandler() {
		return contentHandler;
	}

	/**
	 * @param contentHandler
	 *            the contentHandler to set
	 */
	public void setContentHandler(ContentHandler contentHandler) {
		this.contentHandler = contentHandler;
	}

	public void setShouldHandleInput(boolean handleInput) {
		this.shouldHandleInput = handleInput;
	}

	public boolean isShouldHandleInput() {
		return this.shouldHandleInput;
	}

	public void addRequestProperty(String key, String value) {
		if (this.requestProperties == null)
			this.requestProperties = new LinkedHashMap<>();
		this.requestProperties.put(key, value);
	}

	public String removeRequestProperty(String key) {
		if (this.requestProperties != null)
			return this.requestProperties.remove(key);
		return null;
	}

	public LinkedHashMap<String, String> getRequestProperties() {
		if (requestProperties == null) {
			requestProperties = new LinkedHashMap<>();
		}
		return requestProperties;
	}

	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	public ReturnObject send() {
		if (connnectionHandler != null) {
			return connnectionHandler.send(this);
		} else {
			throw new RuntimeException("No ConnectionHandler available to send...");
		}
	}

	@Override
	public String toString() {
		return server + path;
	}
}
