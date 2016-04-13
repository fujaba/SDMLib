package org.sdmlib.modelcouch.authentication;

import java.net.HttpURLConnection;

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
	public boolean login(String username, String password, String hostName, int port);

	/**
	 * Called in every request
	 * 
	 * @param connection
	 * @return void
	 */
	public void authenticate(HttpURLConnection connection);
}
