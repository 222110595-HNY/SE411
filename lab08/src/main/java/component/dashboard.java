package component;

import lab08.observer.IObserver;
import lab08.observer.ISubject;

public class dashboard implements IObserver {

	private double sensor_data_1=0.0;
	private double sensor_data_2=0.0;


	@Override
	public void update(ISubject subject) {
		if(subject instanceof Sensors) {
			Sensors sensor = (Sensors) subject;
			if(sensor.getId().equals("sensor 1")) {
				sensor_data_1=sensor.getData();
			}
			else if(sensor.getId().equals("sensor 2")) {
				sensor_data_2=sensor.getData();
			}
			System.out.printf("Dashboard: %s = %.2f | sensor 1: %.2f, sensor 2: %.2f%n",
					sensor.getId(), sensor.getData(), sensor_data_1, sensor_data_2);
		}

	}


}
