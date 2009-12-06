package ufcg;

import robocode.ScannedRobotEvent;

public class Killer1 extends Killer {
	@Override
	public void smartFire(ScannedRobotEvent e) {
		if (e.getDistance() > 200 || getEnergy() < 15) {
			fire(1);
		} else if (e.getDistance() > 50) {
			fire(2);
		} else {
			fire(3);
		}
	}
}
