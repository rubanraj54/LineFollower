package LegoEx1;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class LegoEx2 {
	LightSensor sensor = new LightSensor(SensorPort.S1);
	
	public void measureLight(){
		while (Button.ESCAPE.isUp()){
			LCD.drawString("Light:" + sensor.getNormalizedLightValue(), 0, 0);
		}
	}
	
	public static void main(String[] args){
		LegoEx2 testRobot = new LegoEx2();
		testRobot.measureLight();
	}
}
