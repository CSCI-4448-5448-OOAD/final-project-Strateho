public class GenericPiece implements Piece{
    private boolean alive;
    private char color;

    public GenericPiece(char color){
        alive = true;
        this.color = color;
    }


    public char getColor(){
        return color;
    }

    public char getVal(){
        return ' ';
    }

    public void move(Board board){
        return;
    }

    public void remove(){
        alive = false;
    }

    public boolean canMove(Board board, int curX, int curY){
        return false;
    }
}
