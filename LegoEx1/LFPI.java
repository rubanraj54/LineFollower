package LineFollower;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.util.Stopwatch;

public class LFPI {
	float Kp = (float) 12, Ki = (float) 0.3, Kd = (float) 1;
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

	LFPI() {

		while (true) {

			newTime = stopwatch.elapsed();

			if ((newTime - oldTime) >= sampleTime) {

				val1 = sensor1.getLightValue();
				val2 = sensor2.getLightValue();

				error = offset - (val2 - val1);

				integral = integral + error;

				PID = Kp * error + Ki * integral;

				powerB = mainTurn + PID;
				powerC = mainTurn - PID;
				forwardB(powerB);
				forwardC(powerC);

				oldTime = newTime;
			}

		}

	}

	public static void main(String[] args) {
		new LFPI();
	}

}
