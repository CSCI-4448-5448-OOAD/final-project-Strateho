public abstract class DecoratedPiece implements Piece{
    //Abstract class for decorating generic pieces

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

    public String move(Board board){
        return piece.move(board);
    }

    public void remove(){
        piece.remove();
    }

    public boolean canMove(Board board, int curX, int curY){
        return false;
    }

    public int attack(Piece other){
        System.out.println("I don't think this piece should be attacking");
        return -3;
    }

    public boolean isDiscovered(){
        return piece.isDiscovered();
    }

    public void discover(){
        piece.discover();
    }
}
