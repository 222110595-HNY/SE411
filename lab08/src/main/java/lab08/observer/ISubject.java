package lab08.observer;

public interface ISubject {

	/** Registers a new observer */
	void register(IObserver o);

	/** Unregisters an observer */
	void unregister(IObserver o);

	/** Notifies all registered observers of data change */
	void notifyObservers();

}
