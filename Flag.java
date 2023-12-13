public class Flag extends DecoratedPiece{
    public Flag(Piece p){
        super(p);
    }

    @Override
    public char getVal(){
        return 'F';
    }

    @Override
    public String move(Board board){
        return "You dirty cheater, you know you can't move the flag.";
    }

    @Override
    public boolean canMove(Board board, int curX, int curY){
        return false;
    }
    
    @Override
    public int attack(Piece other){
        return -3;
    }

    @Override
    public int getX(){
        return piece.getX();
    }

    @Override
    public int getY(){
        return piece.getY();
    }

    @Override
    public void setPos(int x, int y){
        piece.setPos(x, y);
    }
}
