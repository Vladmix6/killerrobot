package ufcg;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

/**
 * 
 */
public class Killer extends AdvancedRobot {

	private static int PERTO = 50;
	private static int LONGE = 200;
	ScannedRobotEvent alvoAnterior = null;
	int tirosPerdidos = 0;
	int tirosSofridos = 0;
	int tirosNoAlvo = 0;
	boolean sabeOndeAtirar = false;
	double previousEnergy = 100;
	int direcao = 1;
	int gunDirection = 1;

	public void run() {
		setTurnGunRight(99999);
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		// Fire directly at target
		turnGunRight(e.getBearing()+getHeading());
		smartFire(e);
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
		alvoAnterior = e;
		previousEnergy = e.getEnergy();
	}

	public void smartFire(ScannedRobotEvent e) {
		if (e.getDistance() > LONGE || getEnergy() < 15) {
			fire(1);
		} else if (e.getDistance() > PERTO) {
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
