package net.sayon.dovor;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public class Buddy extends Thread {
	private static final Logger log;
	public static final int OFFLINE = -1;
	public static final int HANDSHAKE = 0;
	public static final int ONLINE = 1;
	public static final int AWAY = 2;
	public static final int XA = 3;

	private String address;
	private String profile_name;
	private String profile_text;
	private String client;
	private String version;
	private Dovor dov;
	private String cookie;
	private Socket incoming;
	private Socket outgoing;
	private OutputStreamWriter outgoingWriter;
	private String cookieToPong;
	private AtomicBoolean connecting = new AtomicBoolean(false);
	private long startConnectingAt;
	private boolean pongSent;

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

	public void connect() {
		if (connecting.get()) {
			log.warning(getAddress() + " connect() called but already connecting!");
			return;
		}
		if (outgoing != null) {
			log.warning(getAddress() + " connect() called but outgoing socket not null!");
			return;
		}
		new Thread() {
			public void run() {
				try {
					startConnectingAt = System.currentTimeMillis();
				} catch (Exception e) {
					e.printStackTrace();
					outgoing = null;
				} finally {
					connecting.set(false);
				}
			}
		}.start();
	}

	public void disconnect() {
		if (incoming != null) {
			try {
				incoming.close();
			} catch (IOException e) {
				// nothing
			}
		}
		if (outgoing != null) {
			try {
				outgoing.close();
			} catch (IOException e) {
				// nothing
			}
		}
		connecting.set(false);
	}

	public void setStatus(int status) {
		// TODO
	}

	public void sendPong(String pong) throws IOException {
		send(String.format("pong %s", pong));
		pongSent = true;
	}

	public void sendVersion() throws IOException {
		send(String.format("version %s", dov.getConfig().getVersion()));
	}

	public void sendProfileText() throws IOException {
		send(String.format("profile_text %s", dov.getConfig().getProfileText()));
	}

	public void sendClient() throws IOException {
		send(String.format("client %s", dov.getConfig().getClient()));
	}

	public void sendProfileName() throws IOException {
		send(String.format("profile_name %s", dov.getConfig().getProfileName()));
	}

	public void sendAddNe() throws IOException {
		send("add_me");
	}

	public void sendStatus() throws IOException {
		send(String.format("status %s", dov.getStatusStringInternal()));
	}

	public void send(String s) throws IOException {
		synchronized (outgoing) {
			try {
				outgoingWriter.write(s + "\n");
				outgoingWriter.flush();
			} catch (IOException e) {
				disconnect();
				throw e;
			}
		}
	}

	public void attatchIncoming(Socket s, Scanner sc, String cookie) {
		this.incoming = s;
		this.cookieToPong = cookie;
	}

	private String makeCookie() { // I was really tempted to name this method bakeCookie :3
		StringBuilder sb = new StringBuilder();
		final String cs = "abcdefghijklmnopqrstuvwxyz1234567890";
		for (int i = 0; i < 77; i++) {
			sb.append(cs.charAt(dov.getRandom().nextInt(cs.length())));
		}
		return sb.toString();
	}

	public String getAddress() {
		return address;
	}

	public String getProfile_name() {
		return profile_name;
	}

	public String getProfile_text() {
		return profile_text;
	}

	public String getClient() {
		return client;
	}

	public String getVersion() {
		return version;
	}

}
