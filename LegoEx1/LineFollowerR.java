package LegoEx1;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;

public class LineFollowerR {

	public LightSensor light_sensor;

	public void move_forward() {
		Motor.B.forward();
		Motor.C.forward();
	}

	public void turn_left() {
		Motor.B.rotate(50);
		Motor.C.rotate(-50);
	}

	public void turn_right() {
		Motor.B.rotate(-50);
		Motor.C.rotate(50);
	}

	public LineFollowerR() {
		Motor.B.setSpeed(150);
		Motor.C.setSpeed(150);
		int sensor_value;
		light_sensor = new LightSensor(SensorPort.S1);
		while (true) {
			sensor_value = light_sensor.readValue();
			// System.out.println(sensor_value);
			if (sensor_value >= 34 && sensor_value < 38) {
				System.out.println("right");
			} else if (sensor_value >= 38 && sensor_value < 47) {
				System.out.println("forward");
			} else if (sensor_value >= 47 && sensor_value < 50) {
				System.out.println("left");
			}
		}
	}

	public static void main(String[] args) {
		LineFollowerR robo = new LineFollowerR();
	}
}
