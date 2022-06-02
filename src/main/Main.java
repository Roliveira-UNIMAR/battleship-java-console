package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import models.board.exceptions.PositionException;
import models.game.BattleshipGame;
import models.game.Display;

public class Main {
    public static void main(String[] args) throws PositionException {
        Scanner sc = new Scanner(System.in);
        BattleshipGame game;
        String name1 = "";
        String name2 = "";
        int opt;
        boolean hasName = false;

        try {
            do {
                opt = Display.printMenu();

                switch (opt) {
                    case 1 -> {
                        if (!hasName) {
                            System.out.print("\n Ingresa tu nombre player 1: ");
                            name1 = sc.next();
                            System.out.print("\n Ingresa tu nombre player 2: ");
                            name2 = sc.next();
                            hasName = true;
                        }
                        game = new BattleshipGame(name1, name2);
                        game.run();
                    }
                    case 2 -> {
                        System.out.println("Lo siento, seguimos trabajando en esta función");
                    }
                }
            } while (opt != 0);
        } catch (InputMismatchException e) { }
        Display.printCredits();
        sc.close();
    }
}
