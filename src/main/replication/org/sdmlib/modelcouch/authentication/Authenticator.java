package org.sdmlib.modelcouch.authentication;

import java.net.HttpURLConnection;

import org.sdmlib.modelcouch.ModelCouch;

public interface Authenticator {
	/**
	 * Mainly to Store Username and Password, but also to get cookie or so
	 * 
	 * @param username The User
	 * @param password The Password
	 * @param couch The Database
	 * @return result success if login
	 */
	public boolean login(String username, String password, ModelCouch couch);

	/**
	 * Called in every request
	 * 
	 * @param connection The Conncetion
	 */
	public void authenticate(HttpURLConnection connection);
}
