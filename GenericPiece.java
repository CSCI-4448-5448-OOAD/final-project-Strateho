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

    public int getVal(){
        return 0;
    }

    public void move(Board board){
        return;
    }
}
