package fr.battleship;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import arnaud.paul.Battleship.Battleship;
import arnaud.paul.Battleship.Computer;
import arnaud.paul.Battleship.Game;
import arnaud.paul.Battleship.Player;

public class TestIA {

	public static void main(String[] args) {
		int noobresult2 = 0;
		int noobresult3 = 0;
		int medresult1 = 0;
		int medresult3 = 0;
		int pgmresult1 = 0;
		int pgmresult2 = 0;
		Battleship.modeIA = true;

		Computer computerlvl1 = new Computer(1);
		Computer computerlvl2 = new Computer(2);
		Computer computerlvl3 = new Computer(3);

		Game newgame = new Game(computerlvl1, computerlvl2);
		Player first = newgame.ActivePlayer;
		for (int j = 0; j < 100; j++) {
			if (j != 0) {
				newgame = new Game(newgame.adversary(first), first);
				first = newgame.ActivePlayer;
			}
			// System.out.println("Mise en place des bateaux de " +
			// newgame.ActivePlayer.getPlayername());
			for (int i = 0; i < Battleship.tab.length; i++) {
				newgame.ActivePlayer.implementboat(Battleship.tab[i]);
			}
			// System.out.println("Mise en place des bateaux de " +
			// newgame.OppositePlayer.getPlayername());
			for (int i = 0; i < Battleship.tab.length; i++) {
				newgame.OppositePlayer.implementboat(Battleship.tab[i]);
			}
			// System.out.println("Début de la partie !!!!!!!!!");
			Player winner = newgame.party();
			winner.score++;
			// System.out.println(winner.getPlayername() + " a gagné");

			newgame.ActivePlayer.reset();
			newgame.OppositePlayer.reset();

		}
		noobresult2 = computerlvl1.score;
		medresult1 = computerlvl2.score;

		computerlvl1.score = 0;
		computerlvl2.score = 0;

		newgame = new Game(computerlvl1, computerlvl3);
		first = newgame.ActivePlayer;
		for (int j = 0; j < 100; j++) {
			if (j != 0) {
				newgame = new Game(newgame.adversary(first), first);
				first = newgame.ActivePlayer;
			}
			// System.out.println("Mise en place des bateaux de " +
			// newgame.ActivePlayer.getPlayername());
			for (int i = 0; i < Battleship.tab.length; i++) {
				newgame.ActivePlayer.implementboat(Battleship.tab[i]);
			}
			// System.out.println("Mise en place des bateaux de " +
			// newgame.OppositePlayer.getPlayername());
			for (int i = 0; i < Battleship.tab.length; i++) {
				newgame.OppositePlayer.implementboat(Battleship.tab[i]);
			}
			// System.out.println("Début de la partie !!!!!!!!!");
			Player winner = newgame.party();
			winner.score++;
			// System.out.println(winner.getPlayername() + " a gagné");

			newgame.ActivePlayer.reset();
			newgame.OppositePlayer.reset();

		}
		noobresult3 = computerlvl1.score;
		pgmresult1 = computerlvl3.score;

		computerlvl1.score = 0;
		computerlvl3.score = 0;

		newgame = new Game(computerlvl3, computerlvl2);
		first = newgame.ActivePlayer;
		for (int j = 0; j < 100; j++) {
			if (j != 0) {
				newgame = new Game(newgame.adversary(first), first);
				first = newgame.ActivePlayer;
			}
			// System.out.println("Mise en place des bateaux de " +
			// newgame.ActivePlayer.getPlayername());
			for (int i = 0; i < Battleship.tab.length; i++) {
				newgame.ActivePlayer.implementboat(Battleship.tab[i]);
			}
			// System.out.println("Mise en place des bateaux de " +
			// newgame.OppositePlayer.getPlayername());
			for (int i = 0; i < Battleship.tab.length; i++) {
				newgame.OppositePlayer.implementboat(Battleship.tab[i]);
			}
			// System.out.println("Début de la partie !!!!!!!!!");
			Player winner = newgame.party();
			winner.score++;
			// System.out.println(winner.getPlayername() + " a gagné");

			newgame.ActivePlayer.reset();
			newgame.OppositePlayer.reset();

		}
		pgmresult2 = computerlvl3.score;
		medresult3 = computerlvl2.score;

		File f = new File("ai_proof.csv");

		try {
			FileWriter fw = new FileWriter(f);
			fw.write("AI Level Beginner;" + noobresult2 + "AI Level Medium;" + medresult1 + "\n");
			fw.write("AI Level Beginner;" + noobresult3 + "AI Level Hard;" + pgmresult1 + "\n");
			fw.write("AI Level Medium;" + medresult3 + "AI Level Hard;" + pgmresult2 + "\n");
			fw.close();
		} catch (IOException e) {
			System.out.println("Impossible d'écrire dans le fichier");
		}

		System.out.println(noobresult2 + " " + noobresult3 + " " + medresult1 + " " + medresult3 + " " + pgmresult1
				+ " " + pgmresult2);
	}
}
