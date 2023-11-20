public class Location{
    private GenericPiece piece;
    private boolean lake;
    private int x;
    private int y;

    public Location(int X, int Y){
        piece = null;
        x = X;
        y = Y;
    }

    public boolean hasPiece(){
        if (piece != null){
            return true;
        }else{
            return false;
        }
    }

    public GenericPiece getPiece(){
        return piece;
    }
}
