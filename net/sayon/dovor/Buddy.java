package net.sayon.dovor;

import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class Buddy extends Thread {
	private static final Logger log;
	
	private String address;
	private String profile_name;
	private String profile_text;
	private String client;
	private String version;
	private Dovor dov;
	private String cookie;

	static {
		log = Logger.getLogger(Buddy.class.getName());
		log.setParent(Logger.getLogger(Dovor.class.getName()));
		log.setLevel(Logger.getLogger(Dovor.class.getName()).getLevel());
	}
	
	public Buddy(Dovor dov, String address) {
		this.dov = dov;
		this.address = address;
		this.cookie = makeCookie();
	}

	public void attatchIncoming(Socket s, Scanner sc, String cookie) {
		
	}
	
	private String makeCookie() { // I was really tempted to name this method bakeCookie :3
		StringBuilder sb = new StringBuilder();
		final String cs = "abcdefghijklmnopqrstuvwxyz1234567890";
		for (int i = 0 ; i < 77 ; i++) {
			sb.append(cs.charAt(dov.getRandom().nextInt(cs.length())));
		}
		return sb.toString();
	}

}
