package arnaud.paul;

import java.util.ArrayList;

public class TestIA {

	public static void main(String[] args) {
		int noobresult2 = 0;
		int noobresult3 = 0;
		int medresult1 = 0;
		int medresult3 = 0;
		int pgmresult1 = 0;
		int pgmresult2 = 0;
		Computer computer1 = new Computer();// IA forte
		Computer computer2 = new Computer();// IA nulle
		String shoot;
		
		
		for (int i = 0; i < 300; i++) {
			computer1.implementboat("carrier");
			computer1.implementboat("battleship");
			computer1.implementboat("cruiser");
			computer1.implementboat("submarine");
			computer1.implementboat("destroyer");
			computer2.implementboat("carrier");
			computer2.implementboat("battleship");
			computer2.implementboat("cruiser");
			computer2.implementboat("submarine");
			computer2.implementboat("destroyer");
			
			while (!computer1.isDown() && !computer2.isDown()) {
				Battleship.type = "3";
				
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
					Battleship.type ="1";
					shoot = computer2.shoot();
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
		for (int i = 0; i < 300; i++) {
			computer1.implementboat("carrier");
			computer1.implementboat("battleship");
			computer1.implementboat("cruiser");
			computer1.implementboat("submarine");
			computer1.implementboat("destroyer");
			computer2.implementboat("carrier");
			computer2.implementboat("battleship");
			computer2.implementboat("cruiser");
			computer2.implementboat("submarine");
			computer2.implementboat("destroyer");
			
			while (!computer1.isDown() && !computer2.isDown()) {
				Battleship.type ="3";
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
					Battleship.type ="2";
					shoot = computer2.shoot();
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
		for (int i = 0; i < 300; i++) {
			computer1.implementboat("carrier");
			computer1.implementboat("battleship");
			computer1.implementboat("cruiser");
			computer1.implementboat("submarine");
			computer1.implementboat("destroyer");
			computer2.implementboat("carrier");
			computer2.implementboat("battleship");
			computer2.implementboat("cruiser");
			computer2.implementboat("submarine");
			computer2.implementboat("destroyer");
			
			while (!computer1.isDown() && !computer2.isDown()) {
				Battleship.type ="2";
				shoot = computer1.shoot();
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
					Battleship.type ="1";
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
		System.out.println("troisième partie finie");
		System.out.println(noobresult2 +" " + noobresult3 +" " + medresult1 +" " + medresult3 + " " +pgmresult1 +" " + pgmresult2);
	}
}
