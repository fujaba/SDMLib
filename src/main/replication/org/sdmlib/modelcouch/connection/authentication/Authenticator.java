package org.sdmlib.modelcouch.connection.authentication;

import java.net.HttpURLConnection;

import org.sdmlib.modelcouch.connection.HTTPConnectionHandler;

public interface Authenticator {
	/**
	 * Mainly to Store Username and Password, but also to get cookie or so
	 * 
	 * @param username The User
	 * @param password The Password
	 * @param connectionHandler The HTTPConnectionHandler that sends the requests
	 * @return result success if login
	 */
	public boolean login(String username, String password, HTTPConnectionHandler connectionHandler);

	/**
	 * Called in every request
	 * 
	 * @param connection The Conncetion
	 */
	public void authenticate(HttpURLConnection connection);
}
