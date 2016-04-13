package org.sdmlib.modelcouch.authentication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Tries to get Cookie and sends cookie with every request per CookieHandler
 * 
 * @author alexw
 *
 */
public class CookieAuthenticator implements Authenticator {

	private String username;
	private String password;

	@Override
	public boolean login(String username, String password, String hostName, int port) {
		this.username = username;
		this.password = password;
		// CookieHandler will save the Cookies
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
		int responsecode = getCookie(hostName, port);
		return responsecode == 200;
	}

	private int getCookie(String hostName, int port) {
		String url = "http://" + hostName + ":" + port + "/" + "_session";
		URL obj;
		int responsecode = -1;
		try {
			obj = new URL(url);

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// try to delete database
			con.setRequestMethod("POST");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			DataOutputStream output = new DataOutputStream(con.getOutputStream());

			output.writeBytes("name=" + URLEncoder.encode(username, "UTF-8") + "&password="
					+ URLEncoder.encode(password, "UTF-8"));

			output.close();

			responsecode = con.getResponseCode();
//			if (responsecode >= 400) {
//				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//				System.err.println("Error: " + responsecode);
//				while (reader.ready()) {
//					System.err.println(reader.readLine());
//				}
//				System.err.println();
//			}
			con.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responsecode;
	}

	@Override
	public void authenticate(HttpURLConnection connection) {
	}

}
