public class Flag extends DecoratedPiece{
    public Flag(Piece p){
        super(p);
    }

    @Override
    public char getVal(){
        return 'F';
    }

    @Override
    public void move(Board board){
        //piece.move(board);
    }

    @Override
    public boolean canMove(Board board, int curX, int curY){
        return false;
    }
    
}
