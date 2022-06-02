package models.player;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import models.board.Board;
import models.board.Position;
import models.board.exceptions.BoardException;
import models.board.exceptions.PositionException;
import models.game.Display;
import models.ships.Ship;
import models.ships.utils.Direction;
import models.ships.utils.ShipType;

public class Player {
    private String name;
    private final Board board = new Board(10);
    private final ArrayList<Position> shoots = new ArrayList<>();;
    public Player(String name){
        this.name = name;
    }
    private ArrayList<Ship> initShips(){
        ArrayList<Ship> list = new ArrayList<>();
        for (ShipType type: ShipType.values()){
            for (int i = 0; i < type.getNumShips(); i++){
                list.add(new Ship(ShipType.toSpanishNameShip(type), type.getShipLength()));
            }
        }
        return list;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public Board getBoard() {
        return board;
    }
    public ArrayList<Position> getShoots() {
        return shoots;
    }
    public void addAllShips(){
        boolean isAdded;
        Position position;
        Direction direction;
        String messageInputPosition = " - Introduzca la coordenada (por ejemplo, A1): ";
        String messageInputDirection = " - Introduzca la dirección (h/v): ";
        Scanner sc = new Scanner(System.in);
        ArrayList<Ship> list = initShips();
        for (int i = 0; i < list.size(); i++) {
            Ship ship = list.get(i);
            do {
                Display.printBoard(board);
                Display.printCurrentShip(ship, countShip(list, ship.getLength()));
                position = Input.readPosition(sc, board, messageInputPosition);
                direction = Input.readDirection(sc, messageInputDirection);
                ship.setPosition(position);
                ship.setDirection(direction);
                try {
                    isAdded = board.addShip(ship);
                } catch (BoardException | PositionException e) {
                    Display.printError(e.toString());
                    isAdded = false;
                    }
            } while (!isAdded);
            list.remove(i);
            i--;
        }
        Display.printBoard(board);
    }
    private void randAddAllShips(){
        Random random = new Random();
        ArrayList<Ship> list = initShips();

        boolean isAdded;
        Position position;
        Direction direction;
        int deadlock = 0, limit = 1000;

        for (int i = 0; i < list.size(); i++){
            Ship ship = list.get(i);
            deadlock = 0;
            do {
                try {
                    position = new Position(random.nextInt(board.getLength()), random.nextInt(board.getLength()));
                    direction = random.nextBoolean() ? Direction.VERTICAL : Direction.HORIZONTAL;
                    ship.setPosition(position);
                    ship.setDirection(direction);
                    isAdded = board.addShip(ship);
                } catch (BoardException | PositionException e){ isAdded = false; }
                if (!isAdded) deadlock++;
                if (deadlock > limit) {
                    reset();
                    i = -1;
                    break;
                }
            } while (!isAdded);
        }
    }
    public boolean hasShipsLive(){
        return board.getNumShips() > 0;
    }
    private int countShip(ArrayList<Ship> ships, int length){
        int count = 0;
        for (Ship ship: ships){
            if (ship.getLength() == length) count++;
        }
        return count;
    }
    public int shipsLeft(){
        return board.getNumShips();
    }
    private Position randPosition() throws PositionException {
        Random random = new Random();
        int x = random.nextInt(board.getLength());
        int y = random.nextInt(board.getLength());
        return new Position(x, y);
    }
    public boolean addShoot(Position pos) throws BoardException {
        return board.addHit(pos);
    }
    public Position shoot(Board boardEnemy) throws PositionException {
        Scanner sc = new Scanner(System.in);
        return Input.readPosition(sc, board,  "- " + name + ", ¿dónde quieres disparar? ");
    }
    public void registerShoot(Position position){
        shoots.add(position);
    }
    private void reset(){
        board.reset();
    }
}
