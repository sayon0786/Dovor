package net.sayon.dovor.events;

import net.sayon.dovor.Buddy;

public class RemoveMeEvent extends ConsumableEvent {

	public RemoveMeEvent(Buddy buddy) {
		this.buddy = buddy;
	}
}
