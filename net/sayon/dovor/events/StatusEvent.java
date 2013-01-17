package net.sayon.dovor.events;

import net.sayon.dovor.Buddy;

public class StatusEvent extends Event {
	private int newStatus;
	private int oldStatus;

	public StatusEvent(Buddy buddy, int newStatus, int oldStatus) {
		this.buddy = buddy;
		this.newStatus = newStatus;
		this.oldStatus = oldStatus;
	}

	public int getNewStatus() {
		return newStatus;
	}

	public int getOldStatus() {
		return oldStatus;
	}
}
