public class Spy extends DecoratedPiece {
    public Spy(Piece p) {
        super(p);
    }

    @Override
    public char getVal() {
        return 'S';
    }

    @Override
    public void move(Board board) {
        //piece.move(board);
    }

    @Override
    public boolean canMove(Board board, int curX, int curY) {
        return false;
    }
    
}
