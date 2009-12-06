package ufcg;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

/**
 * 
 */
public class Killer extends AdvancedRobot {

	ScannedRobotEvent alvoAnterior = null;
	public double previousEnergy = 100;
	public int direcao = 1;
	public int gunDirection = 1;

	public void run() {
		setTurnGunRight(99999);
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		// Fire directly at target
		if (alvoAnterior == null)
			alvoAnterior = e;
		// Manter 90 graus em relação ao alvo
		manterAnguloReto(e);
		// Dano por bala sempre é maior que 3
		esquivar(e);
		// When a bot is spotted,
		// sweep the gun and radar
		gunDirection = -gunDirection;
		setTurnGunRight(99999 * gunDirection);
		// Track the energy level
		smartFire(e);
		alvoAnterior = e;
		previousEnergy = e.getEnergy();
	}

	public void smartFire(ScannedRobotEvent e) {
		if (e.getDistance() > 200 || getEnergy() < 15) {
			fire(1);
		} else if (e.getDistance() > 50) {
			fire(2);
		} else {
			fire(3);
		}
	}

	private void manterAnguloReto(ScannedRobotEvent e) {
		setTurnRight(e.getBearing() + 90 - 30 * direcao);
	}

	private boolean devoEsquivar(ScannedRobotEvent e) {
		double changeInEnergy = previousEnergy - e.getEnergy();
		return changeInEnergy > 0 && changeInEnergy <= 3;
	}

	private void esquivar(ScannedRobotEvent e) {
		if (devoEsquivar(e)) {
			direcao = -direcao;
			setAhead((e.getDistance() / 4 + 25) * direcao);
		}

	}

}
