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
        return;
    }

    @Override
    public boolean canMove(Board board, int curX, int curY){
        System.out.println("I don't reccomend trying to move that, I think little timmy would agree if he was still with us.");
        return false;
    }
    
}
