class GameDriver {
    public static void main(String[] args) {
        GameDriver game = new GameDriver();
        while(true){
            // Logger logger = Logger.getInstance(game.turn);
            // game.addObserver(logger);

            // game.playRound();
            // game.turn++;

            // logger.closeLogFile();
            // game.removeObserver(logger);
        }
    }

    public void setup(int numPlayers){
        Board b = new Board(numPlayers);
    }
}