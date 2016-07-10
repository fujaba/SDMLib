package org.sdmlib.modelcouch.connection;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.sdmlib.modelcouch.connection.authentication.Authenticator;
import org.sdmlib.modelcouch.connection.authentication.BasicAuthenticator;

public class HTTPConnectionHandler {

	private Authenticator authenticator;
	private String server;

	public HTTPConnectionHandler(String server) {
		this.server = server;
	}

	public RequestObject createRequestObject(String server, String path) {
		return new RequestObject(this, server, path);
	}

	public RequestObject createRequestObject(String url) {
		return new RequestObject(this, "", url);
	}

	public RequestObject createRequestObject() {
		return new RequestObject(this, this.server, "");
	}

	public Authenticator getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(Authenticator authenticator) {
		this.authenticator = authenticator;
	}

	public HTTPConnectionHandler withAuthenticator(Authenticator authenticator) {
		setAuthenticator(authenticator);
		return this;
	}

	/**
	 * Must be called after setting credentials (and authenticator)
	 * 
	 * You can get the Return from the Server by asking the Authenticator for
	 * the loginRequest
	 * 
	 * @param username
	 *            The Username
	 * @param password
	 *            The Password
	 * @return ThisComponent
	 * @throws Exception
	 *             any Errors for login
	 */
	public HTTPConnectionHandler login(String username, String password) throws Exception {
		if (this.authenticator == null) {
			this.authenticator = new BasicAuthenticator();
		}
		if (this.authenticator.login(username, password, this)) {
			return this;
		} else {
			throw new Exception("Couldn't log in...");
		}
	}

	protected void authenticate(HttpURLConnection connection) {
		if (authenticator != null) {
			authenticator.authenticate(connection);
		}
	}

	public ReturnObject send(RequestObject request) {
		ReturnObject res = new ReturnObject();
		try {
			URL obj = new URL(request.getServer() + request.getPath());
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod(request.getRequestType().toString());
			con.setDoInput(true);

			con.addRequestProperty("Content-Type", request.getContentType().getValue());

			for (Entry<String, String> entry : request.getRequestProperties().entrySet()) {
				con.addRequestProperty(entry.getKey(), entry.getValue());
			}
			authenticate(con);
			// Write to Connection
			if ((request.getOutput() != null && request.getOutput().length > 0)) {
				con.setDoOutput(true);
				con.getOutputStream().write(request.getOutput());
			}

			// Get the Results
			res.setResponseCode(con.getResponseCode());
			res.setResponseMessage(con.getResponseMessage());
			res.setHeaderFields(con.getHeaderFields());

			if (res.getResponseCode() >= 400 && con.getErrorStream() != null) {
				InputStream errorStream = (InputStream) con.getErrorStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream));
				try {
					LinkedList<String> lines = new LinkedList<>();
					String readLine = null;
					do {
						readLine = reader.readLine();
						if (readLine == null) {
							break;
						}
						lines.add(readLine);
					} while (readLine != null);
					res.setError(lines);
				} catch (IOException e) {
					// e.printStackTrace();
				}
			} else {
				if (request.isShouldHandleInput()) {
					InputStream content = (InputStream) con.getContent();
					ByteArrayOutputStream buffer = new ByteArrayOutputStream();
					int nRead;
					List<String> contentLength = res.getHeaderFields().get("Content-Length");
					if (contentLength == null) {
						contentLength = res.getHeaderFields().get("Content-length");
						// try to get Content length
					}
					byte[] data = new byte[Integer.parseInt(contentLength.get(0))];
					while ((nRead = content.read(data, 0, data.length)) != -1) {
						buffer.write(data, 0, nRead);
					}
					buffer.flush();
					BufferedInputStream reader = new BufferedInputStream(content);
					byte[] bytes = buffer.toByteArray();
					res.setContent(bytes);
				}
			}
			con.disconnect();
		} catch (Exception e) {
			res.setResponseCode(400);
			e.printStackTrace();
		}
		return res;
	}

	public boolean testConnection(String hostName, int port, String path) {
		String urlString = "http://" + hostName + ":" + port + "/" + path;
		try {
			// first check, if host is available..
			URL urlObj = new URL(urlString);
			URLConnection openConnection = urlObj.openConnection();
			openConnection.connect();

			// now check for existence of whole URL..
			RequestObject check = createRequestObject(this.server, path);
			check.setRequestType(RequestType.GET);
			check.setShouldHandleInput(true);

			ReturnObject send = send(check);
			if (send.getResponseCode() >= 400) {
				return false;
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}

}
