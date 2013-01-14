package net.sayon.dovor.events;

import net.sayon.dovor.Buddy;

public class RemoveMeEvent extends ConsumableEvent {
	private Buddy buddy;

	public RemoveMeEvent(Buddy buddy) {
		this.buddy = buddy;
	}

	public Buddy getBuddy() {
		return buddy;
	}
}
