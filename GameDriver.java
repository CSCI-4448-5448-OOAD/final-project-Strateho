import java.util.*;

class GameDriver {
    private Board board;
    int turn = 0;

    public static void main(String[] args) {
        //Create game and players
        GameDriver game = new GameDriver();
        Player player1 = new Player('r');
        Player player2 = new Player('b');
        game.board = new Board(2);
        boolean play = true;
        
        //Setup log file
        Logger logger = Logger.getInstance(game.turn);
        logger.update("Turn 0: Setup Board");
        logger.closeLogFile();
        String event = "";
        
        //Loop through turns
        while(play){
            game.turn++;

            logger = Logger.getInstance(game.turn);
            String t = "Turn " + game.turn;
            System.out.println(t);
            logger.update(t);

            //Print board and take player Turns
            game.board.print('r');
            event = player1.turn(game.board);
            logger.update(event);
            if(event.contains("found the flag")) {
                logger.closeLogFile();
                play = false;
                break;
            }
            for (int i = 0; i < 40; i++){
                System.out.println();
            }
            game.board.print('b');
            event = player2.turn(game.board);
            logger.update(event);
            if (event.contains("found the flag")) {
                logger.closeLogFile();
                play = false;
                break;
            }
            logger.closeLogFile();

            // System.out.print(game.turn);

            //if(game.turn > 10) break; //to stop inf loop for now
        }
    }
}