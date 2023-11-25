import java.util.*;

class GameDriver {
    int turn = 0;
    private List<Observer> observers = new ArrayList<>();

    public static void main(String[] args) {
        GameDriver game = new GameDriver();
        while(true){
            Logger logger = Logger.getInstance(game.turn);
            game.addObserver(logger);

            // game.playRound();
            game.turn++;

            game.turn++;

            logger.closeLogFile();
            game.removeObserver(logger);
        }
    }

    public void setup(int numPlayers){
        Board b = new Board(numPlayers);
    }
    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }
    
    // @Override
    // public void notifyObservers(Event event) {
    //     for (Observer observer : observers) {
    //         observer.update(event);
    //     }
    // }
}