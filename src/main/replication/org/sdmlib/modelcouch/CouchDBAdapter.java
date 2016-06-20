package org.sdmlib.modelcouch;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.sdmlib.modelcouch.authentication.Authenticator;
import org.sdmlib.modelcouch.authentication.BasicAuthenticator;

import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonObject;
import de.uniks.networkparser.list.SimpleKeyValueList;
import de.uniks.networkparser.list.SimpleList;

public class CouchDBAdapter {
	private String userName = "";
	private String hostName = "localhost";
	private int port = 5984;

	public String getHostName() {
		return hostName;
	}

	public int getPort() {
		return port;
	}

	public String getUserName() {
		return userName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public CouchDBAdapter withHostName(String hostName) {
		this.hostName = hostName;
		return this;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public CouchDBAdapter withPort(int port) {
		this.port = port;
		return this;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public CouchDBAdapter withUserName(String username) {
		this.userName = username;
		return this;
	}

	public ReturnObject replicate(ModelCouch modelCouch, String source, String target) {
		RequestObject request = createRequestObject();
		request.setPath("_replicate");
		request.setRequestType(RequestType.POST);

		/*
		 * {"source":"http://example.org/example-database","target":
		 * "http://admin:password@127.0.0.1:5984/example-database"}
		 */

		JsonObject replicateObject = new JsonObject();
		replicateObject.add("create_target", true);
		replicateObject.add("source", source);
		replicateObject.add("target", target);
		replicateObject.add("retries_per_request", 2);

		request.setOutput(replicateObject.toString().getBytes());

		return send(request);
	}

	public ReturnObject setUserPrivileges(String database, Collection<String> adminNames, Collection<String> adminRoles,
			Collection<String> memberNames, Collection<String> memberRoles) {
		RequestObject request = createRequestObject();
		request.setPath(database + "/_security");
		request.setRequestType(RequestType.PUT);
		request.setShouldHandleInput(true);

		JsonObject json = new JsonObject();
		JsonObject admins = new JsonObject();
		JsonArray adminNamesJson = new JsonArray();
		adminNamesJson.addAll(adminNames);
		admins.add("names", adminNamesJson);
		JsonArray adminRolesJson = new JsonArray();
		adminRolesJson.addAll(adminRoles);
		admins.add("roles", adminRolesJson);
		json.put("admins", admins);
		JsonObject members = new JsonObject();
		JsonArray memberNamesJson = new JsonArray();
		memberNamesJson.addAll(memberNames);
		members.add("names", memberNamesJson);
		JsonArray memberRolesJson = new JsonArray();
		memberRolesJson.addAll(memberRoles);
		members.add("roles", memberRolesJson);
		json.put("members", members);

		request.setOutput(json.toString().getBytes(Charset.forName("UTF-8")));

		return send(request);
	}

	public RequestObject createRequestObject() {
		return new RequestObject(this);
	}

	public ReturnObject addAttachment(ReturnObject lastRes, Path path, ContentType contentType) {
		ReturnObject res = null;
		byte[] content = lastRes.getContentAsBytes();
		if (content != null && content.length > 0) {
			JsonObject jsonObject = new JsonObject();
			try {
				jsonObject.withValue(new String(content, "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			// ...

			RequestObject attachRequest = createRequestObject();
			attachRequest.setShouldHandleInput(true);
			attachRequest.setContentType(contentType);
			attachRequest.setRequestType(RequestType.PUT);

			String id = (String) jsonObject.getValue("id");
			String rev = (String) jsonObject.getValue("rev");
			
			attachRequest.setServer(lastRes.getHeaderFields().get("Location").get(0));
			attachRequest.setPath("/attachment?rev=" + rev);

			try {
				attachRequest.setOutput(Files.readAllBytes(path));
				res = send(attachRequest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (res == null)
			res = new ReturnObject();
		return res;
	}

	public byte[] getAttachment(ReturnObject lastRes) {
		byte[] res = null;
		LinkedList<String> content = lastRes.getContentAsString();
		if (content != null && content.size() > 0) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.withValue(content.toArray(new String[content.size()]));

			RequestObject attachRequest = createRequestObject();
			attachRequest.setShouldHandleInput(true);
			attachRequest.setRequestType(RequestType.GET);

			String id = (String) jsonObject.getValue("id");
			String rev = (String) jsonObject.getValue("rev");

			attachRequest.setPath("segroup/" + id + "/attachment?rev=" + rev);
			ReturnObject send = send(attachRequest);

			// FIXME: We don't want Strings...
			res = send.getContentAsBytes();
		}
		if (res == null)
			res = new byte[0];
		return res;
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
			// Write to DB
			if ((request.getOutput() != null && request.getOutput().length > 0)) {
				con.setDoOutput(true);
				con.getOutputStream().write(request.getOutput());
			}

			// Get the Results
			res.responseCode = con.getResponseCode();
			res.setResponseMessage(con.getResponseMessage());
			res.setHeaderFields(con.getHeaderFields());

			if (res.responseCode >= 400 && con.getErrorStream() != null) {
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
						// System.out.println(readLine);
					} while (readLine != null);
					res.error = lines;
				} catch (IOException e) {
					// e.printStackTrace();
				}
			} else {
				if (request.isShouldHandleInput()) {
					InputStream content = (InputStream) con.getContent();
					BufferedInputStream reader = new BufferedInputStream(content);
					byte[] bytes = new byte[Integer.parseInt(res.getHeaderFields().get("Content-Length").get(0))];
					reader.read(bytes);
					res.setContent(bytes);
				}
			}

			con.disconnect();
		} catch (Exception e) {
			res.responseCode = 400;
			e.printStackTrace();
		}
		return res;
	}

	public boolean testConnection() {
		String urlString = "http://" + getHostName() + ":" + port;
		try {
			URL urlObj = new URL(urlString);
			URLConnection openConnection = urlObj.openConnection();
			openConnection.connect();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public boolean testConnection(String databaseName) {
		String urlString = "http://" + hostName + ":" + port + "/" + databaseName + "/";
		try {
			// first check, if host is available..
			URL urlObj = new URL(urlString);
			URLConnection openConnection = urlObj.openConnection();
			openConnection.connect();

			// now check for existence of DataBase
			RequestObject check = createRequestObject();
			check.setRequestType(RequestType.GET);
			check.setPath(databaseName);
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

	public ReturnObject createDB(String dbName) {
		RequestObject create = createRequestObject();
		create.setRequestType(RequestType.PUT);
		create.setPath(dbName);
		return send(create);
	}

	public ReturnObject deleteDatabase(String databaseName) {
		RequestObject delete = createRequestObject();
		delete.setPath(databaseName);
		delete.setRequestType(RequestType.DELETE);
		return send(delete);
	}

	private Authenticator authenticator;

	public CouchDBAdapter withAuthenticator(Authenticator autheticator) {
		this.authenticator = autheticator;
		return this;
	}

	public Authenticator getAutheticator() {
		return authenticator;
	}

	/**
	 * Must be called after setting credentials (and authenticator)
	 * 
	 * You can get the Return from the Server by asking the Authenticator for
	 * the loginRequest
	 * 
	 * @param password
	 *            The Password
	 * @return ThisComponent
	 * @throws Exception
	 *             any Errors for login
	 */
	public CouchDBAdapter login(String password) throws Exception {
		if (this.authenticator == null) {
			this.authenticator = new BasicAuthenticator();
		}
		if (this.authenticator.login(getUserName(), password, this)) {
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

}
