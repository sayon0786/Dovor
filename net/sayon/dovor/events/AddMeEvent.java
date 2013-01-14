package net.sayon.dovor.events;

import net.sayon.dovor.Buddy;

public class AddMeEvent extends ConsumableEvent {
	private Buddy buddy;

	public AddMeEvent(Buddy buddy) {
		this.buddy = buddy;
	}

	public Buddy getBuddy() {
		return buddy;
	}
}
