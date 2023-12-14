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
        for (int i = 1; i <= 10; i++){
            for (int j = 1; j <= 10; j++){
                board[i-1][j-1] = new Location(i, j);
            }
        }
        
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 10; j++){
                board[j][i].setPiece(new GenericPiece('r', j+1, i+1));
                //redPieces[i*10+j] = board[j][i].getPiece();
                board[j][9-i].setPiece(new GenericPiece('b', j+1, 10-i));
                //bluePieces[i*10+j] = board[j][9-i].getPiece();
            }
        }
        redPlayer = new Player('r');
        bluePlayer = new Player('b');

        if (numPlayers == 2){
            redPlayer.playerPlace(this);
            bluePlayer.playerPlace(this);
        }else if (numPlayers == 1){
            redPlayer.playerPlace(this);
            bluePlayer.randomPlace(this, null);
        }else{
            redPlayer.randomPlace(this, null);
            bluePlayer.randomPlace(this, null);
        }
    }

    public boolean isLakeLoc(int x, int y){
        return board[x-1][y-1].isLake();
    }

    public void addPiece(char type, int x, int y){
        Piece current = board[x-1][y-1].getPiece();
        if (type == 'f'){
            board[x-1][y-1].setPiece(PieceFactory.createFlag(current));
        }else if (type == 'b'){
            board[x-1][y-1].setPiece(PieceFactory.createBomb(current));
        }else if (type == 's'){
            board[x-1][y-1].setPiece(PieceFactory.createSpy(current));
        }else{
            int level = type - '0';
            assert(level > 0 && level <= 9);
            board[x-1][y-1].setPiece(PieceFactory.createNumberedPiece(current, level));
        }
    }

    
    public void removePiece(int x, int y, boolean leaveGeneric){
        Piece current = board[x-1][y-1].getPiece();
        if (current != null){
            current.remove();
            if (leaveGeneric == false){
                board[x-1][y-1].setPiece(null);
            }else{
                board[x-1][y-1].setPiece(new GenericPiece(current.getColor(), x, y));
            }
        }else{
            System.out.println("There is no piece at that location!");
        }
    }

    public String move(Piece curr, int newX, int newY){
        String event = "";
        int curX = curr.getX();
        int curY = curr.getY();
        Piece otherPiece = board[newX-1][newY-1].getPiece();
        if (otherPiece == null){
            event += ".";
            curr.setPos(newX, newY);
            board[newX-1][newY-1].setPiece(curr);
            board[curX-1][curY-1].setPiece(null);
            return event;
        }
        int result = curr.attack(otherPiece);
        if (result == 0){
            event += "and attacked a " + otherPiece.getVal() + ". The result is a tie and both pieces are removed.";
            this.removePiece(curX, curY, false);
            this.removePiece(newX, newY, false);
        }else if (result == 1){
            event += "and attacked a " + otherPiece.getVal() + ". The result is a win and the " + otherPiece.getVal() + " is removed.";
            this.removePiece(newX, newY, false);
            curr.setPos(newX, newY);
            board[newX-1][newY-1].setPiece(curr);
        }else if (result == -1){
            event += "and attacked a " + otherPiece.getVal() + ". The result is a loss and the " + curr.getVal() + " is removed.";
            this.removePiece(curX, curY, false);
        }else if (result == 2){
            event += "and found the flag! " + curr.getColor() + " wins!";
        }
        board[curX-1][curY-1].setPiece(null);
        return event;
    }
            

    public Piece at(int x, int y){
        return board[x-1][y-1].getPiece();
    }

    public void print(char color){
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String BLUE = "\u001B[34m";
        String CYAN = "\u001B[36m";
        if (color == 'r'){
            System.out.print("    ");
            for (int i = 1; i <= 10; i++){
                System.out.print(i + " ");
            }
            System.out.println();
            System.out.println("   _____________________");
            for (int i = 0; i < 10; i++){
                if (i+1 == 10){
                    System.out.print(10 + "| ");
                }else{
                    System.out.print(i+1 + " | ");
                }
                for (int j = 0; j < 10; j++){
                    Piece current = board[j][i].getPiece();
                    if (current != null){
                        char val = current.getVal();
                        if (!current.isDiscovered()){
                            val = '?';
                        }
                        //System.out.print(' ');
                        if (current.getColor() == 'r'){
                            System.out.print(RED + current.getVal() + RESET);
                        }else{
                            System.out.print(BLUE + val + RESET);
                        }
                        System.out.print(' ');
                    }else{
                        if (board[j][i].isLake()){
                            if (j < 9 && board[j+1][i].isLake()){
                                System.out.print(CYAN + "~~" + RESET);
                            }else{
                                System.out.print(CYAN + "~ " + RESET);
                            }
                        }else{
                            System.out.print("  ");
                        }
                    }
                }
                System.out.println();
            }
        }else if (color == 'b'){
            System.out.print("    ");
            for (int i = 1; i <= 10; i++){
                System.out.print(i + " ");
            }
            System.out.println();
            System.out.println("   _____________________");
            for (int i = 0; i < 10; i++){
                if (i+1 == 10){
                    System.out.print(10 + "| ");
                }else{
                    System.out.print(i+1 + " | ");
                }
                for (int j = 0; j < 10; j++){
                    Piece current = board[j][i].getPiece();
                    if (current != null){
                        char val = current.getVal();
                        if (!current.isDiscovered()){
                            val = '?';
                        }
                        //System.out.print(' ');
                        if (current.getColor() == 'b'){
                            System.out.print(BLUE + current.getVal() + RESET);
                        }else{
                            System.out.print(RED + val + RESET);
                        }
                        System.out.print(' ');
                    }else{
                        if (board[j][i].isLake()){
                            if (j < 9 && board[j+1][i].isLake()){
                                System.out.print(CYAN + "~~" + RESET);
                            }else{
                                System.out.print(CYAN + "~ " + RESET);
                            }
                        }else{
                            System.out.print("  ");
                        }
                    }
                }
                System.out.println();
            }
        }
    }
}
