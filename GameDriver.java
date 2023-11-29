class GameDriver {
    private Board board;
    public static void main(String[] args) {
        GameDriver game = new GameDriver();
        while(true){
            // Logger logger = Logger.getInstance(game.turn);
            // game.addObserver(logger);

            // game.playRound();
            // game.turn++;

            // logger.closeLogFile();
            // game.removeObserver(logger);
            game.board = new Board(1);
            System.out.println("no way that worked");
        }
    }
}