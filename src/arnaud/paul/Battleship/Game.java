package arnaud.paul.Battleship;

public class Game {
	// notion de joueur actif et opposï¿½ ( seulement pour le mode 2joueur) sinon le
	// joueur est le joueur actif de faï¿½on permanente contre l'ordi
	public Player ActivePlayer;
	public Player OppositePlayer;

	public Game(Player player1, Player player2) {
		this.ActivePlayer = player1;
		this.OppositePlayer = player2;
	}

	public Player getActivePlayer() {
		return ActivePlayer;
	}

	public void setActivePlayer(Player activePlayer) {
		ActivePlayer = activePlayer;
	}

	public Player getOppositePlayer() {
		return OppositePlayer;
	}

	public void setOppositePlayer(Player oppositePlayer) {
		OppositePlayer = oppositePlayer;
	}

	public String ActivetoString() {
		return "[ActivePlayer=" + ActivePlayer + "]";
	}

	public String OppositetoString() {
		return "[OppositePlayer=" + OppositePlayer + "]";
	}

	public void changePlayer() {
		Player TempPlayer;
		TempPlayer = this.ActivePlayer;
		this.ActivePlayer = this.OppositePlayer;
		this.OppositePlayer = TempPlayer;
	}

	// fonction vraie ou fausse si la partie est terminï¿½e ou non
	public boolean IsOver() {
		return ActivePlayer.isDown() || OppositePlayer.isDown();
	}

	public Player adversary(Player name) {
		if (this.ActivePlayer == name) {
			return this.OppositePlayer;
		} else {
			return this.ActivePlayer;
		}
	}

	public Player party() {
		while (!IsOver()) {
			if (!Battleship.modeIA) {
				System.out.println("C'est à " + ActivePlayer.getPlayername() + " de jouer : ");
				System.out.println("Voici la carte de vos tirs");
				Battleship.showshoot(ActivePlayer, OppositePlayer);
				System.out.println("Voici la carte des tirs de votre adversaire");
				Battleship.showshoot(OppositePlayer, ActivePlayer);
			}		
			String shoot = ActivePlayer.shoot();
			int i = 0;
			String res = "A l'eau";
			while ((i < (OppositePlayer.length())) && (res.equals("A l'eau"))) {
				if (ActivePlayer.hasAlreadyShot(shoot)) {
					if (ActivePlayer.isFind(shoot)) {
						res = "Touché";
					}
					i = OppositePlayer.length();
				} else {
					Ship ship = OppositePlayer.getBattlecrew().get(i);
					if (ship.isHit(shoot)) {
						ship.lifepoint--;
						ActivePlayer.updateshoot(shoot);
						if (ship.isDestroyed()) {
							res = "Touché Coulé";
							OppositePlayer.removeShip(ship);
							ActivePlayer.doingthings();
						} else {
							res = "Touché";
						}
					}
					i = i + 1;
				}
			}
			if (!ActivePlayer.hasAlreadyShot(shoot)) {
				ActivePlayer.myShoots.add(shoot);
			}
			if (!Battleship.modeIA) {
				System.out.println("C'est : " + res);
				System.out.println("Fin du tour de " + ActivePlayer.getPlayername());
			}
			changePlayer();
		}
		// on regarde qui a gagné et qui a perdu
		if (ActivePlayer.length() == 0) {
			return OppositePlayer;
		} else {
			return ActivePlayer;
		}
	}
}