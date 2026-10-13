package component;

import java.util.ArrayList;
import java.util.List;

import lab08.observer.IObserver;
import lab08.observer.ISubject;

public class Sensors implements ISubject, Cloneable {
	private String id;
	private double data = 0.0;
	private List<IObserver> observers = new ArrayList<>();

	public Sensors(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public double getData() {
		return data;
	}
	public void setData(double data) {
		this.data = data;
		notifyObservers();
	}
	public List<IObserver> getObservers() {
		return observers;
	}
	public void setObservers(List<IObserver> observers) {
		this.observers = observers;
	}

	@Override
	public void register(IObserver o)
	{
		observers.add(o);

	}

	@Override
	public void unregister(IObserver o){
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for(IObserver o : observers) {
			o.update(this);
		}
	}

	@Override
	public Sensors clone() throws CloneNotSupportedException {
		Sensors cloned = (Sensors) super.clone();
		cloned.observers = new ArrayList<>();
		return cloned;
	}

}
