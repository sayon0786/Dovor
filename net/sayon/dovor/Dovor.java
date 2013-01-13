package net.sayon.dovor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dovor {
	private static String hiddenService = System.getenv("HOME") + "/dev/tesths";
	private static final Logger log;
	
	private Server server;
	private BuddyList buddyList;
	private String torchatId;
	private Random random = new Random();
	private Configuration config;

	static {
		log = Logger.getLogger(Dovor.class.getName());
		log.setLevel(Dovor.getLogLevel());
	}

	public static void main(String[] args) {
		new Dovor();
	}
	
	public Dovor() {
		try {
			this.config = new Configuration("config.ini");
		} catch (IOException e) {
			log.severe("Failed to load config " + e.getLocalizedMessage());
			throw new RuntimeException(e);
		}
		loadTorId();
		// yqnkszic6ax54sjx
		this.server = new Server(11010, this);
		server.start();
		
		this.buddyList = new BuddyList(this);
	}

	private void loadTorId() {
		if (hiddenService.length() == 16 && !hiddenService.contains("/") 
				&& !hiddenService.contains("\\")) { // this var likely directly contains the torid
			this.torchatId = hiddenService;
		} else if (hiddenService.length() == 22 && !hiddenService.contains("/") 
				&& !hiddenService.contains("\\")) { // this var likely directly contains the torid with .onion appended
			this.torchatId = hiddenService.substring(0, 16);
		} else {
			File f = new File(hiddenService);
			if (f.isDirectory()) { // we assume this is the folder containing hostname
				f = new File(hiddenService, "hostname");
			}
			if (f.isFile()) { // we assume that this is hostname
				try {
					this.torchatId = new Scanner(f).nextLine().substring(0, 16);
				} catch (FileNotFoundException e) {
					this.torchatId = null;
				}
			}
		}
		
		if (this.torchatId == null) {
			log.severe("Could not acquire torchatId, terminating.");
			throw new RuntimeException("Could not acquire torchatId, terminating.");
		}
	}

	public static Level getLogLevel() {
		return Level.FINEST;
	}

	public BuddyList getBuddyList() {
		return buddyList;
	}
	
	public String getTorchatId() {
		return torchatId;
	}

	public Random getRandom() {
		return random;
	}

	public Configuration getConfig() {
		return config;
	}
}
