package net.sayon.dovor.events;

public class ConsumableEvent extends Event {
	protected boolean consume = false;

	public void consume() {
		consume = true;
	}

	public boolean isConsumed() {
		return consume;
	}
}
