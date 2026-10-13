package component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lab08.observer.IObserver;
import lab08.observer.ISubject;

public class SensorLogger implements IObserver {

	static Logger logger = LoggerFactory.getLogger(SensorLogger.class);

	@Override
	public void update(ISubject subject) {
		if(subject instanceof Sensors) {
			Sensors sensor = (Sensors) subject;
			System.out.printf("Logger: %s reading changed to %.2f%n", sensor.getId(), sensor.getData());
			logger.info(sensor.getId() + " reading changed to " + sensor.getData());
		}
	}

}
