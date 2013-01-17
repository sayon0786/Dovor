package net.sayon.dovor.filetransfer;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;

import javax.xml.bind.DatatypeConverter;

import net.sayon.dovor.Buddy;
import net.sayon.dovor.events.Event;
import net.sayon.dovor.events.MessageEvent;
import net.sayon.dovor.events.ReverseEvent;
import net.sayon.dovor.listeners.BuddyListener;

public class FileTransfer implements BuddyListener {
	private HashMap<String, FileReceive> receiving = new HashMap<String, FileReceive>();
	private HashMap<String, FileSend> sending = new HashMap<String, FileSend>();

	@Override
	public void onEvent(Event e) throws IOException {
		if (e instanceof MessageEvent) {
			MessageEvent me = (MessageEvent) e;
			if (me.getMessage().equalsIgnoreCase("dc"))
				me.getBuddy().disconnect();
			if (me.getMessage().equalsIgnoreCase("rc")) {
				me.getBuddy().disconnect();
				me.getBuddy().connect();
			}
		}
		if (e instanceof ReverseEvent) {
			ReverseEvent re = (ReverseEvent) e;
			System.out.println(re.getBuddy().getAddress() + " | " + re.getText() + " | " + re.getText().equals("filename"));
			if (re.getText().equals("filename")) {
				String s = null;
				s = re.readStringTillChar('\n');
				String[] spl = s.split(" ", 4);
				String id = spl[0];
				long fileSize = Long.parseLong(spl[1]);
				int blockSize = Integer.parseInt(spl[2]);
				String fileName = new File(spl[3]).getName(); // don't trust the filename to not include path manipulation
				FileReceive fr = new FileReceive(this, e.getBuddy(), id, fileSize, blockSize, fileName);
				receiving.put(e.getBuddy().getAddress() + "-" + id, fr);
	
				System.out.println("nl: " + s);
			} else if (re.getText().equals("filedata")) {
				String id = re.readStringTillChar(' ');
				long start = Long.parseLong(re.readStringTillChar(' '));
				String hash = re.readStringTillChar(' ');
				byte[] data = re.readUnescapedBytesTillChar('\n');
				FileReceive fr = getReceiving(re.getBuddy().getAddress() + "-" + id);
				if (fr != null) {
					fr.onData(start, hash, data);
				}
			}
		}
	}

	public void sendFileStopSending(FileReceive fr) throws IOException {
		sendFileStopSending(fr.getBuddy(), fr.getId());
	}

	public void sendFileStopSending(Buddy b, String id) throws IOException {
		b.send(String.format("file_stop_sending %s", id));
		receiving.remove(b.getAddress() + "-" + id);
	}

	public void sendFileStopReceiving(FileReceive fr) throws IOException {
		sendFileStopReceiving(fr.getBuddy(), fr.getId());
	}

	public void sendFileStopReceiving(Buddy b, String id) throws IOException {
		b.send(String.format("file_stop_receiving %s", id));
		sending.remove(b.getAddress() + "-" + id);
	}

	public FileReceive getReceiving(String id) {
		return receiving.get(id);
	}

	public Collection<FileReceive> getReceiving() {
		return receiving.values();
	}

	public FileSend getSending(String id) {
		return sending.get(id);
	}

	public Collection<FileSend> getSending() {
		return sending.values();
	}
	
	public static String getDigestFor(byte[] bytes) {
		try {
			return DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(bytes));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
