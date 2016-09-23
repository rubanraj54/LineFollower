package LineFollower;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.util.Stopwatch;

public class LFPD {
	float Kp = (float) 10, Ki = (float) 0.05, Kd = (float) 0.7;
	int offset = 1;
	float mainTurn = 170, PID = 0, P = 0, powerB, powerC, integral = 0, derivative = 0;
	int error = 0, lastError = 0;
	int val1 = 0, val2 = 0;
	int sampleTime = 10, oldTime = 0, newTime;
	LightSensor sensor1 = new LightSensor(SensorPort.S1);
	LightSensor sensor2 = new LightSensor(SensorPort.S4);
	Stopwatch stopwatch = new Stopwatch();

	public void forwardB(float power) {

		if (power >= 0) {
			if (power > 600) {
				power = 600;
			}

			Motor.B.setSpeed(power);
			Motor.B.forward();
		}

		else {
			if (power < -600) {
				power = -600;
			}
			Motor.B.setSpeed(-power);
			Motor.B.backward();
		}

	}

	public void forwardC(float power) {

		if (power >= 0) {
			if (power > 600) {
				power = 600;
			}
			Motor.C.setSpeed(power);
			Motor.C.forward();
		}

		else {
			if (power < -600) {
				power = -600;
			}
			Motor.C.setSpeed(-power);
			Motor.C.backward();
		}

	}

	LFPD() {

		while (true) {

			newTime = stopwatch.elapsed();
			System.out.println(val1 + " " + val2);

			// System.out.println(newTime - oldTime);

			if ((newTime - oldTime) >= sampleTime) {

				val1 = sensor1.getLightValue();
				val2 = sensor2.getLightValue();

				error = offset - (val2 - val1);

				derivative = error - lastError;
				PID = Kp * error + Kd * derivative;

				powerB = mainTurn + PID;
				powerC = mainTurn - PID;
				forwardB(powerB);
				forwardC(powerC);
				lastError = error;
				oldTime = newTime;
			}

		}

	}

	public static void main(String[] args) {
		new LFPID();
	}

}
