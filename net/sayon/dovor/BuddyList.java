package net.sayon.dovor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

public class BuddyList {
	private static final Logger log;
	private HashMap<String, Buddy> buddies = new HashMap<String, Buddy>();
	private Dovor dov;

	static {
		log = Logger.getLogger(BuddyList.class.getName());
		log.setParent(Logger.getLogger(Dovor.class.getName()));
		log.setLevel(Logger.getLogger(Dovor.class.getName()).getLevel());
	}

	public BuddyList(Dovor dov) {
		this.dov = dov;
	}

	private void add(Buddy b) {
		if (hasBuddy(b.getAddress()))
			return;
		b.setFullBuddy(true);
		buddies.put(b.getAddress(), b);
	}

	public boolean hasBuddy(String address) {
		return buddies.containsKey(address);
	}

	public HashMap<String, Buddy> getBuddies() {
		return buddies;
	}

	/**
	 * 
	 * @param address
	 * @param create
	 *            Whether to create this buddy if it does not exist
	 * @return the buddy or null if for whatever reason we are not accepting new connections or buddies
	 */
	public Buddy getBuddy(String address, boolean create) {
		Buddy b = buddies.get(address);
		if (b != null)
			return b;
		b = new Buddy(dov, address, false);
		add(b);
		return b;
	}

	public void loadBuddylist() throws FileNotFoundException {
		loadBuddylist(dov.getConfig().getBuddyListLocation());
	}

	public void loadBuddylist(String file) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream(dov.getConfig().getBuddyListLocation()));
		while (sc.hasNextLine()) {
			String l = sc.nextLine();
			if (l.trim().length() < 16)
				continue;
			String address = l.substring(0, 16);
			String extra = l.substring(16);
			Buddy b = getBuddy(address, true);
			b.setFullBuddy(true);
			b.setNextReconnection();
			if (extra.length() > 0) {
				if (extra.startsWith("!")) {
					b.setNick(extra.substring(1));
				} else {
					b.setProfileName(extra.substring(1));
				}
			}
		}
	}
}
