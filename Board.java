public class Board {
    private Location[][] board;
    private int numPlayers;
    private Player redPlayer;
    private Player bluePlayer;
    Piece[] redPieces = new Piece[40];
    Piece[] bluePieces = new Piece[40];
    private int redIndex = 0;
    private int blueIndex = 0;

    public Board (int numPlayers){
        this.numPlayers = numPlayers;
        board = new Location[10][10];
        
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 10; j++){
                board[j][i].setPiece(new GenericPiece('r'));
                //redPieces[i*10+j] = board[j][i].getPiece();
                board[j][9-i].setPiece(new GenericPiece('b'));
                //bluePieces[i*10+j] = board[j][9-i].getPiece();
            }
        }
        redPlayer = new Player('r');
        bluePlayer = new Player('b');

    }

    public void addPiece(char type, int x, int y){
        Piece current = board[x-1][y-1].getPiece();
        if (type == 'f'){
            //Piece p = new Flag(current.getColor());
            //b.addPiece(p, x, y);
        }else if (type == 'b'){
            //Piece p = new Bomb(current.getColor());
            //b.addPiece(p, x, y);
        }else if (type == 's'){
            //Piece p = new Spy(current.getColor());
            //b.addPiece(p, x, y);
        }else{
            int level = type - '0';
            assert(level > 0 && level <= 9);
            board[x-1][y-1].setPiece(new NumberedPiece(current, level));
        }
    }

    
    public void removePiece(int x, int y){
        Piece current = board[x-1][y-1].getPiece();
        if (current != null){
            current.remove();
            board[x-1][y-1].setPiece(null);
        }else{
            System.out.println("There is no piece at that location!");
        }
    }

    public Piece at(int x, int y){
        return board[x-1][y-1].getPiece();
    }

    public void print(char color){
        return;
    }
}
