package arnaud.paul;

public class Game {
	public Player activeplayer;
	public Player oppositeplayer;

	public Game(Player player1, Player player2) {
		this.activeplayer = player1;
		this.oppositeplayer = player2;
	}

	public Player getActivePlayer() {
		return activeplayer;
	}

	public void setActivePlayer(Player activePlayer) {
		activeplayer = activePlayer;
	}

	public Player getOppositePlayer() {
		return oppositeplayer;
	}

	public void setOppositePlayer(Player oppositePlayer) {
		oppositeplayer = oppositePlayer;
	}

	public String ActivetoString() {
		return "[ActivePlayer=" + activeplayer + "]";
	}

	public String OppositetoString() {
		return "[OppositePlayer=" + oppositeplayer + "]";
	}

	public void changePlayer() {
		Player TempPlayer;
		TempPlayer = this.activeplayer;
		this.activeplayer = this.oppositeplayer;
		this.oppositeplayer = TempPlayer;
	}

	// fonction vraie ou fausse si la partie est terminée ou non
	public boolean IsOver() {
		return activeplayer.isDown() || oppositeplayer.isDown();
	}

	public Player adversary(Player name) {
		if (this.activeplayer == name) {
			return this.oppositeplayer;
		} else {
			return this.activeplayer;
		}
	}

	public Player party() {
		while (!IsOver()) {
			if (!Config.modeIA) {
				System.out.println("C'est à " + activeplayer.getPlayername() + " de jouer : ");
				System.out.println("Voici la carte de vos tirs");
				Config.showshoot(activeplayer, oppositeplayer);
				System.out.println("Voici la carte des tirs de votre adversaire");
				Config.showboat(oppositeplayer, activeplayer);
			}
			String shoot = activeplayer.shoot();
			int i = 0;
			String res = "A l'eau";
			while ((i < (oppositeplayer.length())) && (res.equals("A l'eau"))) {
				if (activeplayer.hasAlreadyShot(shoot)) {
					if (activeplayer.isInCrew(shoot)) {
						res = "Touché";
					}
					i = oppositeplayer.length();
				} else {
					Ship ship = oppositeplayer.getBattlecrew().get(i);
					if (ship.isHit(shoot)) {
						ship.lifepoint--;
						activeplayer.updateshoot(shoot);
						if (ship.isDestroyed()) {
							res = "Touché Coulé";
							oppositeplayer.removeShip(ship);
							activeplayer.doingthings();
						} else {
							res = "Touché";
						}
					}
					i = i + 1;
				}
			}
			if (!activeplayer.hasAlreadyShot(shoot)) {
				activeplayer.myshoot.add(shoot);
			}
			if (!Config.modeIA) {
				System.out.println("C'est : " + res);
				System.out.println("Fin du tour de " + activeplayer.getPlayername());
			}
			changePlayer();
		}
		// on regarde qui a gagné et qui a perdu
		if (activeplayer.length() == 0) {
			return oppositeplayer;
		} else {
			return activeplayer;
		}
	}
}