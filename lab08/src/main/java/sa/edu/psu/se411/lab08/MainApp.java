package sa.edu.psu.se411.lab08;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import component.SensorLogger;
import component.Sensors;
import component.dashboard;

public class MainApp {

	static Logger logger = LoggerFactory.getLogger(MainApp.class);

	public static void main(String[] args) {
		logger.info("Application is starting...");

		Sensors sensor1 = new Sensors("sensor 1");
		Sensors sensor2 = new Sensors("sensor 2");

		dashboard dashboard = new dashboard();
		SensorLogger sensorLogger = new SensorLogger();

		sensor1.register(dashboard);
		sensor2.register(dashboard);
		sensor1.register(sensorLogger);
		sensor2.register(sensorLogger);

		Random random = new Random();

		for (int i = 0; i < 10; i++) {
            sensor1.setData(20 + random.nextDouble() * 15);
            sensor2.setData(40 + random.nextDouble() * 20);


            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }

		try {
			Sensors sensor3 = sensor1.clone();
			dashboard dashboard2 = new dashboard();
			sensor3.register(dashboard2);
			System.out.println("Original observers: " + sensor1.getObservers().size()
					+ ", clone observers: " + sensor3.getObservers().size());
			sensor3.setData(20 + random.nextDouble() * 15);
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage());
		}

		logger.info("Application is closing...");
	}

}
