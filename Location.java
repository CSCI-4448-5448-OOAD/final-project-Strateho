public class Location{
    private Piece piece;
    private boolean lake;
    private int x;
    private int y;

    public Location(int X, int Y){
        piece = null;
        x = X;
        y = Y;
        if (x == 3 || x == 4 || x == 7 || x == 8){
            if (y == 5 || y == 6){
                lake = true;
            }else{
                lake = false;
            }
        }else{
            lake = false;
        }
    }

    public boolean hasPiece(){
        if (piece != null){
            return true;
        }else{
            return false;
        }
    }

    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece p){
        if (lake == false){
            piece = p;
        }else{
            System.out.println("You cannot move a piece in a lake!");
        }
    }

    public void setPiece(GenericPiece gp){
        piece = gp;
    }

    public boolean isLake(){
        return lake;
    }
}
