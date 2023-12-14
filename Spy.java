import java.util.Scanner;
import java.util.Arrays;

public class Spy extends DecoratedPiece {
    int[][] moves = new int[4][2];
    boolean updatedMoves = false;

    public Spy(Piece p) {
        super(p);
    }

    @Override
    public char getVal() {
        return 'S';
    }

    @Override
    public String move(Board board) {
        //Move like a numbered piece
        String event = "Moved Spy to ";
        Scanner input = new Scanner(System.in);
        if (updatedMoves == true) {
            System.out.println("Where would you like to move this piece?");
            int moveNum = 0;
            while (moves[moveNum][0] != 0 && moves[moveNum][1] != 0) {
                System.out.println((moveNum + 1) + ": " + moves[moveNum][0] + ", " + moves[moveNum][1]);
                moveNum++;
            }
            int moveChoice = input.nextInt();
            while (moveChoice < 1 || moveChoice > moveNum) {
                System.out.println("Please enter a valid move number.");
                moveChoice = input.nextInt();
            }
            int newX = moves[moveChoice - 1][0];
            int newY = moves[moveChoice - 1][1];
            event += newX + ", " + newY;
            event += board.move(this, newX, newY);
        }
        
        for (int i = 0; i < 4; i++){
            Arrays.fill(moves[i], 0);
        }
        updatedMoves = false;
        return event;
    }

    @Override
    public boolean canMove(Board board, int curX, int curY) {
        //Check if the piece can move and update the moves array
        boolean canMove = false;
        updatedMoves = true;
        int moveNum = 0;
        if (curX+1 < 10 && !board.isLakeLoc(curX+1, curY) && (board.at(curX+1, curY) == null || board.at(curX+1, curY).getColor() != this.getColor())){
            moves[moveNum][0] = curX+1;
            moves[moveNum][1] = curY;
            moveNum++;
            canMove = true;
        }
        if (curX-1 > 0 && !board.isLakeLoc(curX-1, curY) && (board.at(curX-1, curY) == null || board.at(curX-1, curY).getColor() != this.getColor())){
            moves[moveNum][0] = curX-1;
            moves[moveNum][1] = curY;
            moveNum++;
            canMove = true;
        }
        if (curY+1 < 10 && !board.isLakeLoc(curX, curY+1) && (board.at(curX, curY+1) == null || board.at(curX, curY+1).getColor() != this.getColor())){
            moves[moveNum][0] = curX;
            moves[moveNum][1] = curY+1;
            moveNum++;
            canMove = true;
        }
        if (curY-1 > 0 && !board.isLakeLoc(curX, curY-1) && (board.at(curX, curY-1) == null || board.at(curX, curY-1).getColor() != this.getColor())){
            moves[moveNum][0] = curX;
            moves[moveNum][1] = curY-1;
            moveNum++;
            canMove = true;
        }
        return canMove;
    }

    @Override
    public int attack(Piece other) {
        //The spy only beats the 1
        char otherVal = other.getVal();
        other.discover();
        this.discover();
        if (other instanceof NumberedPiece){
            if (otherVal == '1'){
                return 1;
            }else{
                return -1;
            }
        }
        if (otherVal == 'F'){
            return 2;
        }else if (otherVal == 'B'){
            return -1;
        }else if (otherVal == 'S'){
            return 0;
        }else{
            System.out.println("How did you even get here?");
            return -3;
        }
    }
    
    @Override
    public int getX(){
        return piece.getX();
    }

    @Override
    public int getY(){
        return piece.getY();
    }

    @Override
    public void setPos(int x, int y){
        piece.setPos(x, y);
    }
}
