package ufcg;

import robocode.ScannedRobotEvent;

public class Killer202 extends Killer {
	@Override
	public void smartFire(ScannedRobotEvent e) {
		if (e.getDistance() > 200 || getEnergy() < 15) {
			fire(2.1999999999999997);
		} else if (e.getDistance() > 50) {
			fire(2.1999999999999997);
		} else {
			fire(2.4);
		}
	}
}
