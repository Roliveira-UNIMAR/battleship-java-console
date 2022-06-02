package models.game;
import models.board.Position;
import models.board.exceptions.BoardException;
import models.board.exceptions.PositionException;
import models.player.Player;

public class BattleshipGame {
    private final Player p1;
    private final Player p2;


    public BattleshipGame(String name1, String name2){
        p1 = new Player(name1);
        p2 = new Player(name2);
    }

    private boolean turn(Player attack, Player defend) throws PositionException {
        Position shoot = null;
        boolean isHit, isAddHit;
        if (attack.hasShipsLive()){
            do {
                try {
                    shoot = attack.shoot(defend.getBoard().getBoardHideShips());
                    isAddHit = defend.addShoot(shoot);
                } catch (BoardException e) {
                    Display.printError("Error, ya has disparado en esta posición");
                    isAddHit = false;
                }
            } while (!isAddHit);
            isHit = defend.getBoard().thereIsHit(shoot);
            if (isHit) attack.registerShoot(shoot);
            Display.printShot(attack, shoot, isHit);
            return true;
        } else {
            return false;
        }
    }

    private void addAllShips(){
        p1.addAllShips();
        System.out.println("Listo player: " + p1.getName());
        System.out.println("Comenzemos player: " + p2.getName());
        p2.addAllShips();
    }

    private void printResultGame(){
        if (p1.shipsLeft() > p2.shipsLeft()) {
            Display.printWinner(p1);
        } else {
            Display.printWinner(p2);
        }
    }

    public void run() throws PositionException {
        addAllShips();
        while (turn(p1, p2) && turn(p2, p1)) { }
        printResultGame();
    }
}
