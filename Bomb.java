public class Bomb extends DecoratedPiece{
    public Bomb(Piece p){
        super(p);
    }

    @Override
    public char getVal(){
        return 'B';
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
