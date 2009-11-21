/*******************************************************************************
 * Copyright (c) 2001, 2008 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/cpl-v10.html
 *
 * Contributors:
 *     Mathew A. Nelson
 *     - Initial implementation
 *     Flemming N. Larsen
 *     - Maintainance
 *******************************************************************************/
package ufcg;

import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;

/**
 * TrackFire - a sample robot by Mathew Nelson, and maintained by Flemming N.
 * Larsen
 * <p/>
 * Sits still. Tracks and fires at the nearest robot it sees
 */
public class Killer extends AdvancedRobot {
	public static double DISTANCIA_MINIMA_ALVO = 30;
	boolean movingForward = true;
	/**
	 * TrackFire's run method
	 */
	public void run() {
		// Set colors
		setBodyColor(Color.pink);
		setGunColor(Color.pink);
		setRadarColor(Color.pink);
		setScanColor(Color.pink);
		setBulletColor(Color.black);
		// Loop forever
		while (true) {
			turnGunRight(360); // Scans automatically
		}
	}

	/**
	 * onScannedRobot: We have a target. Go get it.
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Calculate exact location of the robot
		double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing
				- getGunHeading());
		// If it's close enough, fire!
		if (Math.abs(bearingFromGun) <= 3) {
			turnGunRight(bearingFromGun);
			// We check gun heat here, because calling fire()
			// uses a turn, which could cause us to lose track
			// of the other robot.
			if (getGunHeat() == 0) {
//				fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
			}
		} // otherwise just set the gun to turn.
		// Note: This will have no effect until we call scan()
		else {
			turnGunRight(bearingFromGun);
		}
		// Generates another scan event if we see a robot.
		// We only need to call this if the gun (and therefore radar)
		// are not turning. Otherwise, scan is called automatically.
		if (bearingFromGun == 0) {
			scan();
		}
			turnRight(normalRelativeAngleDegrees(e.getBearing()));
			ahead(20);
	}
	public void perseguir(ScannedRobotEvent e){
		
	}
	
	public void onWin(WinEvent e) {
		// Victory dance
		turnRight(36000);
	}

	public void onHitRobot(HitRobotEvent e) {
		// If we're moving the other robot, reverse!
		if (e.isMyFault()) {
			reverseDirection();
		}
	}

	/**
	 * reverseDirection: Switch from ahead to back & vice versa
	 */
	public void reverseDirection() {
		if (movingForward) {
			movingForward = false;
		} else {
			movingForward = true;
		}
	}

	public boolean devoPerseguir(ScannedRobotEvent e) {
		if (e.getDistance() <= DISTANCIA_MINIMA_ALVO)
			return false;
		return true;
	}

}
