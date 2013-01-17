package net.sayon.dovor.filetransfer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.sayon.dovor.Buddy;

public class FileReceive {
	private FileTransfer fileTransfer;
	private Buddy buddy;
	private String id;
	private long fileSize;
	private int blockSize;
	private String fileName;
	private boolean cancelled;
	
	private FileOutputStream fos;
	private boolean started;

	public FileReceive(FileTransfer ft, Buddy buddy, String id, long fileSize, int blockSize, String fileName) {
		this.fileTransfer = ft;
		this.buddy = buddy;
		this.id = id;
		this.fileSize = fileSize;
		this.blockSize = blockSize;
		this.fileName = fileName;
	}
	
	public void saveTo(String s) throws FileNotFoundException {
		saveTo(new FileOutputStream(s));
	}
	
	public void saveTo(FileOutputStream fos) {
		this.fos = fos;
	}
	
	public void startTransfer() {
		if (fos == null)
			throw new NullPointerException("fos == null");
		this.started = true;
	}

	public void onData(long start, String hash, byte[] data) throws IOException {
		if (cancelled) // this really shouldn't happen, as if a transfer is cancelled it's id is no longer mapped
			return;
		try {
			fos.getChannel().position(start);
			fos.write(data);
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
			cancel();
		}
	}
	
	public void cancel() throws IOException {
		cancelled = true;
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fileTransfer.sendFileStopReceiving(this);
	}

	public FileTransfer getFileTransfer() {
		return fileTransfer;
	}

	public Buddy getBuddy() {
		return buddy;
	}

	public String getId() {
		return id;
	}

	public long getFileSize() {
		return fileSize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public String getFileName() {
		return fileName;
	}

}
