package net.sayon.dovor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

public class Configuration {
	private static final String VERSION = "Junthor";
	private static final String CLIENT = "Dovor";
	private String configFile;
	private Properties prop;

	public Configuration() {
		this.prop = new Properties();
	}

	public void load(InputStream is) throws IOException {
		prop.load(is);
	}

	public void load(String string) throws IOException {
		load(new FileInputStream(string)); // TODO create if not exist
		this.configFile = string;
	}

	/**
	 * Writes config to the file config was last loaded from
	 * 
	 * @throws IOException
	 */
	public void save() throws IOException {
		save(configFile);
	}

	/**
	 * Writes config to the file specified in output
	 * 
	 * @param location
	 *            the file to write to
	 * @throws IOException
	 */
	public void save(String location) throws IOException {
		save(new FileOutputStream(location));
	}

	/**
	 * Writes config to output and closes stream
	 * 
	 * Equivalent to save(os, true)
	 * 
	 * @param os
	 * @throws IOException
	 */
	public void save(OutputStream os) throws IOException {
		save(os, true);
	}

	/**
	 * Writes config to output
	 * 
	 * @param os
	 * @param close
	 *            if true the stream is closed after write
	 * @throws IOException
	 */
	public void save(OutputStream os, boolean close) throws IOException {
		prop.store(os, new Date().toString());
		os.flush();
	}

	public String getBuddyListLocation() {
		return (String) prop.get("buddy_list");
	}

	public Properties getProperties() {
		return prop;
	}

	public String getVersion() {
		return VERSION;
	}

	public String getClient() {
		return CLIENT;
	}

	public String getProfileName() {
		return (String) prop.get("profile_name");
	}

	public String getProfileText() {
		return (String) prop.get("profile_text");
	}

	public int getSocksPort() {
		return Integer.parseInt(prop.getProperty("socks_port"));
	}

}
