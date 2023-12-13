public class Bomb extends DecoratedPiece{
    public Bomb(Piece p){
        super(p);
    }

    @Override
    public char getVal(){
        return 'B';
    }

    @Override
    public String move(Board board){
        return "Tried, and failed disastorously, to move the bomb.";
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
