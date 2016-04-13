package org.sdmlib.modelcouch.authentication;

import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * Uses Base64 and sends credentials with every request that uses authenticate
 * 
 * @author alexw
 *
 */
public class BasicAuthenticator implements Authenticator {

	private String username;
	private String password;

	@Override
	public boolean login(String username, String password, String hostName, int port) {
		this.username = username;
		this.password = password;
		return false;
	}

	@Override
	public void authenticate(HttpURLConnection connection) {
		// Bad idea because of password beeing sent all the time...
		String authenticationData = username + ":" + password;
		String encoding = Base64.getEncoder().encodeToString(authenticationData.getBytes(Charset.forName("utf-8")));
		connection.addRequestProperty("Authorization", "Basic " + encoding);
	}

}
