public class Board {
    private int[] size = new int[2];
    private Location[][] board;
    private Player player1;
    private Player player2;

    public Board (int[] Size){
        assert Size.length == 2;
        size = Size;
        board = new Location[size[0]][size[1]];
    }

    public void addPiece(Piece p, int x, int y){
        board[x][y].setPiece(p);

    }

}
