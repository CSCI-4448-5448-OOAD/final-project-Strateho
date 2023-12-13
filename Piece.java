public interface Piece{
    char getColor();
    char getVal();
    String move(Board board);
    void remove();
    boolean canMove(Board board, int curX, int curY);
    int getX();
    int getY();
    void setPos(int x, int y);
    int attack(Piece other);
    boolean isDiscovered();
    void discover();
}
