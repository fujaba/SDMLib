package org.sdmlib.modelcouch.authentication;

import java.net.HttpURLConnection;

import org.sdmlib.modelcouch.ModelCouch;

public interface Authenticator {
	/**
	 * Mainly to Store Username and Password, but also to get cookie or so
	 * 
	 * @param username
	 * @param password
	 * @param hostName
	 * @param port
	 * @return result
	 */
	public boolean login(String username, String password, ModelCouch couch);

	/**
	 * Called in every request
	 * 
	 * @param connection
	 */
	public void authenticate(HttpURLConnection connection);
}
