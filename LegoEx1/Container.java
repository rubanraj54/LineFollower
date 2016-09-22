package LegoEx1;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.util.Stopwatch;

public class Container {

	float Kp = (float) 13, Ki = (float) 0.3, Kd = (float) 0.5;
	float dafaultMotorPower = 180, PID = 0, P = 0, powerB, powerC, integral = 0, derivative = 0;

	int offset = 1;
	int error = 0, lastError = 0;
	int sampleTime = 10, oldTime = 0, newTime;

	LightSensor sensor1 = new LightSensor(SensorPort.S1);
	LightSensor sensor2 = new LightSensor(SensorPort.S4);

	Stopwatch stopwatch = new Stopwatch();

	public void forwardMotorB(float power) {

		if (power >= 0) {
			if (power > 600) {
				power = 600;
			}
			Motor.B.setSpeed(power);
			Motor.B.forward();
		} else {
			if (power < -600) {
				power = -600;
			}
			Motor.B.setSpeed(-power);
			Motor.B.backward();
		}

	}

	public void forwardMotorC(float power) {

		if (power >= 0) {
			if (power > 600) {
				power = 600;
			}
			Motor.C.setSpeed(power);
			Motor.C.forward();
		} else {
			if (power < -600) {
				power = -600;
			}
			Motor.C.setSpeed(-power);
			Motor.C.backward();
		}
	}

	public void calculatePID() {
		error = offset - (sensor1.getLightValue() - sensor2.getLightValue());
		if (error == 0) {
			integral = 0;
			lastError = 0;
		}
		integral = integral + error;
		derivative = error - lastError;
		PID = Kp * error + Ki * integral + Kd * derivative;
		powerB = dafaultMotorPower - PID;
		powerC = dafaultMotorPower + PID;
		forwardMotorB(powerB);
		forwardMotorC(powerC);
		lastError = error;
	}

	// Constructor goes here
	Container() {
		while (true) {
			newTime = stopwatch.elapsed();
			if ((newTime - oldTime) >= sampleTime) {
				calculatePID();
				oldTime = newTime;
			}
		}

	}
}
