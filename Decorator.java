public interface Decorator {
    int[] getLocation();
    char getColor();
    int getVal();
    void move(Board board);
}
