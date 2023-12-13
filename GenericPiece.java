public class GenericPiece implements Piece{
    private boolean alive;
    private char color;
    private int x;
    private int y;
    private boolean isDiscovered = false;

    public GenericPiece(char color, int x, int y){
        this.alive = true;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public char getColor(){
        return this.color;
    }

    public char getVal(){
        return '-';
    }

    public String move(Board board){
        System.out.println("Yeah uhh I don't really move like that.");
        return "Tried and failed to move " + this.getVal();
    }

    public void remove(){
        this.alive = false;
    }

    public boolean canMove(Board board, int curX, int curY){
        return false;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int attack(Piece other){
        System.out.println("I don't think this piece should be attacking");
        return -3;
    }

    public boolean isDiscovered(){
        return this.isDiscovered;
    }

    public void discover(){
        this.isDiscovered = true;
    }
}
