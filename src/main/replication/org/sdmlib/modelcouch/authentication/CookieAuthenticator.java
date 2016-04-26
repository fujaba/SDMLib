package org.sdmlib.modelcouch.authentication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.sdmlib.modelcouch.CouchDBAdapter;
import org.sdmlib.modelcouch.ModelCouch;
import org.sdmlib.modelcouch.RequestObject;
import org.sdmlib.modelcouch.RequestType;
import org.sdmlib.modelcouch.ReturnObject;

/**
 * Tries to get Cookie and sends cookie with every request per CookieHandler
 * 
 * @author alexw
 *
 */
public class CookieAuthenticator implements Authenticator {

	private String username;
	private String password;
	private CouchDBAdapter couch;
	private ReturnObject loginResponse;

	@Override
	public boolean login(String username, String password, CouchDBAdapter couch) {
		this.username = username;
		this.password = password;
		this.couch = couch;
		// CookieHandler will save the Cookies
		if (CookieHandler.getDefault() == null)
			CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
		loginResponse = getCookie(couch);
		return loginResponse.getResponseCode() == 200;
	}

	private ReturnObject getCookie(CouchDBAdapter couch) {
		RequestObject login = couch.createRequestObject();
		login.setPath("_session");
		login.setShouldHandleInput(true);
		login.setRequestType(RequestType.POST);
		login.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		try {
			login.setOutput(("name=" + URLEncoder.encode(username, "UTF-8") + "&password="
					+ URLEncoder.encode(password, "UTF-8")).getBytes());

			return couch.send(login);
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
		}
		return new ReturnObject();
	}

	@Override
	public void authenticate(HttpURLConnection connection) {
	}

}
