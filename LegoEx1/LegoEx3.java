package LegoEx1;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class LegoEx3 {

	public void displayTacho() {
		int tachoB = 0;
		int tachoC = 0;
		TouchSensor bump = new TouchSensor(SensorPort.S1);
		LCD.drawString("Degrees", 0, 0);
		LCD.drawString("Motor B:", 0, 1);
		LCD.drawString("Motor C:", 0, 2);

		while (Button.ESCAPE.isUp()) {
			if (bump.isPressed()) {
				Motor.B.stop();
				Motor.C.stop();
				Motor.B.resetTachoCount();
				Motor.C.resetTachoCount();
			} else {
				Motor.B.forward();
				Motor.C.forward();
				tachoB = Motor.B.getTachoCount();
				tachoC = Motor.C.getTachoCount();
			}
			LCD.drawInt(tachoB, 5, 9, 1);
			LCD.drawInt(tachoC, 5, 9, 2);
		}
	}

	public static void main(String[] args) {
		LegoEx3 testRobot = new LegoEx3();
		testRobot.displayTacho();
	}
}
