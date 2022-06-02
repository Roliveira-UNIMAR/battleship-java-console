package models.game;

import models.board.Board;
import models.board.Position;
import models.board.exceptions.PositionException;
import models.player.Input;
import models.player.Player;
import models.ships.Ship;
import java.util.Scanner;

public class Display {
    static Scanner t = new Scanner(System.in);
    public static int printMenu(){
        System.out.println("\n ¡Bienvenido al juego Batalla Naval! Elija un opción del menú para continuar... \n");
        System.out.println(" 1 -> Iniciar partita");
        System.out.println(" 2 -> Historial de partidas");
        System.out.println(" 3 -> Salir \n");
        return Input.readOption(new Scanner(System.in),"Opción: ");
    }

    public static void printCredits(){
        System.out.println("\n Realizado por: ");
        System.out.println("\n -> Rodrigo Oliveira, 29.655.609");
        System.out.println("\n -> Eduardo Pacheco, 28.308.053");
    }

    public static void printError(String message){
        System.out.println(message);
    }

    public static void printShot(Player player, Position position, boolean isHit){
        System.out.println(" -> " + player.getName() + " haz disparado en " + position.toStringEncode(position) + ": " +
                (isHit ? " ¡Acertaste!" :
                        " ¡Fallaste!"));
    }

    public static void printWinner(Player player){
        System.out.println("\n -> " + player.getName() + " ¡haz ganado!" + "\n");
        System.out.print("\n Presione una tecla para continuar... ");
        t.nextLine();
    }

    public static void printCurrentShip(Ship ship, int numShipLeft){
        System.out.println(" -> " + ship.getName() + " (" +
                ship.toGraphicLength() +
                ") x" + numShipLeft);
    }

    public static void printAdjacentBoard(Player pOne, Player pTwo) throws PositionException {
        System.out.println(toStringAdjacentBoard(pOne, pTwo));
    }

    public static String toStringAdjacentBoard(Player pOne, Player pTwo) throws PositionException {
        Board firstBoard = pOne.getBoard();
        Board secondBoard = pTwo.getBoard().getBoardHideShips();
        String numbers  = "12345678910";
        String letters = "abcdefghij";
        String s = "\n――――――――――――――――――――――――――――――――――\n";
        s += "\n    ";
        for (int i = 0; i < firstBoard.getLength(); i++) {
            if (i != 9) {
                s += numbers.charAt(i) + "  ";
            } else {
                s += numbers.charAt(i) + numbers.charAt(i+1) + "  ";
            }
        }
        s += "          ";
        for (int i = 0; i < secondBoard.getLength(); i++) s += numbers.charAt(i) + "  ";
        s += "\n";
        for (int i = 0; i < firstBoard.getLength(); i++){
            s += letters.charAt(i) + "  ";
            for (int j = 0; j < firstBoard.getLength(); j++){
                if (firstBoard.getBoard()[i][j] == Board.WATER) s += " " + Board.WATER + " ";
                else if (firstBoard.getBoard()[i][j] == Board.HIT) s += Board.HIT + " " ;
                else if (firstBoard.getBoard()[i][j] == Board.MISS) s += Board.MISS + " " ;
                else s +=  + firstBoard.getBoard()[i][j] + " " ;
            }
            s += "   ";
            s += letters.charAt(i) + "  ";
            for (int j = 0; j < secondBoard.getLength(); j++){
                if (secondBoard.getBoard()[i][j] == Board.WATER) s += " " + Board.WATER + " ";
                else if (secondBoard.getBoard()[i][j] == Board.HIT) s += Board.HIT + " ";
                else if (secondBoard.getBoard()[i][j] == Board.MISS) s += Board.MISS + " ";
                else s +=  + secondBoard.getBoard()[i][j] + " ";
            }

            s += "\n";
        }
        s += "\n――――――――――――――――――――――――――――――――――\n";
        return s;
    }

    public static void printBoard(Board board){
        System.out.println(toStringBoard(board));
    }

    public static String toStringBoard(Board board){
        String numbers  = "12345678910";
        String letters = "abcdefghij";
        String s = "\n    ";
        for (int i = 0; i < board.getLength(); i++) {
            if (i != 9) {
                s += numbers.charAt(i) + "  ";
            } else {
                s += numbers.charAt(i) + numbers.charAt(i+1) + "  ";
            }
        }
        s += "\n";
        for (int i = 0; i < board.getLength(); i++){
            s += letters.charAt(i) + "  ";
            for (int j = 0; j < board.getLength(); j++){
                if (board.getBoard()[i][j] == Board.WATER) s += " " + Board.WATER + " ";
                else if (board.getBoard()[i][j] == Board.HIT) s +=  Board.HIT + " ";
                else if (board.getBoard()[i][j] == Board.MISS) s +=  Board.MISS + " ";
                else s +=  + board.getBoard()[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }
}
