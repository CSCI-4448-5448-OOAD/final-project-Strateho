import java.util.*;

class GameDriver {
    private Board board;
    int turn = 0;
    // private List<Observer> observers = new ArrayList<>();

    public static void main(String[] args) {
        // char[] bombCharacter = Character.toChars(0x1F4A3);
        // System.out.println("Bomb: " + new String(bombCharacter));

        GameDriver game = new GameDriver();
        Player player1 = new Player('r');
        Player player2 = new Player('b');
        game.board = new Board(2);
        boolean play = true;
        
        Logger logger = Logger.getInstance(game.turn);
        logger.update("Turn 0: Setup Board");
        logger.closeLogFile();

        while(play){
            game.turn++;

            logger = Logger.getInstance(game.turn);
            String t = "Turn " + game.turn;
            System.out.println(t);
            logger.update(t);

            game.board.print('r');
            logger.update(player1.turn(game.board));

            game.board.print('b');
            logger.update(player2.turn(game.board));

            logger.closeLogFile();

            // System.out.print(game.turn);

            if(game.turn > 10) break; //to stop inf loop for now
        }
    }
}