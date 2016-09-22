package LegoEx1;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class LegoEx1 {

	LightSensor ls;

	/*
	 * public void moveForward(int durationMS) { Motor.C.forward();
	 * Motor.B.forward();
	 * 
	 * Delay.msDelay(durationMS); Motor.C.stop(); Motor.B.stop();
	 * 
	 * }
	 */

	public LegoEx1() {
		ls = new LightSensor(SensorPort.S3);
		while (true) {
			int v = ls.getLightValue();
			System.out.println("value " + v);
		}
	}

	public static void main(String[] args) {
		LegoEx1 testRobot = new LegoEx1();
		// testRobot.moveForward(3000);
	}
}
