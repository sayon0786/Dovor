package net.sayon.dovor;

import java.util.HashMap;
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

	public Buddy add(String address) {
		Buddy b = get(address);
		if (b != null) {

			return b;
		}
		return b;
	}

	public Buddy get(String address) {
		return buddies.get(address);
	}

	public HashMap<String, Buddy> getBuddies() {
		return buddies;
	}

	/**
	 * 
	 * @param address
	 * @param create
	 *            this buddy if not exist
	 * @return the buddy or null if for whatever reason we are not accepting new connections or buddies
	 */
	public Buddy getBuddy(String address, boolean create) {
		Buddy b = buddies.get(address);
		if (b != null)
			return b;
		b = new Buddy(dov, address);
		return b;
	}

	public void loadBuddylist() {
		dov.getConfig().getBuddyListLocation();
	}
}
