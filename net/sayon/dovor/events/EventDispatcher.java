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

	@Override
	public void onClient(ClientEvent e) {
		for (BuddyListener l : dov.getBuddyListeners()) {
			try {
				l.onClient(e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void onVersion(VersionEvent e) {
		for (BuddyListener l : dov.getBuddyListeners()) {
			try {
				l.onVersion(e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void onProfileName(ProfileNameEvent e) {
		for (BuddyListener l : dov.getBuddyListeners()) {
			try {
				l.onProfileName(e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void onProfileText(ProfileTextEvent e) {
		for (BuddyListener l : dov.getBuddyListeners()) {
			try {
				l.onProfileText(e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void onCommand(TextEvent e) {
		for (BuddyListener l : dov.getBuddyListeners()) {
			try {
				l.onCommand(e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void onStatus(StatusEvent e) {
		for (BuddyListener l : dov.getBuddyListeners()) {
			try {
				l.onStatus(e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void onFullyConnected(FullyConnectedEvent e) {
		for (BuddyListener l : dov.getBuddyListeners()) {
			try {
				l.onFullyConnected(e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void onReverseEvent(ReverseEvent e) {
		for (BuddyListener l : dov.getBuddyListeners()) {
			try {
				l.onReverseEvent(e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
