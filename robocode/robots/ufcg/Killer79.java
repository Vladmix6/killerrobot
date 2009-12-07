package ufcg;

import robocode.ScannedRobotEvent;

public class Killer79 extends Killer {
	@Override
	public void smartFire(ScannedRobotEvent e) {
		if (e.getDistance() > 200 || getEnergy() < 15) {
			fire(1.2);
		} else if (e.getDistance() > 50) {
			fire(1.5999999999999999);
		} else {
			fire(2.8000000000000003);
		}
	}
}
