package org.sdmlib.modelcouch.connection.authentication;

import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Base64;

import org.sdmlib.modelcouch.connection.HTTPConnectionHandler;

/**
 * Uses Base64 and sends credentials with every request that uses authenticate
 * 
 * @author alexw
 *
 */
public class BasicAuthenticator implements Authenticator {

	private String encoding;

	@Override
	public boolean login(String username, String password, HTTPConnectionHandler connectionHandler) {
		String authenticationData = username + ":" + password.toString();
		encoding = Base64.getEncoder().encodeToString(authenticationData.getBytes(Charset.forName("utf-8")));
		return false;
	}

	@Override
	public void authenticate(HttpURLConnection connection) {
		// Bad idea because of password being sent all the time...
		connection.addRequestProperty("Authorization", "Basic " + encoding);
	}

}
