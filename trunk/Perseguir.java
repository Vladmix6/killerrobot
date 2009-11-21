package ufcg;

import static robocode.util.Utils.normalRelativeAngleDegrees;
import robocode.ScannedRobotEvent;

public class Perseguir implements Runnable {

	ScannedRobotEvent alvo = null;
	Killer robo = null;

	public Perseguir(Killer robo, ScannedRobotEvent alvo) {
		this.alvo = alvo;
		this.robo = robo;
	}

	@Override
	public void run() {
		while (alvo.getDistance() > Killer.DISTANCIA_MINIMA_ALVO) {
			double absoluteBearing = robo.getHeading() + alvo.getBearing();
			double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing
					- robo.getGunHeading());
			robo.turnRight(bearingFromGun);
		}
	}

}
