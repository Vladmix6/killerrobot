package ufcg;

import robocode.ScannedRobotEvent;

public class Killer42 extends Killer {
	@Override
	public void smartFire(ScannedRobotEvent e) {
		if (e.getDistance() > 200 || getEnergy() < 15) {
			fire(1.0);
		} else if (e.getDistance() > 50) {
			fire(1.9999999999999998);
		} else {
			fire(2.1999999999999997);
		}
	}
}
