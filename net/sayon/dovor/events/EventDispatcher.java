package net.sayon.dovor.events;

import net.sayon.dovor.Dovor;
import net.sayon.dovor.listeners.BuddyListener;

public class EventDispatcher implements BuddyListener {
	private Dovor dov;

	public EventDispatcher(Dovor dov) {
		this.dov = dov;
	}

	@Override
	public void onAddMe(AddMeEvent e) {
		for (BuddyListener l : dov.getBuddyListeners()) {
			try {
				l.onAddMe(e);
				if (e.isConsumed())
					break;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void onRemoveMe(RemoveMeEvent e) {
		for (BuddyListener l : dov.getBuddyListeners()) {
			try {
				l.onRemoveMe(e);
				if (e.isConsumed())
					break;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void onMessage(MessageEvent e) {
		for (BuddyListener l : dov.getBuddyListeners()) {
			try {
				l.onMessage(e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void onNotImplemented(NotImplementedEvent e) {
		for (BuddyListener l : dov.getBuddyListeners()) {
			try {
				l.onNotImplemented(e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
