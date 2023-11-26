public abstract class DecoratedPiece implements Piece{
    boolean inPlay;
    protected Piece piece;

    public DecoratedPiece(Piece p){
        piece = p;
        inPlay = true;
    }

    public char getColor(){
        return piece.getColor();
    }

    public char getVal(){
        return piece.getVal();
    }

    public void move(Board board){
        piece.move(board);
    }

    public void remove(){
        piece.remove();
    }

    public boolean canMove(Board board, int curX, int curY){
        return false;
    }
}
