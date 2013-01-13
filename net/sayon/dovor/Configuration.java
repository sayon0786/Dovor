package net.sayon.dovor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	private String configFile;
	private Properties prop;

	public Configuration(InputStream is) throws IOException {
		this.prop = new Properties();
		prop.load(is);
	}

	public Configuration(String string) throws IOException {
		this(new FileInputStream(string));
		this.configFile = string;
	}

	public void getBuddyListLocation() {
		
	}

}
