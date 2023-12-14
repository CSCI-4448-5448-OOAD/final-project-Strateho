public class PieceFactory {
    public static NumberedPiece createNumberedPiece(Piece p, int level) {
        return new NumberedPiece(p, level);
    }

    public static Flag createFlag(Piece p){
        return new Flag(p);
    }

    public static Bomb createBomb(Piece p){
        return new Bomb(p);
    }

    public static Spy createSpy(Piece p){
        return new Spy(p);
    }
}