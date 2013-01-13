package net.sayon.dovor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class IncomingConnection extends Thread {
	private static final Logger log;
	private static final AtomicInteger counter = new AtomicInteger();
	private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	
	private Socket s;
	private Dovor dov;
	private int id;
	private Scanner sc;
	
	static {
		log = Logger.getLogger(IncomingConnection.class.getName());
		log.setParent(Logger.getLogger(Dovor.class.getName()));
		log.setLevel(Logger.getLogger(Dovor.class.getName()).getLevel());
	}

	public IncomingConnection(Socket s, Dovor dov) {
		this.id = counter.getAndIncrement();
		this.s = s;
		this.dov = dov;
		
		try {
			this.sc = new Scanner(new InputStreamReader(s.getInputStream(), "UTF8"));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				s.close();
			} catch (IOException e1) {
				// nothing
			}
		}
	}

	public void run() {
		ScheduledFuture<?> sf = executor.schedule(new Timeout(), 30, TimeUnit.SECONDS);
		
		while (sc.hasNextLine() && !isInterrupted()) {
			String l = sc.nextLine();
			String[] spl = l.split(" ");
			
			if (spl[0].equalsIgnoreCase("ping")) {
				String address = spl[1];
				String cookie = spl[2];
				
				Buddy b = dov.getBuddyList().getBuddy(address, true);
				sf.cancel(true);
				b.attatchIncoming(this.s, this.sc, cookie);
			} else {
				try {
					s.close();
				} catch (IOException e) {
					// nothing
				}
				log.warning("Received " + l + " instead of a ping.");
			}
		}
	}
	
	private class Timeout implements Runnable {
		public void run() {
			sc.close();
			try {
				s.close();
			} catch (IOException e) {
				// nothing
			}
			IncomingConnection.this.interrupt();
		}
	}

	public int id() {
		return id;
	}
}
