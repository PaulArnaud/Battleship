package arnaud.paul;

import java.util.ArrayList;

public class TestIA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int noobresult2 = 0;
		int noobresult3 = 0;
		int medresult1 = 0;
		int medresult3 = 0;
		int pgmresult1 = 0;
		int pgmresult2 = 0;
		Computer computer1 = new Computer();// IA forte
		Computer computer2 = new Computer();// IA nulle
		String shoot;
		
		for (int i = 0; i < 200; i++) {
			computer1.implementboatcomputer("carrier");
			computer1.implementboatcomputer("battleship");
			computer1.implementboatcomputer("cruiser");
			computer1.implementboatcomputer("submarine");
			computer1.implementboatcomputer("destroyer");
			computer2.implementboatcomputer("carrier");
			computer2.implementboatcomputer("battleship");
			computer2.implementboatcomputer("cruiser");
			computer2.implementboatcomputer("submarine");
			computer2.implementboatcomputer("destroyer");
			
			while (!computer1.isDown() && !computer2.isDown()) {
				shoot = computer1.shoot();
				computer1.myShoots.add(shoot);
				int j = 0;
				String res = "A l'eau";
				Ship ship;
				while ((j < (computer2.length())) && (res.equals("A l'eau"))) {
					ship = computer2.getBattlecrew().get(j);
					if (ship.isHit(shoot)) {
						computer1.setState("tir");
						computer1.getCurrentboat().add(shoot);
						ship.lifepoint--;
						if (ship.isDestroyed()) {
							res = "Touché Coulé";
							computer2.removeShip(ship);
							computer1.setState("chasse");
							computer1.setDirstate("top");
							computer1.setCurrentboat(new ArrayList<String>());
						} else {
							res = "Touché";
						}
					}
					j = j + 1;
				}

				if (!computer1.isDown() && !computer2.isDown()) {
					shoot = computer2.shootlvlmin();
					j = 0;
					res = "A l'eau";
					ship = null ;
					while ((j < (computer1.length())) && (res.equals("A l'eau"))) {
						ship = computer1.getBattlecrew().get(j);
						if (computer1.isFind(shoot)) {
							if (!computer2.hasAlreadyShot(shoot)) {
								computer2.myShoots.add(shoot);
								ship.lifepoint--;
							}
							if (ship.isDestroyed()) {
								res = "Touché Coulé";
								computer1.removeShip(ship);
							} else {
								res = "Touché";
							}
						}
						j = j + 1;
					}
				}
			}

			if (computer1.length() == 0) {
				noobresult3 += 1;
			} else {
				pgmresult1 += 1;
			}
			computer1.reset();
			computer2.reset();
		}
		System.out.println("première partie finie");
		for (int i = 0; i < 200; i++) {
			computer1.implementboatcomputer("carrier");
			computer1.implementboatcomputer("battleship");
			computer1.implementboatcomputer("cruiser");
			computer1.implementboatcomputer("submarine");
			computer1.implementboatcomputer("destroyer");
			computer2.implementboatcomputer("carrier");
			computer2.implementboatcomputer("battleship");
			computer2.implementboatcomputer("cruiser");
			computer2.implementboatcomputer("submarine");
			computer2.implementboatcomputer("destroyer");
			
			while (!computer1.isDown() && !computer2.isDown()) {
				shoot = computer1.shoot();
				computer1.myShoots.add(shoot);
				int j = 0;
				String res = "A l'eau";
				Ship ship;
				while ((j < (computer2.length())) && (res.equals("A l'eau"))) {
					ship = computer2.getBattlecrew().get(j);
					if (ship.isHit(shoot)) {
						computer1.setState("tir");
						computer1.getCurrentboat().add(shoot);
						ship.lifepoint--;
						if (ship.isDestroyed()) {
							res = "Touché Coulé";
							computer2.removeShip(ship);
							computer1.setState("chasse");
							computer1.setDirstate("top");
							computer1.setCurrentboat(new ArrayList<String>());
						} else {
							res = "Touché";
						}
					}
					j = j + 1;
				}
				if (!computer1.isDown() && !computer2.isDown()) {
					shoot = computer2.hasard();
					computer2.myShoots.add(shoot);
					j = 0;
					res = "A l'eau";
					ship = null ;
					while ((j < (computer1.length())) && (res.equals("A l'eau"))) {
						ship = computer1.getBattlecrew().get(j);
							if (ship.isHit(shoot)) {
								ship.lifepoint--;
								if (ship.isDestroyed()) {
									res = "Touché Coulé";
									computer1.removeShip(ship);
								} else {
									res = "Touché";
								}
							}
						j = j + 1;
					}
				}
			}

			if (computer1.length() == 0) {
				medresult3 += 1;
			} else {
				pgmresult2 += 1;
			}
			computer1.reset();
			computer2.reset();
		}
		System.out.println("deuxième partie finie");
		for (int i = 0; i < 200; i++) {
			computer1.implementboatcomputer("carrier");
			computer1.implementboatcomputer("battleship");
			computer1.implementboatcomputer("cruiser");
			computer1.implementboatcomputer("submarine");
			computer1.implementboatcomputer("destroyer");
			computer2.implementboatcomputer("carrier");
			computer2.implementboatcomputer("battleship");
			computer2.implementboatcomputer("cruiser");
			computer2.implementboatcomputer("submarine");
			computer2.implementboatcomputer("destroyer");
			
			while (!computer1.isDown() && !computer2.isDown()) {
				shoot = computer1.hasard();
				computer1.myShoots.add(shoot);
				int j = 0;
				String res = "A l'eau";
				Ship ship;
				while ((j < (computer2.length())) && (res.equals("A l'eau"))) {
					ship = computer2.getBattlecrew().get(j);
					if (ship.isHit(shoot)) {
						ship.lifepoint--;
						if (ship.isDestroyed()) {
							res = "Touché Coulé";
							computer2.removeShip(ship);
						} else {
							res = "Touché";
						}
					}
					j = j + 1;
				}
				if (!computer1.isDown() && !computer2.isDown()) {
					shoot = computer2.shootlvlmin();
					j = 0;
					res = "A l'eau";
					ship = null ;
					while ((j < (computer1.length())) && (res.equals("A l'eau"))) {
						ship = computer1.getBattlecrew().get(j);
						if (computer1.isFind(shoot)) {
							if (!computer2.hasAlreadyShot(shoot)) {
								computer2.myShoots.add(shoot);
								ship.lifepoint--;
							}
							if (ship.isDestroyed()) {
								res = "Touché Coulé";
								computer1.removeShip(ship);
							} else {
								res = "Touché";
							}
						}
						j = j + 1;
					}
				}
			}

			if (computer1.length() == 0) {
				noobresult2 += 1;
			} else {
				medresult1 += 1;
			}
			computer1.reset();
			computer2.reset();
		}
		System.out.println(noobresult2 +" " + noobresult3 +" " + medresult1 +" " + medresult3 + " " +pgmresult1 +" " + pgmresult2);
	}
}
