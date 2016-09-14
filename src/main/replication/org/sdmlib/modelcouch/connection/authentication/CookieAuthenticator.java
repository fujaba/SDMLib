package org.sdmlib.modelcouch.connection.authentication;

import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import org.sdmlib.modelcouch.connection.ContentType;
import org.sdmlib.modelcouch.connection.HTTPConnectionHandler;
import org.sdmlib.modelcouch.connection.RequestObject;
import org.sdmlib.modelcouch.connection.RequestType;
import org.sdmlib.modelcouch.connection.ReturnObject;

/**
 * Tries to get Cookie and sends cookie with every request per CookieHandler
 * Currently only working with CouchDB...
 * 
 * @author alexw
 *
 */
public class CookieAuthenticator implements Authenticator {

	private String username;
	private String password;
	private HTTPConnectionHandler connectionHandler;
	private ReturnObject loginResponse;
	private CookieManager cookieManager;

	@Override
	public boolean login(String username, String password, HTTPConnectionHandler connectionHandler) {
		this.username = username;
		this.password = password;
		this.connectionHandler = connectionHandler;
		// CookieHandler will save the Cookies
		if (CookieHandler.getDefault() == null) {
			cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
			CookieHandler.setDefault(cookieManager);
		}
		loginResponse = getCookie(connectionHandler);
		return loginResponse.getResponseCode() == 200;
	}

	private ReturnObject getCookie(HTTPConnectionHandler connectionHandler) {
		RequestObject login = connectionHandler.createRequestObject();
		login.setPath("_session");
		login.setShouldHandleInput(true);
		login.setRequestType(RequestType.POST);
		login.setContentType(ContentType.APPLICATION_X_WWW_FORM_URLENCODED);
		try {
			login.setOutput(("name=" + URLEncoder.encode(username, "UTF-8") + "&password="
					+ URLEncoder.encode(password, "UTF-8")).getBytes());

			return connectionHandler.send(login);
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
		}
		return new ReturnObject();
	}

	@Override
	public void authenticate(HttpURLConnection connection) {
	}

	public CookieManager getCookieManager() {
		return cookieManager;
	}

}
