public class GenericPiece implements Decorator{
    private boolean alive;
    private int[] location;

    public GenericPiece(int[] Location){
        alive = true;
        assert Location.length == 2;
        location = Location;
    }

    public int[] getLocation(){
        return null;
    }

    public char getColor(){
        return ' ';
    }

    public int getVal(){
        return 0;
    }

    public void move(Board board){
        return;
    }
}
