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

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

/**
 * 
 */
public class Killer extends AdvancedRobot {

	public static double DISTANCIA_MINIMA_ALVO = 30;
	public static int TAMANHO_PASSO = 20;
	int direction = 0;
	ScannedRobotEvent alvo = null;
	int tirosPerdidos = 0;
	int tirosSofridos = 0;
	int tirosNoAlvo = 0;
	boolean sabeOndeAtirar = false;
	double previousEnergy = 100;
	  int movementDirection = 1;
	  int gunDirection = 1;
	  public void run() {
	    setTurnGunRight(99999);
	  }
	  public void onScannedRobot(
	    ScannedRobotEvent e) {
	      // Stay at right angles to the opponent
	      setTurnRight(e.getBearing()+90-30*movementDirection);
	         
	     // If the bot has small energy drop,
	    // assume it fired
	    double changeInEnergy =previousEnergy-e.getEnergy();
	    if (changeInEnergy>0 && changeInEnergy<=3) {
	         // Dodge!
	         movementDirection = -movementDirection;
	         setAhead((e.getDistance()/4+25)*movementDirection);
	     }
	    // When a bot is spotted,
	    // sweep the gun and radar
	    gunDirection = -gunDirection;
	    setTurnGunRight(99999*gunDirection);
	    // Fire directly at target
	    fire ( 2  ) ;
	    
	    // Track the energy level
	    previousEnergy = e.getEnergy();
	  }
}
