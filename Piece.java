public interface Piece{
    char getColor();
    char getVal();
    void move(Board board);
    void remove();
    boolean canMove(Board board, int curX, int curY);
}
