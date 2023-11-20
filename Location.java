public class Location{
    private GenericPiece piece;
    private int x;
    private int y;

    public Location(int X, int Y){
        piece = null;
        x = X;
        y = Y;
    }

    public boolean hasPiece(){
        return false;
    }

    public GenericPiece getPiece(){
        return piece;
    }
}
