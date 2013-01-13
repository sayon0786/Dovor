package net.sayon.dovor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server extends Thread {
	private static Logger log;
	private ServerSocket ss;
	private int port;
	private Dovor dov;
	
	static {
		log = Logger.getLogger(Server.class.getName());
		log.setParent(Logger.getLogger(Dovor.class.getName()));
		log.setLevel(Logger.getLogger(Dovor.class.getName()).getLevel());
	}

	public Server(int port, Dovor dov) {
		this.port = port;
		this.dov = dov;
	}
	
	@Override
	public void start() {
		log.info("Starting ServerSocket on port " + port);
		try {
			this.ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			log.severe("Failed to start ServerSocket on port " + port);
			throw new RuntimeException("Failed to start ServerSocket on port " + port);
		}
		log.info("Started ServerSocket on port " + port);
		super.start();
	}
	
	@Override
	public void run() {
		while (ss.isBound()) {
			try {
				Socket s = ss.accept();
				IncomingConnection ic = new IncomingConnection(s, this.dov);
				log.info("New IncomingConnection. icid: " + ic.id());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
