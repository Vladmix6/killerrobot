package ufcg;

import robocode.ScannedRobotEvent;

public class Killer117 extends Killer {
	@Override
	public void smartFire(ScannedRobotEvent e) {
		if (e.getDistance() > 200 || getEnergy() < 15) {
			fire(1.4);
		} else if (e.getDistance() > 50) {
			fire(1.7999999999999998);
		} else {
			fire(1.9999999999999998);
		}
	}
}
