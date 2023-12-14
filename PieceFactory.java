public class PieceFactory {
    // public PieceFactory() {
        
    // }

    public NumberedPiece createNumberedPiece(Piece p, int level) {
        return new NumberedPiece(p, level);
    }

    public Flag createFlag(Piece p){
        return new Flag(p);
    }

    public Bomb createBomb(Piece p){
        return new Bomb(p);
    }
}