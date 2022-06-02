package models.player;
import java.util.InputMismatchException;
import java.util.Scanner;

import models.board.Board;
import models.board.Position;
import models.board.exceptions.PositionException;
import models.game.Display;
import models.ships.utils.Direction;
import models.ships.utils.exceptions.DirectionException;

public class Input {

    public static Position readPosition(Scanner sc, Board board, String message){
        try {
            System.out.print(message);
            String s = sc.nextLine().toLowerCase();
            char row = s.charAt(0);
            int column = Integer.parseInt(s.substring(1));
            Position.isInRange(row, column, board);
            return new Position(row, column - 1);
        } catch (PositionException | NumberFormatException | StringIndexOutOfBoundsException e){
            Display.printError("Error, valores permitidos entre a1 y " + Position.encode(board.getLength() - 1) + board.getLength());
            return readPosition(sc, board, message);
        }

    }

    public static Direction readDirection(Scanner sc, String message){
        try {
            System.out.print(message);
            String s = sc.nextLine();
            return Direction.decode(s.charAt(0));
        } catch (DirectionException | StringIndexOutOfBoundsException e){
            Display.printError("Error, los valores permitidos para la dirección son 'h' o 'v'");
            return readDirection(sc, message);
        }
    }

    public static int readOption(Scanner sc, String message){
        try {
            System.out.print(message);
            return Integer.parseInt(sc.next());
        } catch (NumberFormatException | InputMismatchException e){
            Display.printError("Error, por favor ingrese un número para continuar");
            return readOption(sc, message);
        }
    }


}
